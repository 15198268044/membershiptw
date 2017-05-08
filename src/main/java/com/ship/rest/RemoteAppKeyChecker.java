package com.ship.rest;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import java.lang.reflect.Method;

/**
 * @author Mryang
 * @createTime 2017年3月20日
 * @version 1.0
 */
public class RemoteAppKeyChecker implements MethodInterceptor{

	@Inject
	private UserAccountService userAccountService;


	//检查appkey
	public Object invoke(MethodInvocation in) throws Throwable {

		userAccountService.userServer();
		Method method = in.getMethod();
		Object[]  ob = in.getArguments();
		for (Object os:ob) {
			 System.out.println(os);
		}
		System.out.println(method.getName()+"~~~~~~~~~~~~~~~");
		return in.proceed();
	}

}
