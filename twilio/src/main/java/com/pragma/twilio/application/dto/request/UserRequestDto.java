package com.pragma.twilio.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequestDto {

    private Long id;
    private String name;



    private String last_name;



    private String cellphone;



    private String email;



    private String password;


    private RoleRequestDto role;
}
