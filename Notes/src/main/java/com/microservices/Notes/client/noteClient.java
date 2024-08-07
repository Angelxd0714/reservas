package com.microservices.Notes.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservices.Notes.dto.reservationDto;

@FeignClient(name="Reservaciones",url = "localhost:8065")
public interface noteClient {
    @GetMapping("/api/reservation/all")
    List<reservationDto> getAllReservations();

    @GetMapping("/api/reservation/searchId/{id}")
    reservationDto getReservationById(Long id);



    
}
