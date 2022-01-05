package com.frabbi.londriservice.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.frabbi.londriservice.model.OrderGettingModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPref {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        this.preferences = context.getSharedPreferences("OrderList",Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

   public void setOrderList(List<OrderGettingModel> orderList){
       editor.putStringSet("orderList", new HashSet<String>(Collections.singleton(new Gson().toJson(orderList))));
   }
   public List<OrderGettingModel> getOrderList(){
        try {
            Set<String> orderList = preferences.getStringSet("orderList", null);
            Type type = new TypeToken<List<OrderGettingModel>>() {
            }.getType();
            String str = null;
            for (String s : orderList) {
                str = s;
            }
            return new Gson().fromJson(str,type);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

   }


}
