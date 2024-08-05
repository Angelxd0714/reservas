package com.microservices.auth.persistence.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record RequestLogin(@NotNull String username,@NotNull String password) {
}
