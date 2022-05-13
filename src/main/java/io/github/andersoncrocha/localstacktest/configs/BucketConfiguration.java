package io.github.andersoncrocha.localstacktest.configs;

import com.amazonaws.services.s3.AmazonS3;
import io.github.andersoncrocha.localstacktest.configs.properties.AwsConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BucketConfiguration implements CommandLineRunner {

    private final AmazonS3 s3Client;
    private final AwsConfigurationProperties awsConfigurations;

    @Override
    public void run(String... args) {
        String bucketName = awsConfigurations.getS3().getBucketName();
        boolean bucketExists = s3Client.doesBucketExistV2(bucketName);
        if (bucketExists) {
            return;
        }

        s3Client.createBucket(bucketName);
    }

}
