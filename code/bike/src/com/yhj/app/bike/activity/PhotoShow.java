package com.yhj.app.bike.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhj.app.bike.R;


public class PhotoShow extends BaseFragment {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.initView();
	}
	
	private void initView() {
		setContentView(R.layout.activity_photo_show);
	}*/
	
	public static Fragment create() {
		return new PhotoShow();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_photo_show, null);
		return view;
	}
}
