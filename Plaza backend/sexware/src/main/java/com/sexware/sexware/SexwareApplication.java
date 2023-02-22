package com.sexware.sexware;

import com.sexware.sexware.Model.Rol;
import com.sexware.sexware.Model.Usuario;
import com.sexware.sexware.Model.UsuarioRoles;
import com.sexware.sexware.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Access;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SexwareApplication {



	public static void main(String[] args) {
		SpringApplication.run(SexwareApplication.class, args);
	}


}
