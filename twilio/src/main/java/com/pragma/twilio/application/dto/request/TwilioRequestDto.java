package com.pragma.twilio.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TwilioRequestDto {



    @NotNull(message ="El campo numero es obligatorio")
    private String number;


    @NotNull(message ="El campo mensaje es obligatorio")
    private String message;

}
