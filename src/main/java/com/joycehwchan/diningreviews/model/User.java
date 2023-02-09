package com.joycehwchan.diningreviews.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String city;
    private String state;
    private String zipcode;

    private Boolean checksPeanutAllergy;
    private Boolean checksEggAllergy;
    private Boolean checksDiaryAllergy;
}

