package com.pragma.twilio.application.mapper;


import com.pragma.twilio.application.dto.request.UserRequestDto;
import com.pragma.twilio.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    UserModel toUser(UserRequestDto userRequestDto);
}
