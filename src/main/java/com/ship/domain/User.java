package com.ship.domain;

/**
 */
public class User {


    private Long userId;

    private  String headUrl;

    public  User(){

    }
    public  User(Long userId,String headUrl){
        this.userId = userId;
        this.headUrl = headUrl;

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}
