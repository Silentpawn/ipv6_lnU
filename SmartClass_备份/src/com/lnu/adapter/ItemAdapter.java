package com.lnu.adapter;
import com.lnu.activity.HomeWorkActivity;

import com.lnu.fragment.QuestionItemFragment;
import com.lnu.fragment.ScantronItemFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @Description: ViewPager问题提出
 * @author lgx
 */
public class ItemAdapter extends FragmentStatePagerAdapter {
	Context context;
	public ItemAdapter(FragmentManager fm) {
		super(fm);
	}
	@Override
	public Fragment getItem(int arg0) {
		if(arg0 == HomeWorkActivity.questionlist.size()){
			return new ScantronItemFragment();
		}
		return new QuestionItemFragment(arg0);
	}
 

	@Override
	public int getCount() {
		 
		return HomeWorkActivity.questionlist.size()+1;
	}
  


}
