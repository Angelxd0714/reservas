package com.microservices.Reservations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.Reservations.dto.servicesDto;

@FeignClient(name="Services",url = "localhost:8060")
public interface ReservationClient {

  @GetMapping("/api/services/searchId/{id}")
  servicesDto searchId(@PathVariable Long id);

  @GetMapping("/api/services/all")
  Iterable<servicesDto> findAll();
}
