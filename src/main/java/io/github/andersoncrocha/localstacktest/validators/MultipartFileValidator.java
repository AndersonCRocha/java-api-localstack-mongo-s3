package io.github.andersoncrocha.localstacktest.validators;

import org.springframework.web.multipart.MultipartFile;

public interface MultipartFileValidator {

    void validate(MultipartFile file);

}
