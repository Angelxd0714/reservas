package com.microservices.Services.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "services")
@NoArgsConstructor
@Data
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name ="description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "available")
    private Boolean available;
    @Column(name="imagen_url")
    private String imageUrl;
    @ManyToMany
    @JoinTable(name = "service_category", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories;
    
}
