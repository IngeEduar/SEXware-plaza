package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant guardarRestaurant(RestaurantRequest restaurantRequest, String admin) throws Exception;

    public Restaurant obtenerRestaurante(String nombre);
    public List<Restaurant> listarRestaurante();
    public List<Restaurant> listaRestaurantPropietario(String correo);
    public String eliminarRestaurant (Long id);

    public Restaurant actualizarRestaurante(Restaurant restaurant);

}
