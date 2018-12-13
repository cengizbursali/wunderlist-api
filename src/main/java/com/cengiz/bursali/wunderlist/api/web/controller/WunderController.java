package com.cengiz.bursali.wunderlist.api.web.controller;

import com.cengiz.bursali.wunderlist.api.model.wunder.WunderCreateRequest;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderResponse;
import com.cengiz.bursali.wunderlist.api.model.wunder.WunderUpdateRequest;
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
    @RequestMapping(method = RequestMethod.POST, value = "/wunders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody WunderCreateRequest wunderCreateRequest) {
        final String wunderId = wunderService.create(wunderCreateRequest);
        return new ResponseEntity<>(wunderId, HttpStatus.CREATED);
    }

    @ApiOperation("Update wunder")
    @RequestMapping(method = RequestMethod.PUT, path = "/wunders/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody WunderUpdateRequest wunderUpdateRequest) {
        final String wunderId = wunderService.update(id, wunderUpdateRequest);
        return new ResponseEntity<>(wunderId, HttpStatus.OK);
    }

    @ApiOperation("Delete wunder")
    @RequestMapping(method = RequestMethod.DELETE, path = "/wunders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        wunderService.delete(id);
    }

    @ApiOperation("List of wunders")
    @RequestMapping(method = RequestMethod.GET, path = "/wunders/{id}")
    public List<WunderResponse> getAll(@PathVariable("id") UUID id) {
        return wunderService.getWunderResponseList(id);
    }
}
