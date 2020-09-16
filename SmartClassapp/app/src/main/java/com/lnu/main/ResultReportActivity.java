package com.lnu.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnu.utill.UserMessage;
import com.lnu.utill.httputils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ResultReportActivity extends AppCompatActivity {
    JSONArray array = new JSONArray();
    LocalBroadcastManager mLocalBroadcastManager;
    int count = HomeWorkActivity.questionlist.size();//主程序中问题的个数
    int[] mIds = new int[count];
    int point=0;
    int rightanswer=0;
    String Log_sit="./login/point";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result_report);
        initData();
        rightanswer=rightAnswer();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        TextView tv_report_total_question = (TextView) findViewById(R.id.tv_report_total_question);
        TextView tv_myanswer=(TextView)findViewById(R.id.tv_myanswer);
        TextView tv_submit_time=(TextView)findViewById(R.id.tv_submit_time);
        TextView tv_submit_answer=(TextView)findViewById(R.id.tv_submit_answer);
        TextView tv_difficulty=(TextView)findViewById(R.id.tv_difficulty);
        RelativeLayout rl_result_panel = (RelativeLayout) findViewById(R.id.rl_result_panel);
        Button tv_back_anaylysis=(Button)findViewById(R.id.tv_back_analysis);
        String cover="";
        String s="";
        //rightanswer为答对题目的，以留后用
        tv_difficulty.setText(rightanswer+"/"+count);

         new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        setMessage(array.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        if(count==0)
            point=0;
        else
            point=rightanswer*10/count;
        rl_result_panel.setFocusable(true);
        rl_result_panel.setFocusableInTouchMode(true);
        rl_result_panel.requestFocus();
        tv_submit_time.setText(UserMessage.clock);
        //TODO
        for( int i=0;i<count;i++) {
            cover=cover+(i+1)+UserMessage.answer_map.get(Integer.valueOf(i));
            s=s+(i+1)+HomeWorkActivity.tasklist.get(i).getAnswer()+" ";
        }
        tv_myanswer.setText(cover);
        tv_submit_answer.setText("\n"+s);
        if(UserMessage.Position==1)
            tv_back_anaylysis.setOnClickListener(event2);
        else
            tv_back_anaylysis.setOnClickListener(event);
    }
    private boolean setMessage(String answer) throws IOException {
        String Log_sit="login/point";//路由器
        String flog=null;
        URL url = new URL(httputils.BASE_URL+ Log_sit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        String uid=UserMessage.UserId.toString();
        String cid=UserMessage.Cidlist.get(UserMessage.Position-1).toString();
        String pid= UserMessage.paperid;
        connection.setRequestProperty("uid",uid);
        connection.setRequestProperty("cid",cid);
        connection.setRequestProperty("pid",pid);
        connection.setRequestProperty("answer",answer);

        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        String params = "answer="+answer+"&cid="+cid+"&pid="+pid+"&uid="+uid;
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

        return true;
    }
    View.OnClickListener event = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件0Activity 回答卷子
            intent.setClass(ResultReportActivity.this, ChildMenuActivity.class);
            UserMessage.questionlist.clear();
            HomeWorkActivity.tasklist.clear();
            UserMessage.answer_map.clear();
            //TODO用来确认每个用户只有一次作答机会，调试需要表示为false
            UserMessage.status=false;
            startActivity(intent);

            finish();

        }
    };
    View.OnClickListener event2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            // 启动事件0Activity 回答卷子
            intent.setClass(ResultReportActivity.this, MainmenuActivity.class);
            UserMessage.questionlist.clear();
            HomeWorkActivity.tasklist.clear();
            UserMessage.answer_map.clear();
            //TODO用来确认每个用户只有一次作答机会，调试需要表示为false
            UserMessage.status=false;
            startActivity(intent);

            finish();

        }
    };
    private void initData() {
        for (int i = 0; i < count; i++) {
            mIds[i] = i + 1;
        }
    }
    private int rightAnswer(){

        int rightanswer=0;
        try {
            for(int i=0;i<HomeWorkActivity.tasklist.size();i++) {
                String answer1= UserMessage.answer_map.get(Integer.valueOf(i));
                String answer2=HomeWorkActivity.tasklist.get(i).getAnswer()+" ";
                if(answer1.equals(answer2)) {
                    JSONObject obj = new JSONObject();
                    obj.put("question",HomeWorkActivity.tasklist.get(i).getId().toString());
                    obj.put("answer","1");

                    array.put(obj);
                    rightanswer++;
                }else {
                    JSONObject obj = new JSONObject();
                    obj.put("question",HomeWorkActivity.tasklist.get(i).getId().toString());
                    obj.put("answer","0");
                    array.put(obj);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return rightanswer;
    }
}