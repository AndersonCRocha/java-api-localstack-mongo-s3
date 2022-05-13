package io.github.andersoncrocha.localstacktest.configs.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsConfigurationProperties {

    private S3 s3;

    @Getter
    @Setter
    public static class S3 {

        private String endpointUrl;
        private String bucketName;

    }

}
