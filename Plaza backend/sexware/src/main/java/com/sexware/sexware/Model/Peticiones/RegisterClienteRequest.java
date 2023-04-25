package com.sexware.sexware.Model.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RegisterClienteRequest implements Serializable {

    private static final long serialVersionUID=1L;

    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String cedula;
    private String password;


}
