package dansarkitechnology.sialicensebackend.services.blog.blogPostUpdate;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.BlogPost;
import dansarkitechnology.sialicensebackend.dtos.request.BlogPostUpdateRequest;
import dansarkitechnology.sialicensebackend.services.blog.blogPostService.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateBlogPostServiceImp implements UpdateBlogPostService{

    private final BlogPostService blogPostService;

    @Override
    public ApiResponse updateBlogPost(BlogPostUpdateRequest blogPostUpdateRequest) {
        Optional<BlogPost> blogPost = blogPostService.findByPostTitle(blogPostUpdateRequest.getPostTitle());
        if(blogPost.isEmpty()) return GenerateApiResponse.blogPostNotFound(GenerateApiResponse.BLOG_POST_NOT_FOUND);
        updatePost(blogPost.get(), blogPostUpdateRequest);
        return GenerateApiResponse.updateSuccessful(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
    }

    private void updatePost(BlogPost blogPost, BlogPostUpdateRequest blogPostUpdateRequest) {
        if(blogPostUpdateRequest.getBlogContent()!=null){
            blogPost.setBlogContent(blogPostUpdateRequest.getBlogContent());}
        if(blogPostUpdateRequest.getPostTitle()!=null){
            blogPost.setPostTitle(blogPostUpdateRequest.getPostTitle());
        }
        if(blogPostUpdateRequest.getPostImageUrl()!=null){
            blogPost.setPostImageUrl(blogPostUpdateRequest.getPostImageUrl());
        }
        blogPostService.saveBlogPost(blogPost);
    }
}
