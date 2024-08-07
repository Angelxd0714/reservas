package com.microservices.Notes.http.response;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewResponse {
    private Long id;
    private Long reservaId;
    private String comentario;
     private Date startDate;
    private String firstName;
    private String lastName;
    private String name;
    private Timestamp createdAt;
    private Double price;
}
