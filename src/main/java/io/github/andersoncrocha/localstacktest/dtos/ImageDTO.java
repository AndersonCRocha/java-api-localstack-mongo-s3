package io.github.andersoncrocha.localstacktest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    private String id;
    private String filename;
    private String url;
    private LocalDateTime uploadedAt;

}
