package com.sexware.sexware.Controllers;

import com.sexware.sexware.Model.*;
import com.sexware.sexware.Repositorys.AuditoriaRepository;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @PostMapping("/registrar/{email}")
    public Usuario registrar(@PathVariable("email") String email ,@RequestBody Usuario usuario) throws Exception {

        Set<UsuarioRoles> usuarioRol = new HashSet<>();

        Rol rol = new Rol();
        rol.setId(2L);
        rol.setRolNombre("PROPIETARIO");

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        UsuarioRoles usuarioRoles = new UsuarioRoles();
        usuarioRoles.setUsuario(usuario);
        usuarioRoles.setRol(rol);

        usuarioRol.add(usuarioRoles);





        return usuarioService.guardarUsuario(usuario,usuarioRol,email);
    }

    @GetMapping("/buscar/{email}")
    public Usuario obtenerUsuario(@PathVariable("email") String email){
        return usuarioService.obtenerUsuario(email);
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

    @PostMapping("/actualizar-password")
    public String cambiarPass( @RequestBody UpdatePassRequest passRequest){

        Usuario usuario = usuarioService.obtenerUsuario(passRequest.getEmail());

        usuario.setPassword(passwordEncoder.encode(passRequest.getPass()));

        return usuarioService.actualizarPass(usuario);

    }

    @GetMapping("/auditoria")
    public List<Auditoria> auditoria(){
        return auditoriaRepository.findAll();
    }
}
