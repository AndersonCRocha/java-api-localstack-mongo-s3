package io.github.andersoncrocha.localstacktest.services.impl;

import io.github.andersoncrocha.localstacktest.dtos.FileDTO;
import io.github.andersoncrocha.localstacktest.exceptions.ImageNotFoundException;
import io.github.andersoncrocha.localstacktest.models.Image;
import io.github.andersoncrocha.localstacktest.repositories.ImageRepository;
import io.github.andersoncrocha.localstacktest.services.FileStorageService;
import io.github.andersoncrocha.localstacktest.services.ImageService;
import io.github.andersoncrocha.localstacktest.validators.ImageValidator;
import io.github.andersoncrocha.localstacktest.validators.MultipartFileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;
    private final FileStorageService fileStorageService;
    private final MultipartFileValidator imageValidator = new ImageValidator();

    @Override
    public Image uploadImage(MultipartFile multipartFile) {
        try {
            imageValidator.validate(multipartFile);

            String originalFilename = multipartFile.getOriginalFilename();
            FileDTO fileDTO = FileDTO.builder()
                    .filename(originalFilename)
                    .content(multipartFile.getBytes())
                    .build();

            String bucketFilename = fileStorageService.uploadFile(fileDTO);
            Image image = Image.builder()
                    .storedFilename(bucketFilename)
                    .originalFileName(originalFilename)
                    .mimeType(multipartFile.getContentType())
                    .uploadedAt(LocalDateTime.now())
                    .build();
            return repository.save(image);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<Image> listAllImages() {
        return repository.findAll();
    }

    @Override
    public FileDTO downloadImage(String imageId) {
        Image image = repository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        byte[] content = fileStorageService.getFileByName(image.getStoredFilename());
        return FileDTO.builder()
                .content(content)
                .filename(image.getOriginalFileName())
                .mimeType(image.getMimeType())
                .build();
    }

}
