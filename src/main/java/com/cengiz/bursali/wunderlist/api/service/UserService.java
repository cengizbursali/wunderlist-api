package com.cengiz.bursali.wunderlist.api.service;

import com.cengiz.bursali.wunderlist.api.constant.WarningMessage;
import com.cengiz.bursali.wunderlist.api.exception.ExceptionInfo;
import com.cengiz.bursali.wunderlist.api.exception.WunderAppException;
import com.cengiz.bursali.wunderlist.api.model.user.UserLoginRequest;
import com.cengiz.bursali.wunderlist.api.model.user.UserRegisterRequest;
import com.cengiz.bursali.wunderlist.api.persistence.entity.UserEntity;
import com.cengiz.bursali.wunderlist.api.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String register(UserRegisterRequest userRegisterRequest) {
        UserEntity userEntity = userRepository.findOne(Example.of(UserEntity.builder()
                .email(userRegisterRequest.getEmail())
                .build()));

        if (Objects.nonNull(userEntity)) {
            throw new WunderAppException(ExceptionInfo.builder().message(WarningMessage.MAIL_ALREADY_REGISTERED).number(HttpStatus.NOT_ACCEPTABLE.value()).build());
        }

        userEntity = buildUserEntity(userRegisterRequest);
        userRepository.save(userEntity);
        log.info("This user: {} is registered successfully.", userEntity.getId());
        return userEntity.getId();
    }

    public String login(UserLoginRequest userLoginRequest) {
        final UserEntity userEntity = userRepository.findOne(Example.of(UserEntity.builder()
                .email(userLoginRequest.getEmail())
                .password(userLoginRequest.getPassword())
                .build()));
        if (Objects.isNull(userEntity)) {
            throw new WunderAppException(ExceptionInfo.builder().message(WarningMessage.USERNAME_OR_PASSWORD_INCORRECT).number(HttpStatus.NOT_ACCEPTABLE.value()).build());
        }

        return userEntity.getId();
    }

    private UserEntity buildUserEntity(UserRegisterRequest userRegisterRequest) {
        return UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .build();
    }
}
