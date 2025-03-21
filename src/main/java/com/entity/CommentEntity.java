package com.entity;

import jakarta.persistence.*;  // Imports JPA annotations
import jakarta.validation.constraints.NotNull;  // Import for validation

/**
 * Represents a comment entity that stores comments related to a blog.
 * This class maps to the "comments" table in the database.
 */
@Entity
@Table(name = "comments") // Specifies that this class maps to the "comments" table
public class CommentEntity {

    /**
     * Unique identifier for each comment.
     * This field is auto-generated using IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    @Column(name = "comment_id") // Column name in the database
    private Long id;

    /**
     * The content of the comment.
     * Cannot be null due to @NotNull validation.
     */
    @NotNull
    private String comment;

    /**
     * Relationship with the BlogEntity.
     * Many comments can be linked to one blog (Many-to-One relationship).
     * This creates a foreign key "blog" in the comments table.
     */
    @ManyToOne
    @JoinColumn(name = "blog", nullable = false) // Foreign key column in "comments" table
    private BlogEntity blog;

    /** Getter method for comment ID */
    public Long getId() {
        return id;
    }

    /** Setter method for comment ID */
    public void setId(Long id) {
        this.id = id;
    }

    /** Getter method for comment text */
    public String getComment() {
        return comment;
    }

    /** Setter method for comment text */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /** Getter method for the associated blog */
    public BlogEntity getBlog() {
        return blog;
    }

    /** Setter method for the associated blog */
    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }
}
