package com.example.factory;

import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.application.dto.request.RolRequestDto;
import com.example.plaza_comidas.application.dto.request.UserRequestDto;
import com.example.plaza_comidas.application.dto.response.ResponseClientDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.domain.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FactoryRestaurantDataTest {
    public static RestaurantModel getRestaurantModel() {
        RestaurantModel expectedRestaurant = new RestaurantModel();

        expectedRestaurant.setName("qbano");
        expectedRestaurant.setAddress("Carrera 7");
        expectedRestaurant.setOwnerId(4L);
        expectedRestaurant.setPhoneNumber("+10000");
        expectedRestaurant.setUrlLogo("logoUrl");
        expectedRestaurant.setNit("20000");

        return expectedRestaurant;
    }

    public static RestaurantRequestDto getRestaurantRequestDto() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("qbano");
        restaurantRequestDto.setAddress("Carrera 7");
        restaurantRequestDto.setOwnerId(4L);
        restaurantRequestDto.setPhoneNumber("+10000");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantResponseDto getRestaurantResponseDto() {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setName("qbano");
        restaurantResponseDto.setAddress("Carrera 7");
        restaurantResponseDto.setOwnerId(4L);
        restaurantResponseDto.setPhoneNumber("+10000");
        restaurantResponseDto.setUrlLogo("logoUrl");
        restaurantResponseDto.setNit("20000");

        return restaurantResponseDto;
    }

    public static RestaurantRequestDto getRestaurantWithoutName() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setAddress("Carrera 7");
        restaurantRequestDto.setOwnerId(4L);
        restaurantRequestDto.setPhoneNumber("+10000");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantBadPhoneNumber() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("qbano");
        restaurantRequestDto.setAddress("Carrera 7");
        restaurantRequestDto.setOwnerId(4L);
        restaurantRequestDto.setPhoneNumber("Telefono");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantInvalidName() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("123546");
        restaurantRequestDto.setAddress("Carrera 7");
        restaurantRequestDto.setOwnerId(4L);
        restaurantRequestDto.setPhoneNumber("+90000");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }

    public static UserRequestDto getUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setId(1L);
        userRequestDto.setName("Andres");
        userRequestDto.setLastName("Cadavid");
        userRequestDto.setIdNumber("123");
        userRequestDto.setPhone("123");
        userRequestDto.setEmail("andres@gmail.com");
        userRequestDto.setPassword("1234");
        userRequestDto.setRolId(getRolRquestDto());

        return userRequestDto;
    }

    public static RolRequestDto getRolRquestDto() {
        RolRequestDto rolRequestDto = new RolRequestDto();
        rolRequestDto.setName("ROLE_ADMINISTRADOR");
        rolRequestDto.setDescription("Administrador");
        return rolRequestDto;
    }

    public static ResponseClientDto getResponseClientDto() {
        ResponseClientDto responseClientDto = new ResponseClientDto();

        responseClientDto.setMessage("");
        responseClientDto.setError(false);
        responseClientDto.setData(getUserRequestDto());

        return responseClientDto;
    }

    public static ResponseEntity<ResponseClientDto> getResponseEntity() {
        ResponseClientDto responseClientDto = getResponseClientDto();
        return new ResponseEntity<>(responseClientDto, HttpStatus.FOUND);
    }

    public static RestaurantEmployeeModel getRestaurantEmployeeModel() {
        RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();

        restaurantEmployeeModel.setRestaurantId(getRestaurantModel());
        restaurantEmployeeModel.setEmployeeId(getUserModel());
        restaurantEmployeeModel.setField("");

        return restaurantEmployeeModel;
    }

    public static UserModel getUserModel() {
        UserModel userModel = new UserModel();

        userModel.setId(1L);
        userModel.setName("Andres");
        userModel.setLastName("Cadavid");
        userModel.setIdNumber("100");
        userModel.setPhone("123");
        userModel.setEmail("andres@gmail.com");
        userModel.setPassword("1234");
        userModel.setRolId(null);

        return userModel;
    }
}
