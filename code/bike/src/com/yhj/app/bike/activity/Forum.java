package com.yhj.app.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yhj.app.bike.R;
import com.yhj.app.bike.activity.forum.ForumBikeCategory;


public class Forum extends BaseFragment {
	
	//帖子分类列表
	private ListView mPostCategoryList = null;
	
	public static Fragment create() {
		return new Forum();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum, null);
		
		this.initView(view);
		
		return view;
	}
	
	private void initView(View rootView) {
		this.mPostCategoryList = (ListView) rootView.findViewById(R.id.post_category_list);
		
		this.mPostCategoryList.setAdapter(new PostCategoryAdapter());
		
		this.mPostCategoryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Intent intent = new Intent(getActivity(),ForumBikeCategory.class);
				startActivity(intent);
			}});
	}
	
	class PostCategoryAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
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
			// TODO Auto-generated method stub
			convertView = Forum.this.getActivity().getLayoutInflater().inflate(R.layout.item_post_category,null);
			return convertView;
		}
		
	}
}
