package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Registrer.UserRegistrer.Auditoria;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.*;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Rol rol, String email) throws Exception {

        Usuario usuario1 = usuarioRepository.findByEmail(usuario.getEmail());


        if (usuario1 != null){
            String rol1 = usuario1.getRoles().getRolNombre();
            if (rol1.equals(rol.getRolNombre())) {
                throw new Exception("EL usuario ya esta registrado con este rol");
            }
        }
            rolRepository.save(rol);

            usuario.setRoles(rol);
            Usuario usuarioLocal = usuarioRepository.save(usuario);

            String fecha = String.valueOf(LocalDate.now());
            String hora = String.valueOf(LocalTime.now());

            Usuario usuarioAdmin = obtenerUsuario(email);
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
        Usuario usuarioLocal = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }else {

            rolRepository.save(rol);

            usuarioLocal = usuarioRepository.save(usuario);


        }

        return usuarioLocal;
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


}
