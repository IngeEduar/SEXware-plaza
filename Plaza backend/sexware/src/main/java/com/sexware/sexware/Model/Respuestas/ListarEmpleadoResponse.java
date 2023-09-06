package com.sexware.sexware.Model.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ListarEmpleadoResponse {

    private String nombre;
    private String apellido;
    private String email;
    private String rol;
}
