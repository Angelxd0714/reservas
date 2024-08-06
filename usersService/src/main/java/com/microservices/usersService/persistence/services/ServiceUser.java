package com.microservices.usersService.persistence.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.usersService.persistence.entity.UsersEntity;
import com.microservices.usersService.persistence.repository.RepositoryUserCrud;

@Service
public class ServiceUser {
    @Autowired
    private RepositoryUserCrud repositoryUser;

    public UsersEntity save(UsersEntity user){
        return repositoryUser.save(user);
    }
    public UsersEntity findByEmail(String email){
        return repositoryUser.findByEmail(email);
    }
    public Optional<UsersEntity> findByIdentificacion(long identificacion){
        return repositoryUser.findByIdentificacion(identificacion);
    }
    public Optional<UsersEntity> findById(long id){
        return repositoryUser.findById(id);
    }
    public Iterable<UsersEntity> findAll(){
        return repositoryUser.findAll();
    }
    public void deleteById(long id){
        repositoryUser.deleteById(id);
    }
    public void updateUser(long id,UsersEntity usersEntity){
        repositoryUser.findById(id).ifPresent(e->{
            e.setRole(usersEntity.getRole());
            e.setEmail(usersEntity.getEmail());
            e.setIdentificacion(usersEntity.getIdentificacion());
            e.setFirstName(usersEntity.getFirstName());
            e.setLastName(usersEntity.getLastName());
            e.setUpdatedAt(usersEntity.getUpdatedAt());
            repositoryUser.save(e);
        });
        
    }
}
