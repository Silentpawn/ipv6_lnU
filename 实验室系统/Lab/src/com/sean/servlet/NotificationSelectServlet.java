/**
* @Title: TeacherSelectServlet.java
* @Package com.sean.servlet
* @Description: TODO（教师列表显示模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.2.11
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.NotificationDao;
import com.sean.dao.TeacherDao;
import com.sean.model.Notification;
import com.sean.model.Teacher;

@WebServlet("/notification/select")
public class NotificationSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
NotificationDao notificationDao = new NotificationDao();
		
		ArrayList<Notification> mark=notificationDao.getNotificationList();
		request.setAttribute("list", mark);
		
		request.getRequestDispatcher("/Administrator/notification_list.jsp").forward(request, response);
	}

}
