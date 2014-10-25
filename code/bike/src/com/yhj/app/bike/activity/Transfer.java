package com.yhj.app.bike.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhj.app.bike.R;


public class Transfer extends BaseFragment {
	
	public static Fragment create() {
		return new Transfer();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_transfer, null);
		return view;
	}
}
