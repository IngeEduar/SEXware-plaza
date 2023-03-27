package com.sexware.sexware.Model.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RegisterEmpleadoRequest {

    private String nombre;
    private String apellido;
    private String cedula;
    private String celular;
    private String email;
    private String password;
    private String nombreRestaurante; //nombre del restaurante donde se registrara el empleado
    private String propietario; //email del propietario del restaurante

}
