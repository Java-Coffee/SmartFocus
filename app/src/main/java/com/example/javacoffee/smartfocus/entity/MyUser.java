package com.example.javacoffee.smartfocus.entity;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser{

    private String mailaccount;


    public String getMailaccount() {
        return mailaccount;
    }

    public void setMailaccount(String mailaccount) {
        this.mailaccount = mailaccount;
    }


}
