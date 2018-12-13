package com.cengiz.bursali.wunderlist.api.web.controller;

import com.cengiz.bursali.wunderlist.api.model.wunder.WunderCreateRequest;
import com.cengiz.bursali.wunderlist.api.model.wunder.WundorResponse;
import com.cengiz.bursali.wunderlist.api.model.wunder.WundorUpdateRequest;
import com.cengiz.bursali.wunderlist.api.service.WunderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(value = "WunderController")
@BaseController
@RequiredArgsConstructor
public class WunderController {

    private final WunderService wunderService;

    @ApiOperation("Create wunder")
    @RequestMapping(method = RequestMethod.POST, value = "/wundors", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody WunderCreateRequest wunderCreateRequest) {
        final String wunderId = wunderService.create(wunderCreateRequest);
        return new ResponseEntity<>(wunderId, HttpStatus.CREATED);
    }

    @ApiOperation("Update wundor")
    @RequestMapping(method = RequestMethod.PUT, path = "/wundors/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody WundorUpdateRequest wundorUpdateRequest) {
        final String wunderId = wunderService.update(id, wundorUpdateRequest);
        return new ResponseEntity<>(wunderId, HttpStatus.OK);
    }

    @ApiOperation("Delete wundor")
    @RequestMapping(method = RequestMethod.DELETE, path = "/wundors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        wunderService.delete(id);
    }

    @ApiOperation("List of wundors")
    @RequestMapping(method = RequestMethod.GET, path = "/wundors/{id}")
    public List<WundorResponse> getAll(@PathVariable("id") UUID id) {
        return wunderService.getWundorResponseList(id);
    }
}
