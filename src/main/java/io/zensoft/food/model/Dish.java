package io.zensoft.food.model;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes")

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double portion;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    private String imageName;

    @ManyToOne
    @JoinColumn(name = "todays_menu_id")
    private TodaysMenu todaysMenu;
}
