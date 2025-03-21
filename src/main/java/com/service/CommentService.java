package com.service;

import java.util.List;
import java.util.stream.Collectors; // Used to convert List of Entities to List of DTOs

import org.springframework.stereotype.Service;

import com.dto.CommentDTO; // Data Transfer Object for Comment
import com.entity.BlogEntity; // Blog Entity class (DB Model)
import com.entity.CommentEntity; // Comment Entity class (DB Model)
import com.exceptions.ResourceNotFoundException; // Custom Exception if Resource Not Found
import com.repository.BlogRepository; // Repository to access Blog Table
import com.repository.CommentRepository; // Repository to access Comment Table

@Service // Marks this class as a Service Component in Spring
public class CommentService {
	
	private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    // Constructor-Based Dependency Injection
    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    /**
     * ✅ Save a new comment to the database.
     * @param commentEntity - The comment entity to be saved.
     * @return The saved CommentEntity object.
     */
	public CommentEntity addComment(CommentEntity commentEntity) {
		return commentRepository.save(commentEntity);
	}
	
    /**
     * ✅ Add a comment to a specific blog.
     * @param id - The Blog ID to which the comment should be added.
     * @param commentDTO - The DTO containing comment details.
     * @return The saved CommentDTO with the assigned ID.
     */
	public CommentDTO postComment(Long id, CommentDTO commentDTO) {
        // Fetch the blog by ID or throw an exception if not found
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Blog Available with Id : " + commentDTO.getBlogId()));

        // Convert DTO to Entity
        CommentEntity comment = new CommentEntity();
        comment.setComment(commentDTO.getComment());
        comment.setBlog(blog);

        // Save the comment entity in the database
        CommentEntity savedComment = commentRepository.save(comment);

        // Set the generated ID back to DTO
        commentDTO.setId(savedComment.getId());
        return commentDTO;
    }
	
    /**
     * ✅ Fetch a comment by its ID.
     * @param commentId - The ID of the comment.
     * @return The corresponding CommentDTO.
     * @throws ResourceNotFoundException if the comment does not exist.
     */
	public CommentDTO getCommentById(Long commentId) {
        // Fetch comment from DB or throw an error if not found
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with the blog id " + commentId));

        // Convert Entity to DTO
        return mapToDTO(comment);
    }

    /**
     * ✅ Fetch all comments for a given blog.
     * @param blogId - The Blog ID.
     * @return List of CommentDTO objects.
     * @throws ResourceNotFoundException if the blog does not exist.
     */
	public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        // Check if blog exists in the database
        if (!blogRepository.existsById(blogId)) {
            throw new ResourceNotFoundException("Blog not found with ID: " + blogId);
        }

        // Fetch all comments for the blog and map them to DTO format
        return commentRepository.findByBlogId(blogId)
                .stream()
                .map(comment -> mapToDTO(comment)) // Convert each entity to DTO
                .collect(Collectors.toList()); // Collect results as a List
    }
	
    /**
     * ✅ Convert CommentEntity to CommentDTO.
     * @param comment - The CommentEntity object.
     * @return Converted CommentDTO object.
     */
	private CommentDTO mapToDTO(CommentEntity comment) {
        CommentDTO dto = new CommentDTO();
        
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        dto.setBlogId(comment.getBlog().getId()); // Fetch blog ID from Blog entity
        return dto;
    }
}
