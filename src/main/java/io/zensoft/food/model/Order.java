package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zensoft.food.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> items = new ArrayList<>();

    private Long userId;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "company_order_id")
    private CompanyOrder companyOrder;

    private BigDecimal total;

    public void addItem(@NonNull OrderItem orderItem) {
        if (CollectionUtils.isEmpty(this.items)) {
            this.items = new ArrayList<>();
        }

        this.items.add(orderItem);
        orderItem.setOrder(this);
    }

    public BigDecimal getTotal() {

        BigDecimal totalSum = new BigDecimal(0);

        for (OrderItem item : items) {
            totalSum = totalSum.add(item.getTotal());
        }

        return totalSum;
    }
}
