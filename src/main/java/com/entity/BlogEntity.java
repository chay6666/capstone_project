package com.entity;

import java.util.List;

import jakarta.persistence.*;  // Importing JPA annotations for Entity, Table, etc.
import jakarta.validation.constraints.NotNull; // Importing validation annotations

/**
 * Represents a Blog entity in the database.
 * This entity is mapped to the "blogs" table and has a one-to-many relationship with comments.
 */
@Entity  // Marks this class as a JPA entity (a table in the database)
@Table(name = "blogs")  // Maps this entity to the "blogs" table
public class BlogEntity {

    /** 
     * The primary key for the Blog table. It is auto-generated.
     */
    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    @Column(name = "blog_id") // Maps to the "blog_id" column in the database
    private Long id;

    /** 
     * Title of the blog post. Cannot be null.
     */
    @NotNull  // Ensures this field cannot be null
    private String title;

    /** 
     * Content of the blog post. Cannot be null.
     */
    @NotNull
    private String content;

    /**
     * One-to-Many relationship with CommentEntity.
     * - `mappedBy = "blog"`: The "blog" field in CommentEntity manages the relationship.
     * - `cascade = CascadeType.ALL`: Deleting a blog will delete all its comments.
     * - `fetch = FetchType.LAZY`: Comments are **not** loaded automatically with the blog (loaded only when needed).
     */
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    // ✅ Getters and Setters (Used to access and modify fields)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
