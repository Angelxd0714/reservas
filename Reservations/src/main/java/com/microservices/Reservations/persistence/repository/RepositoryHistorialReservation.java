package com.microservices.Reservations.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.Reservations.persistence.entity.ReservationsHistoryEntity;

import jakarta.transaction.Transactional;

@Repository
public interface RepositoryHistorialReservation extends CrudRepository<ReservationsHistoryEntity,Long> {
    @Transactional
    public ReservationsHistoryEntity save(ReservationsHistoryEntity reservationsHistoryEntity);
}
