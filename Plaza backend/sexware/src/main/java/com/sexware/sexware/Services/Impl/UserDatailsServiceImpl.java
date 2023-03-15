package com.sexware.sexware.Services.Impl;

import com.sexware.sexware.Model.Registrer.UserRegistrer.Usuario;
import com.sexware.sexware.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario users = usuarioRepository.findByEmail(email);



        if (users == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return users;
    }

    public UserDetails loadUser(String email, String rol){
        List<Usuario> usuario = usuarioRepository.findAll();
        Usuario user = null;
        for (Usuario users:usuario){
            if (users.getRoles().getRolNombre().equals(rol)&&
                    Objects.equals(users.getEmail(), email)){
                user = users;
            }
        }

        if (user == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return user;
    }
}
