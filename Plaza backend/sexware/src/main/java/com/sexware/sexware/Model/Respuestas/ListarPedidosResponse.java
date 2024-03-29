package com.sexware.sexware.Model.Respuestas;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.DetallePedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ListarPedidosResponse {

    private int numeroP;
    private String nombreCliente;
    private String email; //email cliente
    private String estado;
}
