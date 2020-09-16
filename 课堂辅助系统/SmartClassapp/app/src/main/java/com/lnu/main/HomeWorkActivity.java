package com.lnu.main;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.lnu.adapter.ItemAdapter;
import com.lnu.bean.QuestionBean;
import com.lnu.bean.QuestionOptionBean;
import com.lnu.bean.Task;
import com.lnu.utill.UserMessage;
import com.lnu.utill.httputils;
import com.lnu.view.ConfirmDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeWorkActivity extends AppCompatActivity implements View.OnClickListener {
    String Log_sit="./login/homework";
    //List<View> list = new ArrayList<View>();
    public static boolean over=false;
    public static List<QuestionBean> questionlist = new ArrayList<QuestionBean>();//题干列表
    public static QuestionBean question;
    public List<QuestionOptionBean> options;//问题一选项
    public static List<Task> tasklist=new ArrayList<Task>();
    public static List<String> answerlist=new ArrayList<String>();

    public static QuestionOptionBean option;
    private ViewPager vp;
    private ItemAdapter pagerAdapter;
    View pager_item;
    public static int currentIndex = 0;
    private TextView tv_time;
    private TextView tv_answercard;
    private TextView tv_back;
    Thread load;
    String cid=null;
    String pid=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        over=false;
        UserMessage.answer_map.clear();
        questionlist.clear();
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_menu);
        if(UserMessage.Position==1){
            try {
                load = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            loadData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                load.join();
                load.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (load.isAlive()) {

            }
        }else {

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (!bundle.isEmpty()) {
                cid = bundle.getString("cid");
                pid = bundle.getString("pid");
                UserMessage.paperid = pid;
            }
            System.out.println("______" + pid + "|" + cid + "___________");

            try {
                load = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            loadData(cid, pid);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                load.join();
                load.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (load.isAlive()) {

            }
        }
            System.out.println("#########"+tasklist.size()+"task");
            for(int i=1;i<=tasklist.size();i++) {//注入信息，以单选情况实现
                Task task=tasklist.get(i-1);
                options = new ArrayList<QuestionOptionBean>();
                option = new QuestionOptionBean("A",task.getOption1());
                options.add(option);
                option = new QuestionOptionBean("B", task.getOption2());
                options.add(option);
                option = new QuestionOptionBean("C", task.getOption3());
                options.add(option);
                option = new QuestionOptionBean("D", task.getOption4());
                options.add(option); //可以将
                question = new QuestionBean(i+"", task.getTitle(),1, options);
                answerlist.add(task.getAnswer());
                questionlist.add(question);
            }



        //相关组件初始化
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_answercard = (TextView) findViewById(R.id.tv_answercard);
        tv_time = (TextView) findViewById(R.id.tv_time);
        startCounter();
        tv_back.setOnClickListener(this);
        tv_answercard.setOnClickListener(this);
        tv_time.setOnClickListener(this);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setCurrentItem(0);
        pagerAdapter = new ItemAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//对组件fragment进行处理

            @Override
            public void onPageSelected(int arg0) {//选择页面

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                currentIndex = position;
            }
        });

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        //TODO
        /**
         *用来响应跳转页面的命令的
         * */
        filter.addAction("com.lnu.jumptopage");
        filter.addAction("com.lnu.jumptonext");
        filter.addAction("com.lnu.alert");
        lbm.registerReceiver(mMessageReceiver, filter);

    }
    public void loadData(String cid,String pid) throws IOException, JSONException {
        // String Log_sit="http://192.168.1.102:80/SmartClass/studentAJAX/login_student";//路由器
        String Log_sit="login/homework";//路由器
        String flog=null;
        URL url = new URL(httputils.BASE_URL+ Log_sit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("name", UserMessage.UserId.toString());
        connection.setRequestProperty("cid", cid);
        connection.setRequestProperty("pid", pid);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        String params = "name="+UserMessage.UserId.toString()+"&cid="+cid+"&pid="+pid;
        OutputStream out = connection.getOutputStream();
        out.write(params.getBytes());
        out.flush();
        out.close();
        String msg = "";
        int code = connection.getResponseCode();
        if (code == 200) {
            //test="999";
            Log.i("MainActivity","code");
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
        flog=flog.substring(0,flog.length()-1);
        System.out.println("###########################################");
        System.out.println("################"+flog+"#####################");
        System.out.println("###########################################");

        JSONArray array = new JSONArray(flog);
        for (int i = 0; i <array.length(); i++) {

            System.out.println(i+"E#EEEEEEEEEEEEEEEEEEE");
            Task t=new Task();
            JSONObject obj = array.getJSONObject(i);
            t.setId(Integer.valueOf(obj.getInt("id")));
            t.setTitle(obj.getString("title"));
            t.setOption1(obj.getString("option1"));
            t.setOption2(obj.getString("option2"));
            t.setOption3(obj.getString("option3"));
            t.setOption4(obj.getString("option4"));
            t.setAnswer(obj.getString("answer"));
            t.setTid(null);
            tasklist.add(t);
        }
    }
    public void loadData() throws IOException, JSONException {
        // String Log_sit="http://192.168.1.102:80/SmartClass/studentAJAX/login_student";//路由器
        String Log_sit="login/recommand";//路由器
        String flog=null;
        URL url = new URL(httputils.BASE_URL+ Log_sit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("name", UserMessage.UserId.toString());
        connection.connect();
        String params = "name="+UserMessage.UserId.toString();
        OutputStream out = connection.getOutputStream();
        out.write(params.getBytes());
        out.flush();
        out.close();
        String msg = "";
        int code = connection.getResponseCode();
        if (code == 200) {
            //test="999";
            Log.i("MainActivity","code");
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
//        flog=flog.substring(0,flog.length()-1);
        System.out.println("###########################################");
        System.out.println("################"+flog+"#####################");
        System.out.println("###########################################");
        if(flog!=null) {
            JSONArray array = new JSONArray(flog);
            for (int i = 0; i < array.length(); i++) {

                System.out.println(i + "E#EEEEEEEEEEEEEEEEEEE");
                Task t = new Task();
                JSONObject obj = array.getJSONObject(i);
                t.setId(Integer.valueOf(obj.getInt("id")));
                t.setTitle(obj.getString("title"));
                t.setOption1(obj.getString("option1"));
                t.setOption2(obj.getString("option2"));
                t.setOption3(obj.getString("option3"));
                t.setOption4(obj.getString("option4"));
                t.setAnswer(obj.getString("answer"));
                t.setTid(null);
                tasklist.add(t);
            }
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back://选项1，返回按钮的响应事件（未写）
                //清空问题题干
                Intent intent = new Intent();
                // 启动事件0Activity 回答卷子
                //TODO
                UserMessage.questionlist.clear();
                tasklist.clear();
                questionlist.clear();
                if(UserMessage.Position==1){
                    intent.setClass(this, MainmenuActivity.class);
                }else
                    intent.setClass(this, ChildMenuActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_answercard://

                jumpToPage(questionlist.size()+1);

                break;
            case R.id.tv_time://停止计时
                //TODO
                stopCounter();
                final ConfirmDialog confirmDialog = new ConfirmDialog(this, "中场休息");
                confirmDialog.setCancelable(false);
                confirmDialog.show();
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {

                    @Override
                    public void doProceed() {
                        //TODO
                        confirmDialog.dismiss();
                        startCounter();
                    }

                });
                break;
            default:
                break;
        }
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {//接受广播信息
        @Override
        public void onReceive(Context context, Intent intent) {//接受两个fragment发送的信息，并作出反应
            if (intent.getAction().equals("com.lnu.jumptopage")) {
                HomeWorkActivity.over=true;
                int index = intent.getIntExtra("index",0);
                jumpToPage(index);
            }  else if (intent.getAction().equals("com.lnu.jumptonext")) { //尝试在这里
                jumpToNext();
            }  else if(intent.getAction().contentEquals("com.lnu.alert")) {
                jumpToPage(0);
                Toast.makeText(HomeWorkActivity.this, "请先完成试卷", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void jumpToNext() {//跳转下一页实现

        int position = vp.getCurrentItem();
        System.out.println("############"+position+"##############");
        vp.setCurrentItem(vp.getCurrentItem() + 1);

    }
    public void jumpToPage(int index) {//跳转页面实现
        vp.setCurrentItem(index);
    }
    int time = 0;
    int second = 0;
    int minute = 0;
    String timeStr  ="00:00";
    int[] iTime = new int[]{0,0,0,0};

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){//对计时器实现
        public void handleMessage(android.os.Message msg) {//时钟事件的实现
            switch (msg.what) {
                case 1:
                    time++;
                    second = time %60;
                    minute = time /60;
                    if(minute>99){
                        break;
                    }
                    if(second < 10 && minute < 10){
                        iTime[0]=0;
                        iTime[1]=minute;
                        iTime[2]=0;
                        iTime[3]=second;

                    }else if(second >= 10 && minute < 10){
                        iTime[0]=0;
                        iTime[1]=minute;
                        iTime[2]=(second+"").charAt(0)-48;
                        iTime[3]=(second+"").charAt(1)-48;

                    }else if(second < 10 && minute >= 10){
                        iTime[0]=(minute+"").charAt(0)-48;
                        iTime[1]=(minute+"").charAt(1)-48;
                        iTime[2]=0;
                        iTime[3]=second;
                    }else if(second >= 10 && minute >= 10){
                        iTime[0]=(minute+"").charAt(0)-48;
                        iTime[1]=(minute+"").charAt(1)-48;
                        iTime[2]=(second+"").charAt(0)-48;
                        iTime[3]=(second+"").charAt(1)-48;

                    }
                    UserMessage.clock=""+iTime[0]+iTime[1]+":"+iTime[2]+iTime[3];
                    tv_time.setText(UserMessage.clock);
                    handler.sendEmptyMessageDelayed(1, 1000);
                    break;

                default:
                    break;
            }

        };

    };
    // 延时启动
    public void startCounter() {
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    // 停止计时
    public void stopCounter() {
        handler.removeCallbacksAndMessages(null);
    }
    //计时结束
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}