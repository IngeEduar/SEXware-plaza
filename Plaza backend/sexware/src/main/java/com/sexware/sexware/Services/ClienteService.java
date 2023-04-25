package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Peticiones.RegisterClienteRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;

import java.util.List;

public interface ClienteService {

    public void registrarCliente(RegisterClienteRequest clienteRequest);

    public List<Restaurant> listarRestaurantes();


}
