package com.sexware.sexware.Repositorys;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
