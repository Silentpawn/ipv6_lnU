package com.lnu.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import com.lnu.utill.UserMessage;
import com.lnu.utill.httputils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChildMenuActivity extends AppCompatActivity {
    public static Integer Cid;
    private Integer workIcon= R.drawable.color1;
    private Integer workIcon2= R.drawable.color1;
    List<Integer> plist=new ArrayList<Integer>();
    private Integer[] s = {
            workIcon,workIcon2,R.drawable.operateicon };

    private Integer[] mThumbIds = {R.drawable.operateicon, 0, 0, 0, 0, 0, 0, 0, 0 };
    private String[] menuString = {"登出","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("试卷页");
        try {
            loadData();
            menuString[0]="登出";
            int i=0;
            for(Integer p:plist){
                System.out.println("__________"+p+"-------------");
                i+=1;
                if(i>=8)
                    break;
                menuString[i]="试卷"+p;
                mThumbIds[i]=R.drawable.color1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_chiled_menu);
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

    private void loadData() throws InterruptedException {// 架子问题及选项
        Thread load=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    GetInformation(UserMessage.Cidlist.get(UserMessage.Position-1).toString(),UserMessage.UserId.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        load.start();
        load.join();
    }


    private void GetInformation(String id,String sid) throws IOException {// 架子问题及选项
        String Log_sit= httputils.BASE_URL+"login/checktask";//路由器
        String flog=null;
        URL url = new URL(Log_sit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        System.out.println("#"+id+"|"+sid+"|");
        connection.setRequestProperty("id",id);
        connection.setRequestProperty("sid",sid);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        String params =  "id="+id+"&sid="+sid;

        OutputStream out = connection.getOutputStream();
        out.write(params.getBytes());
        out.flush();
        out.close();
        String msg = "";
        int code = connection.getResponseCode();
        if (code == 200) {
            //test="999";
            Log.i("MainmenuActivity","code");
            System.out.println("____________________________");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                msg += line + "\n";
                flog=msg;
            }
            reader.close();
        }

        // 5. 断开连接
        connection.disconnect();

        // 处理结果
        System.out.println("###########################################");
        System.out.println("################"+flog+"#####################");
        System.out.println("###########################################");
        try {
            JSONArray array = new JSONArray(flog);
            for(int i=0;i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                plist.add(Integer.valueOf(obj.getInt("id")));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        UserMessage.mload = true;


    }
    // 继承BaseAdapter
    public class ImageAdapter extends BaseAdapter {

        LayoutInflater inflater;

        // 上下文
        private Context mContext;
        // 图片资源数组
        private Integer[] s = {
                workIcon,workIcon2,R.drawable.operateicon };

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
        @SuppressLint("InflateParams") public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.gv_item, null);
            TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
            ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);
            tv.setText(menuString[position]);
            iv.setImageResource(mThumbIds[position]);
            switch (position) {
                case 0:
                    // 事件作业
                    view.setOnClickListener(event0);
                    break;
                case 1:
                    //事件签到
                    if(plist.size()>=1&&( mThumbIds[1]==R.drawable.color1)) {
                        view.setOnClickListener(event1);
                    }
                    break;
                case 2:
                    if(plist.size()>=2&&( mThumbIds[2]==R.drawable.color1)) {
                        // 事件3监听器
                        view.setOnClickListener(event2);
                    }
                    break;
                case 3:
                    if(plist.size()>=3&&( mThumbIds[3]==R.drawable.color1)) {
                        // 事件4监听器{
                        view.setOnClickListener(event3);
                    }
                    break;
                case 4:
                    if(plist.size()>=4&&( mThumbIds[4]==R.drawable.color1)) {
                        // 事件5监听器{
                        view.setOnClickListener(event4);
                    }
                    break;
                case 5:
                    if(plist.size()>=5&&( mThumbIds[5]==R.drawable.color1))
                    // 事件6监听器
                    {
                        view.setOnClickListener(event5);

                    }
                    break;
                case 6:
                    if(plist.size()>=6&&( mThumbIds[6]==R.drawable.color1))
                    // 事件7监听器
                    {
                        view.setOnClickListener(event6);

                    }
                    break;
                case 7:
                    if(plist.size()>=7&&( mThumbIds[7]==R.drawable.color1))
                    // 事件8管理监听器
                    {
                        view.setOnClickListener(event7);

                    }
                    break;
                case 8:
                    if(plist.size()>=8&&( mThumbIds[8]==R.drawable.color1))
                    // 事件9管理监听器
                    {
                        view.setOnClickListener(event8);

                    }
                    break;

                default:
                    break;
            }
            return view;
        }

    }

    View.OnClickListener event0 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件0Activity 回答卷子
            intent.setClass(ChildMenuActivity.this,  MainmenuActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener event1 = new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件1Activity 签到功能
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid", UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(0)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();

        }
    };
    View.OnClickListener event2 = new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件2Activity
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(1)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    };

    View.OnClickListener event3 = new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件3Activity
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(2)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    };



    View.OnClickListener event4= new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件Activity
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(3)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    };

    View.OnClickListener event5 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(4)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    };

    View.OnClickListener event6 = new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件Activity
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(5)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    };
    View.OnClickListener event7 = new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件3Activity
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(6)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    };

    View.OnClickListener event8 = new View.OnClickListener() {
        public void onClick(View v) {
            // 启动事件3Activity
            Intent intent = new Intent();
            intent.setClass(ChildMenuActivity.this,  HomeWorkActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("cid",UserMessage.Cidlist.get(UserMessage.Position-1).toString());
            bundle.putString("pid",String.valueOf(plist.get(7)));
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
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
            intent.setClass(ChildMenuActivity.this,
                    MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == 2) {//退出
            System.exit(0);
        }
        return true;
    }
}