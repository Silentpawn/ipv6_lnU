package com.lnu.activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("菜单");
        setContentView(R.layout.main_menu);
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
        
        /***********************更改图标***************************************************/
        // 图片资源数组
        private Integer[] mThumbIds = {
                R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon
        };
        
        
        private String[] menuString = {"功能0","功能1","功能2","功能3","功能4","功能5","功能6","功能7","功能8"};

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
        public View getView(int position, View convertView, ViewGroup parent) { 
        	View view = inflater.inflate(R.layout.gv_item, null);
			TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
			ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);  
			tv.setText(menuString[position]); 
			iv.setImageResource(mThumbIds[position]); 
			  switch (position) {
				case 0:
					// 事件1监听器
					view.setOnClickListener(event0);
					break;
				case 1:
					//事件2监听器
					view.setOnClickListener(event1);
					break;
				case 2:
					// 事件3监听器
					view.setOnClickListener(event2);
					break;
				case 3:
					// 事件4监听器
					view.setOnClickListener(event3);
					break;
				case 4:
					// 事件5监听器
					view.setOnClickListener(event4);
					break;
				case 5:
					// 事件6监听器
					view.setOnClickListener(event5);
					break;
				case 6:
					// 事件7监听器
					view.setOnClickListener(event6);
					break;
				case 7:
					// 事件8管理监听器
					view.setOnClickListener(event7);
					break;
				case 8:
					// 事件9管理监听器
					view.setOnClickListener(event8);
					break;

			 
				default:
					break;
				} 
			return view; 
        }
       
    }
    
    OnClickListener event0 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件0Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event1 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件1Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event2 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件2Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event3 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件3Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event4 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件4Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event5 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件5Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event6 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动选事件6Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event7 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件7Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event8 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动事件8Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
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
			intent.setClass(MainMenuActivity.this,
					LogInActicity.class);
			startActivity(intent);
		} else if (item.getItemId() == 2) {//退出
			System.exit(0);  
		} 
		return true; 
	}
}
