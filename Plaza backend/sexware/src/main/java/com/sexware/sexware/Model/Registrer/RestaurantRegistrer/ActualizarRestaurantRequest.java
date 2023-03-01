package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ActualizarRestaurantRequest {

    private String nombre;
    private String email; //este es el email del nuevo propietario

}
