package com.frabbi.londriservice.roomdb.repositories;

import androidx.lifecycle.LiveData;

import com.frabbi.londriservice.roomdb.db.OrderListDAO;
import com.frabbi.londriservice.roomdb.db.RoomDatabaseDB;
import com.frabbi.londriservice.roomdb.entities.OrderList;

import java.util.List;

public class Repository {
    private final OrderListDAO dao;
    public Repository(OrderListDAO dao){
        this.dao = dao;
    }

    public void insert(OrderList orderList){
        RoomDatabaseDB.databaseWrite.execute(() ->{
            dao.insert(orderList);
        });
    }

    public LiveData<List<OrderList>> getAllData(){
       return dao.getAllData();
    }

    public LiveData<List<OrderList>> getAllPendingList(){
        return dao.getPendingOrderList();
    }

    public void deleteAll(){
        RoomDatabaseDB.databaseWrite.execute(()->{
            dao.deleteAll();
        });
    }
    public void update(OrderList obj){
        RoomDatabaseDB.databaseWrite.execute(()->{
            dao.update(obj);
        });
    }
    public void delete(OrderList list){
        RoomDatabaseDB.databaseWrite.execute(()->{
            dao.delete(list);
        });
    }

    public LiveData<OrderList> findId(Integer id){
        return dao.findId(id);
    }
}
