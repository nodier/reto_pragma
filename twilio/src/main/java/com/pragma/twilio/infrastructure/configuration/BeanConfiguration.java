package com.pragma.twilio.infrastructure.configuration;


import com.pragma.twilio.domain.api.*;
import com.pragma.twilio.domain.spi.*;
import com.pragma.twilio.domain.usecase.*;
import com.pragma.twilio.infrastructure.input.rest.client.IUserClient;
import com.pragma.twilio.infrastructure.out.jpa.adapter.*;
import com.pragma.twilio.infrastructure.out.jpa.mapper.*;
import com.pragma.twilio.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {




    private final IUserRepository userRepository;
    private final IUserEntityMapper userentityMapper;


    private final IUserClient userClientRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userentityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUsecase(userPersistencePort());
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userClientRepository.getUserByEmail(username);
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
