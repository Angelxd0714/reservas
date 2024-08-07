package com.microservices.Reservations.persistence.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Reservations.client.ReservationClient;
import com.microservices.Reservations.client.clientReservation;
import com.microservices.Reservations.dto.authDto;
import com.microservices.Reservations.dto.servicesDto;
import com.microservices.Reservations.http.response.ServiceResponse;
import com.microservices.Reservations.http.response.UserResponse;
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
    @Autowired
    private ReservationClient reservationClient;
    @Autowired
    private clientReservation serviceClientReservation;

    public Iterable<ServiceResponse> getAllReservations() {
         List<ReservationEntity> reservations = (List<ReservationEntity>) repositoryReservation.findAll();
         Iterable<ServiceResponse> response = reservations.stream().map(reservation -> {
            servicesDto service = reservationClient.searchId(reservation.getServicioId());
            UserResponse user = serviceClientReservation.getAuth(reservation.getUsuarioId());
            return ServiceResponse.builder().id(reservation.getId()).status(reservation.getStatus()).categories(service.getCategories()).lastName(user.getLastName()).role(user.getRole()).phone(user.getPhone()).email(user.getEmail()).identificacion(user.getIdentificacion()).role(user.getRole()).phone(user.getPhone()).firstName(user.getFirstName()).upDate(reservation.getUpDate()).serviceId(service.getId()).description(service.getDescription()).createdAt(reservation.getCreatedAt()).startDate(reservation.getStartDate()).usuarioId(reservation.getUsuarioId()).imageUrl(service.getImageUrl()).price(reservation.getPrice()).name(service.getName()).available(service.getAvailable()).build();
        }).toList();
        return response;
     
    }
    public ServiceResponse getReservationById(Long id) {
         ReservationEntity reservation = repositoryReservation.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
                servicesDto service = reservationClient.searchId(reservation.getServicioId());
                UserResponse user = serviceClientReservation.getAuth(reservation.getUsuarioId());
                return ServiceResponse.builder().id(reservation.getId()).status(reservation.getStatus()).categories(service.getCategories()).lastName(user.getLastName()).role(user.getRole()).phone(user.getPhone()).email(user.getEmail()).identificacion(user.getIdentificacion()).role(user.getRole()).phone(user.getPhone()).firstName(user.getFirstName()).upDate(reservation.getUpDate()).serviceId(service.getId()).description(service.getDescription()).createdAt(reservation.getCreatedAt()).startDate(reservation.getStartDate()).usuarioId(reservation.getUsuarioId()).imageUrl(service.getImageUrl()).price(reservation.getPrice()).name(service.getName()).available(service.getAvailable()).build();
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
    public ServiceResponse findByCreatedAt(Timestamp timestamp) {
        ReservationEntity reservation = repositoryReservation.findByCreatedAt(timestamp);
        servicesDto service = reservationClient.searchId(reservation.getServicioId());
        UserResponse user = serviceClientReservation.getAuth(reservation.getUsuarioId());
        return ServiceResponse.builder().id(reservation.getId()).status(reservation.getStatus()).categories(service.getCategories()).lastName(user.getLastName()).role(user.getRole()).phone(user.getPhone()).email(user.getEmail()).identificacion(user.getIdentificacion()).role(user.getRole()).phone(user.getPhone()).firstName(user.getFirstName()).upDate(reservation.getUpDate()).serviceId(service.getId()).description(service.getDescription()).createdAt(reservation.getCreatedAt()).startDate(reservation.getStartDate()).usuarioId(reservation.getUsuarioId()).imageUrl(service.getImageUrl()).price(reservation.getPrice()).name(service.getName()).available(service.getAvailable()).build();
    }

    public ServiceResponse findReservationStatus(String status) {
        ReservationEntity reservation = repositoryReservation.findByStatus(status);
        servicesDto service = reservationClient.searchId(reservation.getServicioId());
        UserResponse user = serviceClientReservation.getAuth(reservation.getUsuarioId());
        return ServiceResponse.builder().id(reservation.getId()).status(reservation.getStatus()).categories(service.getCategories()).lastName(user.getLastName()).role(user.getRole()).phone(user.getPhone()).email(user.getEmail()).identificacion(user.getIdentificacion()).role(user.getRole()).phone(user.getPhone()).firstName(user.getFirstName()).upDate(reservation.getUpDate()).serviceId(service.getId()).description(service.getDescription()).createdAt(reservation.getCreatedAt()).startDate(reservation.getStartDate()).usuarioId(reservation.getUsuarioId()).imageUrl(service.getImageUrl()).price(reservation.getPrice()).name(service.getName()).available(service.getAvailable()).build();
    }
    public ServiceResponse getReservationsByServiceId(Long id) {
       Optional<ReservationEntity> response = repositoryReservation.findById(id);
        servicesDto service = reservationClient.searchId(id);
        UserResponse user = serviceClientReservation.getAuth(response.get().getUsuarioId());
        return ServiceResponse.builder().id(response.get().getId()).status(response.get().getStatus()).categories(service.getCategories()).lastName(user.getLastName()).role(user.getRole()).phone(user.getPhone()).email(user.getEmail()).identificacion(user.getIdentificacion()).role(user.getRole()).phone(user.getPhone()).firstName(user.getFirstName()).upDate(response.get().getUpDate()).serviceId(service.getId()).description(service.getDescription()).createdAt(response.get().getCreatedAt()).startDate(response.get().getStartDate()).usuarioId(response.get().getUsuarioId()).imageUrl(service.getImageUrl()).price(response.get().getPrice()).name(service.getName()).available(service.getAvailable()).build();
    }

}
