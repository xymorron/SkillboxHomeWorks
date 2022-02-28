package com.example.bookshop.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactConfirmationPayload {

    private String contact;
    private String code;

}
