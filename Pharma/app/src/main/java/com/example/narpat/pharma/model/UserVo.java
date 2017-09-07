package com.example.narpat.pharma.model;

/**
 * Created by narpat on 12/8/17.
 */

public class UserVo {


    private int id;
    private String name;
    private String mobile;
    private String password;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile=mobile;

    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }



}
