package dansarkitechnology.sialicensebackend.services.blog.blogPostUpdate;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogPostUpdateRequest;
import dansarkitechnology.sialicensebackend.exceptions.BlogException;

public interface UpdateBlogPostService {
    ApiResponse updateBlogPost(BlogPostUpdateRequest blogPostUpdateRequest) throws BlogException;
}
