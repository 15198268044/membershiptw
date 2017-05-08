package com.ship.common.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseResponse implements Serializable {
	
	private static final long serialVersionUID = -1030057153846644432L;
	
	private int status;

    private String message;

    private String code;

    private Object date;

    public BaseResponse(int status,String message,String code) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int status,String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse(int status,String message,String code,Object date) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public static String of(int status,String code, String message){
       return JSONObject.toJSONString(new BaseResponse(status,message,code));
    }

    public static String of(int status, String message){
        return JSONObject.toJSONString(new BaseResponse(status,message));
    }
    public static String of(int status, String message,String code, Object data){
        return JSONObject.toJSONString(new BaseResponse(status,message,code,data));
    }

}
