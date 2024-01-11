package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.enums.BlogStatus;
import dansarkitechnology.sialicensebackend.data.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    Optional<BlogPost> findByPostTitleEqualsIgnoreCase(String postTitle);
    //BlogPost findAllByBlogStatusActive(PaginationRequest paginationRequest);

    List<BlogPost> findAllByBlogStatus(BlogStatus blogStatus);
}
