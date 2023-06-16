package com.pragma.twilio.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;


    private String name;


    private String last_name;


    private String cellphone;


    private String email;


    private String password;

    private RoleModel role;
}
