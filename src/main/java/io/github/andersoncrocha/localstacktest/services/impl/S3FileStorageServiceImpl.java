package io.github.andersoncrocha.localstacktest.services.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import io.github.andersoncrocha.localstacktest.configs.properties.AwsConfigurationProperties;
import io.github.andersoncrocha.localstacktest.dtos.FileDTO;
import io.github.andersoncrocha.localstacktest.exceptions.S3ImageUploadException;
import io.github.andersoncrocha.localstacktest.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

@Service
@Log4j2
@RequiredArgsConstructor
public class S3FileStorageServiceImpl implements FileStorageService {

    private final AmazonS3 s3Client;
    private final AwsConfigurationProperties awsConfiguration;

    @Override
    public String uploadFile(FileDTO fileDTO) {
        try (InputStream fileStream = new ByteArrayInputStream(fileDTO.getContent())) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(fileDTO.getContent().length);
            String filename = System.nanoTime() + fileDTO.getFilename();
            s3Client.putObject(awsConfiguration.getS3().getBucketName(), filename, fileStream, objectMetadata);
            return filename;
        } catch (AmazonServiceException | IOException exception) {
            log.error(exception);
            throw new S3ImageUploadException(exception);
        }
    }

    @Override
    public byte[] getFileByName(String storedFilename) {
        try {
            GetObjectRequest request = new GetObjectRequest(awsConfiguration.getS3().getBucketName(), storedFilename);
            S3Object object = s3Client.getObject(request);
            S3ObjectInputStream objectContentStream = object.getObjectContent();
            return FileCopyUtils.copyToByteArray(objectContentStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
