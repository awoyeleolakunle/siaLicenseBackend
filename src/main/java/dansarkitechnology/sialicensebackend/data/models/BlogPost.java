package dansarkitechnology.sialicensebackend.data.models;

import dansarkitechnology.sialicensebackend.data.enums.BlogCategory;
import dansarkitechnology.sialicensebackend.data.enums.BlogStatus;
import dansarkitechnology.sialicensebackend.data.enums.BlogType;
import dansarkitechnology.sialicensebackend.data.enums.PublishStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postTitle;
    private String postAuthor;
    private String email;
    private LocalDate publishDate;
    private LocalTime publishTime;
    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatus;
    @Column(length = 100000)
    private String blogContent;
    private String postImageUrl;
    @Enumerated(EnumType.STRING)
    private BlogType blogType;
    @Enumerated(EnumType.STRING)
    private BlogStatus blogStatus;
    private BlogCategory blogPostCategory;
}
