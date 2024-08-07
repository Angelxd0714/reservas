package com.microservices.usersService.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private Boolean isEnabled;
    private String email;
    private String firstName;
    private String lastName;
    private Long identificacion;
}
