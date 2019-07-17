package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zensoft.food.enums.CompanyOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "company_orders")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CompanyOrderStatus orderStatus;

    private LocalDateTime openingTime;

    private LocalDateTime closingTime;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "companyOrder", orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    private BigDecimal total;

    public List<Order> getCompanyTotal() {

        HashMap<Long, BigDecimal> cafesMap = new HashMap<>();

        List<Cafe> cafes = new ArrayList<>();

        for (Order order : this.orders) {
            for (OrderItem orderItem : order.getItems()) {
                cafes.add(orderItem.getDish().getCafe());
            }
        }

        for (Cafe cafe : cafes) {
            cafesMap.putIfAbsent(cafe.getId(), BigDecimal.ZERO);
            cafesMap.put(cafe.getId(), cafesMap.get(cafe.getId()).add(BigDecimal.ONE));
        }

        for (Order order : this.orders) {

            for (Cafe cafe : order.getCafes()) {

                BigDecimal delivery = cafe.getDelivery();

                if (!delivery.equals(BigDecimal.ZERO)) {

                    if (cafesMap.keySet().contains(cafe.getId())) {
                        order.setTotal((order.getTotal()).
                                add(delivery.divide(cafesMap.get(cafe.getId()), MathContext.DECIMAL32)));
                    }
                }
            }
        }
        return this.orders;
    }
}
