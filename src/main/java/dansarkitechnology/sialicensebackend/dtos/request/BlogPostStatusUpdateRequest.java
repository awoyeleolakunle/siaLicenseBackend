package dansarkitechnology.sialicensebackend.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostStatusUpdateRequest {
    private Long id;
    private String blogPostStatus;
}
