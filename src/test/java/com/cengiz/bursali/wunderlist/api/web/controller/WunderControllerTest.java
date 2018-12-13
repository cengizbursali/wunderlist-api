package com.cengiz.bursali.wunderlist.api.web.controller;

import com.cengiz.bursali.wunderlist.api.model.wunder.WunderCreateRequest;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderUpdateRequest;
import com.cengiz.bursali.wunderlist.api.service.WunderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class WunderControllerTest {

    @Mock
    private WunderService mockWunderService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WunderController(mockWunderService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void successfulCreateWunder() throws Exception {

        final WunderCreateRequest wunderCreateRequest = new WunderCreateRequest("spring boot", "spring boot guide for users", "1313-sdfd-2323-ssas");
        final String wunderId = "cefe-3423-234f-df09";

        when(mockWunderService.create(any())).thenReturn(wunderId);

        mockMvc.perform(post("/api/wunders")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(wunderCreateRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateWundor() throws Exception {
        final WunderUpdateRequest wunderUpdateRequest = new WunderUpdateRequest("sprng boot rest api", "using rest template");
        final UUID wunderId = UUID.randomUUID();

        when(mockWunderService.update(wunderId, wunderUpdateRequest)).thenReturn("hjhbhjhjb");

        mockMvc.perform(put("/api/wunders/" + wunderId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(wunderUpdateRequest)))
                .andExpect(status().isOk());
    }
}