package io.github.andersoncrocha.localstacktest.converters;

import io.github.andersoncrocha.localstacktest.dtos.ImageDTO;
import io.github.andersoncrocha.localstacktest.models.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageConverter {

    public ImageDTO toDTO(Image image) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("images/{imageId}")
                .buildAndExpand(image.getId())
                .toString();

        return ImageDTO.builder()
                .id(image.getId())
                .filename(image.getOriginalFileName())
                .uploadedAt(image.getUploadedAt())
                .url(url)
                .build();
    }

    public List<ImageDTO> toCollectionDTO(List<Image> images) {
        return images.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
