package com.yhj.app.bike.activity.forum;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yhj.app.bike.R;
import com.yhj.app.bike.activity.BaseActivity;

public class ForumBikeCategory extends BaseActivity {
	
	private ListView mForumBikeList = null;
	
	private TextView mCloseText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.initView();
	}
	
	private void initView() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_forum_bike_category);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.mForumBikeList = (ListView) this.findViewById(R.id.forum_bike_category_list);
		this.mCloseText = (TextView) this.findViewById(R.id.close);
		
		this.mForumBikeList.setAdapter(new BikeAdapter());
		
		this.initListener();
	}

	private void initListener() {
		this.mForumBikeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				finish();
			}
			
		});
		
		this.mCloseText.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					finish();
				}
				return true;
			}});
	}
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			finish();
		}
		return true;
	}



	class BikeAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 6;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View convertView, ViewGroup arg2) {
			
			convertView = ForumBikeCategory.this.getLayoutInflater().inflate(R.layout.item_forum_bike, null);
			
			return convertView;
		}
		
	}
	
}
