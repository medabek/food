package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zensoft.food.enums.CompanyOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public BigDecimal getTotal() {

        BigDecimal totalSum = new BigDecimal(0);

        for (Order order : orders){
            totalSum = totalSum.add(order.getTotal());
        }

        return totalSum;
    }
}
