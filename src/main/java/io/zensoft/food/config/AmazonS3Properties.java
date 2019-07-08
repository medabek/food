package io.zensoft.food.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws")
public class AmazonS3Properties {

    private String endpointUrl;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
