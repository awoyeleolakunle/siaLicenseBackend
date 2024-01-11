package dansarkitechnology.sialicensebackend.dtos.request;

import dansarkitechnology.sialicensebackend.data.enums.BlogStatus;
import dansarkitechnology.sialicensebackend.data.enums.BlogType;
import dansarkitechnology.sialicensebackend.data.enums.PublishStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostUpdateRequest {
    private String postTitle;
    private String email;
    private LocalDate publishDate;
    private LocalTime publishTime;
    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatus;
    private String blogContent;
    private String postImageUrl;
    @Enumerated(EnumType.STRING)
    private BlogType blogType;
    @Enumerated(EnumType.STRING)
    private BlogStatus blogStatus;
}
