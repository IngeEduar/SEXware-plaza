package com.sexware.sexware.Model.Registrer.UserRegistrer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 50,nullable = false)
    private String apellido;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(length = 13,nullable = false)
    private String celular;
    @Column(unique = true, length = 15, nullable = false)
    private String nIdentidad;
    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRoles> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.roles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getRolNombre()));
        });
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
