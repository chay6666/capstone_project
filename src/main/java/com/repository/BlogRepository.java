package com.repository;  // Defines the package location for this interface

import java.util.Optional;  // Importing Optional for handling nullable return values

import org.springframework.data.jpa.repository.JpaRepository;  // Importing JpaRepository for CRUD operations

import com.dto.BlogDTO;  // Importing BlogDTO (Data Transfer Object)
import com.entity.BlogEntity;  // Importing BlogEntity (Represents the Blog table in DB)

import jakarta.validation.Valid;  // Importing validation support for input validation

/**
 * BlogRepository is an interface for performing CRUD operations on BlogEntity.
 * It extends JpaRepository, which provides built-in methods like save(), findById(), delete(), etc.
 */
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    
    // JpaRepository already provides common methods like:
    // - save(BlogEntity entity)  → Insert/Update a blog
    // - findById(Long id)  → Retrieve a blog by ID
    // - findAll()  → Retrieve all blogs
    // - deleteById(Long id)  → Delete a blog by ID
    
    // ✅ If needed, we can define custom query methods here
    // Example: Optional<BlogEntity> findByTitle(String title);
    
}
