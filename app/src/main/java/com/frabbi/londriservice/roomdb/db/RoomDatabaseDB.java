package com.frabbi.londriservice.roomdb.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.frabbi.londriservice.roomdb.entities.OrderList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {OrderList.class},version = 1,exportSchema = false)
public abstract class RoomDatabaseDB extends RoomDatabase {

    public abstract OrderListDAO OrderListDao();

    public static volatile RoomDatabaseDB instance;
    public static final int number_of_threads = 4;
    public static final ExecutorService databaseWrite = Executors.newFixedThreadPool(number_of_threads);

    public static RoomDatabaseDB getInstance(final Context context){
        if (instance == null) {
            synchronized (RoomDatabaseDB.class){
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RoomDatabaseDB.class,
                            "LaundryServiceDB"
                    ).build();                        //.allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
