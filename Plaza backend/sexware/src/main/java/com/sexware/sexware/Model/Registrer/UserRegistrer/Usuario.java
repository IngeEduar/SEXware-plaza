package com.sexware.sexware.Model.Registrer.UserRegistrer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 50,nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String email;
    @Column(length = 13,nullable = false)
    private String celular;
    @Column(length = 15, nullable = false)
    private String cedula;
    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Rol roles;

    private boolean activo = true;

    private String tokenPassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> autoridades = new ArrayList<>();
        autoridades.add(new SimpleGrantedAuthority(roles.getRolNombre()));
        return autoridades;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
