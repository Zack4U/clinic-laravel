package com.clinica.demo.model.usuarios;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Debes devolver una colección de roles aquí.
        // Puedes convertir tu Set<Role> a una colección de GrantedAuthority.
        return null;
    }

    @Override
    public String getPassword() {
        // Devuelve la contraseña aquí.
        return null;
    }

    @Override
    public String getUsername() {
        // Devuelve el nombre de usuario aquí.
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Devuelve true si la cuenta no ha expirado.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Devuelve true si la cuenta no está bloqueada.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Devuelve true si las credenciales no han expirado.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Devuelve true si el usuario está habilitado.
        return true;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        // Establece la contraseña aquí.
    }

    public Role getRole() {
        // Devuelve la contraseña aquí.
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}