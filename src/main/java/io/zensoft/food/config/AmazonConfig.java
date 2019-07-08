package io.zensoft.food.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AmazonConfig {

    private final AmazonS3Properties amazonS3Properties;

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(amazonS3Properties.getAccessKey(),
                amazonS3Properties.getSecretKey());

        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(credentials)
                ).build();
    }
}
