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
import com.yhj.app.bike.model.OrgData;
import com.yhj.app.bike.model.dataloader.BaseDataLoader;
import com.yhj.app.bike.model.dataloader.CacheDataLoader;
import com.yhj.app.bike.model.req.RegisterReq;

public class Register extends BaseActivity {
	
	//用户名
	private EditText mUsernameEdit = null;
	//用户密码
	private EditText mPassEdit = null;
	//确认密码
	private EditText mConfirmPassEdit = null;
	//注册按钮
	private Button mRegisterBtn = null;
	
	private final String TAG_REGISTER_LOADER = "register";
	private CacheDataLoader registerLoader = new CacheDataLoader(TAG_REGISTER_LOADER,this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.initView();
		
		this.initListener();
	}
	
	private void initView() {
		setContentView(R.layout.register_activity);
		
		this.mUsernameEdit = (EditText) this.findViewById(R.id.username);
		this.mPassEdit = (EditText) this.findViewById(R.id.passwd);
		this.mConfirmPassEdit = (EditText) this.findViewById(R.id.confirm_passwd);
		
		this.mRegisterBtn = (Button) this.findViewById(R.id.register);
	}
	
	private void initListener() {
		this.mRegisterBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RegisterReq reqObj = new RegisterReq();
				
				reqObj.setCmd(HttpTag.REGIST);
				reqObj.setUsername(mUsernameEdit.getText().toString());
				reqObj.setPasswd(mPassEdit.getText().toString());
				
				HttpDataRequest request = HttpRequestFactory.getInstance().post(reqObj, "/register");
				registerLoader.loadData(BaseDataLoader.LOADER_FROM_SRV, request);

			}
		});
	}
	
	@Override
	public void onQueryError(BaseDataLoader dataloader, HttpCode retCode,
			String msg) {
		// TODO Auto-generated method stub
		super.onQueryError(dataloader, retCode, msg);
	}

	@Override
	public void onQueryComplete(BaseDataLoader dataloader, boolean cache) {
		// TODO Auto-generated method stub
		super.onQueryComplete(dataloader, cache);
		
		if (TAG_REGISTER_LOADER.equals(dataloader.getTag())) {
			OrgData orgObj = dataloader.getOrgData();
			
			
		}
	}
	
	
}
