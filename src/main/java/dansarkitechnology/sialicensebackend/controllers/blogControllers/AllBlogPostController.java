package dansarkitechnology.sialicensebackend.controllers.blogControllers;

import dansarkitechnology.sialicensebackend.data.models.BlogPost;
import dansarkitechnology.sialicensebackend.dtos.request.PaginationRequest;
import dansarkitechnology.sialicensebackend.services.blog.blogPostService.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sialicence+/blog/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AllBlogPostController {
    private final BlogPostService blogPostService;

    @PostMapping("allBlogPosts")
    public ResponseEntity<List<BlogPost>> fetchAllBlogPost(@RequestBody PaginationRequest paginationRequest){
        return new ResponseEntity<>(blogPostService.findAllBlogPost(paginationRequest), HttpStatus.OK);
}
}
