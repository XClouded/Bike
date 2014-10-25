package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class MobleQQAccountsInfo implements Serializable {
    private static final long serialVersionUID = 9L;
    
    private String appid;
    private String paytoken;
    private String openid;
    private long expires;
    private String pfvalue;
    private String pfkey;
    private String access_token;
    
    public MobleQQAccountsInfo(){
        
    }
    
    //appid
    public String getAppid(){
        return appid;
    }
    public void setAppid(String appid){
        this.appid = appid;
    }
    
    //paytoken
    public String getPayToken(){
        return paytoken;
    }
    public void setPayToken(String paytoken){
        this.paytoken = paytoken;
    }

    //openid
    public String getOpenId(){
        return openid;
    }
    public void setOpenId(String openid){
        this.openid = openid;
    }

    //expires
    public long getExpires(){
        return expires;
    }
    public void setExpires(long expires){
        this.expires = expires;
    }

    //pfvalue
    public String getPfValue(){
        return pfvalue;
    }
    public void setPfValue(String pfvalue){
        this.pfvalue = pfvalue;
    }

    //pfkey
    public String getPfKey(){
        return pfkey;
    }
    public void setPfKey(String pfkey){
        this.pfkey = pfkey;
    }
    
    //access_token
    public String getAccessToken(){
        return access_token;
    }
    public void setAccessToken(String access_token){
        this.access_token = access_token;
    }
    
    @Override
    public String toString(){
        // TODO Auto-generated method stub
        return "appid "+appid+"paytoken "+paytoken+"openid "+openid+"expires "+expires+"pfvalue "+pfvalue+"pfkey "+pfkey+"access_token "+access_token;
       
    }
}
