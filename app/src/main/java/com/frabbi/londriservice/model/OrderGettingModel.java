package com.frabbi.londriservice.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class OrderGettingModel implements Serializable {
    private String serviceCategory;
    private String selectedItem;
    private String selectedQty;
    private Double gotPrice;
    private Integer totalQty;
    private Double totalAmount;

    /**
     * @param serviceCategory
     * @param selectedItem
     * @param selectedQty
     * @param gotPrice
     * @param totalQty
     * @param totalAmount
     */
    public OrderGettingModel(String serviceCategory, String selectedItem,
                             String selectedQty, Double gotPrice, Integer totalQty, Double totalAmount) {
        this.serviceCategory = serviceCategory;
        this.selectedItem = selectedItem;
        this.selectedQty = selectedQty;
        this.gotPrice = gotPrice;
        this.totalQty = totalQty;
        this.totalAmount = totalAmount;
    }

}
