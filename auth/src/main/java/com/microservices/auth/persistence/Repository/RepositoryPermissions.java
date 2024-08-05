package com.microservices.auth.persistence.Repository;

import com.microservices.auth.persistence.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryPermissions extends CrudRepository<PermissionEntity,Long> {
    Optional<PermissionEntity> findById(Long id);

}
