package com.cengiz.bursali.wunderlist.api.model.wunder;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WunderUpdateRequest {

    private String title;
    private String description;
}
