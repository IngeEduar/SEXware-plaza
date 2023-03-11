package com.sexware.sexware.Controllers;

import com.sexware.sexware.Model.ConfigUser.UpdatePassRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.AuditoriaRepository;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Usuario registrar(@PathVariable("email") String email , @RequestBody Usuario usuario) throws Exception {


        Rol rol = new Rol();
        rol.setId(2L);
        rol.setRolNombre("PROPIETARIO");

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));


        return usuarioService.guardarUsuario(usuario,rol,email);
    }

    @GetMapping("/buscar/{email}")
    public Usuario obtenerUsuario(@PathVariable("email") String email){
        return usuarioService.obtenerUsuario(email);
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public String eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){

        return usuarioService.eliminarUsuario(usuarioId);
    }
    @GetMapping("/listar-usuario")
    public List<Usuario> listarUsuario(){
        return usuarioService.listarUsuario();
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

    @GetMapping("/lista-propietarios")
    public List<Usuario> listarPropietarios(){
        return usuarioService.listarPropietarios();
    }
}
