package com.frabbi.londriservice.application;

import android.app.Application;

import com.frabbi.londriservice.roomdb.db.RoomDatabaseDB;
import com.frabbi.londriservice.roomdb.repositories.Repository;

public class MyApplication extends Application {
    private static RoomDatabaseDB room;
    private static Repository repository;

    public static Repository getRepository() {
        return repository;
    }

    private synchronized void getInstance(){
        if (room == null) {
            room = RoomDatabaseDB.getInstance(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getInstance();
        if(repository == null){
            repository = new Repository(room.OrderListDao());
        }

    }
}
