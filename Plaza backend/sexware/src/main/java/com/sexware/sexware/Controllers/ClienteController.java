package com.sexware.sexware.Controllers;

import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.Model.Peticiones.RealizarPedidoRequest;
import com.sexware.sexware.Model.Peticiones.RegisterClienteRequest;
import com.sexware.sexware.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente (@RequestBody RegisterClienteRequest clienteRequest){

        try{
            clienteService.registrarCliente(clienteRequest);

            return new ResponseEntity<>(new Mensaje("Registro exitoso"), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<>(new Mensaje("Error al registrar"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/listar-restaurantes")
    public ResponseEntity<?> listarRestaurantes (){

        return new ResponseEntity<>(clienteService.listarRestaurantes(), HttpStatus.OK);

    }

    @GetMapping("/listar-platos/{nombreRest}")
    public ResponseEntity<?> listarPlatosDelRestaurante (@PathVariable("nombreRest") String nombreRest){

        return new ResponseEntity<>(clienteService.listarPlatoRest(nombreRest), HttpStatus.OK);

    }

    @GetMapping("/listar-platos/{nombreRest}/{categoria}")
    public ResponseEntity<?> listarPlatosCategoria (@PathVariable("nombreRest")String nombreRest,
                                                    @PathVariable("categoria")String categoria){

        return new ResponseEntity<>(clienteService.listarPlatoCategoria(nombreRest, categoria), HttpStatus.OK);

    }

    @PostMapping("/realizar-pedido/{nombreRest}/{email}")
    public ResponseEntity<?> realizarPedido (@PathVariable("nombreRest") String nombreRest,
                                             @PathVariable("email") String email,
                                             @RequestBody RealizarPedidoRequest[] pedidoRequests){

        return new ResponseEntity<>(clienteService.realizarPedido(nombreRest, email, pedidoRequests), HttpStatus.OK);

    }

}
