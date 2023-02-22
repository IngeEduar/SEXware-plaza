package com.sexware.sexware.Controllers;


import com.sexware.sexware.Model.*;
import com.sexware.sexware.Security.AuthenticateService;
import com.sexware.sexware.Security.JwtUtils;
import com.sexware.sexware.Services.Impl.UserDatailsServiceImpl;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDatailsServiceImpl userDatailsService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{
            autenticar(jwtRequest.getEmail(),jwtRequest.getPassword());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDatailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDatailsService.loadUserByUsername(principal.getName());
    }

    @GetMapping("/save-admin")
    public String guardarAdmin() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("admin");
        usuario.setApellido("admin");
        usuario.setNIdentidad("1005085311");
        usuario.setCelular("3105081250");
        usuario.setEmail("admin@gmail.com");
        usuario.setPassword(passwordEncoder.encode("admin"));

        Rol rol = new Rol();
        rol.setId(1L);
        rol.setRolNombre("ADMIN");

        Set<UsuarioRoles> usuarioRoles = new HashSet<>();
        UsuarioRoles usuarioRol = new UsuarioRoles();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        Usuario usuarioGuardado = usuarioService.guardarAdmin(usuario,usuarioRoles);
        System.out.println(usuarioGuardado.getNombre());

        return "Admin guardado exitosamente";
    }
}
