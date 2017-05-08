package com.ship.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSONObject;
/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 发送网络请求
 */
public class HttpRequestUtil {
	
	public static String postRequest(String url, Map<String, String> params) {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建POST方法的实例
		PostMethod postMethod = new PostMethod(url);
		// 设置请求头信息
		postMethod.setRequestHeader("Connection", "close");
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		// 添加参数
		for (Map.Entry<String, String> entry : params.entrySet()) {
			postMethod.addParameter(entry.getKey(), entry.getValue());
		}
		// 使用系统提供的默认的恢复策略,设置请求重试处理，用的是默认的重试处理：请求三次
		httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		// 接收处理结果
		String result = null;
		try {
			// 执行Http Post请求
			httpClient.executeMethod(postMethod);
			// 返回处理结果
			result = postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			System.out.println("发生网络异常!");
			e.printStackTrace();
		} finally {
			// 释放链接
			postMethod.releaseConnection();
			// 关闭HttpClient实例
			if (httpClient != null) {
				((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
				httpClient = null;
			}
		}
		return result;
	}


	/**
	 * jsonp跨域请求数据响应<br/>
	 * 方法名：responsejsonpData<br/>
	 * @author：Mryang<br/>
	 * @createTime：2016年7月31日-下午11:17:31 <br/>
	 * @tel: 15198268054<br/>
	 * @param request
	 * @param response
	 * @param map void<br/>
	 * @exception <br/>
	 * @since  1.0.0
	 */
	public void responsejsonpData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		 response.setCharacterEncoding("UTF-8");
		 response.setHeader("Content-Type", "text/html;Charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
		    String params = request.getParameter("callback");
		    String json = JSONObject.toJSONString(map);
			writer.print(params+ "("+json+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
/*
        String url = "http://jifen.juhlgo.com/index.php?c=account&a=register_post_json";
        Map<String,String> map = new HashMap<String, String>();
        map.put("phone","15198268564");
        map.put("nickname","test");
        map.put("password","yang123asd");
        String str =  postRequest(url,map);
        JSONObject obj = JSONObject.parseObject(str);*/
//      String string = URLDecoder.decode("\u6ce8\u518c\u6210\u529f\uff01\uff01","UTF-8");
//      System.err.println(string);

	}
	
	
	
	
	
	
	
	
}
