package com.microservices.Notes.http.response;


import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

import com.microservices.Notes.dto.rolesAuthDto;




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
    private String role;

    private String phone;

    private Date createdAt;

    private Date updatedAt;

    private Set<rolesAuthDto> roles;

}
