package com.lnu.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
@SuppressWarnings("deprecation")
public class IndexPageActivity extends TabActivity{
	@SuppressWarnings("unused")
	private Button reback_button;
	private TextView view_result;

	@SuppressWarnings("deprecation")
	@Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("手机客户端-主界面");
        setContentView(R.layout.indexpage);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        
        AnimationSet set = new AnimationSet(false);
        Animation animation = new AlphaAnimation(0,1);
        animation.setDuration(500);
        set.addAnimation(animation);
        
        animation = new TranslateAnimation(1, 13, 10, 50);
        animation.setDuration(300);
        set.addAnimation(animation);
        
        animation = new RotateAnimation(30,10);
        animation.setDuration(300);
        set.addAnimation(animation);
        
        animation = new ScaleAnimation(5,0,2,0);
        animation.setDuration(300);
        set.addAnimation(animation);
        
        LayoutAnimationController controller = new LayoutAnimationController(set, 1);
        
        gridview.setLayoutAnimation(controller);
        
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    // 继承BaseAdapter
    public class ImageAdapter extends BaseAdapter {
    	
    	LayoutInflater inflater;
    	
    	// 上下文
        private Context mContext;
        
        // 图片资源数组
        private Integer[] mThumbIds = {
                R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon
        };
        private String[] menuString = {"学院信息管理","专业信息管理","班级信息管理","学生信息管理","教师信息管理","课程信息管理","选课信息管理","成绩信息管理","新闻信息管理"};

        // 构造方法
        public ImageAdapter(Context c) {
            mContext = c;
            inflater = LayoutInflater.from(mContext);
        }
        // 组件个数
        public int getCount() {
            return mThumbIds.length;
        }
        // 当前组件
        public Object getItem(int position) {
            return null;
        }
        // 当前组件id
        public long getItemId(int position) {
            return 0;
        }
        // 获得当前视图
        @SuppressLint("ViewHolder")
        public View getView(int position, View convertView, ViewGroup parent) { 
        	View view = inflater.inflate(R.layout.gv_item, null);
			TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
			ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);  
			tv.setText(menuString[position]); 
			iv.setImageResource(mThumbIds[position]); 
			  switch (position) {
				case 0:
					// 签到管理监听器
					view.setOnClickListener(SignIn);
					break;
				case 1:
					// 作业发布管理监听器
					view.setOnClickListener(homework);
					break;
				case 2:
					// 时间2管理监听器
					view.setOnClickListener(event2);
					break;
				case 3:
					// 事件3管理监听器
					view.setOnClickListener(event3);
					break;
				case 4:
					// 事件4管理监听器
					view.setOnClickListener(event4);
					break;
				case 5:
					// 事件5管理监听器
					view.setOnClickListener(event5);
					break;
				case 6:
					// 事件6管理监听器
					view.setOnClickListener(event6);
					break;
				case 7:
					// 事件6管理监听器
					view.setOnClickListener(event7);
					break;
				case 8:
					// 事件8管理监听器
					view.setOnClickListener(event8);
					break;

			 
				default:
					break;
				} 
			return view; 
        }
       
    }
    
    OnClickListener SignIn = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动学院信息管理Activity
			intent.setClass(IndexPageActivity.this, SignInActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener homework = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动专业信息管理Activity
			intent.setClass(IndexPageActivity.this, HomeWorkActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event2 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动班级信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event3 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动学生信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event4 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动教师信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event5 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动课程信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event6= new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动选课信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event7 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动成绩信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event8 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动新闻信息管理Activity
			intent.setClass(IndexPageActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "重新登入");  
		menu.add(0, 2, 2, "退出"); 
		return super.onCreateOptionsMenu(menu); 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {//重新登入 
			Intent intent = new Intent();
			intent.setClass(IndexPageActivity.this,
					LogInActicity.class);
			startActivity(intent);
		} else if (item.getItemId() == 2) {//退出
			System.exit(0);  
		} 
		return true; 
	}
}
