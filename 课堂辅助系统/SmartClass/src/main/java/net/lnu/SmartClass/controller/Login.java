package net.lnu.SmartClass.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.lnu.SmartClass.result.Error;
import net.lnu.SmartClass.result.Result;
import net.lnu.SmartClass.result.Success;

/**
 * 
 * 模板测试.
 * 
 * @author Administrator
 *
 * 
 * 
 */

@Controller
@RequestMapping("/login")
public class Login {
	@Autowired
	private LoginService loginService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SignService signService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CourseService courseService;
	private HttpSession getSession() {
		return request.getSession();
	}

	@ResponseBody
	@RequestMapping("/login")
	public Result login(LoginVO loginVO) {
		System.out.println(loginVO.getType());
		try {
			if (loginVO.getType().equals("admin")) {
				getSession().setAttribute("loginUser", "admin");
				getSession().setAttribute("admin",
						loginService.AdminLogin(new Admin(loginVO.getName(), loginVO.getPassword())));
				;
			} else if (loginVO.getType().equals("teacher")) {
				getSession().setAttribute("loginUser", "teacher");
				getSession().setAttribute("teacher",
						loginService.teacherLogin(new Teacher(loginVO.getName(), loginVO.getPassword())));
			} 
				  else if (loginVO.getType().equals("student")) {
				  getSession().setAttribute("loginUser", "student");
				  getSession().setAttribute("student", loginService.studentLogin(new
				  Student(loginVO.getName(), loginVO.getPassword()))); }
				 
			return new Success();
		} catch (Exception e) {
			return new Error(e.getMessage());
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		getSession().removeAttribute("loginUser");
		getSession().removeAttribute("admin");
		getSession().removeAttribute("teacher");
		getSession().removeAttribute("student");
		return "/index/login";
	}

	@ResponseBody
	@RequestMapping("/register")
	public Result register(Teacher teacher) {

		try {
			loginService.teacherRegister(teacher);
			return new Success();
		} catch (Exception e) {
			return new Error(e.getMessage());
		}
	}

	// 接受post请求，解析为json——————这条暂时改为测试能否接受到android端发送的数据
	// 对发送内容进行查询，判断是否存在
	// 以json形式返回结果
	//已废用
	@ResponseBody
	@PostMapping("/judge")
	public void judgelogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		
		  String loginName = request.getParameter("name"); String loginPassword =
		  request.getParameter("pwd"); System.out.println("登录:"+loginName);
		  System.out.print("密码:"+loginPassword);
		//TODO
		/**
		 * 可不可以做一个索引，传共给登录使用，节省代码空间，方便登录
		 */
		PrintWriter out = response.getWriter();
		
		out.write("success");
		//out.write("wrong");

	}
	
	@ResponseBody
	@PostMapping("/registerid")
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		String loginNo = request.getParameter("id");
		String loginPwd=request.getParameter("pwd");
		String loginType=request.getParameter("type");
		String loginSex=request.getParameter("sex");
		String loginProfession=request.getParameter("profession");
		String loginName=request.getParameter("name");
		if(loginType.equals("teacher")) 
		  { 
			  System.out.println(loginNo);
			  System.out.println(loginPwd); 
			  Teacher t=new Teacher(loginNo,loginPwd,loginName,loginSex,loginProfession);
			  t.setStatus("未通过"); 
			  teacherService.editTeacher2(t); 
			 }else { 
				 Student s=new Student(loginNo,loginPwd,loginName,loginSex,loginProfession);
				 studentService.editStudent(s); }
		 
		 
		 
		//TODO
		/**
		 * 可不可以做一个索引，传共给登录使用，节省代码空间，方便登录
		 */
		
