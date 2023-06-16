package com.pragma.twilio.infrastructure.input.rest.client;


import com.pragma.twilio.application.dto.reponse.ResponseClientDto;
import com.pragma.twilio.application.dto.request.UserEmailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@FeignClient(name = "users-service", path = "/api/v1/user")
public interface IUserClient {

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDto> getUserById(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable Long id);

    @GetMapping("/me")
    public ResponseEntity<ResponseClientDto> getInfo(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);


    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseClientDto> getUserByEmail(@PathVariable String email);
}
