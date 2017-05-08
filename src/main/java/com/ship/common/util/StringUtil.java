package com.ship.common.util;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/2/27.
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 为空判断
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return str == null || str.toString().length() == 0 || str.equals("") || str.toString().matches("\\s*");
    }



    /**
     * 非空判断
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }


    /**
     * 隐藏手机号码
     * @param str
     * @return
     */
    public static String hideTel(String str){
        return  str.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }

    /**
     * 生成邀请码
     * @return
     */
    public static  String getInviteCode(int bit){
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bit; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 由年月日时分秒+3位随机数
     * 生成流水号
     * @return
     */
    public static String getSerial(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = formatter.format(currentTime);
        int x=(int)(Math.random()*999999)+100;
        String serial = t + x;
        return serial;
    }


    public static void main(String[] args) {
        System.out.print(getSerial());
    }




}
