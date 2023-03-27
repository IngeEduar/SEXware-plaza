package com.sexware.sexware.Controllers;

import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.Model.Peticiones.RegisterEmpleadoRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.UserRegisterRespons;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Model.Registrer.UserRegistrer.UsuarioEmpleado;
import com.sexware.sexware.Repositorys.RestaurantRepository;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEmpleado(@RequestBody RegisterEmpleadoRequest empleadoRequest){

        Rol rol = new Rol();
        rol.setId(3L);
        rol.setRolNombre("EMPLEADO");

        try{

            usuarioService.guardarEmpleado(empleadoRequest,rol);

            return new ResponseEntity<>(new Mensaje("Empleado guardado"), HttpStatus.OK);

        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al guardar el empleado"), HttpStatus.NOT_FOUND);
        }

    }

}
