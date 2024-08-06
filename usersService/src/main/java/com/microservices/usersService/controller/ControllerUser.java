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
import org.springframework.web.bind.annotation.RestController;

import com.microservices.usersService.persistence.entity.UsersEntity;
import com.microservices.usersService.persistence.services.ServiceUser;

@RestController
@RequestMapping("/api/users")
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
    public ResponseEntity<Optional<UsersEntity>> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceUser.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping("/searchEmail/{email}")
    public ResponseEntity<UsersEntity> getUserByUsername(@PathVariable String email) {
        try {
            return ResponseEntity.ok(serviceUser.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<UsersEntity> saveUser(@RequestBody UsersEntity user) {
        try {
            return ResponseEntity.ok(serviceUser.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable Long id, @RequestBody UsersEntity user) {
        try {
                  
            serviceUser.updateUser(id, user);
            return ResponseEntity.ok(user);
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        try {
            serviceUser.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }
}
