package org.example.userservice.mapper;

import org.example.userservice.dto.AppRoleDTO;
import org.example.userservice.dto.UpdateUserDTO;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.dto.UserResponseDTO;
import org.example.userservice.entities.AppRole;
import org.example.userservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);
    UserResponseDTO toUserResponseDTO(User user);
    User ToUserUpdate(UpdateUserDTO updateUserDTO);
    AppRole toAppRole(AppRoleDTO appRoleDTO);
    AppRoleDTO ToAppRoleDTO(AppRole appRole);

}
