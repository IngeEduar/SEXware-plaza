package com.sexware.sexware.Controllers;

import com.google.gson.Gson;
import com.sexware.sexware.Model.Registrer.PlatoRegister.*;
import com.sexware.sexware.Repositorys.PlatoRepository;
import com.sexware.sexware.SaveImage.FileUploadUtil;
import com.sexware.sexware.Services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/plato")
@CrossOrigin("*")
public class PlatoController {

    @Autowired
    private PlatoService platoService;
    @Autowired
    private PlatoRepository platoRepository;



    @PostMapping("/agregar")
    public CrearPlatoResponse agregarPlato(@RequestBody CrearPlatoRequest crearPlatoRequest){

        return platoService.crearPlato(crearPlatoRequest);

    }

    @PostMapping("/agregar-imagen")
    public CrearPlatoResponse agregarPlatoImg(@RequestParam("plato") String strPlato, @RequestParam("img") MultipartFile img){

        try {
            String filename = StringUtils.cleanPath(img.getOriginalFilename());
            String dirFile = "images/plato";
            filename= "P"+filename;

            Gson gson = new Gson();
            CrearPlatoRequest crearPlatoRequest = gson.fromJson(strPlato, CrearPlatoRequest.class);
            crearPlatoRequest.setImg(filename);

            CrearPlatoResponse rest = platoService.crearPlato(crearPlatoRequest);

            FileUploadUtil.saveFile(dirFile,filename,img);

            return rest;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/listar/{namerest}")
    public List<Plato> listarPlatoRest(@PathVariable("namerest")String name){
        return platoService.listarPlatoRest(name);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminarPlato(@PathVariable("id") Long id){
        try{
            boolean rs = platoService.eliminarPlato(id);
            Optional<Plato> plato = platoRepository.findById(id);
            FileUploadUtil.eliminarfile(plato.get().getImg());
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

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
