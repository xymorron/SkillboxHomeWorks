package com.example.bookshop.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistrationForm {

    private String name;
    private String email;
    private String phone;
    private String pass;

}
