package com.microservices.Reservations.persistence.services;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Reservations.persistence.entity.ReservationEntity;
import com.microservices.Reservations.persistence.entity.ReservationsHistoryEntity;
import com.microservices.Reservations.persistence.repository.RepositoryHistorialReservation;
import com.microservices.Reservations.persistence.repository.RepositoryReservation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ServiceReservation {
    @Autowired
    private RepositoryReservation repositoryReservation;
    @Autowired
    private RepositoryHistorialReservation repositoryHistorialReservation;

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
    @Transactional
    public ReservationEntity updateReservation(Long id, ReservationEntity reservation) {
         ReservationEntity existingReservation = repositoryReservation.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        // Guardar el historial
        ReservationsHistoryEntity history = new ReservationsHistoryEntity();
        history.setReservationId(existingReservation.getId());
        history.setOldStatus(existingReservation.getStatus());
        history.setNewStatus(reservation.getStatus());
        history.setModificationDate(existingReservation.getUpDate());
        history.setUserIdMod(existingReservation.getUsuarioId()); // Ajusta según sea necesario
        repositoryHistorialReservation.save(history);

        // Actualizar la entidad
        existingReservation.setCreatedAt(reservation.getCreatedAt());
        existingReservation.setStatus(reservation.getStatus());
        existingReservation.setPrice(reservation.getPrice());
        existingReservation.setStartDate(reservation.getStartDate());
        existingReservation.setUpDate(reservation.getUpDate());

        return repositoryReservation.save(existingReservation);
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
