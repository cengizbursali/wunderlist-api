package com.cengiz.bursali.wunderlist.api.service;

import com.cengiz.bursali.wunderlist.api.exception.WunderAppException;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderCreateRequest;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderResponse;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderUpdateRequest;
import com.cengiz.bursali.wunderlist.api.persistence.entity.UserEntity;
import com.cengiz.bursali.wunderlist.api.persistence.entity.WunderEntity;
import com.cengiz.bursali.wunderlist.api.persistence.repository.UserRepository;
import com.cengiz.bursali.wunderlist.api.persistence.repository.WunderRespository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;


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


    @Test(expected = WunderAppException.class)
    public void shouldThrowUserNotFoundException_whenUserEntityIsNotExist() {
        final WunderCreateRequest wunderCreateRequest = new WunderCreateRequest("Java 8", "Java 8 new features", "2342-2342-2342-2342");

        Mockito.when(mockUserRepository.findOne(wunderCreateRequest.getCreatedByUser())).thenReturn(null);

        final String wunderId = wunderService.create(wunderCreateRequest);
        Mockito.verify(mockWunderRespository, Mockito.times(0)).save(new WunderEntity());

    }

    @Test()
    public void addWunderShouldSuccessfully_whenUserEntityIsExist() {
        final WunderCreateRequest wunderCreateRequest = new WunderCreateRequest("Java 9", "Java 9 new features", "2342-2342-2342-2342");

        Mockito.when(mockUserRepository.findOne(wunderCreateRequest.getCreatedByUser())).thenReturn(new UserEntity());

        final String wunderId = wunderService.create(wunderCreateRequest);

        Mockito.verify(mockWunderRespository, Mockito.times(1)).save((WunderEntity) any());

    }

    @Test()
    public void updateWunderSuccesfully_whenWunderEntityIsPresent() {
        final WunderUpdateRequest wunderUpdateRequest = new WunderUpdateRequest("Java 8", "Java 8 new features");

        Mockito.when(mockWunderRespository.findOne(Matchers.anyString())).thenReturn(new WunderEntity());

        final String wunderId = wunderService.update(UUID.randomUUID(), wunderUpdateRequest);
        Mockito.verify(mockWunderRespository, Mockito.times(1)).save((WunderEntity) any());
    }

    @Test(expected = WunderAppException.class)
    public void updateShouldThrowWunderNotFoundException_whenOptionalWunderEntityIsNotPresent() {
        final WunderUpdateRequest wunderUpdateRequest = new WunderUpdateRequest("Java 8", "Java 8 new features");

        Mockito.when(mockWunderRespository.findOne(Matchers.anyString())).thenReturn(null);

        final String wunderId = wunderService.update(UUID.randomUUID(), wunderUpdateRequest);
        Mockito.verify(mockWunderRespository, Mockito.times(0)).save(new WunderEntity());
    }

    @Test
    public void wunderListSizeResponseShouldBeEqualToSizeOfWunderEntities() {
        final WunderEntity wunderEntity1 = new WunderEntity("23132", "java5", "new features", "1231-1233-2313-1231", new Date(), new Date());
        final WunderEntity wunderEntity2 = new WunderEntity("23132", "java5", "new features", "1231-1233-2313-1231", new Date(), new Date());
        final WunderEntity wunderEntity3 = new WunderEntity("23132", "java5", "new features", "1231-1233-2313-1231", new Date(), new Date());
        final WunderEntity wunderEntity4 = new WunderEntity("23132", "java5", "new features", "1231-1233-2313-1231", new Date(), new Date());
        final List<WunderEntity> wunderEntityList = Arrays.asList(wunderEntity1, wunderEntity2, wunderEntity3, wunderEntity4);

        Mockito.when(mockWunderRespository.findAll((Example<WunderEntity>) any())).thenReturn(wunderEntityList);

        final List<WunderResponse> wunderResponseList = wunderService.getWunderResponseList(anyString());

        Assert.assertTrue(Objects.equals(wunderEntityList.size(), wunderResponseList.size()));

    }


}