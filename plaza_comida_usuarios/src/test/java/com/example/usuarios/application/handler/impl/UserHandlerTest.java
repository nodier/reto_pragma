package com.example.usuarios.application.handler.impl;

import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.ResponseClientDto;
import com.example.usuarios.application.dto.response.ResponseDto;
import com.example.usuarios.application.dto.response.RolResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.application.handler.IJwtHandler;
import com.example.usuarios.application.handler.impl.Factory.FactoryUserDataTest;
import com.example.usuarios.application.mapper.request.IUserRequestMapper;
import com.example.usuarios.application.mapper.response.IRolResponseMapper;
import com.example.usuarios.application.mapper.response.IUserResponseMapper;
import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.configuration.FeignClientInterceptorImp;
import com.example.usuarios.infrastructure.exception.EmailAlreadyTaken;
import com.example.usuarios.infrastructure.exception.NoDataFoundException;
import com.example.usuarios.infrastructure.input.rest.Mall.IMallService;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
class UserHandlerTest {

    @InjectMocks
    UserHandler userHandler;

    @Mock
    IUserServicePort userServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IUserResponseMapper userResponseMapper;
    @Mock
    IRolServicePort rolServicePort;
    @Mock
    IRolResponseMapper rolResponseMapper;
    @Mock
    IJwtHandler jwtHandler;
    @Mock
    IMallService mallService;


    @Test
    void mustRegisterAnUser() {
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(1L);
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();
        UserModel expectedUser = FactoryUserDataTest.getUserModel(1L, "ROLE_ADMINISTRADOR");
        RolResponseDto rolResponseDto = FactoryUserDataTest.getRolResponseDto();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(rolServicePort.getRol(any())).thenReturn(expectedUser.getRolId());
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(expectedUser);
        Mockito.when(rolResponseMapper.toResponse(any())).thenReturn(rolResponseDto);
        Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);

        Assertions.assertEquals(userResponseDto, userHandler.register(userRequestDto));

        Mockito.verify(userServicePort).saveUser(any(UserModel.class));
    }

    @Test
    void throwEmailAlreadyTakenExceptionWhenAttemptToRegisterAnUser() {

        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(1L);
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.register(userRequestDto)
        );
    }

    @Test
    void mustRegisterAnOwner() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(2L);
        UserModel userModel = FactoryUserDataTest.getUserModel(2L, "ROLE_PROPIETARIO");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(userRequestMapper.toUserRequestDto(any())).thenReturn(userRequestDto);
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(userModel);
        Mockito.when(rolServicePort.getRol(any())).thenReturn(userModel.getRolId());
        Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);

        Assertions.assertEquals(userResponseDto, userHandler.ownerRegister(registerRequestDto));

        Mockito.verify(userServicePort).saveUser(any(UserModel.class));
    }

    @Test
    void throwEmailAlreadyTakenExceptionWhenAttemptToRegisterAnOwner() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.ownerRegister(registerRequestDto)
        );
    }

    @Test
    void mustRegisterAnEmployee() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(3L);
        UserModel userModel = FactoryUserDataTest.getUserModel(3L, "ROLE_EMPLEADO");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();
        ResponseEntity<ResponseDto> response = FactoryUserDataTest.getResponseEntity();
        UserEntity userEntity2 = FactoryUserDataTest.getUserEntity2();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            Mockito.when(userServicePort.findUserByEmail(registerRequestDto.getEmail())).thenReturn(Optional.empty());
            Mockito.when(jwtHandler.extractUserName(any())).thenReturn("andres2@gmail.com");
            Mockito.when(userServicePort.findUserByEmail("andres2@gmail.com")).thenReturn(Optional.of(userEntity2));
            Mockito.when(userServicePort.saveUser(any())).thenReturn(userModel);
            Mockito.when(userRequestMapper.toUserRequestDto(any())).thenReturn(userRequestDto);
            Mockito.when(userRequestMapper.toUser(any())).thenReturn(userModel);
            Mockito.when(rolServicePort.getRol(any())).thenReturn(userModel.getRolId());
            Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);
            Mockito.when(mallService.saveRestaurantEmployee(any())).thenReturn(response);

            Assertions.assertEquals(userResponseDto, userHandler.employeeRegister(registerRequestDto, 1L));

            Mockito.verify(userServicePort).saveUser(any(UserModel.class));
        }
    }

    @Test
    void throwEmailAlreadyTakenExceptionWhenAttemptToRegisterAnEmployee() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.employeeRegister(registerRequestDto, 1L)
        );
    }

    @Test
    void throwNoDataFoundExceptionWhenAttemptToRegisterAnEmployee() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(3L);
        UserModel userModel = FactoryUserDataTest.getUserModel(3L, "ROLE_EMPLEADO");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();
        ResponseEntity<ResponseDto> response = FactoryUserDataTest.getResponseEntity();
        UserEntity userEntity2 = FactoryUserDataTest.getUserEntity2();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            Mockito.when(userServicePort.findUserByEmail(registerRequestDto.getEmail())).thenReturn(Optional.empty());
            Mockito.when(jwtHandler.extractUserName(any())).thenReturn("andres2@gmail.com");
            Mockito.when(userServicePort.findUserByEmail("andres2@gmail.com")).thenReturn(Optional.empty());

            Assertions.assertThrows(
                    NoDataFoundException.class,
                    () -> userHandler.employeeRegister(registerRequestDto, 1L)
            );
        }


    }

    @Test
    void mustRegisterAClient() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(4L);
        UserModel userModel = FactoryUserDataTest.getUserModel(4L, "ROLE_CLIENTE");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(userRequestMapper.toUserRequestDto(any())).thenReturn(userRequestDto);
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(userModel);
        Mockito.when(rolServicePort.getRol(any())).thenReturn(userModel.getRolId());
        Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);

        Assertions.assertEquals(userResponseDto, userHandler.clientRegister(registerRequestDto));

        Mockito.verify(userServicePort).saveUser(any(UserModel.class));
    }

    @Test
    void throwEmailAlreadyTakenExceptionWhenAttemptToRegisterAClient() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.clientRegister(registerRequestDto)
        );
    }

}