package com.pragma.twilio.application.dto.reponse;


import com.pragma.twilio.application.dto.request.UserRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseClientDto {
    private boolean error;
    private String message;
    private UserRequestDto data;
}
