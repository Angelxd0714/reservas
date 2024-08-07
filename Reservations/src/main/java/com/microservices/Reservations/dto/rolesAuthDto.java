package com.microservices.Reservations.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class rolesAuthDto {
    private Long id;

    private String roleEnum;

    private Set<permissionAuthDto> permissionList;
}
