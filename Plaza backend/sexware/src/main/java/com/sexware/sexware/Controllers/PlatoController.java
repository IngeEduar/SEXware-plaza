package com.sexware.sexware.Controllers;

import com.sexware.sexware.Model.Registrer.PlatoRegister.CrearPlatoRequest;
import com.sexware.sexware.Model.Registrer.PlatoRegister.CrearPlatoResponse;
import com.sexware.sexware.Services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plato")
@CrossOrigin("*")
public class PlatoController {

    @Autowired
    private PlatoService platoService;



    @PostMapping("/agregar")
    public CrearPlatoResponse agregarPlato(@RequestBody CrearPlatoRequest crearPlatoRequest){

        return platoService.crearPlato(crearPlatoRequest);

    }

}
