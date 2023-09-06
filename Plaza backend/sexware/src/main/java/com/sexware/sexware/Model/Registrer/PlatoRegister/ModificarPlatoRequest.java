package com.sexware.sexware.Model.Registrer.PlatoRegister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ModificarPlatoRequest {

    private String descripcion;
    private String precio;
    private String nombre; // Este es el nombre del plato que va a modificar
    private String logeado; // correo de la persona que esta logeada
    private String restaurante; // Me envia el nombre del restaurante al que se le va amodificar el plato

}
