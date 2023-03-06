package com.sexware.sexware.SaveImage;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/img")
public class MyController {

    @GetMapping("/photos/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {

            if(!filename.equals("default.jpg")) {
                if(filename.startsWith("R")){
                    image = FileUtils.readFileToByteArray(new File("images/restaurante/" + filename));
                } else if (filename.startsWith("P")) {
                    image = FileUtils.readFileToByteArray(new File("images/plato/"+filename));
                }

            }else{
                image = FileUtils.readFileToByteArray(new File("images/"+filename));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @PostMapping("/users/save")
    public void saveUser(@RequestParam("usuario") String strUsuario, @RequestParam("fichero") MultipartFile multipartFile) throws IOException {
        //Obtenemos el nombre del fichero
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //Establecemos el directorio donde se subiran nuestros ficheros
        String uploadDir = "images";

        Gson gson = new Gson();
        User usuario = gson.fromJson(strUsuario, User.class);
        //Obtenemos la propiedades del usuario
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getApellidos());
        System.out.println(usuario.getEmail());

        //Establacecemos la imagen
        usuario.setImagen(fileName);
        System.out.println(usuario.getImagen());

        //Guardamos la imagen
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }

    public static boolean eliminarIMG(String name) {
        try {

            if(!name.equals("default.jpg")) {
                if (name.startsWith("P")) {
                    FileUploadUtil.eliminarfile("plato/" + name);
                } else if (name.startsWith("R")) {
                    FileUploadUtil.eliminarfile("restaurante/" + name);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
