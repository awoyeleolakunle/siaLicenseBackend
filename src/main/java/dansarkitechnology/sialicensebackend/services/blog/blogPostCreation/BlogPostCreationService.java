package dansarkitechnology.sialicensebackend.services.blog.blogPostCreation;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.BlogException;

public interface BlogPostCreationService {

    ApiResponse createBlogPost(BlogCreationRequest blogCreationRequest) throws BlogException;
}
