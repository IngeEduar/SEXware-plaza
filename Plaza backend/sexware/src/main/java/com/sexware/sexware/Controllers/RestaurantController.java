package com.sexware.sexware.Controllers;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.ActualizarRestaurantRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Services.RestaurantService;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/guardar")
    public Restaurant guardarRestaurant(@RequestBody RestaurantRequest restaurantRequest){

        return restaurantService.guardarRestaurant(restaurantRequest);

    }

    @GetMapping("/listar")
    public List<Restaurant> listarRestaurantes(){
       return restaurantService.listarRestaurante();
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarRestaurante(@PathVariable("id")Long id){

        return restaurantService.eliminarRestaurant(id);

    }

    @PostMapping("/actualizar")
    public Restaurant actualizarRestaurante(@RequestBody ActualizarRestaurantRequest request){

        Restaurant rest = restaurantService.obtenerRestaurante(request.getId());
        Usuario usuario = usuarioService.obtenerUsuario(request.getEmailPropietario());

        rest.setUsuarioId(usuario);

        return restaurantService.actualizarRestaurante(rest);
    }

}
