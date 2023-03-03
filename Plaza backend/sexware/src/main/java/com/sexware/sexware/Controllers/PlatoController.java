package com.sexware.sexware.Controllers;

import com.sexware.sexware.Model.Registrer.PlatoRegister.AgregarCategoriaRequest;
import com.sexware.sexware.Model.Registrer.PlatoRegister.Categoria;
import com.sexware.sexware.Model.Registrer.PlatoRegister.CrearPlatoRequest;
import com.sexware.sexware.Model.Registrer.PlatoRegister.CrearPlatoResponse;
import com.sexware.sexware.Services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Metodos para controlar categorias
    @PostMapping("/agregar-categoria")
    public Categoria agregarCategoria(@RequestBody AgregarCategoriaRequest categoriaRequest){
        return platoService.agregarCategoria(categoriaRequest);
    }
    @GetMapping("/lista-categorias")
    public List<Categoria> listaCategoria(){
        return platoService.listarCategorias();
    }

    // ------------------------------------------


}
