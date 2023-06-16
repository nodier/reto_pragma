package com.pragma.twilio.domain.usecase;

import com.pragma.twilio.domain.api.IUserServicePort;
import com.pragma.twilio.domain.spi.IUserPersistencePort;

public class UserUsecase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;

    public UserUsecase(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }
}
