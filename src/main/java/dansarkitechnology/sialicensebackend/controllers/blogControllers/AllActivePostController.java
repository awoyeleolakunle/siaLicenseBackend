package dansarkitechnology.sialicensebackend.controllers.blogControllers;

import dansarkitechnology.sialicensebackend.data.models.BlogPost;
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
public class AllActivePostController {


    private final BlogPostService blogPostService;

    @GetMapping("allActiveBlogPosts")
    public ResponseEntity<List<BlogPost>> fetchAllActiveBlogPost(@RequestParam int pageSize, @RequestParam int pageNumber){
        return new ResponseEntity<>(blogPostService.findAllActiveBlogPost(pageSize, pageNumber), HttpStatus.OK);
    }
}
