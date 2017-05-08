package com.ship.common.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * web工具类
 */
public class WebUtil {




    /**
     * 获取ip地址
     * @return
     */
    public static String getIpaddr(){
     String ipaddr = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ipaddr = addr.getHostAddress();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
       return  ipaddr;
    }




    /**
     * 打印request 请求参数
     * @param request
     */
    public static  String getRequestParammeterMap(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String[]> next = iterator.next();
            String[] value = next.getValue();
            for (String string : value) {
                if ("userId".equals(next.getKey())){
                    return string;
                }

            }
        }
        return  "";
    }

}
