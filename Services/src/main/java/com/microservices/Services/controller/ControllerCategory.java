package com.microservices.Services.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.Services.persistence.entity.CategoryEntity;
import com.microservices.Services.persistence.services.ServiceCategory;



import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ControllerCategory {
    @Autowired
    private  ServiceCategory serviceCategory;

    /**
     * This method handles the GET request to retrieve a list of all categories.
     *
     * @return A list of Category objects.
     */
    @GetMapping("/all")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        List<CategoryEntity> categories = (List<CategoryEntity>) serviceCategory.findAll();
        return ResponseEntity.ok(categories);
    }

    /**
     * This method handles the GET request to retrieve a specific category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The Category object with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceCategory.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method handles the POST request to create a new category.
     *
     * @param category The Category object to be created.
     * @return The created Category object.
     */
    @PostMapping("/save")
    public ResponseEntity<?> createCategory(@RequestBody CategoryEntity category) {
        try {
            serviceCategory.save(category);
            return ResponseEntity.ok().body("save");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error save category: " + e.getMessage());
        }
      
    }

    /**
     * This method handles the PUT request to update an existing category.
     *
     * @param id       The ID of the category to be updated.
     * @param category The updated Category object.
     * @return The updated Category object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity category) {
        try {
             serviceCategory.updateCategory(id, category);
            return ResponseEntity.ok().body("save");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method handles the DELETE request to delete a category by its ID.
     *
     * @param id The ID of the category to be deleted.
     * @return A success message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            serviceCategory.deleteById(id);
            return ResponseEntity.ok().body("delete");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

