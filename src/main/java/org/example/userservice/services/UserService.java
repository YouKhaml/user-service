package org.example.userservice.services;


import org.example.userservice.dto.*;
import org.example.userservice.entities.AppRole;
import org.example.userservice.entities.User;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);
    UserResponseDTO getUser(Long id);
    UserResponseDTO getUserByUsername(String Username);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UpdateUserDTO updateUserDTO );
    void deleteUser(Long id);
    AppRoleDTO createRole(AppRoleDTO appRoleDTO);
    AppRoleDTO getRole(Long id);
    List<AppRoleDTO> getAllRoles();
    AppRoleDTO updateRole(Long id, AppRoleDTO AppRoleDTO);
    void deleteRole(Long id);
    void addRoleToUser(RoleToUserDTO roleToUserDto);
}
