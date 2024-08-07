package com.microservices.Reservations.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Reservations.persistence.entity.ReservationsHistoryEntity;
import com.microservices.Reservations.persistence.repository.RepositoryHistorialReservation;
import com.microservices.Reservations.persistence.repository.RepositoryReservation;

@Service
public class ServiceHistorReservations {
    @Autowired
    RepositoryHistorialReservation repositoryHistorialReservation;

    public Iterable<ReservationsHistoryEntity> getHisIterable() {
        return repositoryHistorialReservation.findAll();
    }

    public ReservationsHistoryEntity save(ReservationsHistoryEntity reservationsHistoryEntity) {
        return repositoryHistorialReservation.save(reservationsHistoryEntity);
    }
}
