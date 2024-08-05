package com.microservices.auth.persistence.services;

import com.microservices.auth.persistence.Repository.RepositoryPermissions;
import com.microservices.auth.persistence.entity.PermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePermission {
    @Autowired
    private RepositoryPermissions repositoryPermissions;
    public ServicePermission(RepositoryPermissions repositoryPermissions) {
        this.repositoryPermissions = repositoryPermissions;
    }
    public PermissionEntity findById(Long id){
        return repositoryPermissions.findById(id).orElse(null);
    }
    public Iterable<PermissionEntity> findAll(){
        return repositoryPermissions.findAll();
    }
    public PermissionEntity save(PermissionEntity permissionEntity){
        return repositoryPermissions.save(permissionEntity);
    }
    public void deleteById(Long id){
        repositoryPermissions.deleteById(id);
    }
    public void updatePermission(PermissionEntity permissionEntity,long id){
        repositoryPermissions.findById(id).ifPresent(existingPermission -> {
            existingPermission.setName(permissionEntity.getName());
            repositoryPermissions.save(existingPermission);
        });
    }
}
