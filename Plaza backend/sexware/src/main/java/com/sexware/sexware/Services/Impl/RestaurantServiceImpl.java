package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.AuditoriaRepository;
import com.sexware.sexware.Repositorys.RestaurantRepository;
import com.sexware.sexware.Repositorys.UsuarioRepository;
import com.sexware.sexware.Services.RestaurantService;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuditoriaRepository auditoriaRepository;


    @Override
    public Restaurant guardarRestaurant(RestaurantRequest restaurantRequest) {

        String emailUser = restaurantRequest.getUser();
        String emailAdmin = restaurantRequest.getAdmin();

        Usuario usuario = usuarioService.obtenerUsuario(emailUser);
        Usuario usuarioAdmin = usuarioService.obtenerUsuario(emailAdmin);

        Restaurant restaurant = new Restaurant();
        restaurant.setNombre(restaurantRequest.getNombre());
        restaurant.setDireccion(restaurantRequest.getDireccion());
        restaurant.setNit(restaurantRequest.getNit());
        restaurant.setTelefono(restaurantRequest.getTelefono());
        restaurant.setUrlLogo(restaurantRequest.getLogo());
        restaurant.setUsuarioId(usuario);

        restaurantRepository.save(restaurant);

        String fecha = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());

        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(usuarioAdmin);
        auditoria.setTitulo("REGISTRO");
        auditoria.setDescripcion("Registro el restaurante "+restaurant.getNombre()+
                " Propietario: "+usuario.getNombre()+" "+usuario.getApellido()+
                " Documento: "+usuario.getNIdentidad());
        auditoria.setFecha(fecha+" "+hora);

        auditoriaRepository.save(auditoria);

        return restaurant;
    }

    @Override
    public Restaurant obtenerRestaurante(Long id) {
        return restaurantRepository.findByUsuarioId(id);
    }

    @Override
    public List<Restaurant> listarRestaurante() {
        return restaurantRepository.findAll();
    }

    @Override
    public String eliminarRestaurant(Long id) {

        try{
            restaurantRepository.deleteById(id);
            return "Restaurante eliminado correctamente";
        }catch (Exception e){
            e.printStackTrace();
            return "Error al eliminar el restaurante";
        }



    }

    @Override
    public Restaurant actualizarRestaurante(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}