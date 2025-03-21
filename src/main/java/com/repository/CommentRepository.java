package com.repository; // Package where the repository is located

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.CommentEntity;

/**
 * Repository interface for CommentEntity.
 * It extends JpaRepository to provide built-in CRUD operations.
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    /**
     * Custom method to retrieve comments by blog ID.
     * Spring Data JPA will generate the necessary SQL query automatically.
     * 
     * @param blogId The ID of the blog whose comments are to be fetched.
     * @return List of comments belonging to the specified blog.
     */
    List<CommentEntity> findByBlogId(Long blogId);
}
