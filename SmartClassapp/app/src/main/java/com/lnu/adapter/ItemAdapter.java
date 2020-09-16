package com.lnu.adapter;
import com.lnu.fragment.QuestionItemFragment;
import com.lnu.fragment.ScantronItemFragment;
import com.lnu.main.HomeWorkActivity;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @Description: ViewPager�������
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
	public int getCount() {//������ҵ����ĳ���

		return HomeWorkActivity.questionlist.size()+1;
	}



}
