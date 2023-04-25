package com.sexware.sexware.Model.ConfigUser;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;

import java.util.Comparator;

public class OrdenarRestaurant implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant p1, Restaurant p2) {
        return p1.getNombre().compareTo(p2.getNombre());
    }

}
