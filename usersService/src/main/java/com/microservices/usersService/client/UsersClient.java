package com.microservices.usersService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.usersService.dto.authDto;


@FeignClient(name="auth",url = "localhost:8095/api")
public interface UsersClient {
    @GetMapping("/searchId/{id}")
    List<authDto> findByuserId(@PathVariable Long id);
}
