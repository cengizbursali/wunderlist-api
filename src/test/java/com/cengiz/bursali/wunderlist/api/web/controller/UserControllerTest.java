package com.cengiz.bursali.wunderlist.api.web.controller;

import com.cengiz.bursali.wunderlist.api.model.user.UserLoginRequest;
import com.cengiz.bursali.wunderlist.api.model.user.UserRegisterRequest;
import com.cengiz.bursali.wunderlist.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService mockUserService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(mockUserService)).build();
    }

    @Test
    public void login() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final UserLoginRequest userLoginRequest = new UserLoginRequest("cengizbursali@gmail.com", "****");

        when(mockUserService.login(userLoginRequest)).thenReturn(anyString());

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userLoginRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void register() throws Exception {

        final ObjectMapper objectMapper = new ObjectMapper();
        final UserRegisterRequest userRegisterRequest = objectMapper.readValue(
                Files.readAllBytes(Paths.get("./src/test/resources/json/userRegisterRequest.json")),
                UserRegisterRequest.class);
        final String userId = "cefe-3423-234f-df09";

        when(mockUserService.register(any())).thenReturn(userId);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userRegisterRequest)))
                .andExpect(status().isCreated());
    }
}