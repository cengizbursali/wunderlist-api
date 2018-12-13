package com.cengiz.bursali.wunderlist.api.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wunder", schema = "adm")
public class WunderEntity {

    @Id
    @Column(columnDefinition = "id")
    private String id;
    @Column(columnDefinition = "title")
    private String title;
    @Column(columnDefinition = "description")
    private String description;
    @Column(columnDefinition = "created_by_user")
    private String createdByUser;
    @Column(columnDefinition = "creation_time")
    private Date creationTime;
    @Column(columnDefinition = "modification_time")
    private Date modificationTime;

}


