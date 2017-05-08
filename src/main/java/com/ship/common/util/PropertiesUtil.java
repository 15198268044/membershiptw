package com.ship.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
	
	private static Properties pro = new Properties();

	static {
		InputStream is = PropertiesUtil.class .getResourceAsStream("/config.properties");
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProperties(){
		return pro;
	}
	
	
	
	

}
