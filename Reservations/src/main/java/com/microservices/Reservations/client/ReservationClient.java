package com.microservices.Reservations.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="Reservaciones",url = "localhost:8072")
public class ReservationClient {
    //usuarios
    
}
