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
