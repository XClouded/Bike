package com.yhj.app.bike.model;

import java.io.Serializable;

import com.google.gson.Gson;
import com.yhj.app.bike.command.HttpTagDispatch.HttpTag;

public class BaseReq implements Serializable{
	
	private String cmd ;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public void setCmd(HttpTag tag) {
		this.cmd = String.valueOf(tag);
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public boolean needAuth()
	{
		return true;
	}
}
