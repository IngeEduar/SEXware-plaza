package com.sexware.sexware.Controllers;


import com.sexware.sexware.Model.*;
import com.sexware.sexware.Model.Login.JwtRequest;
import com.sexware.sexware.Model.Login.JwtResponse;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
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
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<AdminResponse> guardarAdmin() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("admin");
        usuario.setApellido("fesc");
        usuario.setCedula("800235151");
        usuario.setCelular("+573227613865");
        usuario.setEmail("adminFesc@fesc.edu.co");
        String pass = generarPassword();
        usuario.setPassword(passwordEncoder.encode(pass));
        System.out.println(pass);

        Rol rol = new Rol();
        rol.setId(1L);
        rol.setRolNombre("ADMIN");

        usuario.setRoles(rol);

        Usuario usuarioGuardado = usuarioService.guardarAdmin(usuario,rol);
        System.out.println(usuarioGuardado.getNombre());

        return ResponseEntity.ok(new AdminResponse(usuario.getEmail(), pass));
    }

    public String generarPassword(){

        final String caracteres ="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789@/._-?&%$#!";

        int cant = (int)(Math.random()*(15-6+1)+6);
        StringBuilder contrasena = new StringBuilder();
        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; i<cant;i++){

            int cantidadDeCaracteres = caracteres.length();
            int nRandom = (int) (Math.random()*cantidadDeCaracteres);

            if (numeros.contains(nRandom)){
                i--;
            }else {
                numeros.add(nRandom);
                contrasena.append(caracteres.charAt(nRandom));
            }

        }

        return contrasena.toString();
    }
}
