package com.microservices.Reservations.utils;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.Reservations.persistence.entity.ReservationEntity;
import com.microservices.Reservations.persistence.entity.ReservationsHistoryEntity;
import com.microservices.Reservations.persistence.repository.RepositoryHistorialReservation;

import jakarta.persistence.PostUpdate;


@Component
public class ReservationEntityListener {
    @Autowired
    private  RepositoryHistorialReservation repositoryHistorialReservation;

 

    @PostUpdate
    public void onPostUpdate(ReservationEntity reservation) {
        try{
        ReservationsHistoryEntity history = new ReservationsHistoryEntity();
        history.setReservationId(reservation.getId());
        history.setOldStatus(reservation.getStatus()); // Puedes agregar lógica para obtener el estado antiguo si es necesario
        history.setNewStatus(reservation.getStatus()); // Este valor puede ser ajustado según la lógica del cambio
        history.setModificationDate(reservation.getUpDate());
        history.setUserIdMod(reservation.getUsuarioId()); // Ajusta según sea necesario para el usuario que realiza la modificación
        repositoryHistorialReservation.save(history);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
