package com.sexware.sexware.Controllers;

import com.google.gson.Gson;
import com.sexware.sexware.Model.Registrer.PlatoRegister.*;
import com.sexware.sexware.SaveImage.FileUploadUtil;
import com.sexware.sexware.Services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/agregar-img")
    public CrearPlatoResponse agregarPlatoimg(@RequestParam("plato")String strPlato, @RequestParam("img")MultipartFile img){
        try{
            String filename = StringUtils.cleanPath(img.getOriginalFilename());
            filename = "P"+filename;

            String dirFile = "images/plato";

            Gson gson = new Gson();
            CrearPlatoRequest crearPlatoRequest = gson.fromJson(strPlato, CrearPlatoRequest.class);
            crearPlatoRequest.setImg(filename);

            CrearPlatoResponse crearPlatoResponse = platoService.crearPlato(crearPlatoRequest);

            FileUploadUtil.saveFile(dirFile,filename,img);

            return crearPlatoResponse;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/lista-rest/{nombre}")
    public List<Plato> listarPlatosRest(@PathVariable("nombre")String nombre){
        return platoService.listarPlatoRest(nombre);
    }

    @PostMapping("/modificar")
    public String mdificarPlato(@RequestBody ModificarPlatoRequest modificarPlatoRequest){

        return platoService.modificarPlato(modificarPlatoRequest);

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
