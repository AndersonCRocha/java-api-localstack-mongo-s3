package io.github.andersoncrocha.localstacktest.controllers;

import io.github.andersoncrocha.localstacktest.converters.ImageConverter;
import io.github.andersoncrocha.localstacktest.dtos.FileDTO;
import io.github.andersoncrocha.localstacktest.dtos.ImageDTO;
import io.github.andersoncrocha.localstacktest.models.Image;
import io.github.andersoncrocha.localstacktest.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService service;
    private final ImageConverter imageConverter;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageDTO uploadImage(@RequestParam("image") MultipartFile multipartFile) {
        Image image = service.uploadImage(multipartFile);
        return imageConverter.toDTO(image);
    }

    @GetMapping
    public List<ImageDTO> listImages() {
        List<Image> images = service.listAllImages();
        return imageConverter.toCollectionDTO(images);
    }

    @GetMapping("{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageId) {
        FileDTO downloadedImage = service.downloadImage(imageId);
        String contentDispositionHeader = String.format("attachment; filename=\"%s\"", downloadedImage.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeader)
                .contentType(MediaType.parseMediaType(downloadedImage.getMimeType()))
                .body(new ByteArrayResource(downloadedImage.getContent()));
    }

}
