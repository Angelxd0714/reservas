package com.microservices.auth.persistence.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record RequestCreateRol(@Size(message = "maximo dos roles")List<String>roles) {
}
