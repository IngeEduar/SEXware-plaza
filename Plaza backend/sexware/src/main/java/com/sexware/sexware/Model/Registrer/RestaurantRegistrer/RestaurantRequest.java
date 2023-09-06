package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class RestaurantRequest {

    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String urlLogo;
    private String admin;
    private String user;


}
