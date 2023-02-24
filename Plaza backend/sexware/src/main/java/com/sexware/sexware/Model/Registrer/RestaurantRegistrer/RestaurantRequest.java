package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RestaurantRequest {

    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String logo;
    private String admin;
    private String user;


}
