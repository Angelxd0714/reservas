package com.microservices.usersService.persistence.entity;

import java.sql.Date;

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
@Builder
@Entity
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
public class UsersEntity{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column(name="identificacion",unique = true)
   private Long identificacion;
   @Column(name = "first_name")
   private String firstName;
   @Column(name = "last_name")
   private String lastName;
   @Column(name = "email")
   private String email;
   @Column(name = "role")
   private String role;
   @Column(name="phone")
   private String phone;
   @Column(name="created_at")
   private Date createdAt;
   @Column(name="updated_at")
   private Date updatedAt;
}