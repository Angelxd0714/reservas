package com.microservices.Notes.dto;


import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class authDto {

    private Long id;

    private String username;
  

    private boolean isEnabled;

    private boolean accountNoExpired;

    private boolean accountNoLocked;

    private boolean credentialNoExpired;
    
    private Set<rolesAuthDto> roles;
    
}
