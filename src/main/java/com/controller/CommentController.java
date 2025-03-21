package com.controller; // Defines the package where this class belongs

import java.util.List; // Import List for returning multiple comments

import org.springframework.http.ResponseEntity; // Used for HTTP responses
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests
import org.springframework.web.bind.annotation.PathVariable; // Extracts values from the URL
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests
import org.springframework.web.bind.annotation.RequestBody; // Binds request body to a Java object
import org.springframework.web.bind.annotation.RequestMapping; // Defines base URL for controller
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST API controller

import com.dto.CommentDTO; // Importing Data Transfer Object (DTO) for comments
import com.service.CommentService; // Importing service layer to handle business logic

import io.swagger.v3.oas.annotations.tags.Tag; // Swagger annotation for API documentation
import jakarta.validation.Valid; // Ensures request body validation

/**
 * CommentController handles all HTTP requests related to comments on blogs.
 */
@RestController // Marks this class as a REST controller
@RequestMapping("/api/blogs") // Base URL for all endpoints in this controller
public class CommentController {
	
	private final CommentService commentService; // Service layer for handling comment-related operations

    /**
     * Constructor-based dependency injection of CommentService.
     * @param commentService - Service class to handle business logic
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 🌟 API: Add a Comment to a Blog
     * 📌 URL: POST /api/blogs/{blogId}/comments
     * This method adds a comment to a specific blog.
     * 
     * @param blogId - The ID of the blog to which the comment belongs
     * @param commentDto - The comment data received from the request body
     * @return ResponseEntity with the saved comment DTO
     */
    @PostMapping("/{blogId}/comments") // Maps HTTP POST requests to /api/blogs/{blogId}/comments
    @Tag(name="Add the Comment") // Swagger documentation tag
    public ResponseEntity<CommentDTO> postComment(@PathVariable Long blogId, @Valid @RequestBody CommentDTO commentDto) {
    	commentDto.setBlogId(blogId); // Associate the comment with the given blogId
        return ResponseEntity.ok(commentService.postComment(blogId, commentDto)); // Call service method to save the comment
    }

    /**
     * 🌟 API: Get All Comments for a Blog
     * 📌 URL: GET /api/blogs/{blogId}/comments
     * This method retrieves all comments associated with a specific blog.
     * 
     * @param blogId - The ID of the blog for which comments are retrieved
     * @return ResponseEntity with a list of CommentDTOs
     */
    @GetMapping("/{blogId}/comments") // Maps HTTP GET requests to /api/blogs/{blogId}/comments
    @Tag(name="Get all the Comments based on blog id") // Swagger documentation tag
    public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@PathVariable Long blogId) {
        List<CommentDTO> comments = commentService.getCommentsByBlogId(blogId); // Call service method to fetch comments
        return ResponseEntity.ok(comments); // Return comments with HTTP 200 OK status
    }
    
    /**
     * 🌟 API: Get a Comment by ID
     * 📌 URL: GET /api/blogs/comment/{commentId}
     * This method retrieves a specific comment by its ID.
     * 
     * @param commentId - The ID of the comment to retrieve
     * @return ResponseEntity with the CommentDTO
     */
    @GetMapping("/comment/{commentId}") // Maps HTTP GET requests to /api/blogs/comment/{commentId}
    @Tag(name="Get the comment by id") // Swagger documentation tag
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId) {
        CommentDTO commentDto = commentService.getCommentById(commentId); // Call service method to fetch a single comment
        return ResponseEntity.ok(commentDto); // Return the comment with HTTP 200 OK status
    }
}
