package org.example.userservice.Controllers;

import jakarta.validation.Valid;
import org.example.userservice.dto.RoleToUserDTO;
import org.example.userservice.dto.UpdateUserDTO;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.dto.UserResponseDTO;
import org.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDTO>  createUser(@Valid  @RequestBody UserDTO userDTO) {
        UserResponseDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUser(id);

        if (userResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // ou message d’erreur personnalisé
        }

        return ResponseEntity.ok(userResponseDTO);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        UserResponseDTO userResponseDTO = userService.getUserByUsername(username);

        if (userResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // ou message d’erreur personnalisé
        }

        return ResponseEntity.ok(userResponseDTO);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(users);
    }

    @PutMapping("/update_user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDTO updateUserDTO) {

        UserResponseDTO updatedUser = userService.updateUser(id, updateUserDTO);

        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/roleToUser")
    public ResponseEntity<Void> AddRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        userService.addRoleToUser(roleToUserDTO);
        return ResponseEntity.noContent().build();
    }


}