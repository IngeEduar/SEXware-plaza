package com.sexware.sexware.Security;

import com.sexware.sexware.Model.Login.JwtRequest;
import com.sexware.sexware.Repositorys.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository repository;

    private final JwtUtils jwtUtils;

    public String validation(JwtRequest jwtRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getEmail(),
                        jwtRequest.getPassword()
                )
        );

        var usuario = repository.findByEmail(jwtRequest.getEmail());

        var token = jwtUtils.generateToken(usuario);

        return token;

    }
}
