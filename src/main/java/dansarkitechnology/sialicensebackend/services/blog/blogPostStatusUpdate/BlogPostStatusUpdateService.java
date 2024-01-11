package dansarkitechnology.sialicensebackend.services.blog.blogPostStatusUpdate;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogPostStatusUpdateRequest;

public interface BlogPostStatusUpdateService {
    ApiResponse updateBlogPostBlogStatus(BlogPostStatusUpdateRequest blogPostStatusUpdateRequest);
}
