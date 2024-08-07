package com.microservices.Notes.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import com.microservices.Notes.persistence.entity.ReviewsEntity;
import com.microservices.Notes.persistence.repository.RepositoryReview;

@Service
public class ServiceReview {
    @Autowired
    private RepositoryReview repositoryReview;

    public Iterable<ReviewsEntity> getIterable() {
        return repositoryReview.findAll();
    }


    public ReviewsEntity save(ReviewsEntity reviewsEntity) {
        return repositoryReview.save(reviewsEntity);
    }
    public void delete(Long id) {
        repositoryReview.deleteById(id);
    }
    public ReviewsEntity getById(Long id) {
        return repositoryReview.findById(id).orElse(null);
    }
    public ReviewsEntity update(Long id,ReviewsEntity reviewsEntity) {
        ReviewsEntity existingReview = repositoryReview.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        existingReview.setComentario(reviewsEntity.getComentario());
        existingReview.setReservaId(reviewsEntity.getReservaId());
        existingReview.setCreateAt(reviewsEntity.getCreateAt());
        return repositoryReview.save(existingReview);
    }

}
