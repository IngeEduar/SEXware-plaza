package com.sexware.sexware.Model.Registrer.UserRegistrer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "roles")
public class Rol implements Serializable {

    @Id
    private Long id;
    private String rolNombre;

}
