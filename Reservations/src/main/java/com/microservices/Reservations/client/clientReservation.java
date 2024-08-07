package com.microservices.Reservations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.Reservations.dto.authDto;
import com.microservices.Reservations.http.response.UserResponse;

@FeignClient(name = "UserServices",url="localhost:8072")
public interface clientReservation {
  @GetMapping("/api/users/searchId/{id}")
  UserResponse getAuth(@PathVariable Long id);

}
