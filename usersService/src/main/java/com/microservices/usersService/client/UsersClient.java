package com.microservices.usersService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.microservices.usersService.config.filter.JwtTokenValidator;
import com.microservices.usersService.dto.authDto;
import com.microservices.usersService.http.request.UserRequest;


@FeignClient(name="autenticacion-service",url = "localhost:8080")
public interface UsersClient {
    @GetMapping("/api/users/{id}")
    authDto findByuserId(@PathVariable Long id);

    @PostMapping("/api/users/save")
    authDto saveUser(@RequestBody authDto user);

    @PutMapping("/api/users/{id}")
    authDto upAuthDto(@PathVariable Long id, @RequestBody authDto userRequest);
    @GetMapping("/api/users/all")
    List<authDto> findAll();

    @DeleteMapping("/api/users/{id}")
    void delete(@PathVariable Long id);
}
