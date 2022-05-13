package io.github.andersoncrocha.localstacktest.services;

import io.github.andersoncrocha.localstacktest.dtos.FileDTO;
import io.github.andersoncrocha.localstacktest.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image uploadImage(MultipartFile image);

    List<Image> listAllImages();

    FileDTO downloadImage(String imageId);

}
