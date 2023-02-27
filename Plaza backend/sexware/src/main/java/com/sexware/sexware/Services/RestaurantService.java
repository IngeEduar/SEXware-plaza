package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant guardarRestaurant(RestaurantRequest restaurantRequest);

    public List<Restaurant> listarRestaurantes();

}
