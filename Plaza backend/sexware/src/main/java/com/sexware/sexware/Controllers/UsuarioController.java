package com.sexware.sexware.Controllers;

import com.sexware.sexware.ForgotPassword.DTO.ChangePasswordDTO;
import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
import com.sexware.sexware.Model.ConfigUser.UpdatePassRequest;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.AuditoriaRepository;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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

    /*@GetMapping("/buscar/{email}")
    public Usuario obtenerUsuario(@PathVariable("email") String email){
        return usuarioService.obtenerUsuario(email);
    }*/

    @DeleteMapping("/eliminar/{usuarioId}")
    public String eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){

        return usuarioService.eliminarUsuario(usuarioId);
    }
    @GetMapping("/listar-usuario")
    public List<Usuario> listarUsuario(){
        return usuarioService.listarUsuario();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody UpdatePassRequest passRequest) {

        List<Usuario> usuarioOpt = usuarioService.listarUsuario();
        Usuario user = null;
        if(usuarioOpt.isEmpty())
            return new ResponseEntity<>(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        for (Usuario users:usuarioOpt){
            if (Objects.equals(users.getRoles().getRolNombre(),passRequest.getRol())&&
                    Objects.equals(users.getEmail(), passRequest.getEmail())){
                user = users;
            }
        }

        String newPassword = passwordEncoder.encode(passRequest.getPass());
        assert user != null;
        user.setPassword(newPassword);
        user.setTokenPassword(null);
        usuarioService.save(user);
        return new ResponseEntity<>(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
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
