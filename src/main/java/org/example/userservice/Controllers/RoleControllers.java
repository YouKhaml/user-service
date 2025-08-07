package org.example.userservice.Controllers;

import jakarta.validation.Valid;
import org.example.userservice.dto.AppRoleDTO;
import org.example.userservice.entities.AppRole;
import org.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleControllers {
    private UserService userService;


    public RoleControllers(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/createRole")
    @PreAuthorize("hasAuthority('Admin,SuperAdmin')")
    public ResponseEntity<AppRoleDTO> createRole(@Valid @RequestBody AppRoleDTO role) {
        AppRoleDTO createdRole = userService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }
    @GetMapping("/role/{id}")
    @PreAuthorize("hasAuthority('Admin,SuperAdmin')")
    public ResponseEntity<AppRoleDTO> getRole(@PathVariable Long id) {
        AppRoleDTO role = userService.getRole(id);

        if (role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // ou message d’erreur personnalisé
        }

        return ResponseEntity.ok(role);
    }
    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('Admin,SuperAdmin')")
    public ResponseEntity<?> getAllRoles() {
        List<AppRoleDTO> roles = userService.getAllRoles();

        if (roles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(roles);
    }

    @PutMapping("/updateRole/{id}")
    @PreAuthorize("hasAuthority('Admin,SuperAdmin')")
    public ResponseEntity<AppRoleDTO> updateRole(@PathVariable Long id, @Valid @RequestBody AppRoleDTO role) {
        AppRoleDTO updatedRole = userService.updateRole(id, role);
        if (updatedRole == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/deleteRole/{id}")
    @PreAuthorize("hasAuthority('Admin,SuperAdmin')")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        userService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
