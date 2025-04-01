package com.fodi.auth_register.dto;

import lombok.Data;

@Data
public class SigninRequest {
    String phoneNumber;
    String password;
}
