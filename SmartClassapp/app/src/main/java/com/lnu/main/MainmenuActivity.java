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
import android.widget.Toast;

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

public class MainmenuActivity extends AppCompatActivity {

    // private List <String> CourseList;
    private String Log_sit = "./login/checkcourse";

    private Integer[] mThumbIds = { R.drawable.operateicon, 0, 0, 0, 0, 0, 0, 0, 0 };
    private String[] menuString = { "登出", "", "", "", "", "", "", "", "" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserMessage.Cidlist.clear();
        UserMessage.Cnamelist.clear();
        setContentView(R.layout.activity_mainmenu);
        setTitle("主菜单");
            try {
                loadData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        loadView();
        GridView gridview = (GridView) findViewById(R.id.gridview);

        AnimationSet set = new AnimationSet(false);
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        set.addAnimation(animation);
        animation = new TranslateAnimation(1, 13, 10, 50);
        animation.setDuration(300);
        set.addAnimation(animation);

        animation = new RotateAnimation(30, 10);
        animation.setDuration(300);
        set.addAnimation(animation);
        animation = new ScaleAnimation(5, 0, 2, 0);
        animation.setDuration(300);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 1);
        gridview.setLayoutAnimation(controller);
        gridview.setAdapter(new ImageAdapter(this));

        System.out.println(UserMessage.UserId);

    }

    private void loadView() {

        int i=1;
        for (String e : UserMessage.Cnamelist) {
            menuString[i] = e;
            mThumbIds[i++] = R.drawable.operateicon;
        }
    }
    private void loadData() throws InterruptedException {// 架子问题及选项
        Thread load=new Thread(new Runnable() {
                      @Override
                    public void run() {

                          try {
                              GetInformation(UserMessage.UserId.toString());
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }
                    });
        load.start();
        load.join();
    }

    public void GetInformation(String userNo) throws IOException {
        String Log_sit= httputils.BASE_URL+"login/checkcourse";
        String flog=null;
        URL url = new URL(Log_sit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        System.out.println("#"+userNo+"|");
        connection.setRequestProperty("name", userNo.substring(0,userNo.length()-1));
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        System.out.println(userNo+"#########");
        String params =  "name="+userNo;

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
       if(flog!=null)
        try {
            JSONArray array = new JSONArray(flog);
            UserMessage.Cidlist.add(0);
            UserMessage.Cnamelist.add("智能推荐");
            for(int i=0;i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                UserMessage.Cidlist.add(Integer.valueOf(obj.getInt("id")));
                UserMessage.Cnamelist.add(obj.getString("name"));
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

        /***********************
         * 更改图标
         ***************************************************/
        // private Integer[] mThumbIds;
        // 图片资源数组

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
        @SuppressLint("InflateParams")
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = inflater.inflate(R.layout.gv_item, null);
            TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
            ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);
            if (UserMessage.User_type.contentEquals("student")) {
                tv.setText(menuString[position]);
                iv.setImageResource(mThumbIds[position]);
            }

            switch (position) {
                case 0:
                    // 事件1家庭作业事件监听器
                    view.setOnClickListener(event0);
                    break;
                case 1:
                    // 事件2签到时间监听器
                    if(UserMessage.Cnamelist.size()>=1) {
                        view.setOnClickListener(event1);
                    }
                    break;
                case 2:
                    // 事件3监听器
                    if(UserMessage.Cnamelist.size()>=2){
                        view.setOnClickListener(event2);
                    }
                    break;
                case 3:
                    if(UserMessage.Cnamelist.size()>=3) {
                        // 事件4监听器{
                        view.setOnClickListener(event3);
                    }
                    break;
                case 4:
                    if(UserMessage.Cnamelist.size()>=4) {
                        // 事件5监听器{
                        view.setOnClickListener(event4);
                    }
                    break;
                case 5:
                    if(UserMessage.Cnamelist.size()>=5)
                    // 事件6监听器
                    {
                        view.setOnClickListener(event5);
                        UserMessage.Position=5;
                    }
                    break;
                case 6:
                    if(UserMessage.Cnamelist.size()>=6)
                    // 事件7监听器
                    {
                        view.setOnClickListener(event6);
                        UserMessage.Position=6;
                    }
                    break;
                case 7:
                    if(UserMessage.Cnamelist.size()>=7)
                    // 事件8管理监听器
                    {
                        view.setOnClickListener(event7);
                        UserMessage.Position=7;
                    }
                    break;
                case 8:
                    if(UserMessage.Cnamelist.size()>=8)
                    // 事件9管理监听器
                    {
                        view.setOnClickListener(event8);
                        UserMessage.Position=8;
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
            // 启动事件8Activity
            intent.setClass(MainmenuActivity.this, MainActivity.class);
            Toast.makeText(MainmenuActivity.this, UserMessage.Userno + "已经登出", Toast.LENGTH_SHORT).show();
            UserMessage.returnNull();
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener event1 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件Activity
            UserMessage.Position=1;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, HomeWorkActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener event2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件Activity
            UserMessage.Position=2;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener event3 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件3Activity
            UserMessage.Position=3;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };



    View.OnClickListener event4= new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件Activity
            UserMessage.Position=4;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener event5 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件Activity
            UserMessage.Position=5;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener event6 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件Activity
            UserMessage.Position=6;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener event7 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件3Activity
            UserMessage.Position=7;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener event8 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件3Activity
            UserMessage.Position=8;
            Toast.makeText(MainmenuActivity.this, UserMessage.Cnamelist.get(UserMessage.Position-1)+"",Toast.LENGTH_LONG).show();
            intent.setClass(MainmenuActivity.this, ChildMenuActivity.class);
            startActivity(intent);
            finish();
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
        if (item.getItemId() == 1) {// 重新登入
            Intent intent = new Intent();
            intent.setClass(MainmenuActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == 2) {// 退出
            System.exit(0);
        }
        return true;
    }
}