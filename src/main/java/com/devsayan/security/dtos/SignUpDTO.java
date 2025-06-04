package com.devsayan.security.dtos;

import lombok.Data;

@Data
public class SignUpDTO {
    private String email;
    private String name;
    private String password;
}
