package com.yhj.app.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.model.dataloader.BaseDataLoader;
import com.yhj.app.bike.model.dataloader.BaseDataLoader.DataLoaderCallback;

public class BaseActivity extends FragmentActivity implements DataLoaderCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	protected void go(Class<?> cls) {
		Intent intent = new Intent(this,cls);
		startActivity(intent);
	}
	
	@Override
	public void onQueryError(BaseDataLoader dataloader, HttpCode retCode,
			String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQueryComplete(BaseDataLoader dataloader, boolean cache) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartQuery(BaseDataLoader dataloader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQuerying(BaseDataLoader dataloader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQueryCanceled(BaseDataLoader dataloader) {
		// TODO Auto-generated method stub
		
	}
	
	
}
