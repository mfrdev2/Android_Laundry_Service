package com.frabbi.londriservice.utils;

import com.frabbi.londriservice.model.AddressModel;
import com.frabbi.londriservice.model.OrderGettingModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonConverter {

  public static String getJsonOrderListString(List<OrderGettingModel> list){
      Gson gson = new Gson();
      return gson.toJson(list);
  }
  public static List<OrderGettingModel> getJsonOrderListObject(String list){
      Gson gson = new Gson();
      return gson.fromJson(list,new TypeToken<List<OrderGettingModel>>(){}.getType());
  }

  public static String getGsonAddressString(AddressModel addressModel){
      return new Gson().toJson(addressModel);
  }
  public static AddressModel getGsonAddressObject(String addressModel){
      return new Gson().fromJson(addressModel,new TypeToken<AddressModel>(){}.getType());
  }
}
