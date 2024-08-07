package com.microservices.usersService.persistence.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.usersService.client.UsersClient;
import com.microservices.usersService.dto.authDto;
import com.microservices.usersService.http.request.UserRequest;
import com.microservices.usersService.http.response.UserResponse;
import com.microservices.usersService.persistence.entity.UsersEntity;
import com.microservices.usersService.persistence.repository.RepositoryUserCrud;

@Service
public class ServiceUser {
    @Autowired
    private RepositoryUserCrud repositoryUser;
    @Autowired
    private UsersClient usersClient;

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
    public void updateUser(Long id,UserRequest userRequest){
        repositoryUser.findById(id).ifPresent(e->{
            e.setRole(userRequest.getRole());
            e.setEmail(userRequest.getEmail());
            e.setIdentificacion(userRequest.getIdentificacion());
            e.setFirstName(userRequest.getFirstName());
            e.setLastName(userRequest.getLastName());
            e.setUpdatedAt(userRequest.getUpdatedAt());
            repositoryUser.save(e);
        
        });
        authDto auth = usersClient.findByuserId(id);
        if(auth!=null){
            auth.setUsername(userRequest.getUsername());
            auth.setPassword(userRequest.getPassword());
            auth.setEnabled(userRequest.isEnabled());
            auth.setRoles(userRequest.getRoles());
            auth.setCredentialNoExpired(userRequest.isCredentialNoExpired());
            auth.setAccountNoExpired(userRequest.isAccountNoExpired());
            auth.setAccountNoLocked(userRequest.isAccountNoLocked());
            usersClient.upAuthDto(id, auth);
        }

        
    }
    public UserResponse findResponseUser (Long id){
        UsersEntity user = repositoryUser.findById(id).orElse(null);
        System.out.println(user);
        authDto userDto = usersClient.findByuserId(id);
        return UserResponse.builder().email(user.getEmail()).id(id).username(userDto.getUsername()).firstName(user.getFirstName()).lastName(user.getLastName()).identificacion(user.getIdentificacion()).roles(userDto.getRoles()).isEnabled(userDto.isEnabled()).build();
    }
    public void saveUser(UsersEntity user,authDto aDto){
        repositoryUser.save(user);
        usersClient.saveUser(aDto);
    }
    public UserResponse findEemilResponse(String email){
        UsersEntity user = repositoryUser.findByEmail(email);
        authDto userDto = usersClient.findByuserId(user.getId());
        return UserResponse.builder().email(user.getEmail()).id(user.getId()).username(userDto.getUsername()).firstName(user.getFirstName()).lastName(user.getLastName()).identificacion(user.getIdentificacion()).roles(userDto.getRoles()).isEnabled(userDto.isEnabled()).build();
    }
    public void deleteUser(Long id){
        repositoryUser.deleteById(id);
        usersClient.delete(id);
    }
}
