package com.sexware.sexware.Model.Login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDatosRespons {
    private String nombre;
    private String apellido;
    private String email;
    private Collection<?> authorities;
}
