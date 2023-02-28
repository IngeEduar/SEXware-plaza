package com.sexware.sexware.Model.Registrer.RestaurantRegistrer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ActualizarRestaurantRequest {

    private Long id;
    private String emailPropietario;

}
