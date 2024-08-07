package com.microservices.Notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.Notes.persistence.entity.ReviewsEntity;
import com.microservices.Notes.persistence.services.ServiceReview;


@RestController
@RequestMapping("/api/review")
public class ControllerReview {
    @Autowired
    private ServiceReview serviceReview;

    @GetMapping("/all")
    public ResponseEntity<?> getAllReviews() {
        try {
            return ResponseEntity.ok(serviceReview.getIterable());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/searchId/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceReview.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveReview(@RequestBody ReviewsEntity reviewsEntity) {
        try {
            return ResponseEntity.ok(serviceReview.save(reviewsEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewsEntity reviewsEntity) {
        try {
            return ResponseEntity.ok(serviceReview.update(id, reviewsEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        try {
            serviceReview.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
