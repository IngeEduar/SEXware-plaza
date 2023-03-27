package com.sexware.sexware.Controllers;


import com.sexware.sexware.ForgotPassword.DTO.Mensaje;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private UserDatailsServiceImpl userDatailsService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest){


        String mensaje = autenticar(jwtRequest.getEmail(),jwtRequest.getPassword(),jwtRequest.getRol());
        System.out.println("------- "+jwtRequest.getRol()+" --------");
        switch (mensaje){
            case "OK":
                UserDetails userDetails =  this.userDatailsService.loadUser(jwtRequest.getEmail(),jwtRequest.getRol());
                String token = this.jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(token));
            case "FAIL":
                return new  ResponseEntity<>(new Mensaje("Credenciales invalidas"), HttpStatus.NOT_FOUND);
            case "NX":
                return new ResponseEntity<>(new Mensaje("No existe ningun usuario con esas credenciales"),HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(new Mensaje("..."),HttpStatus.NOT_FOUND);
        }

    }

    private String autenticar(String username,String password,String rol) {

        List<Usuario> user = usuarioService.listarUsuario();
        if (!user.isEmpty()){
            String msj="";
            for (Usuario users:user){
                if (passwordEncoder.matches(password, users.getPassword()) && Objects.equals(users.getRoles().getRolNombre(), rol)&&
                        Objects.equals(users.getEmail(), username)) {
                    msj = "OK";
                    break;
                }else {
                    msj = "FAIL";
                }
            }

            return msj;
        }else {
            return "NX";
        }

    }

    @PostMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(@RequestBody JwtRequest principal){
        return (Usuario) this.userDatailsService.loadUser(principal.getEmail(), principal.getRol());
    }


    @GetMapping("/save-admin")
    public ResponseEntity<AdminResponse> guardarAdmin() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("admin");
        usuario.setApellido("fesc");
        usuario.setCedula("800235151");
        usuario.setCelular("+573227613865");
        usuario.setEmail("adminFesc@fesc.edu.co");
        String pass = "Ac.ñ@1p!87Da$-";
        usuario.setPassword(passwordEncoder.encode(pass));

        Rol rol = new Rol();
        rol.setId(1L);
        rol.setRolNombre("ADMIN");

        usuario.setRoles(rol);

        Usuario usuarioGuardado = usuarioService.guardarAdmin(usuario,rol);
        System.out.println(usuarioGuardado.getNombre());

        return ResponseEntity.ok(new AdminResponse(usuario.getEmail(), pass));
    }

    /*
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
    -----Autenticar usuario --- login ----
    try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    */
}
