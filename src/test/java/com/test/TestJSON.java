package com.test;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by yang on 2017/4/20.
 */
public class TestJSON {


    public static void main(String[] args) {

        String  str = "{\"code\":\"SYS.0003\",\"date\":{\"userId\":\"9\"},\"message\":\"SUCCESS\",\"status\":1000}";


        JSONObject obj =  JSONObject.parseObject(str);

        JSONObject data =  obj.getJSONObject("date");
        Long s =  data.getLong("userId");




        System.out.print(s+"~~~~~~~~~~~~~~");



    }

}
