package com.lnu.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.lnu.adapter.OptionsListAdapter;
import com.lnu.bean.QuestionBean;
import com.lnu.main.HomeWorkActivity;
import com.lnu.main.R;
import com.lnu.utill.UserMessage;
import com.lnu.view.NoScrollListview;

/**
 * @version 1.0
 * @author lgx
 * @date 2019-8-24
 */

@SuppressLint("ValidFragment")
public class QuestionItemFragment extends Fragment {
	QuestionBean questionBean;
	int index=0 ;
	private OptionsListAdapter adapter;
	private StringBuffer sb;
	private NoScrollListview lv;      //得到bundle对象  ；

	LocalBroadcastManager mLocalBroadcastManager;

	public QuestionItemFragment(int index){//问题页面索引号获得
		this.index = index;
		questionBean = HomeWorkActivity.questionlist.get(index);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
		View rootView = inflater.inflate(R.layout.pager_item,
				container, false);
		lv = (NoScrollListview) rootView.findViewById(R.id.lv_options);
		TextView tv_paper_name = (TextView) rootView.findViewById(R.id.tv_paper_name);
		TextView tv_sequence = (TextView) rootView.findViewById(R.id.tv_sequence);
		TextView tv_total_count = (TextView) rootView.findViewById(R.id.tv_total_count);
		TextView tv_description = (TextView) rootView.findViewById(R.id.tv_description);
		Button btn_submit = (Button) rootView.findViewById(R.id.btn_submit);


		adapter = new OptionsListAdapter(getActivity(), questionBean.getQuestionOptions(),lv,index);
		lv.setAdapter(adapter);
		//TODO
		/**
		 * 作业应答页，
		 * */
		//TODO
		//可以跟作业的科目连接
		/*
		 * 位置信息表述
		 * */
		tv_sequence.setText((index+1)+"");
		tv_total_count.setText("/"+HomeWorkActivity.questionlist.size());//位置信息表述
		/*
		 * 考虑将单选多选各自创建一个list
		 * */
		tv_description.setText(questionBean.getDescription());
		int questionType = questionBean.getQuestionType();
		sb = new StringBuffer();
		if(questionType == 1){//

			tv_paper_name.setText("单选题：总体问题描述");
			SpannableStringBuilder ssb = new SpannableStringBuilder((index+1)+"(单选)");//问题你
			ssb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			ssb.append(questionBean.getDescription());
			tv_description.setText(ssb);
			lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position,
										long id) {
					//TODO
					/**
					 * 单选记录借鉴自多选提交
					 * */
					adapter.notifyDataSetChanged();
					long[] ids = lv.getCheckedItemIds();
					for (int i = 0; i < ids.length; i++) {
						long id1 = ids[i];
						//sb.append(questionBean.getQuestionOptions().get((int)id1).getName()).append(" ");
					}
					//TODO
					//跳转功能，借鉴自别人代码，为进度，暂时注释掉
					/*
					 * Intent intent = new Intent("com.lnu.jumptonext");
					 * mLocalBroadcastManager.sendBroadcast(intent);
					 */

				}
			});
			btn_submit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					long[] ids = lv.getCheckedItemIds();
					for (int i = 0; i < ids.length; i++) {
						long id = ids[i];
						sb.append(questionBean.getQuestionOptions().get((int)id).getName()).append(" ");
					}
					//TODO
					/*
					 *在这里可以将答案封装起来，答案应该为一个List<string>用来保证答案的性质，index可以作为list的索引
					 * */
					Intent intent = new Intent("com.lnu.jumptonext");
					mLocalBroadcastManager.sendBroadcast(intent);
					if(UserMessage.answer_map.get(Integer.valueOf(index))!=null)
						UserMessage.answer_map.remove(Integer.valueOf(index));
					UserMessage.answer_map.put(Integer.valueOf(index),sb.toString());
					Toast.makeText(getActivity(), "第"+(index+1)+"题"+"你选择的答案为："+sb.toString(), Toast.LENGTH_SHORT).show();
					sb.setLength(0);
				}
			});
		}else if(questionType == 2){//

			tv_paper_name.setText("多选题总体问题描述");
			SpannableStringBuilder ssb = new SpannableStringBuilder((index+1)+"(多选)");
			ssb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			ssb.append(questionBean.getDescription());
			tv_description.setText(ssb);
			lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position,
										long id) {
					adapter.notifyDataSetChanged();
				}
			});
			btn_submit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					long[] ids = lv.getCheckedItemIds();
					for (int i = 0; i < ids.length; i++) {
						long id = ids[i];
						sb.append(questionBean.getQuestionOptions().get((int)id).getName()).append(" ");
					}
					//TODO
					/*
					 * 同上，可以将
					 *在这里可以将答案封装起来，答案应该为一个List<string>用来保证答案的性质，index可以作为list的索引
					 * */
					Intent intent = new Intent("com.lnu.jumptonext");
					mLocalBroadcastManager.sendBroadcast(intent);
					if(UserMessage.answer_map.get(Integer.valueOf(index))!=null)
						UserMessage.answer_map.remove(Integer.valueOf(index));
					UserMessage.answer_map.put(Integer.valueOf(index),sb.toString());

					Toast.makeText(getActivity(),"第"+(index+1)+"题你选择的答案为："+sb.toString(), Toast.LENGTH_SHORT).show();
					sb.setLength(0);
				}
			});
		}

		return rootView;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

}