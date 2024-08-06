package com.microservices.Services.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.Services.persistence.entity.CategoryEntity;

@Repository
public interface RepositoryCategory extends CrudRepository<CategoryEntity,Long> {
    
    CategoryEntity findByName(String name);
    boolean existsByName(String name);
    Optional<CategoryEntity> findById(Long id);

    
}
