package com.microservices.Services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.Services.persistence.entity.ServiceEntity;
import com.microservices.Services.persistence.services.FunctionService;


@RestController
@RequestMapping("/api/services")
public class ControllerServices {
    @Autowired
    private FunctionService functionService;
    public ControllerServices(FunctionService functionService) {
        this.functionService = functionService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllServices() {
        try {
            return ResponseEntity.ok(functionService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(Long id) {
        try {
            return ResponseEntity.ok(functionService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getServiceByName(String name) {
        try {
            return ResponseEntity.ok(functionService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveService(ServiceEntity service) {
        try {
            return ResponseEntity.ok(functionService.save(service));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(Long id) {
        try {
            functionService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/sale/{price}")
    public ResponseEntity<?> countServices(@PathVariable Double price) {
        try {
            return ResponseEntity.ok(functionService.findByPrice(price));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/sale/{price}/{name}")
    public ResponseEntity<?> countServices(@PathVariable Double price, @PathVariable String name) {
        try {
            return ResponseEntity.ok(functionService.findByPriceAndName(price, name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{available}")
    public ResponseEntity<?> findByAvailable(@PathVariable Boolean available) {
        try {
            return ResponseEntity.ok(functionService.findByAvailable(available));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

