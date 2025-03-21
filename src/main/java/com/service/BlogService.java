package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
import com.exceptions.ResourceNotFoundException;
import com.repository.BlogRepository;

import jakarta.validation.Valid;

@Service  // Marks this class as a Spring service component, making it eligible for dependency injection.
public class BlogService {
	
	private final BlogRepository blogRepository;

    // Constructor-based dependency injection for BlogRepository
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    /**
     * Creates a new blog entry in the database.
     * @param blogDTO DTO containing blog details.
     * @return The saved BlogDTO with generated ID.
     */
    public BlogDTO createBlog(@Valid BlogDTO blogDTO) {
        BlogEntity blog = new BlogEntity();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());

        // Save the blog entity in the database and return the saved entity
        BlogEntity savedBlog = blogRepository.save(blog);

        // Convert the saved entity to DTO and return
        return mapToDTO(savedBlog);
    }

    /**
     * Retrieves all blog entries from the database.
     * @return List of BlogDTOs.
     */
    public List<BlogDTO> getAllBlogs() {
        return blogRepository.findAll() // Fetch all blogs from database
                .stream() // Convert list to stream for processing
                .map(blog -> mapToDTO(blog)) // Convert each BlogEntity to BlogDTO
                .collect(Collectors.toList()); // Collect the mapped DTOs into a list
    }

    /**
     * Retrieves a single blog by its ID.
     * @param id ID of the blog to fetch.
     * @return BlogDTO of the requested blog.
     * @throws ResourceNotFoundException if blog is not found.
     */
    public BlogDTO getBlogById(Long id) {
        BlogEntity blog = blogRepository.findById(id) // Fetch blog by ID
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id)); // Throw exception if not found
        return mapToDTO(blog); // Convert entity to DTO and return
    }

    /**
     * Converts a BlogEntity to BlogDTO.
     * @param blog Blog entity.
     * @return Converted BlogDTO.
     */
    private BlogDTO mapToDTO(BlogEntity blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        return dto;
    }
	
    /**
     * Updates an existing blog.
     * @param id ID of the blog to update.
     * @param blogDto BlogDTO with updated data.
     * @return Updated BlogDTO.
     * @throws ResourceNotFoundException if blog is not found.
     */
    public BlogDTO updateBlog(Long id, BlogDTO blogDto) {
        // Find the blog or throw exception if not found
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

        // Update blog details
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());

        // Save updated blog and return the DTO
        BlogEntity updatedBlog = blogRepository.save(blog);
        return mapToDTO(updatedBlog);
    }
	
    /**
     * Deletes a blog by ID.
     * @param id ID of the blog to delete.
     * @throws ResourceNotFoundException if blog is not found.
     */
    public void deleteBlog(Long id) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

        // Delete the blog from the database
        blogRepository.delete(blog);
    }
}
