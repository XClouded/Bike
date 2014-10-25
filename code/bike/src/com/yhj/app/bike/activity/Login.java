package com.yhj.app.bike.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yhj.app.bike.R;
import com.yhj.app.bike.command.HttpDataRequest;
import com.yhj.app.bike.command.HttpTagDispatch.HttpTag;
import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.http.request.HttpRequestFactory;
import com.yhj.app.bike.model.LoginRes;
import com.yhj.app.bike.model.OrgData;
import com.yhj.app.bike.model.dataloader.BaseDataLoader;
import com.yhj.app.bike.model.dataloader.CacheDataLoader;
import com.yhj.app.bike.model.req.LoginReq;

public class Login extends BaseActivity {
	
	private EditText mUsernameEdit = null;
	private EditText mPassEdit = null;
	
	private Button mLoginBtn = null;
	private Button mRegisterBtn = null;
	
	private final String TAG_LOGIN_LOADER = "login";
	private CacheDataLoader loginLoader = new CacheDataLoader(TAG_LOGIN_LOADER, this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.initView();
		
		this.initListener();
	}
	
	private void initView() {
		setContentView(R.layout.login_activity);
		
		this.mUsernameEdit = (EditText) this.findViewById(R.id.username);
		this.mPassEdit = (EditText) this.findViewById(R.id.password);
		
		this.mLoginBtn = (Button) this.findViewById(R.id.login);
		this.mRegisterBtn = (Button) this.findViewById(R.id.to_register);
	}

	private void initListener() {
		this.mLoginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LoginReq reqObj = new LoginReq();
				
				reqObj.setCmd(HttpTag.LOGIN);
				reqObj.setUsername(mUsernameEdit.getText().toString());
				reqObj.setPasswd(mPassEdit.getText().toString());
				
				HttpDataRequest request =  HttpRequestFactory.getInstance().post(reqObj, "/login");
				loginLoader.loadData(BaseDataLoader.LOADER_FROM_SRV, request);
			}
		});
		
		this.mRegisterBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				go(Register.class);
			}
		});
	}
	
	@Override
	public void onQueryError(BaseDataLoader dataloader, HttpCode retCode,
			String msg) {
		
	}

	@Override
	public void onQueryComplete(BaseDataLoader dataloader, boolean cache) {
		OrgData org = dataloader.getOrgData();
		
		LoginRes res = (LoginRes) org.getData();
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
