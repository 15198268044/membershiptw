package com.ship.common.jpa;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServlet;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
public class Logger extends HttpServlet {



    @Override
    public void init() {

        try{
            String prefix = getServletContext().getRealPath("/");
            String file = getInitParameter("/WEB-INF/classes/log4j.properties");
            if (file != null) {
                PropertyConfigurator.configure(prefix + file);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }





}
