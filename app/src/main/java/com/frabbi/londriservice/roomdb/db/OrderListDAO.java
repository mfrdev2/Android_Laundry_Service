package com.frabbi.londriservice.roomdb.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.frabbi.londriservice.roomdb.entities.OrderList;

import java.util.List;

@Dao
public interface OrderListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(OrderList orderList);

    @Query("SELECT * FROM pending_order_list ORDER BY Id")
    LiveData<List<OrderList>> getAllData();

    @Query("DELETE FROM pending_order_list")
    void deleteAll();

    @Query("Select * from pending_order_list where isPending = 1")
    LiveData<List<OrderList>> getPendingOrderList();

    @Update
    void update(OrderList orderList);

    @Delete
    void delete(OrderList orderList);

    @Query("select * from pending_order_list where Id = :id")
    LiveData<OrderList> findId(Integer id);

}
