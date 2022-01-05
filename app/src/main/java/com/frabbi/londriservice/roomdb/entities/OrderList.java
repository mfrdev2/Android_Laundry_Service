package com.frabbi.londriservice.roomdb.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.frabbi.londriservice.model.AddressModel;
import com.frabbi.londriservice.model.OrderGettingModel;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(tableName = "pending_order_list")
public class OrderList implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer Id;
    @ColumnInfo
    private String pOrderList;
    @ColumnInfo
    private String pAddress;
    @ColumnInfo
    private Boolean isPending;
    @ColumnInfo
    private String dateAndTime;

    public OrderList(String pOrderList, String pAddress, Boolean isPending, String dateAndTime) {
        this.pOrderList = pOrderList;
        this.pAddress = pAddress;
        this.isPending = isPending;
        this.dateAndTime = dateAndTime;
    }
}
