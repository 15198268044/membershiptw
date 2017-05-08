package com.ship.common.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/2/28.
 * 读取配置参数
 */
public class Param {

    //获取配置信息
    private static Properties properties = new Properties();

    static{
        try {
            properties.load(Param.class.getClassLoader().getResourceAsStream("param.properties"));//获取properties文件
        } catch (IOException e) {e.printStackTrace();}
    }

    //获取配置参数值
    public static String getValue(String key){
        return (String)properties.get(key);
    }

    /**
     * 获取出初页码
     * 方法名：pageIndex
     * @author：Mryang
     * @createTime：2016年7月9日-上午10:50:42
     * @return Integer
     * @exception
     * @since  1.0.0
     */
    public static Integer pageIndex(){
        return Integer.parseInt(getValue("pageIndex"));
    }
    /**
     * 获取初始页大小
     * 方法名：pageSize
     * @author：Mryang
     * @createTime：2016年7月9日-上午10:50:18
     * @return Integer
     * @exception
     * @since  1.0.0
     */
    public static Integer pageSize(){
        return Integer.parseInt(getValue("pageSize"));
    }


    public static void main(String[] args) {



    }

}
