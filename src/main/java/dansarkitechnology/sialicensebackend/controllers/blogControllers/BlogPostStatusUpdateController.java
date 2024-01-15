package dansarkitechnology.sialicensebackend.controllers.blogControllers;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogPostStatusUpdateRequest;
import dansarkitechnology.sialicensebackend.exceptions.BlogException;
import dansarkitechnology.sialicensebackend.services.blog.blogPostStatusUpdate.BlogPostStatusUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/blog/blogPostStatusUpdate")
@CrossOrigin (origins = "*")
@AllArgsConstructor
public class BlogPostStatusUpdateController {
    private final BlogPostStatusUpdateService blogPostStatusUpdateService;

    @PatchMapping("blogPostStatusUpdate")
    public ResponseEntity<ApiResponse> updateBlogPostBlogStatus(@RequestBody BlogPostStatusUpdateRequest blogPostStatusUpdateRequest) throws BlogException {
        return new ResponseEntity<>(blogPostStatusUpdateService.updateBlogPostBlogStatus(blogPostStatusUpdateRequest), HttpStatus.OK);
    }
}
