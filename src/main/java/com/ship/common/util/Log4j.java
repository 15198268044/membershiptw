package com.ship.common.util;

import org.apache.log4j.Logger;

public class Log4j {

	private static Logger logger = Logger.getLogger(Log4j.class); 
	
	
	/**
	 * 记录debug级别的信息
	 * @param log
	 */
	public static void debug(String log) {
	    logger.debug(log);  
	}
	
	
	
	/**
	 * 记录info级别的信息
	 * @param log
	 */
	public static void info(String log) {
	    logger.info(log);  
	}
	
	/**
	 * 记录error级别的信息
	 * @param log
	 */
	public static void error(String log) {
	    logger.error(log);  
	}
}
