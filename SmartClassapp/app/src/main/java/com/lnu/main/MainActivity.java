package com.lnu.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lnu.utill.UserMessage;
import com.lnu.utill.httputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String flog=null;
    /** UI */
    private EditText view_userName;
    private EditText view_password;
    private Button view_loginSubmit;
    public static	 boolean loginState=false;//登录标识
    private static final int MENU_EXIT = Menu.FIRST - 1;
    private static final int MENU_ABOUT = Menu.FIRST;
    private boolean isNetError=false;
    //确认登录是否过
    private boolean isUnPassed=false;
    //登录loading提示框
    private ProgressDialog proDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();


    }
    /** 初始化注册View组件 */
    private void findViewsById() {
        view_userName = (EditText) findViewById(R.id.loginUserNameEdit);
        view_password = (EditText) findViewById(R.id.loginPasswordEdit);
        view_loginSubmit = (Button) findViewById(R.id.loginSubmit);
        view_loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            Log.i("MainActivity","internet start");
                                validateLocalLogin(view_userName.getText().toString(),view_password.getText().toString());
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(flog==null)
                                        Toast.makeText(MainActivity.this,"请检查网络",Toast.LENGTH_LONG).show();
                                    else if(flog.equals("wrong\n"))
                                        Toast.makeText(MainActivity.this,"请检查密码，密码/账号",Toast.LENGTH_LONG).show();
                                    else {
                                        System.out.println(flog);
                                        UserMessage.UserId=flog;
                                        UserMessage.Userno=view_userName.getText().toString();
                                        Toast.makeText(MainActivity.this, flog, Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent();
                                        intent.setClass(MainActivity.this,MainmenuActivity.class);

                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                         } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        if(flog==null)
            System.out.println("五");
        else if(flog.equals("wrong"))

            System.out.println("wrong");
        else
            System.out.println("true");
    }
    public void validateLocalLoginhttp(String userNo,String passWord) throws MalformedURLException {
            String Log_sit="studentAJAX/login_student";//路由器
            String url =httputils.BASE_URL+ Log_sit;
            String result;




    }
    public void validateLocalLogin(String userNo, String passWord) throws IOException {
       // String Log_sit="http://192.168.1.102:80/SmartClass/studentAJAX/login_student";//路由器
        String Log_sit="studentAJAX/login_student";//路由器

        URL url = new URL(httputils.BASE_URL+ Log_sit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("no", userNo);
        connection.setRequestProperty("pwd", passWord);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        String params = "no="+userNo+"&pwd="+passWord;
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
        System.out.println(httputils.BASE_URL);
        // 处理结果
        System.out.println("###########################################");
        System.out.println("################"+flog+"#####################");
        System.out.println("###########################################");


    }
}