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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        List<Usuario> usuario = usuarioService.listarUsuario();
        Usuario userP = null;

        Usuario userA = null;
        for (Usuario user:usuario){
            if (user.getRoles().getRolNombre().equals("PROPIETARIO")&&
                    Objects.equals(user.getEmail(), emailUser)){
                userP = user;
            }
        }
        for (Usuario user:usuario){
            if (user.getRoles().getRolNombre().equals("ADMIN")&&
                    Objects.equals(user.getEmail(), emailAdmin)){
                userA = user;
            }
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setNombre(restaurantRequest.getNombre());
        restaurant.setDireccion(restaurantRequest.getDireccion());
        restaurant.setNit(restaurantRequest.getNit());
        restaurant.setTelefono(restaurantRequest.getTelefono());
        restaurant.setUrlLogo(restaurantRequest.getUrlLogo());
        restaurant.setUsuarioId(userP);

        restaurantRepository.save(restaurant);

        String fecha = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());

        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(userA);
        auditoria.setTitulo("REGISTRO");
        assert userP != null;
        auditoria.setDescripcion("Registro el restaurante "+restaurant.getNombre()+
                " Propietario: "+userP.getNombre()+" "+userP.getApellido()+
                " Documento: "+userP.getCedula());
        auditoria.setFecha(fecha+" "+hora);

        auditoriaRepository.save(auditoria);

        return restaurant;
    }

    @Override
    public Restaurant obtenerRestaurante(String nombre) {
        return restaurantRepository.findByNombre(nombre);
    }

    @Override
    public List<Restaurant> listarRestaurante() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> listaRestaurantPropietario(String correo) {


        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<Restaurant> restaurantList = new ArrayList<>();

        for(Restaurant rest:restaurants){
            if (Objects.equals(rest.getUsuarioId().getEmail(), correo)){
                restaurantList.add(rest);
            }
        }

        return restaurantList;
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
