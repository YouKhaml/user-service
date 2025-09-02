package org.example.userservice.services;


import org.example.userservice.dto.*;
import org.example.userservice.entities.AppRole;
import org.example.userservice.entities.User;
import org.example.userservice.exception.RoleAlreadyExistsException;
import org.example.userservice.exception.RoleNotFoundException;
import org.example.userservice.exception.UserAlreadyExistsException;
import org.example.userservice.exception.UserNotFoundException;
import org.example.userservice.mapper.UserMapper;
import org.example.userservice.repositories.AppRoleRepository;
import org.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final AppRoleRepository appRoleRepository;

   private final UserMapper userMapper;
   private final PasswordEncoder passwordEncoder;
   public UserServiceImpl(UserRepository userRepository, AppRoleRepository appRoleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
       this.userRepository = userRepository;
       this.appRoleRepository = appRoleRepository;
       this.userMapper = userMapper;
       this.passwordEncoder = passwordEncoder;
   }



    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Un utilisateur avec ce username existe déjà.");
        }
        User user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
       User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("utilisateur introuvable avec id " + id));

        return userMapper.toUserResponseDTO(user);
    }


    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur avec "+ username + " introuvable"));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
       try {
           return userRepository.findAll()
                   .stream()
                   .map(userMapper::toUserResponseDTO)
                   .collect(Collectors.toList());
       }catch (Exception e){
           throw new UserNotFoundException("erreur lors de la liste des users");
       }

   }
   // !! modifier le code ou cas de userDTO.getUsername() ou userDTO.getPassword() ou userDTO.getRole() est vide !!!!!!!!!!!!!
   @Override
   public UserResponseDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
       return userRepository.findById(id)
               .map(existingUser -> {
                   if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isBlank()) {
                       existingUser.setUsername(updateUserDTO.getUsername());
                   }

                   // Encode le mot de passe s'il est non vide
                   if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isBlank()) {
                       existingUser.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
                   }
                   if(updateUserDTO.getNom() != null && !updateUserDTO.getNom().isBlank()) {
                       existingUser.setNom(updateUserDTO.getNom());
                   }
                   if(updateUserDTO.getPrenom() != null && !updateUserDTO.getPrenom().isBlank()) {
                       existingUser.setPrenom(updateUserDTO.getPrenom());

                   }
                   if(updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isBlank()) {
                       existingUser.setEmail(updateUserDTO.getEmail());
                   }


                   User updatedUser = userRepository.save(existingUser);
                   return userMapper.toUserResponseDTO(updatedUser);
               })
               .orElseThrow(() -> new UserNotFoundException("Utilisateur avec ID " + id + " non trouvé"));
   }

    @Override
    public void  deleteUser(Long id) {
       User user = userRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException("Utilisateur avec ID " + id + " introuvable"));
       userRepository.delete(user);
    }

    @Override
    public AppRoleDTO createRole(AppRoleDTO appRoleDTO) {
        if(appRoleRepository.existsByRoleName(appRoleDTO.getRoleName())) {
            throw new RoleAlreadyExistsException("Un role avec ce nom existe déjà.");
        }
        AppRole appRole = userMapper.toAppRole(appRoleDTO);
        return userMapper.ToAppRoleDTO(appRoleRepository.save(appRole));
    }

    @Override
    public AppRoleDTO getRole(Long id) {
        return userMapper.ToAppRoleDTO(appRoleRepository.findById(id).orElseThrow(() ->new RoleNotFoundException("Role avec ID " + id + " introuvable")));
    }

    @Override
    public List<AppRoleDTO> getAllRoles() {
        try {
            return appRoleRepository.findAll().stream().map(userMapper::ToAppRoleDTO).collect(Collectors.toList());

        }catch (Exception e){
            throw new RoleNotFoundException("erreur lors de la liste des roles");
        }
    }

    @Override
    public AppRoleDTO updateRole(Long id, AppRoleDTO appRoleDTO) {
       AppRole role = appRoleRepository.findById(id).orElseThrow(()->new RoleNotFoundException("Role avec ID " + id + " introuvable"));
       role.setRoleName(appRoleDTO.getRoleName());
        return userMapper.ToAppRoleDTO(appRoleRepository.save(role)) ;
    }

    @Override
    public void deleteRole(Long id) {
        AppRole role = appRoleRepository.findById(id)
                .orElseThrow(()->new RoleNotFoundException("Role avec ID " + id + " introuvable"));
        appRoleRepository.delete(role);
    }

    @Override
    public void addRoleToUser(RoleToUserDTO roleToUserDto) {
        User user = userRepository.findById(roleToUserDto.getUserId()).orElseThrow(()->new UserNotFoundException("User " + roleToUserDto.getUserId() + " introuvable"));
        AppRole role = appRoleRepository.findByRoleName(roleToUserDto.getRoleName());
        user.getRoles().add(role);
        userRepository.save(user);
        System.out.println("Role " + role.getRoleName() + " added to user " + user.getId());
    }


}
