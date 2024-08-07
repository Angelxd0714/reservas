package com.microservices.Notes.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class reservationDto {
     private Long id;
     private Timestamp createdAt;
     private Double price;
}
