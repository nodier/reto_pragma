package com.pragma.twilio.application.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserEmailRequestDto {
    @NotNull(message ="El campo correo electronico es obligatorio")
    @Email(message ="Digite correctamente el correo electronico")
    private String email;
}
