package com.sexware.sexware.Model.Registrer.PlatoRegister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CrearPlatoRequest {

    private String nombre;
    private String descripcion;
    private String precio;
    private String img;
    private String categoria;
    private String logeado;
    private String restaurante; // Me envia el nombre del restaurante al que se le va a agregar el plato

}
