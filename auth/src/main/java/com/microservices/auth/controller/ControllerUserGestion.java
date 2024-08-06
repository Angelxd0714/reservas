package com.microservices.auth.controller;

import com.microservices.auth.persistence.entity.UserEntity;
import com.microservices.auth.persistence.services.ServiceUser;



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

@RestController
@RequestMapping("/api")
public class ControllerUserGestion {
    @Autowired
    ServiceUser userDetailService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        try {
            return ResponseEntity.ok(userDetailService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error update user: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userDetailService.findById(id));
        }catch (Exception e){
            
            return ResponseEntity.badRequest().body("Error seacrh user: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            userDetailService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error delete user: " + e.getMessage());
        }
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        try {
            return ResponseEntity.ok(userDetailService.findByUsername(username));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error search user: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserEntity user){
        try {
            userDetailService.UpdateUser(id, user);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error update user: " + e.getMessage());
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userDetailService.save(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error save user: " + e.getMessage());
        }
    }
}
