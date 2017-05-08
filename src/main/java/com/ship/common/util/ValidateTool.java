package com.ship.common.util;

import com.ship.domain.SysParam;
import sun.misc.MessageUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 数据验证工具类
 */
public class ValidateTool {

    /** 手机 */
    private static final String V_MOBILE="1\\d{10}";



    /**
     * 验证是不是手机号码
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Mobile(String value){
        return match(V_MOBILE,value);
    }


    /**
     * @param regex 正则表达式字符串
     * @param str 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static void main(String[] args) {

        MessageUtils.err(Mobile("15184849498")+"");


    }


}
