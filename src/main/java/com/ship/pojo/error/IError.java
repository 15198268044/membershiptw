package com.ship.pojo.error;
/**
 * @author Mryang
 * @createTime 2017年3月16日
 * @version 1.0
 * 系统级错误
 */
public interface IError {

	/**
	 * 错误代码
	 * @return
	 */
	public String getErrCode();

	/**
	 * 错误消息
	 * @return
	 */
	public  String getErrMessage();
}
