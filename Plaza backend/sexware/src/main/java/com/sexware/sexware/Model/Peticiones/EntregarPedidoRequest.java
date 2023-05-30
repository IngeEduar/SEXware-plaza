package com.sexware.sexware.Model.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class EntregarPedidoRequest {

    private int numeroP; // numero del pedido
    private String codigo;

}
