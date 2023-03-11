package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.UserRegistrer.Rol;
import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;

import java.util.List;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Rol rol, String email) throws Exception;

    public Usuario obtenerUsuario(String email);
    public List<Usuario> listarUsuario();

    public String eliminarUsuario(Long usuarioId);

    public String actualizarPass(Usuario usuario);

    public Usuario guardarAdmin(Usuario usuario, Rol rol)throws Exception;
    public List<Usuario> listarPropietarios();

}
