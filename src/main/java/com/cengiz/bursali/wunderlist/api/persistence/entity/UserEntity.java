package com.cengiz.bursali.wunderlist.api.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "adm")
public class UserEntity {

    @Id
    @Column(columnDefinition = "id")
    private String id;
    @Column(columnDefinition = "email")
    private String email;
    @Column(columnDefinition = "password")
    private String password;
    @Column(columnDefinition = "first_name")
    private String firstName;
    @Column(columnDefinition = "last_name")
    private String lastName;

}


