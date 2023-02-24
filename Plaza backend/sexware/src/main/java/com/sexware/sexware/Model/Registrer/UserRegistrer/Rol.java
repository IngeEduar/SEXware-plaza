package com.sexware.sexware.Model.Registrer.UserRegistrer;

import com.sexware.sexware.Model.Registrer.UserRegistrer.UsuarioRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "roles")
public class Rol {

    @Id
    private Long id;
    private String rolNombre;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRoles> usuarioRoles = new HashSet<>();

}
