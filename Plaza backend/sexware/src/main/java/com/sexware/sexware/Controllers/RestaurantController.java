package com.sexware.sexware.Controllers;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;
import com.sexware.sexware.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/guardar")
    public Restaurant guardarRestaurant(@RequestBody RestaurantRequest restaurantRequest){

        return restaurantService.guardarRestaurant(restaurantRequest);

    }

}
