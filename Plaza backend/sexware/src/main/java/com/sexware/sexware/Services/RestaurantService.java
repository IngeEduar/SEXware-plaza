package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant guardarRestaurant(RestaurantRequest restaurantRequest);

    public Restaurant obtenerRestaurante(Long id);
    public List<Restaurant> listarRestaurante();

    public String eliminarRestaurant (Long id);

    public Restaurant actualizarRestaurante(Restaurant restaurant);

}
