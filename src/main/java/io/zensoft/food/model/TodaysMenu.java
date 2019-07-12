package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zensoft.food.enums.TodaysMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todays_menu")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodaysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TodaysMenuStatus status = TodaysMenuStatus.RELEVANT;

    private LocalDateTime time = LocalDateTime.now();

    @OneToMany(mappedBy = "todaysMenu", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Dish> dishes = new ArrayList<>();

    public void addDish(@NonNull Dish dish){

            this.dishes.add(dish);
            dish.setTodaysMenu(this);
    }

    public void removeDish(@NonNull Dish dish){

        this.dishes.remove(dish);
        dish.setTodaysMenu(null);
    }
}
