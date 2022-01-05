package com.frabbi.londriservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCategoryModel {
    private Integer imgSrc;
    private String title;

    public OrderCategoryModel(Integer imgSrc, String title) {
        this.imgSrc = imgSrc;
        this.title = title;
    }
}
