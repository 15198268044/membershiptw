package com.ship.rest;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ship.common.util.BaseResponse;
import com.ship.common.util.Status;
import com.ship.common.util.StringUtil;
import com.ship.common.util.UserInfo;
import com.ship.pojo.error.SysError;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author Mryang
 * @createTime 2017年3月20日
 * @version 1.0
 * 登录拦截
 */
public class LoginInterceptor implements MethodInterceptor{


	public Object invoke(MethodInvocation in) throws Throwable {
        String name = in.getMethod().getName();
        long startTime = System.nanoTime();
        System.out.println(String.format("before method[%s] at %s", name, startTime));
        Object obj = null;
        try {
           /* if (name.contains("login") || name.contains("userlogin") || name.contains("exitLogin")){
                obj = in.proceed();// 执行服务
              }else{
                 try{
                    if (StringUtil.isEmpty(UserInfo.returnUserId())){
                        obj = in.proceed();// 执行服务
                    }else{
                      obj =  BaseResponse.of(Status.ERROR, SysError.ACCOUNTNOTLOGIN.getErrMessage());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    obj =  BaseResponse.of(Status.ERROR, SysError.ACCOUNTNOTLOGIN.getErrMessage());
                }
            }*/
            obj = in.proceed();// 执行服务
        } finally {
            long endTime = System.nanoTime();
            System.out.println(String.format("after method[%s] at %s, cost(ns):%d", name, endTime, (endTime - startTime)));
        }
        return obj;
	}

}
