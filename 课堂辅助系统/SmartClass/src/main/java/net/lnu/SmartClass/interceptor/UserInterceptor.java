/**
 * 
 */
package net.lnu.SmartClass.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Administrator
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(  
			HttpServletRequest request, HttpServletResponse response,   
			Object handler)   
					throws Exception{
		System.out.println("拦截器"+request.getRequestURI());
		
		String user = (String) request.getSession().getAttribute("loginUser");
		if(user!=null&&(user.equals("admin")||user.equals("teacher")||user.equals("student"))){
			if(request.getSession().getAttribute("admin")==null&&request.getSession().getAttribute("teacher")==null&&request.getSession().getAttribute("student")==null){
				return false;
			}
			return true;
			
		}else{
			response.getWriter().append("{\"status\":\"error\",\"msg\":\"没有权限\",\"data\":null}");   
			return false;
		}
		

	}
}
