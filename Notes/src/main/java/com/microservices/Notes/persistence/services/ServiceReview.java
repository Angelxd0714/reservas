package com.microservices.Notes.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.microservices.Notes.client.noteClient;
import com.microservices.Notes.dto.reservationDto;
import com.microservices.Notes.http.response.ReviewResponse;
import com.microservices.Notes.persistence.entity.ReviewsEntity;
import com.microservices.Notes.persistence.repository.RepositoryReview;

@Service
public class ServiceReview {
    @Autowired
    private RepositoryReview repositoryReview;
    @Autowired
    private noteClient serviceReservation;

    public Iterable<ReviewsEntity> getIterable() {
        return repositoryReview.findAll();
    }


    public ReviewsEntity save(ReviewsEntity reviewsEntity) {
        return repositoryReview.save(reviewsEntity);
    }
    public void delete(Long id) {
        repositoryReview.deleteById(id);
    }
    public ReviewResponse getById(Long id) {
        ReviewsEntity reviewsEntity = repositoryReview.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        reservationDto reservation = serviceReservation.getReservationById(reviewsEntity.getReservaId());
        return ReviewResponse.builder().comentario(reviewsEntity.getComentario()).id(reviewsEntity.getId()).createdAt(reservation.getCreatedAt()).startDate(reviewsEntity.getCreateAt()).build();
    }
    public ReviewsEntity update(Long id,ReviewsEntity reviewsEntity) {
        ReviewsEntity existingReview = repositoryReview.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        existingReview.setComentario(reviewsEntity.getComentario());
        existingReview.setReservaId(reviewsEntity.getReservaId());
        existingReview.setCreateAt(reviewsEntity.getCreateAt());
        return repositoryReview.save(existingReview);
    }

}
