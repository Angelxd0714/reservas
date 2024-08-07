package com.microservices.auth.persistence.services;

import com.microservices.auth.persistence.Repository.RepositoryPermissions;
import com.microservices.auth.persistence.Repository.RepositoryRoles;
import com.microservices.auth.persistence.Repository.RepositoryUsers;
import com.microservices.auth.persistence.entity.PermissionEntity;
import com.microservices.auth.persistence.entity.RolEntity;
import com.microservices.auth.persistence.entity.UserEntity;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceUser {
  @Autowired
  private RepositoryUsers repositoryUsers;

  @Autowired
  private RepositoryRoles repositoryRoles;

  @Autowired
  private RepositoryPermissions permissionRepository;

  public ServiceUser(RepositoryUsers repositoryUsers) {
    this.repositoryUsers = repositoryUsers;
  }

  public Optional<UserEntity> findByUsername(String username) {
    return repositoryUsers.findByUsername(username);
  }

  public UserEntity save(UserEntity user) {
    return repositoryUsers.save(user);
  }

  public Optional<UserEntity> findById(Long id) {
    return repositoryUsers.findById(id);
  }

  public boolean existsByUsername(String username) {
    return repositoryUsers.existsByUsername(username);
  }

  public boolean existsById(Long id) {
    return repositoryUsers.existsById(id);
  }

  public void deleteById(Long id) {
    repositoryUsers.deleteById(id);
  }

  public void updateUser(long id, UserEntity user) {
    repositoryUsers.findById(id)
        .map(existingUser -> {
          // Actualiza información básica del usuario
          existingUser.setUsername(user.getUsername());
          existingUser.setPassword(user.getPassword());

          // Actualiza roles y permisos
          Set<RolEntity> updatedRoles = user.getRoles().stream()
              .map(this::updateOrCreateRole)
              .collect(Collectors.toSet());

          existingUser.setRoles(updatedRoles);

          return repositoryUsers.save(existingUser);
        })
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
  }

  public Iterable<UserEntity> findAll() {
    return repositoryUsers.findAll();
  }

  private RolEntity updateOrCreateRole(RolEntity role) {
    RolEntity existingRole = repositoryRoles.findRoleEntityByRoleEnum(role.getRoleEnum());
    if (existingRole == null) {
      return role; // Rol no existe, lo creamos y lo retornamos
    }
    // Actualiza permisos
    existingRole.setPermissionList(updateOrCreatePermissions(role.getPermissionList()));
    return existingRole;
  }

  private Set<PermissionEntity> updateOrCreatePermissions(Set<PermissionEntity> permissions) {
    return permissions.stream()
        .map(this::updateOrCreatePermission)
        .collect(Collectors.toSet());
  }

  private PermissionEntity updateOrCreatePermission(PermissionEntity permission) {
    PermissionEntity existingPermission = permissionRepository.findByName(permission.getName());
    if (existingPermission == null) {
      return permissionRepository.save(permission); // Crea y guarda nuevo permiso
    }
    return existingPermission; // Retorna permiso existente
  }
}
