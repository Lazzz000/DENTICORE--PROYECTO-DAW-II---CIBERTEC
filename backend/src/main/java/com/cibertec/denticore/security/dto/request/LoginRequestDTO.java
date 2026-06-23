package com.cibertec.denticore.security.dto.request;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String dni;
    private String password;
}
