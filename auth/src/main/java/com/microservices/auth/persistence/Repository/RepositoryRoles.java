package com.microservices.auth.persistence.Repository;

import com.microservices.auth.persistence.entity.RolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RepositoryRoles extends CrudRepository<RolEntity,Long> {
    @Override
    Optional<RolEntity> findById(Long aLong);
    List<RolEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
    RolEntity findRoleEntityByRoleEnum(String roleName);
}
