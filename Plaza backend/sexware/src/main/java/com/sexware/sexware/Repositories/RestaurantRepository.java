package com.sexware.sexware.Repositories;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {


    public Restaurant findByNombre(String nombre);

    @Query(nativeQuery = true, value = "SELECT * FROM restaurantes ORDER BY nombre ASC")
    public List<Restaurant> restaurantesOrdenados();

}
