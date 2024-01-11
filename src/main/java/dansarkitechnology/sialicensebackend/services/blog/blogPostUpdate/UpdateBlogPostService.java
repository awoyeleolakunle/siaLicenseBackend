package dansarkitechnology.sialicensebackend.services.blog.blogPostUpdate;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.BlogPostUpdateRequest;

public interface UpdateBlogPostService {
    ApiResponse updateBlogPost(BlogPostUpdateRequest blogPostUpdateRequest);
}
