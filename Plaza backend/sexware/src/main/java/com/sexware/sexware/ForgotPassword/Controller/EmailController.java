package com.sexware.sexware.ForgotPassword.Controller;


import com.sexware.sexware.ForgotPassword.DTO.ChangePasswordDTO;
import com.sexware.sexware.ForgotPassword.DTO.EmailValuesDTO;
import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.ForgotPassword.Service.EmailService;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/email-password")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "CAMBIO DE CONTRASEÑA";

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) throws Exception {
        Usuario usuarioOpt = usuarioService.obtenerUsuario(dto.getMailTo());
        if(usuarioOpt == null)
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        Usuario usuario = modelMapper.map(usuarioOpt, Usuario.class);
        dto.setMailFrom(mailFrom);
        dto.setMailTo(usuario.getEmail());
        dto.setSubject(subject);
        dto.setUserName(usuario.getNombre());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        usuario.setTokenPassword(tokenPassword);
        usuarioService.save(usuario);
        emailService.sendEmail(dto);
        return new ResponseEntity(new Mensaje("Te hemos enviado un correo"), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        Usuario usuarioOpt = usuarioService.getByTokenPassword(dto.getTokenPassword());
        if(usuarioOpt == null)
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        Usuario usuario = modelMapper.map(usuarioOpt, Usuario.class);
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
    }


}
