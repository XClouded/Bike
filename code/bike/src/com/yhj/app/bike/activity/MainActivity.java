package com.yhj.app.bike.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.yhj.app.bike.R;


public class MainActivity extends BaseActivity {
	
	private ViewPager mMainViewPager = null;
	private RadioGroup mMenuRadioGroup = null;
	
	private List<Fragment> mFragments = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.initView();
	}
	
	private void initView() {
		this.setContentView(R.layout.activity_main);
		
		this.mMainViewPager = (ViewPager)this.findViewById(R.id.view_pager);
		this.mMenuRadioGroup = (RadioGroup) this.findViewById(R.id.radio_group);
		
		this.initViewPager();
		
		this.initListener();
	}
	
	private void initViewPager() {
		this.mFragments = new ArrayList<Fragment>();
		
		this.mFragments.add(PhotoShow.create());
		this.mFragments.add(Forum.create());
		this.mFragments.add(Transfer.create());
		this.mFragments.add(Setting.create());
		
		this.mMainViewPager.setAdapter(new ViewPagerFragmentAdapter(this.getSupportFragmentManager(),mFragments));
		
		this.mMainViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageSelected(int pos) {
				switch(pos) {
					case 0: {
						mMenuRadioGroup.check(R.id.photo_show);
						break;
					}
					case 1: {
						mMenuRadioGroup.check(R.id.forum);
						break;
					}
					case 2: {
						mMenuRadioGroup.check(R.id.transfer);
						break;
					}
					case 3: {
						mMenuRadioGroup.check(R.id.setting);
						break;
					}
				}
				
			}
			
		});
	}
	
	private void initListener() {
		this.mMenuRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup radiogroup, int id) {
				switch(id) {
					case R.id.photo_show: {
						mMainViewPager.setCurrentItem(0);
						break;
					}
					case R.id.forum: {
						mMainViewPager.setCurrentItem(1);
						break;
					}
					case R.id.transfer: {
						mMainViewPager.setCurrentItem(2);
						break;
					}
					case R.id.setting: {
						mMainViewPager.setCurrentItem(3);
						break;
					}
				}
			}});
	}
	
	class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
		
		List<Fragment> mFragments = null;
		
		public ViewPagerFragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
			super(fm);
			this.mFragments = fragments;
		}

		@Override
		public Fragment getItem(int pos) {
			return this.mFragments.get(pos);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return super.isViewFromObject(view, object);
		}
	}
}
