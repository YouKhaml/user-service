package org.example.userservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.userservice.entities.AppRole;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "le nom ne peut pas être vide")
    private String username;
    @NotBlank(message = "le mot de passe est obligatoire")
    private String password;
    private String nom;
    private String prenom;
    private String email;

    private List<AppRoleDTO> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRoleDTO> roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
