package com.test;


import java.util.Map;
import java.util.HashMap;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/11.
 */
public class BranchRecordTest {


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<String,Object>();
       /*String s  = "admin/branch/handlerBanch.do";
        map.put("tatalMoney",100);
        map.put("account","15978451256");*/

        String s  = "web/sms/sendSms.do";
        map.put("type",1);
        map.put("phone","15198268044");
        ClientRequest.request(s,map);
    }











}
