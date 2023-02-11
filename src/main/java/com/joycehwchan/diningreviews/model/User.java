package com.joycehwchan.diningreviews.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String city;
    private String state;
    private String zipCode;

    private Boolean checksPeanutAllergy;
    private Boolean checksEggAllergy;
    private Boolean checksDiaryAllergy;
}

