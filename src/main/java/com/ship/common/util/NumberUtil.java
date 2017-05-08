package com.ship.common.util;

import java.util.Random;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 数字工具类
 */
public class NumberUtil {


    /**
     * 计算
     * @param money
     * @param proportion
     * @return
     */
    public static Double calc(Double money,Integer proportion){
        return  proportion * (money/100);
    }

    /**
     * 生成随机验证码
     * @return
     */
    public static  String rand(){
        String checkCode = "";
        for (int i = 0; i < 6; i++) {
            checkCode += new Random().nextInt(10);
        }
        return checkCode;
    }



    public static void main(String[] args) {

        System.err.print(calc(100.00,5));


    }




}
