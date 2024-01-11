package dansarkitechnology.sialicensebackend.services.blog.blogPostService;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.enums.BlogStatus;
import dansarkitechnology.sialicensebackend.data.models.BlogPost;
import dansarkitechnology.sialicensebackend.data.repositories.BlogPostRepository;
import dansarkitechnology.sialicensebackend.dtos.request.PaginationRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class BlogPostServiceImp implements BlogPostService{
    private final BlogPostRepository blogPostRepository;
    @Override
    public BlogPost saveBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public Optional<BlogPost> findByPostTitle(String postTitle) {
        return blogPostRepository.findByPostTitleEqualsIgnoreCase(postTitle);
    }

    @Override
    public Optional<BlogPost> findPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    @Override
    public List<BlogPost> findAllBlogPost(PaginationRequest paginationRequest) {

        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        Page<BlogPost> pages =  blogPostRepository.findAll(pageable);
        return pages.getContent();
    }

    @Override
    public ApiResponse deleteBlogPostById(Long id) {
        blogPostRepository.deleteById(id);
        return GenerateApiResponse.deleteSuccessful(GenerateApiResponse.DELETED_SUCCESSFULLY);
    }

    @Override
    public List<BlogPost> findAllActiveBlogPost(int pageSize, int pageNumber) {

        List<BlogPost> listOfActiveBlogPost = blogPostRepository.findAllByBlogStatus(BlogStatus.ACTIVE);
        if(listOfActiveBlogPost.isEmpty()){ return listOfActiveBlogPost;}

        int totalSizeOfListOfActiveBlogPost = listOfActiveBlogPost.size();
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalSizeOfListOfActiveBlogPost);
        return listOfActiveBlogPost.subList(startIndex, endIndex);
    }
}
