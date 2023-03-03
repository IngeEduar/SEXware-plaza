package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Model.Registrer.UserRegistrer.UsuarioRoles;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRoles> usuarioRoles, String email) throws Exception;

    public Usuario obtenerUsuario(String email);
    public List<Usuario> listarUsuario();

    public String eliminarUsuario(Long usuarioId);

    public String actualizarPass(Usuario usuario);

    public Usuario guardarAdmin(Usuario usuario,Set<UsuarioRoles> usuarioRoles)throws Exception;
    public List<Usuario> listarPropietarios();

}