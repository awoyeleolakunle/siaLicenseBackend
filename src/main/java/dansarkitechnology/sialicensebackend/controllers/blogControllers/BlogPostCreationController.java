package dansarkitechnology.sialicensebackend.controllers.blogControllers;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogCreationRequest;
import dansarkitechnology.sialicensebackend.services.blog.blogPostCreation.BlogPostCreationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/blog/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BlogPostCreationController {
    private final BlogPostCreationService blogPostCreationService;

    @PostMapping("blogPostCreation")
    public ResponseEntity<ApiResponse> createBlogPost(@RequestBody BlogCreationRequest blogCreationRequest){
        return new ResponseEntity<>(blogPostCreationService.createBlogPost(blogCreationRequest), HttpStatus.OK);
    }
}