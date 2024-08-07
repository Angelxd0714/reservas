package com.microservices.Notes.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class servicesDto {
     private Long id;
 
    private String name;

    private String description;
  
    private Double price;

    private Boolean available;
  
    private String imageUrl;
    
    private List<categoriaDto> categories;
}
