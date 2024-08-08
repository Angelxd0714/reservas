package com.microservices.usersService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Date;

import com.microservices.usersService.dto.authDto;
import com.microservices.usersService.http.request.UserRequest;
import com.microservices.usersService.http.response.UserResponse;
import com.microservices.usersService.persistence.entity.UsersEntity;
import com.microservices.usersService.persistence.services.ServiceUser;

@RestController
@RequestMapping("/api/usersGestion")
public class ControllerUser {
    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/all")
    public ResponseEntity<Iterable<UsersEntity>> getAllUsers() {
        try {
            return ResponseEntity.ok(serviceUser.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/searchId/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceUser.findResponseUser(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(" error: "+e.getMessage());
        }
    }

    @GetMapping("/searchEmail/{email}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String email) {
        try {
            return ResponseEntity.ok(serviceUser.findEemilResponse(email));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        try {
            UsersEntity user = new UsersEntity();
            user.setIdentificacion(userRequest.getIdentificacion());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setRole(userRequest.getRole());
            user.setPhone(userRequest.getPhone());
            user.setCreatedAt(userRequest.getCreatedAt());
            user.setUpdatedAt(userRequest.getUpdatedAt());
            authDto aDto = authDto.builder().id(userRequest.getIdentificacion()).username(userRequest.getUsername()).password(userRequest.getPassword()).credentialNoExpired(userRequest.isCredentialNoExpired()).isEnabled(userRequest.isEnabled()).accountNoExpired(userRequest.isAccountNoExpired()).roles(userRequest.getRoles()).accountNoLocked(userRequest.isAccountNoLocked()).build();
            serviceUser.saveUser(user, aDto);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error: "+ e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        try {

            serviceUser.updateUser(id, user);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        try {
            serviceUser.deleteUser(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }
}
