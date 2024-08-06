package com.microservices.Services.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.Services.persistence.entity.ServiceEntity;

@Repository
public interface RepositoryServices extends CrudRepository<ServiceEntity,Long> {

    ServiceEntity findByName(String name);
    boolean existsByName(String name);
    Optional<ServiceEntity> findById(Long id);
    boolean existsByAvailable(boolean available);
    Iterable<ServiceEntity> findByAvailable(boolean available);
    Iterable<ServiceEntity> findByPrice(Double price);
    ServiceEntity findByPriceAndName(Double price, String name);

}
