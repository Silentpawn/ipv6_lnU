package com.lnu.utill;

import com.lnu.bean.QuestionBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMessage {
    public static String Userno=null;
    public static String UserId=null;
    public static String clock;
    public static Map<Integer, String> answer_map = new HashMap<Integer,String>();
    public static String User_type="student";//初始化，标志测试用户类型暂时为student
   public static List<QuestionBean> questionlist = new ArrayList<QuestionBean>();//题干列表
    public static List<String> Sb=new ArrayList<String>();
    public static List<String> Cnamelist=new ArrayList<String>();
    public static List<Integer> Cidlist=new ArrayList<Integer>();
    public static String paperid="0";
    public static int Position=0;
    public static boolean status=false;
    public static boolean mload=false;
    public static void returnNull() {
        answer_map = new HashMap<Integer,String>();
        User_type="student";//初始化，标志测试用户类型暂时为student
     //   questionlist = new ArrayList<QuestionBean>();//题干列表
        Sb=new ArrayList<String>();
        Cnamelist.clear();
        Cidlist.clear();
        status=false;
        mload=false;
    }


}
