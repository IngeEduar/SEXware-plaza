package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Model.Registrer.UserRegistrer.UsuarioRoles;
import com.sexware.sexware.Repositorys.AuditoriaRepository;
import com.sexware.sexware.Repositorys.RestaurantRepository;
import com.sexware.sexware.Repositorys.RolRepository;
import com.sexware.sexware.Repositorys.UsuarioRepository;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario,Set<UsuarioRoles> usuarioRoles, String email) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }else {
            for (UsuarioRoles roles:usuarioRoles){
                rolRepository.save(roles.getRol());
            }

            usuario.getRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);

            String fecha = String.valueOf(LocalDate.now());
            String hora = String.valueOf(LocalTime.now());

            Usuario usuarioAdmin = obtenerUsuario(email);
            Auditoria auditoria = new Auditoria();
            auditoria.setUsuario(usuarioAdmin);
            auditoria.setTitulo("REGISTRO");
            auditoria.setDescripcion("Registro al usuario "+usuario.getNombre()+" Correo: "+usuario.getEmail());
            auditoria.setFecha(fecha+" "+hora);

            auditoriaRepository.save(auditoria);

        }

        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public String eliminarUsuario(Long usuarioId) {

        try {
            usuarioRepository.deleteById(usuarioId);

            return "Usuario eliminado correctamente";
        }catch (Exception e){
            e.printStackTrace();
            return "Error al eliminar el usuario";
        }
    }

    @Override
    public String actualizarPass(Usuario usuario) {

        usuarioRepository.save(usuario);

        return "Contraseña actualizada";
    }

    @Override
    public Usuario guardarAdmin(Usuario usuario, Set<UsuarioRoles> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }else {
            for (UsuarioRoles roles:usuarioRoles){
                rolRepository.save(roles.getRol());
            }
            usuario.getRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);


        }

        return usuarioLocal;
    }


}
