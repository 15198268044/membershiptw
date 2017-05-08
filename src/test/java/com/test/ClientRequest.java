package com.test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/11.
 */
public class ClientRequest {




    private static String apiUrl = "http://127.0.0.1:8080";



    public static String request(String url, Map<String,Object> mDate){
        Client client = new Client();
        Form param = new Form();
        Iterator<Map.Entry<String, Object>> iterator = mDate.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            param.putSingle(next.getKey(),next.getValue());
        }
        String messs =  client.resource(apiUrl).path(url).queryParams(param).type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept(MediaType.APPLICATION_JSON).post(String.class);
        System.out.print(messs);
        return  messs;
    }










}
