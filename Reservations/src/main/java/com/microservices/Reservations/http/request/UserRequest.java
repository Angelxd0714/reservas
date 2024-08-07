package com.microservices.Reservations.http.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.Set;

import com.microservices.Reservations.dto.rolesAuthDto;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private Long identificacion;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private String phone;

    private Date createdAt;

    private Date updatedAt;
    private String username;
    private String password;

    private boolean isEnabled;

    private boolean accountNoExpired;

    private boolean accountNoLocked;

    private boolean credentialNoExpired;

    private Set<rolesAuthDto> roles;
}
