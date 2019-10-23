package com.mongo.springmongodemo.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {
    private String userName;
    private int rating;
    private Boolean approved;
}
