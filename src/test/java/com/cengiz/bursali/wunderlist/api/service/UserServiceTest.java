package com.cengiz.bursali.wunderlist.api.service;

import com.cengiz.bursali.wunderlist.api.exception.WunderAppException;
import com.cengiz.bursali.wunderlist.api.model.user.UserLoginRequest;
import com.cengiz.bursali.wunderlist.api.model.user.UserRegisterRequest;
import com.cengiz.bursali.wunderlist.api.persistence.entity.UserEntity;
import com.cengiz.bursali.wunderlist.api.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(mockUserRepository);
    }

    @Test(expected = WunderAppException.class)
    public void registerShouldThrowAlreadyExists_whenUserIsPresent() {
        final UserRegisterRequest userRegisterRequest = getUserRegisterRequest();

        when(mockUserRepository.findOne((Example<UserEntity>) any())).thenReturn(new UserEntity());
        userService.register(userRegisterRequest);

        verify(mockUserRepository).findOne((Example<UserEntity>) any());
        verify(mockUserRepository, times(0)).findOne((Example<UserEntity>) any());
    }

    @Test(expected = WunderAppException.class)
    public void loginShouldThrowUsernameOrPasswordIncorrect_whenUserIsNotExist() {
        final UserLoginRequest userLoginRequest = getUserLoginRequest();

        when(mockUserRepository.findOne((Example<UserEntity>) any())).thenReturn(null);
        userService.login(userLoginRequest);

        verify(mockUserRepository).findOne((Example<UserEntity>) any());
    }

    @Test
    public void registerShouldSave_ifUserIsNotExists() {
        final UserRegisterRequest userRegisterRequest = getUserRegisterRequest();

        when(mockUserRepository.findOne((Example<UserEntity>) any())).thenReturn(null);
        final String userId = userService.register(userRegisterRequest);

        verify(mockUserRepository).save(any(UserEntity.class));
        assertNotNull(userId);
    }

    @Test
    public void loginShouldReturnUserId_whenUserLoginInfoCorrectly() {
        final UserLoginRequest userLoginRequest = getUserLoginRequest();
        final UserEntity userEntity = new UserEntity(UUID.randomUUID().toString(), "cengizbursali@gmail.com", "*****", "Cengiz", "Bursalioğlu");

        when(mockUserRepository.findOne((Example<UserEntity>) any())).thenReturn(userEntity);
        final String userId = userService.login(userLoginRequest);

        assertNotNull(userId);
        assertEquals(userId, userEntity.getId());
    }

    private UserRegisterRequest getUserRegisterRequest() {
        return UserRegisterRequest.builder()
                .email("cengizbursali@gmail.com")
                .firstName("Cengiz")
                .lastName("Vural")
                .password("*****")
                .build();
    }

    private UserLoginRequest getUserLoginRequest() {
        return UserLoginRequest.builder()
                .email("cengizbursali@gmail.com")
                .password("*******")
                .build();
    }
}