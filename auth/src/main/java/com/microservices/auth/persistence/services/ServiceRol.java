package com.microservices.auth.persistence.services;

import com.microservices.auth.persistence.Repository.RepositoryRoles;
import com.microservices.auth.persistence.entity.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRol {
    @Autowired
    private RepositoryRoles repositoryRoles;
    public ServiceRol(RepositoryRoles repositoryRoles) {
        this.repositoryRoles = repositoryRoles;
    }
    public RolEntity save(RolEntity rolEntity){
        return repositoryRoles.save(rolEntity);
    }
    public RolEntity findById(Long id){
        return repositoryRoles.findById(id).orElse(null);
    }
    public Iterable<RolEntity> findAll(){
        return repositoryRoles.findAll();
    }
    public void deleteById(Long id){
        repositoryRoles.deleteById(id);
    }
    public void update(RolEntity rolEntity,long id){
        repositoryRoles.findById(id).ifPresent(e->{
                e.setRoleEnum(e.getRoleEnum());
                e.setPermissionList(e.getPermissionList());
                repositoryRoles.save(e);
        });

    }
}