		PrintWriter out = response.getWriter();
		out.write("success");
		
	}
	
	@ResponseBody
	@PostMapping("/homework")
	public void homework(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		String loginName = request.getParameter("name");
		String course=request.getParameter("course");
		java.util.Date date= new java.util.Date();
		Long  time= date.getTime();
		Date now = new java.sql.Date(time);	
	    /*********************************************/
		System.out.print(course.toString());
		List<Task> tasklist=taskService.querryTaskByCourseid(Integer.valueOf(course));
		//List<Sign> sign=signService.querrySign(Integer.valueOf(loginName), now);
	   // Sign sign=signService.querrySign(Integer.valueOf(loginName),Integer.valueOf(cid));
	 
	    // System.out.print(sign.getCourseid());
		/********/
		//TODO
		   //JSON数组   
		//将试卷以json形式发回客户端
		
		  JSONArray array = new JSONArray(); 
		   for(Task bean:tasklist){
		  //单个用户JSON对象 
		JSONObject obj = new JSONObject(); 
		try{
				obj.put("title",bean.getTitle());
				obj.put("id", bean.getId());
				obj.put("option1", bean.getOption1()); 
				obj.put("option2", bean.getOption2());
				obj.put("option3", bean.getOption3());
				obj.put("option4", bean.getOption4());
				obj.put("answer", bean.getAnswer()); 
				obj.put("author", bean.getAuthor());
				obj.put("Tid", bean.getTid()); 
				obj.put("courseid", bean.getCourseid());
		  		}catch (Exception e) { // TODO: handle exception  
		  	} 
			array.put(obj); 
			  
		  }
		  PrintWriter out = response.getWriter(); out.write(array.toString());
		  out.flush();

	}
	
	@ResponseBody
	@PostMapping("/checkcourse")
	public void checkcourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		String loginName = request.getParameter("name");
		List<Course> courselist=new ArrayList<Course>();
		java.util.Date date= new java.util.Date();
		Long  time= date.getTime();
		Date now = new java.sql.Date(time);
		//查找每条当天签到记录
		List<Sign> sign=signService.querrySign(Integer.valueOf(loginName), now);
		for(Sign s:sign) {
			System.out.println("#################");
			Course c=courseService.queryCourse(s.getCourseid());
			System.out.println();
			System.out.println(c.getName());
			System.out.println(c.getId());
			courselist.add(c);
		}
		new java.sql.Date(time);
		 JSONArray array = new JSONArray();
		 int i=0;
		for(Course bean:courselist) {
			 //单个用户JSON对象 
			  JSONObject obj = new JSONObject(); 
			   try{
				  obj.put("id",bean.getId()); 
				  obj.put("name", bean.getName());
			    //TODO
				  /**
				   * 用来判断是否做过题目
				   * */
				/*
				 * if(sign.get(i).getPoint()==null) {
				 * System.out.println("####################"+sign.get(i).getPoint());
				 * obj.put("point", sign.get(i).getPoint()); } i++;
				 */
			   }catch (Exception e) {
				// TODO: handle exception
			}
			  array.put(obj);
		}
				
		PrintWriter out = response.getWriter();
		out.write(array.toString());
		out.flush();   
		
	}
	
	@ResponseBody
	@PostMapping("/point")
	public void getpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		String point = request.getParameter("point");
		String Cid=request.getParameter("cid");
		String Uid=request.getParameter("uid");
		Sign s=signService.querrySign(Integer.valueOf(Uid), Integer.valueOf(Cid));
		s.setPoint(Integer.valueOf(point));
		signService.editSign(s);
		PrintWriter out = response.getWriter();
		out.write("success");
		
	}
	@ResponseBody
	@PostMapping("/checktask")
	public void checktask(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		String Id = request.getParameter("id");
		List<Task> t=taskService.querryTaskByCourseid(Integer.valueOf(Id));
		PrintWriter out = response.getWriter();
		
		  if(t.size()==0) 
			  out.write("wrong"); 
		  else
			out.write("success");
		
	}
	@ResponseBody
	@PostMapping("/checksign")
	public void checksign(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Type", "application/xml; charset=UTF-8");
		String studentid = request.getParameter("sid");
		String courseid=request.getParameter("cid");
		Sign sign=signService.querrySign(Integer.valueOf(studentid), Integer.valueOf(courseid));
		Course course=courseService.queryCourse(Integer.valueOf(courseid));
		PrintWriter out = response.getWriter();
		java.util.Date date= new java.util.Date();
		System.out.println(date);
		Long time= date.getTime();
		java.sql.Timestamp t=new java.sql.Timestamp(time);
		if(t.compareTo(course.getStarttime())==1)
			System.out.println("\n##################"+t.compareTo(course.getEndtime())+"*"+course.getEndtime().toString()+"*"+t.toString());  
		if(sign.getSignstatus().equals("Y")||(t.compareTo(course.getStarttime())==-1)||(t.compareTo(course.getEndtime())==1)) 
			  out.write("wrong");
		  else	
			  out.write("success"); 
			 
		
	}
}
