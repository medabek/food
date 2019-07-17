package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zensoft.food.enums.OrderStatus;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> items = new ArrayList<>();

    private Long userId;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "company_order_id")
    private CompanyOrder companyOrder;

    private BigDecimal total = BigDecimal.ZERO;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_cafes",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "cafe_id"))
    private List<Cafe> cafes = new ArrayList<>();

    public void addItem(@NonNull OrderItem orderItem) {
        if (CollectionUtils.isEmpty(this.items)) {
            this.items = new ArrayList<>();
        }

        if (!items.contains(orderItem)) {
            this.items.add(orderItem);
            orderItem.setOrder(this);
        }
    }

    public void addCafe(@NonNull Cafe cafe) {
        this.cafes.add(cafe);
    }

    public BigDecimal getOrderTotal() {

        BigDecimal totalSum = this.getTotal();
        for (OrderItem item : this.items) {
            totalSum = totalSum.add(item.getTotal());
        }
        return totalSum;
    }
}
