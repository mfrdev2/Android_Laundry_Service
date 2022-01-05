package com.frabbi.londriservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardSliderModel {
    private Integer imgSrc;
    private String imgName;
    public CardSliderModel(Integer imgSrc,String imgName) {
        this.imgSrc = imgSrc;
        this.imgName = imgName;
    }
}
