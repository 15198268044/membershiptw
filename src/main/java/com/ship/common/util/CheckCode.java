package com.ship.common.util;

import java.util.Random;

/**
 * 生成图形验证码
 */
public class CheckCode {

    public static final char[] code = {'a','b','c','d','e','f','g',
            'h','i','j','k','l','m','n',
            'o','p','q','r','s','t',
            'u','v','w','x','y','z',
            'A','B','C','D','E','F','G',
            'H','I','J','K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z',
            '0','1','2','3','4',
            '5','6','7','8','9'};


    public static String getSms(){
        StringBuffer checkcode=new StringBuffer();
            // 每循环一次，生成一位
        for(int i=0;i<4;i++){
            int generated=(new Random()).nextInt(62);
            checkcode.append(code[generated]);
        }
        return  checkcode.toString();
    }


}
