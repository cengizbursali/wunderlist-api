package com.cengiz.bursali.wunderlist.api.service;

import com.cengiz.bursali.wunderlist.api.persistence.repository.UserRepository;
import com.cengiz.bursali.wunderlist.api.persistence.repository.WunderRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class WunderServiceTest {

    @Mock
    private WunderRespository mockWunderRespository;
    @Mock
    private UserRepository mockUserRepository;

    private WunderService wunderService;

    @Before
    public void setUp() {
        wunderService = new WunderService(mockWunderRespository, mockUserRepository);
    }

    @Test
    public void create() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }

    @Test
    public void getWundorResponseList() {

    }
}