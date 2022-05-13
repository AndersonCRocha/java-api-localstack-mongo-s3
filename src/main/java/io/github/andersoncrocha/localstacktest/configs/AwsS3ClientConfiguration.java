package io.github.andersoncrocha.localstacktest.configs;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.github.andersoncrocha.localstacktest.configs.properties.AwsConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

@Configuration
@RequiredArgsConstructor
public class AwsS3ClientConfiguration {

    private final AwsConfigurationProperties awsConfiguration;

    @Bean
    public AmazonS3 s3Client() {
        Regions region = Regions.US_EAST_1;
        String endpointUrl = awsConfiguration.getS3().getEndpointUrl();
        EndpointConfiguration endpointConfiguration = new EndpointConfiguration(endpointUrl, region.getName());
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .enablePathStyleAccess()
                .build();
    }

}
