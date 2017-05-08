package com.ship.rest.web;
import com.ship.common.jdbc.UserDao;
import com.ship.common.util.UploadUtil;
import com.ship.common.util.UserInfo;
import com.ship.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


public class FileUpload extends HttpServlet {


    private UserDao user = new UserDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Long userId = UserInfo.getId();
         User userin  =  user.getUser(userId);
         String ctxPath = request.getSession().getServletContext().getRealPath("/") + "/"+UploadUtil.uploadFileUrl+userin.getHeadUrl();
         File file =  new File(ctxPath);
         if (file.exists()){
            file.delete();
         }
         userin.setHeadUrl(UploadUtil.uploadFile(request));
         user.updateHead(userin);

    }



}
