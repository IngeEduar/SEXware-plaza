package com.sexware.sexware.Services;

import com.sexware.sexware.Model.Usuario;
import com.sexware.sexware.Model.UsuarioRoles;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRoles> usuarioRoles, String email) throws Exception;

    public Usuario obtenerUsuario(String email);

    public void eliminarUsuario(Long usuarioId);

    public String actualizarPass(Usuario usuario);

    public Usuario guardarAdmin(Usuario usuario,Set<UsuarioRoles> usuarioRoles)throws Exception;

}
