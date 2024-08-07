package com.microservices.Reservations.persistence.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Reservations.persistence.entity.ReservationEntity;
import com.microservices.Reservations.persistence.repository.RepositoryReservation;

@Service
public class ServiceReservation {
    @Autowired
    private RepositoryReservation repositoryReservation;

    public Iterable<ReservationEntity> getAllReservations() {
        return repositoryReservation.findAll();
    }
    public ReservationEntity getReservationById(Long id) {
        return repositoryReservation.findById(id).orElse(null);
    }
    public ReservationEntity saveReservation(ReservationEntity reservation) {
        return repositoryReservation.save(reservation);
    }
    public void deleteReservation(Long id) {
        repositoryReservation.deleteById(id);
    }
    public ReservationEntity updateReservation(Long id, ReservationEntity reservation) {
        ReservationEntity reservationDB = repositoryReservation.findById(id).orElse(null);
        if (reservationDB == null) {
            return null;
        }
        reservationDB.setCreatedAt(reservation.getCreatedAt());
        reservationDB.setStatus(reservation.getStatus());
        reservationDB.setPrice(reservation.getPrice());
        reservationDB.setStartDate(reservation.getStartDate());
        reservationDB.setUpDate(reservation.getUpDate());
        return repositoryReservation.save(reservationDB);
    }
    public ReservationEntity findByCreatedAt(Timestamp timestamp) {
        return repositoryReservation.findByCreatedAt(timestamp);
    }
    public ReservationEntity findByServicioId(Long servicioId) {
        return repositoryReservation.findByServicioId(servicioId);
    }
    public ReservationEntity findReservationStatus(String status) {
        return repositoryReservation.findByStatus(status);
    }

}
