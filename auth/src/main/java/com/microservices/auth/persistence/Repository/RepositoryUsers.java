package com.microservices.auth.persistence.Repository;

import com.microservices.auth.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUsers extends CrudRepository<UserEntity,Long> {
    @Override
    Optional<UserEntity> findById(Long aLong);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);


}
