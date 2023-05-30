package com.sexware.sexware.Controllers;

import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.Model.Peticiones.RegisterEmpleadoRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.RestaurantService;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEmpleado(@RequestBody RegisterEmpleadoRequest empleadoRequest){

        Rol rol = new Rol();
        rol.setId(3L);
        rol.setRolNombre("EMPLEADO");

        try{

            usuarioService.guardarEmpleado(empleadoRequest,rol);

            return new ResponseEntity<>(new Mensaje("Empleado guardado"), HttpStatus.OK);

        }catch (MyException ex){
            throw ex;
        }
        catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al guardar el empleado"), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/listar/{rest}")
    public ResponseEntity<?> listarEmpleado(@PathVariable("rest") String rest) {

        try {

            return new ResponseEntity<>(usuarioService.listarEmpleados(rest), HttpStatus.OK);

        }catch (MyException ex){
            throw ex;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error"), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/listar-pedidos/{rest}/{estado}")
    public ResponseEntity<?> listarPedidosDelRestaurante(@PathVariable("rest")String nombreRest,
                                                         @PathVariable("estado")String estado){

        return new ResponseEntity<>(restaurantService.listarPedidosDelRest(nombreRest, estado), HttpStatus.OK);

    }

    @PostMapping("/asignar-pedido/{nPedido}/{empleado}")
    public ResponseEntity<?> asignarmePedido (@PathVariable("nPedido")int numeroP,
                                              @PathVariable("empleado") String email){

        return new ResponseEntity<>(restaurantService.asignarmePedido(numeroP, email), HttpStatus.OK);
    }

    @GetMapping("/pedidos-asignados/{email}")
    public ResponseEntity<?> listarPedidosAsign(@PathVariable("email")String email){

        return new ResponseEntity<>(restaurantService.listarPedidosEmpleado(email),HttpStatus.OK);

    }

}
