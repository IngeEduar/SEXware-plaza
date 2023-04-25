package com.sexware.sexware.Controllers;

import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
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


}
