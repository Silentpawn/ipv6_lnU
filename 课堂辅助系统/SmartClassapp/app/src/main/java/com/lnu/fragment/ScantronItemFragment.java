package com.lnu.fragment;

import com.lnu.main.HomeWorkActivity;
import com.lnu.main.R;
import com.lnu.main.ResultReportActivity;
import com.lnu.utill.UserMessage;
import com.lnu.view.NoScrollGridView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * @version 1.0
 * @author lgx
 * @date 2019-6-24
 */

public class ScantronItemFragment extends Fragment {
	LocalBroadcastManager mLocalBroadcastManager;
	public ScantronItemFragment() {
		
	}

	int count = HomeWorkActivity.questionlist.size();
	int[] mIds = new int[count];
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initData();
		 mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
		View rootView = inflater.inflate(R.layout.pager_item_scantron,
				container, false);
		@SuppressLint("WrongViewCast") NoScrollGridView gv = (NoScrollGridView) rootView.findViewById(R.id.gridview);//���⿨ѡ��
		TextView tv_submit_result = (TextView) rootView.findViewById(R.id.tv_submit_result);
		tv_submit_result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(UserMessage.answer_map.size()== HomeWorkActivity.tasklist.size()) {
					Intent intent = new Intent(getActivity(), ResultReportActivity.class);
					startActivity(intent);
				}else {
					 Intent intent = new Intent("com.lnu.alert");
					 
	                 // intent.putExtra("index", 0);
	                  mLocalBroadcastManager.sendBroadcast(intent);
				}
				
			}
		});
		MyAdapter adapter = new MyAdapter(getActivity());
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				  Intent intent = new Intent("com.lnu.jumptopage");
                  intent.putExtra("index", position);
                  mLocalBroadcastManager.sendBroadcast(intent);
			//	 Toast.makeText(getActivity(),HomeWorkActivity.questionlist.get(position).getKnowledgePointName().toString(),Toast.LENGTH_SHORT).show();
			}
		});
		return rootView;

	}

	private void initData() {
		// TODO
		  HomeWorkActivity.over=true;
		for (int i = 0; i < count; i++) {
			mIds[i] = i + 1;
		}
	}

	private class MyAdapter extends BaseAdapter {
		private Context mContext;

		public MyAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {
			return mIds.length;
		}

		@Override
		public Object getItem(int position) {
			return mIds[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//ѡ�ѡ��
			TextView tv = new TextView(mContext);
			tv.setGravity(Gravity.CENTER);
			tv.setLayoutParams(new GridView.LayoutParams(70, 70));
			tv.setPadding(8, 8, 8, 8);
			//TODO
			/**
			 * tv��ֵ����Ϊanswer��
			 * */
			tv.setText(mIds[position]+"");
			tv.setBackgroundResource(R.drawable.option_btn_single_normal);
			return tv;
		}

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

}