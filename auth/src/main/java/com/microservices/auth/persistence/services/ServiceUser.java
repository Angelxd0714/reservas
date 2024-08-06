package com.microservices.auth.persistence.services;

import com.microservices.auth.persistence.Repository.RepositoryUsers;
import com.microservices.auth.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUser{
  @Autowired
  private RepositoryUsers repositoryUsers;
  public ServiceUser(RepositoryUsers repositoryUsers) {
    this.repositoryUsers = repositoryUsers;
  }
  public Optional<UserEntity> findByUsername(String username){
    return repositoryUsers.findByUsername(username);
  }
  public UserEntity save(UserEntity user){
    return repositoryUsers.save(user);
  }
  public Optional<UserEntity> findById(Long id){
    return repositoryUsers.findById(id);
  }
  public boolean existsByUsername(String username){
    return repositoryUsers.existsByUsername(username);
  }
  public boolean existsById(Long id){
    return repositoryUsers.existsById(id);
  }
  public void deleteById(Long id){
    repositoryUsers.deleteById(id);
  }
  public void UpdateUser(long id,UserEntity user){
     repositoryUsers.findById(id)
             .map(user1 -> {
                 user1.setUsername(user.getUsername());
                 user1.setPassword(user.getPassword());
                 user1.setRoles(user.getRoles());
                 return repositoryUsers.save(user1);
             });
  }
  public Iterable<UserEntity> findAll(){
    return repositoryUsers.findAll();
  }
}
