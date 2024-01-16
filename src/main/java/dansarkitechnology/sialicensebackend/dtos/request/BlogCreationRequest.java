package dansarkitechnology.sialicensebackend.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogCreationRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @NotEmpty
    private String postAuthor;
    @NotBlank
    @NotEmpty
    private String postTitle;
    @NotBlank
    @NotEmpty
    private LocalDate publishDate;
    @NotBlank
    @NotEmpty
    private String publishTime;
    @NotBlank
    @NotEmpty
    private String publishStatus;
    @NotBlank
    @NotEmpty
    private String blogContent;

    private String postImageUrl;

    private String blogType;

    private String blogStatus;
    @NotBlank
    @NotEmpty
    private String blogPostCategory;
}
