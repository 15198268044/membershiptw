package com.ship.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2017/4/16.
 */
public class UserInfo {

    private static Map<String,Long> mData = new HashMap<String,Long>();


    public static  void setId(Long userId){
        mData.put("userId",userId);
    }

    public static  Long  getId(){
        return  Long.parseLong(mData.get("userId").toString());
    }

    public static  void remove(){

        mData.remove("userId");
    }

    public static Long returnUserId() throws  NullPointerException{

        return  Long.parseLong(mData.get("userId").toString());
    }



}
