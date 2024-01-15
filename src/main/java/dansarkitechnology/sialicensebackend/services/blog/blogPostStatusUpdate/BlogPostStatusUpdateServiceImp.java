package dansarkitechnology.sialicensebackend.services.blog.blogPostStatusUpdate;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.enums.BlogStatus;
import dansarkitechnology.sialicensebackend.data.models.BlogPost;
import dansarkitechnology.sialicensebackend.dtos.request.BlogPostStatusUpdateRequest;
import dansarkitechnology.sialicensebackend.exceptions.BlogException;
import dansarkitechnology.sialicensebackend.services.blog.blogPostService.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogPostStatusUpdateServiceImp implements BlogPostStatusUpdateService {

    private final BlogPostService blogPostService;


    @Override
    public ApiResponse updateBlogPostBlogStatus(BlogPostStatusUpdateRequest blogPostStatusUpdateRequest) throws BlogException {
        Optional<BlogPost> blogPost = blogPostService.findPostById(blogPostStatusUpdateRequest.getId());
        if(blogPost.isEmpty()) throw new BlogException(GenerateApiResponse.BLOG_POST_NOT_FOUND);
        blogPost.get().setBlogStatus(BlogStatus.valueOf(blogPostStatusUpdateRequest.getBlogPostStatus().toUpperCase()));
        blogPostService.saveBlogPost(blogPost.get());
        return GenerateApiResponse.UpdateStatus(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
    }
}
