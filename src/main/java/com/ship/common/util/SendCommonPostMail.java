package com.ship.common.util;
import java.util.HashMap;
import java.util.Map;
/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/23.
 * 发送电子邮件
 */
public class SendCommonPostMail {

    private static  final  String  url = "http://api.sendcloud.net/apiv2/mail/send";
    private static  final  String  apiUser = "mryanger_test_7lqApn";
    private static  final  String  apiKey = "EHZ74EbwkvExO1Ns";
    private static  final  String  title = "来自皇家平台邮件";


    public static void  sendEmail(String recipient,String content)    {
        Map<String,String> map = new HashMap<String, String>();
        map.put("apiUser", apiUser);
        map.put("apiKey", apiKey);
        map.put("from", "service@sendcloud.im");
        map.put("fromName", "");
        map.put("to", recipient);
        map.put("subject", title);
        map.put("html", "您的验证码："+content);
        String res = HttpRequestUtil.postRequest(url,map);
        Log4j.info(res);

    }

    public static void main(String[] args)  {

     //sendEmail("646059208@qq.com","25985");
   // sendEmail("wangyuan5070@qq.com","25985");

    }


}
