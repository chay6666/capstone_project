package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for handling Comment data.
 * This class is used to transfer data between the controller and service layers
 * while ensuring validation constraints.
 */
public class CommentDTO {
    
    // Unique identifier for the comment (Automatically generated in the database)
    private Long id;
    
    // ID of the associated blog post
    private Long blogId;
    
    /**
     * The actual text of the comment.
     * 
     * - @NotBlank ensures the comment is not empty or null.
     * - @Size ensures the comment length is between 3 and 200 characters.
     */
    @NotBlank
    @Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
    private String comment;

    // Getter method for retrieving the comment ID
    public Long getId() {
        return id; 
    }

    // Setter method for setting the comment ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method for retrieving the associated Blog ID
    public Long getBlogId() {
        return blogId;
    }

    // Setter method for setting the associated Blog ID
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    // Getter method for retrieving the comment text
    public String getComment() {
        return comment;
    }

    // Setter method for setting the comment text
    public void setComment(String comment) {
        this.comment = comment;
    }
}
