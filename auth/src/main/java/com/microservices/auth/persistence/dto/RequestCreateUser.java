package com.microservices.auth.persistence.dto;

import jakarta.validation.constraints.NotNull;

public record RequestCreateUser(@NotNull String username,@NotNull String password,@NotNull RequestCreateRol roles) {
}
