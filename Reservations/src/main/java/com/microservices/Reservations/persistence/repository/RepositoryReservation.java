package com.microservices.Reservations.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.Reservations.persistence.entity.ReservationEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface RepositoryReservation extends CrudRepository<ReservationEntity,Long> {
     Optional<ReservationEntity> findById(Long id);
     Iterable<ReservationEntity> findAll();
     ReservationEntity save(ReservationEntity reservationEntity);
     void deleteById(Long id);
     ReservationEntity findByStatus(String status);
     ReservationEntity findByStartDate(Date startDate);
     ReservationEntity findByCreatedAt(Timestamp timestamp);
     ReservationEntity findByServicioId(Long servicioId);
   
}
