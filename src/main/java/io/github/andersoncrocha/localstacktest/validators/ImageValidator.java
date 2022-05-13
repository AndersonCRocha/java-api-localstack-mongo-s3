package io.github.andersoncrocha.localstacktest.validators;

import io.github.andersoncrocha.localstacktest.exceptions.InvalidImageException;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements MultipartFileValidator {

    @Override
    public void validate(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null || !contentType.contains("image")) {
            throw new InvalidImageException("File must be an image");
        }
    }

}
