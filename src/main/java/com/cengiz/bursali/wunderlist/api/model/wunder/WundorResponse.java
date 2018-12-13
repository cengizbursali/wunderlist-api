package com.cengiz.bursali.wunderlist.api.model.wunder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WundorResponse {
    private String id;
    private String title;
    private String description;
    private String createdByUser;
    private Date creationTime;
    private Date modificationTime;
}
