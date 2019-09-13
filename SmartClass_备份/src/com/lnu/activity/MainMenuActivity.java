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
        setTitle("�˵�");
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
    
    // �̳�BaseAdapter
    public class ImageAdapter extends BaseAdapter {
    	
    	LayoutInflater inflater;
    	
    	// ������
        private Context mContext;
        
        /***********************����ͼ��***************************************************/
        // ͼƬ��Դ����
        private Integer[] mThumbIds = {
                R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon,R.drawable.operateicon
        };
        
        
        private String[] menuString = {"����0","����1","����2","����3","����4","����5","����6","����7","����8"};

        // ���췽��
        public ImageAdapter(Context c) {
            mContext = c;
            inflater = LayoutInflater.from(mContext);
        }
        // �������
        public int getCount() {
            return mThumbIds.length;
        }
        // ��ǰ���
        public Object getItem(int position) {
            return null;
        }
        // ��ǰ���id
        public long getItemId(int position) {
            return 0;
        }
        // ��õ�ǰ��ͼ
        public View getView(int position, View convertView, ViewGroup parent) { 
        	View view = inflater.inflate(R.layout.gv_item, null);
			TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
			ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);  
			tv.setText(menuString[position]); 
			iv.setImageResource(mThumbIds[position]); 
			  switch (position) {
				case 0:
					// �¼�1������
					view.setOnClickListener(event0);
					break;
				case 1:
					//�¼�2������
					view.setOnClickListener(event1);
					break;
				case 2:
					// �¼�3������
					view.setOnClickListener(event2);
					break;
				case 3:
					// �¼�4������
					view.setOnClickListener(event3);
					break;
				case 4:
					// �¼�5������
					view.setOnClickListener(event4);
					break;
				case 5:
					// �¼�6������
					view.setOnClickListener(event5);
					break;
				case 6:
					// �¼�7������
					view.setOnClickListener(event6);
					break;
				case 7:
					// �¼�8���������
					view.setOnClickListener(event7);
					break;
				case 8:
					// �¼�9���������
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
			// �����¼�0Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event1 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�1Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event2 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�2Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event3 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�3Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event4 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�4Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event5 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�5Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event6 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// ����ѡ�¼�6Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event7 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�7Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};
    OnClickListener event8 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// �����¼�8Activity
			intent.setClass(MainMenuActivity.this, EmptyActivity.class);
			startActivity(intent);
		}
	};


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "���µ���");  
		menu.add(0, 2, 2, "�˳�"); 
		return super.onCreateOptionsMenu(menu); 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {//���µ��� 
			Intent intent = new Intent();
			intent.setClass(MainMenuActivity.this,
					LogInActicity.class);
			startActivity(intent);
		} else if (item.getItemId() == 2) {//�˳�
			System.exit(0);  
		} 
		return true; 
	}
}
