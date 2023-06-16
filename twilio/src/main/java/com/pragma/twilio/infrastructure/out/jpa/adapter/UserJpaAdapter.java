package com.pragma.twilio.infrastructure.out.jpa.adapter;


import com.pragma.twilio.domain.spi.IUserPersistencePort;
import com.pragma.twilio.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.twilio.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper restaurantEntityMapper;

}
