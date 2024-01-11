package dansarkitechnology.sialicensebackend.services.blog.blogPostService;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.BlogPost;
import dansarkitechnology.sialicensebackend.dtos.request.PaginationRequest;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    BlogPost saveBlogPost(BlogPost blogPost);

    Optional<BlogPost> findByPostTitle(String postTitle);

    Optional<BlogPost> findPostById(Long id);

    List<BlogPost> findAllBlogPost(PaginationRequest paginationRequest);

    ApiResponse deleteBlogPostById(Long id);

    List<BlogPost> findAllActiveBlogPost(int pageSize, int pageNumber);
}
