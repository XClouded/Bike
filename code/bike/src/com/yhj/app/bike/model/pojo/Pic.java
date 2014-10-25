package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class Pic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6433313596164805473L;
    private String pic;
    private String logo;
    private String background;
    private String start;
    private String end;
    private String mode;
    private String time;
    
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setPic(String pic){
    	this.pic = pic;
    }

    public String getPic(){
    	return this.pic;
    }
    
    public void setLogo(String logo){
    	this.logo = logo;
    }
    
    public String getLogo(){
    	return this.logo;
    }
    
    public void setBackGround(String background){
    	this.background = background;
    }
    
    public String getBackGround(){
    	return this.background;
    }
    
    public void setStart(String start){
    	this.start = start;
    }
    
    public String getStart(){
    	return this.start;
    }
    
    public void setEnd(String end){
    	this.end = end;
    }
    
    public String getEnd(){
    	return this.end;
    }
    
    public void setMode(String mode){
		this.mode = mode;
	}
	
	public String getMode(){
		return this.mode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new Gson().toJson(this);
	}
}
