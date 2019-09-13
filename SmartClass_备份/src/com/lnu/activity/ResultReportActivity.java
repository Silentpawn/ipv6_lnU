package com.lnu.activity;
import com.lnu.activity.R;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @version 1.0
 * @author lgx
 * @date 2019-6-24
 */

public class ResultReportActivity extends FragmentActivity  {
	LocalBroadcastManager mLocalBroadcastManager;
	int count = HomeWorkActivity.questionlist.size();
	int[] mIds = new int[count];


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_report);
		
		initData();
		 mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
		GridView gv = (GridView) findViewById(R.id.report_gv);
		TextView tv_report_total_question = (TextView) findViewById(R.id.tv_report_total_question);
		TextView tv_report_exam_type = (TextView) findViewById(R.id.tv_report_exam_type);
		RelativeLayout rl_result_panel = (RelativeLayout) findViewById(R.id.rl_result_panel);
		//鐠佸墽鐤唖crollview 閼奉亜濮╃純顕�銆�
		rl_result_panel.setFocusable(true);
		rl_result_panel.setFocusableInTouchMode(true);
		rl_result_panel.requestFocus();
		
		tv_report_total_question.setText("闁拷/"+count+"闁拷");
		MyAdapter adapter = new MyAdapter(this);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO鐠哄疇娴嗛崚鎵祲鎼存梻娈憊iewpager 妞ょ敻娼�
				  Intent intent = new Intent("com.leyikao.jumptopage");
                 intent.putExtra("index", position);
                 mLocalBroadcastManager.sendBroadcast(intent);
			}
		});
	}
	private void initData() {
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
			TextView tv = new TextView(mContext);
			tv.setGravity(Gravity.CENTER);
			tv.setLayoutParams(new GridView.LayoutParams(70, 70));
			tv.setPadding(8, 8, 8, 8);

			tv.setText(mIds[position] + "");
			tv.setBackgroundResource(R.drawable.option_btn_single_normal);
			return tv;
		}

	}
 
}
