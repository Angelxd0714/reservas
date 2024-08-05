package com.microservices.auth;

import com.microservices.auth.persistence.Repository.RepositoryPermissions;
import com.microservices.auth.persistence.Repository.RepositoryRoles;
import com.microservices.auth.persistence.Repository.RepositoryUsers;
import com.microservices.auth.persistence.entity.PermissionEntity;
import com.microservices.auth.persistence.entity.RolEntity;
import com.microservices.auth.persistence.entity.RolEnum;
import com.microservices.auth.persistence.entity.UserEntity;
import com.microservices.auth.persistence.services.ServicePermission;
import com.microservices.auth.persistence.services.ServiceRol;
import com.microservices.auth.persistence.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;


@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {
    @Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
	@Bean
    CommandLineRunner init(RepositoryPermissions servicePermission, RepositoryRoles serviceRol, RepositoryUsers serviceUser) {
        return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			/* Create ROLES */
			RolEntity roleAdmin = RolEntity.builder()
					.roleEnum(RolEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RolEntity roleUser = RolEntity.builder()
					.roleEnum(RolEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();
            String passwordAdmin = "admin";

			UserEntity userAdmin = UserEntity.builder()
					.username("admin")
					.password(passwordEncoder.encode(passwordAdmin))
					.roles(Set.of(roleAdmin))
					.isEnabled(true)
					.build();

			UserEntity userUser = UserEntity.builder()
					.username("user")
					.password("user")
					.roles(Set.of(roleUser))
					.isEnabled(true)
					.build();


			serviceUser.saveAll(List.of(userUser,userAdmin));

        };
    }
}
