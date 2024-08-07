package com.microservices.Reservations.persistence.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="reservations")
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id", nullable=false)
    private Long usuarioId;
    @Column(name="service_id", nullable=false)
    private Long servicioId;
    @Column(name="reservation_date", nullable=false)
    private Timestamp createdAt;
    @Column(name="reservation_status", nullable=false)
    private String status;
    @Column(name="reservation_price", nullable=false)
    private Double price;
    @Column(name="reservation_start_date", nullable=false)
    private Date startDate;
    @Column(name="reservation_update_date", nullable=false)
    private Date upDate;



}
