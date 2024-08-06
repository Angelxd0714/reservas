package com.microservices.usersService.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.usersService.persistence.entity.UsersEntity;

@Repository
public interface RepositoryUserCrud extends CrudRepository<UsersEntity,Long> {
    <Optional>UsersEntity findByEmail(String email);
    @Override
    Optional<UsersEntity> findById(Long id);
    Optional<UsersEntity> findByIdentificacion(long identificacion);
}
