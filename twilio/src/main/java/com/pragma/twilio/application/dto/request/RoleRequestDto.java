package com.pragma.twilio.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDto {
    private long id;
    private String name;
    private String description;
}
