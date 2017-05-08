package com.ship.common.util;

import java.security.MessageDigest;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/2/27.
 * 加密工具类
 */
public class Encryption {

    public static final String   SALTMARSH = "~!@#$%^&'*(`)>_+?";

    /**
     * MD5加密
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        byte[] md5Bytes = md5.digest(inStr.getBytes());
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static void main(String[] args) {

        System.out.print(MD5("123456"));

    }


}
