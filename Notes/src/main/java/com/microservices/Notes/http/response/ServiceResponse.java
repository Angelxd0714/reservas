package com.microservices.Notes.http.response;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.microservices.Notes.dto.categoriaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceResponse {
    private Long id;
    private Long serviceId;
    private Long usuarioId;
    private String name;
    private Timestamp createdAt;
    private String description;
    private String status;
    private Double price;
    private Date startDate;
    private Boolean available;
    private Date upDate;
    private String imageUrl;
    private String email;
    private String firstName;
    private String lastName;
    private Long identificacion;
    private String role;
    private String phone;
    private List<categoriaDto> categories;
}
