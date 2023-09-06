package com.sexware.sexware.Model.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PlatoModEstado {
    private String nombreRest;
    private String nombrePlato;
    private boolean estado;
    private String userLogeado;

}
