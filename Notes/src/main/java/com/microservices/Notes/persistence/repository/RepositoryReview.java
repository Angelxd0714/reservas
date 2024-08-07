package com.microservices.Notes.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.Notes.persistence.entity.ReviewsEntity;

@Repository
public interface RepositoryReview extends CrudRepository<ReviewsEntity,Long> {
    
}
