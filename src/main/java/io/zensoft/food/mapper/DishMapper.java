package io.zensoft.food.mapper;

import io.zensoft.food.config.AmazonS3Properties;
import io.zensoft.food.dto.DishDto;
import io.zensoft.food.model.Dish;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    private AmazonS3Properties amazonS3Properties;

    @Autowired
    public DishMapper(AmazonS3Properties amazonS3Properties) {
        this.amazonS3Properties = amazonS3Properties;
    }

    public DishDto toDishDto(@NonNull Dish dish) {
        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .portion(dish.getPortion())
                .price(dish.getPrice())
                .imageUrl(getImageUrl(dish))
                .build();
    }

    private String getImageUrl(@NonNull Dish dish) {
        return amazonS3Properties.getEndpointUrl() + "/"
                + amazonS3Properties.getBucketName() + "/"
                + dish.getImageName();
    }
}
