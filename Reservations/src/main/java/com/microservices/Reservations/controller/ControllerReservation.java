package com.microservices.Reservations.controller;

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

import com.microservices.Reservations.persistence.entity.ReservationEntity;
import com.microservices.Reservations.persistence.services.ServiceHistorReservations;
import com.microservices.Reservations.persistence.services.ServiceReservation;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/reservation")
public class ControllerReservation {
    @Autowired
    private ServiceReservation serviceReservation;
    @Autowired
    private ServiceHistorReservations serviceHistorReservations;

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservations() {
        try {
            return ResponseEntity.ok(serviceReservation.getAllReservations());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener las reservas: " + e.getMessage());
        }
    }
    @GetMapping("searchId/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceReservation.getReservationById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener la reserva: " + e.getMessage());
        }
    }
    @GetMapping("searchDate/{date}")
    public ResponseEntity<?> getReservationByDate(@PathVariable Timestamp date) {
        try {
            return ResponseEntity.ok(serviceReservation.findByCreatedAt(date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener la reserva: " + e.getMessage());
        }
    }
    @GetMapping("searchServicioId/{servicioId}")
    public ResponseEntity<?> getReservationByServicioId(@PathVariable Long servicioId) {
        try {
            return ResponseEntity.ok(serviceReservation.findByServicioId(servicioId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener la reserva: " + e.getMessage());
        }
    }
    @GetMapping("searchStatus/{status}")
    public ResponseEntity<?> getReservationByStatus(@PathVariable String status) {
        try {
            return ResponseEntity.ok(serviceReservation.findReservationStatus(status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener la reserva: " + e.getMessage());
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveReservation(@RequestBody ReservationEntity reservation) {
        try {
            return ResponseEntity.ok(serviceReservation.saveReservation(reservation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar la reserva: " + e.getMessage());
        }
    }
    @GetMapping("/listHist")
    public ResponseEntity<?> saveHistorReservation() {
        try {
            return ResponseEntity.ok(serviceHistorReservations.getHisIterable());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar la reserva: " + e.getMessage());
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReservation(@RequestBody ReservationEntity reservation,@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceReservation.updateReservation(id,reservation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la reserva: " + e.getMessage());
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        try {
            serviceReservation.deleteReservation(id);
            return ResponseEntity.ok("Reserva eliminada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar la reserva: " + e.getMessage());
        }
    }

}
