package com.lnu.fragment;

import com.lnu.bean.QuestionBean;
import com.example.view.NoScrollListview;
import com.lnu.activity.HomeWorkActivity;
import com.lnu.activity.R;
import com.lnu.adapter.OptionsListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
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

/**
 * @version 1.0
 * @author lgx
 * @date 2019-8-24
 */

public class QuestionItemFragment extends Fragment {
	QuestionBean questionBean;
	int index ;
	private OptionsListAdapter adapter;
	private StringBuffer sb;
	private NoScrollListview lv;
	LocalBroadcastManager mLocalBroadcastManager;
 
	public QuestionItemFragment(int index){
		this.index = index;
		questionBean =HomeWorkActivity.questionlist.get(index);
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
		//TODO 閻忕偞娲栫槐鎲�istvie闁圭鎷烽柡鍫濐槸閻℃瑩寮堕敍鍕獥濞达綀娉曢弫銈嗙閸℃艾娈伴悗瑙勭煯缁犵儾istview闁挎稑濂旂粭鍛存閵忋垺鐣遍柡鍌濐潐绾爼寮垫径鎰紪濡府鎷�
		//setListViewHeightBasedOnChildren(lv);
		
	 
		tv_paper_name.setText("濞戞挻鎹囬妴宥夊疾妤﹀灝鍘寸紓浣稿暕缁★拷(閻熷嚖鎷烽悹鍥跺幘閹﹦鎲撮敐鍕憿閻炴稏鍔忛幓锟�)");
		tv_sequence.setText((index+1)+"");
		tv_total_count.setText("/"+HomeWorkActivity.questionlist.size());
		
		tv_description.setText(questionBean.getDescription());
		
		//濡増锚閸忛亶骞撹箛姘墯闁告挸绉瑰浼村礉閻樿京鐟�(闁告娲熼敓钘夘樀椤ｏ拷)闁硅揪鎷�(濠㈣埖宀搁敓钘夘樀椤ｏ拷)
		//闁告帇鍊栭弻鍥及椤栨艾绀嬮梺顐㈩槼缁绘洟寮伴姘兼▼闂侇偓鎷�
		int questionType = questionBean.getQuestionType();
		sb = new StringBuffer();
		if(questionType == 1){//闁告娲熼敓鏂ゆ嫹
			SpannableStringBuilder ssb = new SpannableStringBuilder("(闁告娲熼敓钘夘樀椤ｏ拷)");  
			ssb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			ssb.append(questionBean.getDescription());
			tv_description.setText(ssb);
			lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
					adapter.notifyDataSetChanged();
					//TODO 闁告娲熼敓钘夘樀椤ｄ粙鏌呮径澶庡幀闁告艾姘﹂崵婊堝礉閵娿劎鍎查弶鐑嗗墮閸╁本绋夌�ｂ晝顏卞銈忔嫹
					 Intent intent = new Intent("com.leyikao.jumptonext");
	                    mLocalBroadcastManager.sendBroadcast(intent);
					
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
					Toast.makeText(getActivity(), "闂侇偄顦懙鎴︽儍閸曨垽鎷锋径鎰╋拷宥嗙▔閿燂拷"+sb.toString(), Toast.LENGTH_SHORT).show();
					sb.setLength(0);
				}
			});
		}else if(questionType == 2){//濠㈣埖宀搁敓鏂ゆ嫹
			SpannableStringBuilder ssb = new SpannableStringBuilder("(濠㈣埖宀搁敓钘夘樀椤ｏ拷)");  
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
					Toast.makeText(getActivity(), "闂侇偄顦懙鎴︽儍閸曨垽鎷锋径鎰╋拷宥嗙▔閿燂拷"+sb.toString(), Toast.LENGTH_SHORT).show();
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