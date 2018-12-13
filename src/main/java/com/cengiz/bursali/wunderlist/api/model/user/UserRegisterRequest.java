package com.cengiz.bursali.wunderlist.api.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
