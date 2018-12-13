package com.cengiz.bursali.wunderlist.api.web.controller;

import com.cengiz.bursali.wunderlist.api.model.user.UserLoginRequest;
import com.cengiz.bursali.wunderlist.api.model.user.UserRegisterRequest;
import com.cengiz.bursali.wunderlist.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "UserController")
@BaseController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("Register user")
    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        final String userId = userService.register(userRegisterRequest);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @ApiOperation("Login user")
    @RequestMapping(method = RequestMethod.POST, path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        final String userId = userService.login(userLoginRequest);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
