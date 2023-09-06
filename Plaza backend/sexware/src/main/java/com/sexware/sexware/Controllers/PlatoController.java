package com.sexware.sexware.Controllers;

import com.google.gson.Gson;
import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.Model.Peticiones.PlatoModEstado;
import com.sexware.sexware.Model.Registrer.PlatoRegister.*;
import com.sexware.sexware.SaveImage.FileUploadUtil;
import com.sexware.sexware.Security.Exceptions.MyException;
import com.sexware.sexware.Services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CrearPlatoResponse agregarPlato(@RequestBody CrearPlatoRequest crearPlatoRequest) throws Exception {

        return platoService.crearPlato(crearPlatoRequest);

    }

    @PostMapping("/agregar-img")
    public ResponseEntity<?> agregarPlatoimg(@RequestParam("plato")String strPlato, @RequestParam("img")MultipartFile img){
        try{
            String filename = StringUtils.cleanPath(img.getOriginalFilename());
            filename = "P"+filename;

            String dirFile = "images/plato";

            Gson gson = new Gson();
            CrearPlatoRequest crearPlatoRequest = gson.fromJson(strPlato, CrearPlatoRequest.class);
            crearPlatoRequest.setImg(filename);

            CrearPlatoResponse crearPlatoResponse = platoService.crearPlato(crearPlatoRequest);

            FileUploadUtil.saveFile(dirFile,filename,img);

            return new ResponseEntity<>(new CrearPlatoResponse(crearPlatoResponse.getNombre(),
                    crearPlatoResponse.getDescripcion(),
                    crearPlatoResponse.getPrecio(),
                    crearPlatoResponse.getImg(),
                    crearPlatoResponse.getCategoria()), HttpStatus.OK);

        }catch (MyException ex){
            throw ex;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al crear plato, No eres el propietario"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lista-rest/{nombre}")
    public ResponseEntity<?> listarPlatosRest(@PathVariable("nombre")String nombre){

        return new ResponseEntity<>(platoService.listarPlatoRest(nombre), HttpStatus.OK);

    }

    @PostMapping("/modificar")
    public ResponseEntity<?> modificarPlato(@RequestBody ModificarPlatoRequest modificarPlatoRequest){
        try {
            platoService.modificarPlato(modificarPlatoRequest);
            return new ResponseEntity<>(new Mensaje("Plato Modificado con exito"),HttpStatus.OK);
        }catch (MyException ex){
            throw ex;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al modificar el plato, No eres el propieatrio"),HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/mod-estado")
    public ResponseEntity<?> modificarEstado(@RequestBody PlatoModEstado platoModEstado){

        return new ResponseEntity<>(platoService.deshabilitarPlato(platoModEstado), HttpStatus.OK);

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
