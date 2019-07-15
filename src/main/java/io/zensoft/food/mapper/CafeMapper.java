package io.zensoft.food.mapper;

import io.zensoft.food.config.AmazonS3Properties;
import io.zensoft.food.dto.CafeDto;
import io.zensoft.food.model.Cafe;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CafeMapper {

    private AmazonS3Properties amazonS3Properties;

    @Autowired
    public CafeMapper(AmazonS3Properties amazonS3Properties) {
        this.amazonS3Properties = amazonS3Properties;
    }

    public CafeDto toCafeDto(@NonNull Cafe cafe) {
        return CafeDto.builder()
                .id(cafe.getId())
                .name(cafe.getName())
                .logoUrl(getLogoUrl(cafe))
                .build();
    }

    private String getLogoUrl(@NonNull Cafe cafe) {
        return amazonS3Properties.getEndpointUrl() + "/"
                + amazonS3Properties.getBucketName() + "/"
                + cafe.getLogoName();
    }
}
