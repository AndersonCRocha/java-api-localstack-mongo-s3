package io.github.andersoncrocha.localstacktest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("images")
public class Image {

    @Id
    private String id;
    private String mimeType;
    private String originalFileName;
    private String storedFilename;
    private LocalDateTime uploadedAt;

}
