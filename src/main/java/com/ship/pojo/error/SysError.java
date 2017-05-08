package com.ship.pojo.error;
/**
 * @author Mryang
 * @createTime 2017年3月16日
 * @version 1.0
 * 系统级错误
 */
public enum SysError implements IError {

	//系统错误
	SYS_ERROR("0000", "System Internal Error"),

	//参数错误
	SYS_PARAMETER("0001", "Invalid Parameter"),

	//服务找不到
	SERVICE_NOT_FOUND("0002", "Service Not Found"),

	//账户未登录
	ACCOUNTNOTLOGIN("0004", "Account not logged in"),

	//处理成功
	 SUCCESS("0003", "SUCCESS");

	private String code;
    private String message;
    private String ns = "SYS";
    
    SysError(String code,String message){
    	this.code = code;
    	this.message = message;
    }
    
	public String getErrCode() {
		return this.ns+"."+this.code;
	}
	
	public String getErrMessage() {
		return this.message;
	}
	
	public String getNs() {
	        return ns;
	}

}
