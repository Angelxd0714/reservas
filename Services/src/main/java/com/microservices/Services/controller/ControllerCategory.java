package com.microservices.Services.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.microservices.Services.persistence.entity.CategoryEntity;
import com.microservices.Services.persistence.services.ServiceCategory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class ControllerCategory {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ServiceCategory serviceCategory;

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
     * This method handles the GET request to retrieve a specific category by its
     * ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The Category object with the specified ID.
     */
    @GetMapping("/searchId/{id}")
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
    public ResponseEntity<?> createCategory(@RequestParam("file") MultipartFile file,
            @RequestParam("name") String name) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            String nameFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(uploadDir, nameFile).toString();
            Files.write(file.getBytes(), new java.io.File(filePath));
            String fileUrl = "/upload/" + nameFile;
            String fullFileUrl = "http://localhost:8060" + fileUrl;
            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            category.setImagenUrl(fullFileUrl);
            serviceCategory.save(category);

            return ResponseEntity.ok().body("ok");
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
     * @throws IOException
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name) throws IOException {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            CategoryEntity service = serviceCategory.findById(id).orElse(null);
            if (service.getImagenUrl() != null) {
                String oldFileName = service.getImagenUrl().substring(service.getImagenUrl().lastIndexOf("/") + 1);
                String oldFilePath = Paths.get(uploadDir, oldFileName).toString();
                java.nio.file.Files.deleteIfExists(new java.io.File(oldFilePath).toPath());
            }
            String nameFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(uploadDir, nameFile).toString();
            Files.write(file.getBytes(), new java.io.File(filePath));
            String fileUrl = "/upload/" + nameFile;
            String fullFileUrl = "http://localhost:8060" + fileUrl;
            service.setName(name);
            service.setImagenUrl(fullFileUrl);
            serviceCategory.updateCategory(id, service);

            return ResponseEntity.ok().body("ok");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error update category: " + e.getMessage());
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
            return ResponseEntity.ok().body("ok");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
