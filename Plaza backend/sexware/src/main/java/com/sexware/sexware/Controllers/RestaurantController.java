package com.sexware.sexware.Controllers;

import com.google.gson.Gson;
import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.ActualizarRestaurantRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRegisterRespons;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.RestaurantRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.RestaurantRepository;
import com.sexware.sexware.SaveImage.FileUploadUtil;
import com.sexware.sexware.SaveImage.MyController;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.RestaurantService;
import com.sexware.sexware.Services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/guardar/{admin}")
    public ResponseEntity<?> guardarRestaurant(@PathVariable("admin")String admin,@RequestParam("restaurante") String strRestaurant, @RequestParam("img")MultipartFile img){

        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
            System.out.println(fileName);
            String dirFile = "images/restaurante";
            fileName = "R"+fileName;

            Gson gson = new Gson();
            RestaurantRequest restaurantRequest = gson.fromJson(strRestaurant, RestaurantRequest.class);
            restaurantRequest.setUrlLogo(fileName);

            Restaurant rest = restaurantService.guardarRestaurant(restaurantRequest, admin);
            FileUploadUtil.saveFile(dirFile,fileName,img);

            return new ResponseEntity<>(new RestaurantRegisterRespons(rest.getNombre(),
                    rest.getNit(),
                    rest.getUsuarioId().getNombre()),
                    HttpStatus.OK);
        }catch (MyException ex){
            throw ex;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al crear restaurante, No eres Admin"),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar-sin/{admin}")
    public Restaurant guardarSinImagen(@RequestBody RestaurantRequest restaurantRequest,@PathVariable("admin") String admin) throws Exception {

        restaurantRequest.setUrlLogo("default.jpg");
        return restaurantService.guardarRestaurant(restaurantRequest, admin);
    }

    @GetMapping("/listar")
    public List<Restaurant> listarRestaurantes(){
       return restaurantService.listarRestaurante();
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarRestaurante(@PathVariable("id")Long id){

        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);

            String rest = restaurantService.eliminarRestaurant(id);
            MyController.eliminarIMG(restaurant.get().getUrlLogo());

            return rest;
        }catch (Exception e){
            e.printStackTrace();
            return "ERROR AL ELIMAR EL RESTAURANTE";
        }
    }

    /*
    @PostMapping("/actualizar")
    public Restaurant actualizarRestaurante(@RequestBody ActualizarRestaurantRequest request){

        Restaurant rest = restaurantService.obtenerRestaurante(request.getNombre());
        Usuario usuario = usuarioService.obtenerUsuario(request.getEmail());

        rest.setUsuarioId(usuario);

        return restaurantService.actualizarRestaurante(rest);
    }*/


    @GetMapping("/lista-propietario/{correo}")
    public List<Restaurant> listarRest(@PathVariable("correo") String correo){

        return restaurantService.listaRestaurantPropietario(correo);
    }
}
