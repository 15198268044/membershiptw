package com.ship.common.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendSMS {

	public static  void sendSmsCode(int type,String phone,String checkCode){
		switch (type){
			case 0 :
				send(phone,SMSTemplent.regTemplent(checkCode));
				break;
			case 1 :
				send(phone,SMSTemplent.forgotTemplent(checkCode));
				break;
			case 2 :
				send(phone,SMSTemplent.modifyPassTemplent(checkCode));
				break;
			case 3:
				send(phone,SMSTemplent.modifyBindTelTemplent(checkCode));
				break;
			default:
				System.err.print("----");
		}
	}

	public static void send(String phone, String content)    {
		// 发送内容
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");	
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?");
		try {
			// APIKEY
			sb.append("apikey=" + Param.getValue("apikey"));
			// 用户名
			sb.append("&username=" + Param.getValue("username"));
			// 向StringBuffer追加密码
			sb.append("&password=" + Param.getValue("password"));
			// 向StringBuffer追加手机号码
			sb.append("&mobile=" + phone);
			// 向StringBuffer追加消息内容转URL标准码
			sb.append("&content=" + URLEncoder.encode(content, "GBK"));
			// 创建url对象
			URL url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 返回发送结果
			String inputline = in.readLine();
			// 输出结果
			System.out.println(inputline);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
