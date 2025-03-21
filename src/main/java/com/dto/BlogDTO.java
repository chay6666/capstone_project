package com.dto;  // Package declaration

import jakarta.validation.constraints.NotBlank;  // Import for validation - ensures the field is not blank
import jakarta.validation.constraints.Size;  // Import for validation - restricts field length

// DTO (Data Transfer Object) for Blog
public class BlogDTO {
    
    private Long id;  // Unique identifier for the blog

    // Title should not be blank and must have between 3 and 100 characters
    @NotBlank(message="Title must not be Blank")  // Ensures title is not null or empty
    @Size(min=3, max=100, message="Title must be between 3 and 100 characters")  // Limits title length
    private String title;

    // Content should not be blank and must have between 3 and 200 characters
    @NotBlank(message="Content must not be blank")  // Ensures content is not null or empty
    @Size(min=3, max=200, message="Content must be between 3 and 200 characters")  // Limits content length
    private String content;

    // Getter and Setter methods for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter methods for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
