package com.sexware.sexware.Model.Login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class JwtResponse {

    private String token;
    private String pass;
}
