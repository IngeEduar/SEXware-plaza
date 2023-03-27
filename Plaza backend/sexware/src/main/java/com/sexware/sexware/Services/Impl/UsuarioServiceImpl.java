package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Peticiones.RegisterEmpleadoRequest;
import com.sexware.sexware.Model.Registrer.RestaurantRegistrer.Restaurant;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Model.Registrer.UserRegistrer.UsuarioEmpleado;
import com.sexware.sexware.Repositorys.*;
import com.sexware.sexware.Services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Rol rol, String email) throws Exception {

        List<Usuario> usuario1 = usuarioRepository.findAll();
        Usuario usuarioAdmin = null;

        if (!usuario1.isEmpty()){
            for (Usuario user:usuario1){
                if (Objects.equals(user.getRoles().getRolNombre(),rol.getRolNombre()) &&
                        Objects.equals(user.getEmail(), usuario.getEmail())){
                    throw new Exception("Ya existe un usuario con este rol");
                }
            }
            for (Usuario users:usuario1){

                if (users.getRoles().getRolNombre().equals("ADMIN")&&
                        Objects.equals(users.getEmail(), email)){
                        usuarioAdmin = users;
                }
            }
        }
        if (usuarioAdmin == null){
            throw new Exception("No estas registrado como admin");
        }
            rolRepository.save(rol);

            usuario.setRoles(rol);
            Usuario usuarioLocal = usuarioRepository.save(usuario);

            String fecha = String.valueOf(LocalDate.now());
            String hora = String.valueOf(LocalTime.now());


            Auditoria auditoria = new Auditoria();
            auditoria.setUsuario(usuarioAdmin);
            auditoria.setTitulo("REGISTRO");
            auditoria.setDescripcion("Registro al usuario "+usuarioLocal.getNombre()+" Correo: "+usuarioLocal.getEmail());
            auditoria.setFecha(fecha+" "+hora);

            auditoriaRepository.save(auditoria);

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
    public Usuario guardarAdmin(Usuario usuario,Rol rol) throws Exception {
        List<Usuario> usuarioLocal = usuarioRepository.findAll();
        Usuario user = null;
        if (!usuarioLocal.isEmpty()) {
            for (Usuario users : usuarioLocal) {
                if (Objects.equals(users.getRoles().getRolNombre(), rol.getRolNombre())&&
                        Objects.equals(users.getEmail(), usuario.getEmail())) {
                    throw new Exception("Ya existe un usuario con este rol");
                }
            }
        } else {

            rolRepository.save(rol);

            user = usuarioRepository.save(usuario);


        }

        return user;
    }

    @Override
    public List<Usuario> listarPropietarios() {

        List<Usuario> usuarioList = usuarioRepository.findAll();
        List<Usuario> usuarios = new ArrayList<>();

        for (Usuario user: usuarioList) {
            String rol = user.getRoles().getRolNombre();

            if(rol.equals("PROPIETARIO")){
                usuarios.add(user);
            }
        }



        return usuarios;
    }

    @Override
    public Usuario getByTokenPassword(String token) {
        return usuarioRepository.findByTokenPassword(token);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario guardarEmpleado(RegisterEmpleadoRequest usuario, Rol rol) throws Exception {

        Restaurant restaurant = restaurantRepository.findByNombre(usuario.getNombreRestaurante());
        List<Usuario> usuarioList = usuarioRepository.findAll();
        Usuario userPropietario = null;

        if (!usuarioList.isEmpty()){
            for (Usuario user:usuarioList){
                if (Objects.equals(user.getRoles().getRolNombre(),rol.getRolNombre()) &&
                        Objects.equals(user.getEmail(), usuario.getEmail())){
                    throw new Exception("Ya existe un usuario con este rol");
                }
            }
            for (Usuario user:usuarioList){

                if (user.getRoles().getRolNombre().equals("PROPIETARIO")&&
                        Objects.equals(user.getEmail(), usuario.getPropietario())){
                    userPropietario = user;
                }
            }
        }

        if (userPropietario == null){
            throw new Exception("No eres propietario");
        }

        rolRepository.save(rol);

        UsuarioEmpleado usuarioEmpleado = modelMapper.map(usuario, UsuarioEmpleado.class);
        usuarioEmpleado.setRestaurant(restaurant);
        usuarioEmpleado.setRoles(rol);

        Usuario user = usuarioRepository.save(usuarioEmpleado);

        String fecha = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());


        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(userPropietario);
        auditoria.setTitulo("REGISTRO");
        auditoria.setDescripcion("Registro al usuario "+user.getNombre()+" Correo: "+user.getEmail() +
                " como empleado del restaurante: "+restaurant.getNombre());
        auditoria.setFecha(fecha+" "+hora);

        auditoriaRepository.save(auditoria);

        return user;
    }


}
