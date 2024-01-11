package dansarkitechnology.sialicensebackend.services.blog.blogPostCreation;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogCreationRequest;

public interface BlogPostCreationService {

    ApiResponse createBlogPost(BlogCreationRequest blogCreationRequest);
}
