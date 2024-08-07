package com.microservices.Notes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoriaDto {
    private Long id;
    private String name;
    private String imagenUrl;
}
