package com.cengiz.bursali.wunderlist.api.configuration;

import com.cengiz.bursali.wunderlist.api.persistence.repository.UserRepository;
import com.cengiz.bursali.wunderlist.api.persistence.repository.WunderRespository;
import com.cengiz.bursali.wunderlist.api.service.UserService;
import com.cengiz.bursali.wunderlist.api.service.WunderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public WunderService wunderService(WunderRespository wunderRespository, UserRepository userRepository) {
        return new WunderService(wunderRespository, userRepository);
    }
}
