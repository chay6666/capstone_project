package com.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.BlogDTO;
import com.service.BlogService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController  // ✅ Marks this class as a RESTful controller.
@RequestMapping("/api/blogs")  // ✅ Base URL for all endpoints in this controller.
public class BlogController {

    private final BlogService blogService;

    // ✅ Constructor-based dependency injection
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * ✅ Create a new blog.
     * @param blogDto - Blog details received in the request body.
     * @return ResponseEntity containing the created blog and HTTP status 201 (Created).
     */
    @PostMapping
    @Tag(name = "Add the Blog")  // ✅ API documentation using Swagger.
    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDto) {
        BlogDTO createdBlog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    /**
     * ✅ Get all blogs.
     * @return ResponseEntity with a list of all blogs and HTTP status 200 (OK).
     */
    @GetMapping
    @Tag(name = "Get all the Blogs")
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        List<BlogDTO> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    /**
     * ✅ Get a specific blog by ID.
     * @param id - The blog ID.
     * @return ResponseEntity with the found blog or HTTP status 404 if not found.
     */
    @GetMapping("/{id}")
    @Tag(name = "Get the Blog By id")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        BlogDTO blog = blogService.getBlogById(id);
        if (blog == null) {
            return new ResponseEntity<>("The Blog was not found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    /**
     * ✅ Update a blog by ID.
     * @param id - The blog ID.
     * @param blogDto - Updated blog details.
     * @return ResponseEntity with the updated blog or HTTP status 404 if not found.
     */
    @PutMapping("/{id}")
    @Tag(name = "Update the Blog")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDto) {
        BlogDTO blog = blogService.updateBlog(id, blogDto);
        if (blog == null) {
            return new ResponseEntity<>("The Blog was not found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    /**
     * ✅ Delete a blog by ID.
     * @param id - The blog ID.
     * @return ResponseEntity with a success message.
     */
    @DeleteMapping("/{id}")
    @Tag(name = "Delete the Blog")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("The blog deleted successfully with id " + id);
    }
}
