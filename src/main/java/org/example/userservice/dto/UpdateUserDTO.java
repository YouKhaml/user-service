package org.example.userservice.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.userservice.entities.AppRole;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
public class UpdateUserDTO {



    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private List<AppRole> roles;



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

    public List<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRole> roles) {
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
