package com.pragma.twilio.infrastructure.out.jpa.mapper;

import com.pragma.twilio.domain.model.UserModel;
import com.pragma.twilio.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    UserEntity toEntity(UserModel userModel);
}
