package com.cengiz.bursali.wunderlist.api.service;

import com.cengiz.bursali.wunderlist.api.constant.WarningMessage;
import com.cengiz.bursali.wunderlist.api.exception.ExceptionInfo;
import com.cengiz.bursali.wunderlist.api.exception.WunderAppException;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderCreateRequest;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderResponse;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderUpdateRequest;
import com.cengiz.bursali.wunderlist.api.persistence.entity.UserEntity;
import com.cengiz.bursali.wunderlist.api.persistence.entity.WunderEntity;
import com.cengiz.bursali.wunderlist.api.persistence.repository.UserRepository;
import com.cengiz.bursali.wunderlist.api.persistence.repository.WunderRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class WunderService {
    private final WunderRespository wunderRespository;
    private final UserRepository userRepository;

    public String create(WunderCreateRequest wunderCreateRequest) {

        final UserEntity userEntity = userRepository.findOne(wunderCreateRequest.getCreatedByUser());
        if (Objects.isNull(userEntity)) {
            throw new WunderAppException(ExceptionInfo.builder().message(WarningMessage.NO_USER_FOUND).number(HttpStatus.NOT_FOUND.value()).build());
        }
        final WunderEntity wunderEntity = buildWunderEntity(wunderCreateRequest);
        wunderRespository.save(wunderEntity);
        log.info("This wunder id {} is created.", wunderEntity.getId());

        return wunderEntity.getId();
    }

    public String update(UUID id, WunderUpdateRequest wunderUpdateRequest) {
        WunderEntity wunderEntity = wunderRespository.findOne(id.toString());
        if (Objects.isNull(wunderEntity)) {
            throw new WunderAppException(ExceptionInfo.builder().message(WarningMessage.NO_WUNDER_FOUND).number(HttpStatus.NOT_FOUND.value()).build());
        }

        wunderEntity.setTitle(wunderUpdateRequest.getTitle());
        wunderEntity.setDescription(wunderUpdateRequest.getDescription());
        wunderEntity.setModificationTime(new Date());

        wunderRespository.save(wunderEntity);

        return wunderEntity.getId();
    }

    public void delete(UUID id) {
        wunderRespository.delete(id.toString());
    }

    public List<WunderResponse> getWunderResponseList(String id) {
        final List<WunderEntity> wunderEntityList = wunderRespository.findAll(Example.of(WunderEntity.builder().createdByUser(id).build()));

        return wunderEntityList.parallelStream().map(this::convert).collect(Collectors.toList());
    }

    private WunderEntity buildWunderEntity(WunderCreateRequest wunderCreateRequest) {
        final Date now = new Date();
        return WunderEntity.builder()
                .id(UUID.randomUUID().toString())
                .title(wunderCreateRequest.getTitle())
                .description(wunderCreateRequest.getDescription())
                .createdByUser(wunderCreateRequest.getCreatedByUser())
                .creationTime(now)
                .modificationTime(now)
                .build();
    }

    private WunderResponse convert(WunderEntity wunderEntity) {
        return WunderResponse.builder()
                .id(wunderEntity.getId())
                .creationTime(wunderEntity.getCreationTime())
                .modificationTime(wunderEntity.getModificationTime())
                .description(wunderEntity.getDescription())
                .title(wunderEntity.getTitle())
                .createdByUser(wunderEntity.getCreatedByUser())
                .build();
    }
}
