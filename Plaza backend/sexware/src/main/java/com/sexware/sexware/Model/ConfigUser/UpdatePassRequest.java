package com.sexware.sexware.Model.ConfigUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdatePassRequest {

    private String pass;

    private String email;
    private String rol;

}
