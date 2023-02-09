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
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private String submittedBy;
    private long restaurantId;
    private String comment;

    private Integer peanutScore;
    private Integer eggScore;
    private Integer diaryScore;

    private ReviewStatus status;

}
