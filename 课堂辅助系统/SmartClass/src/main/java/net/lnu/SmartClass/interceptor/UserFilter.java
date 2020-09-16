/**
 * 
 */
package net.lnu.SmartClass.interceptor;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Administrator
 *
 */
@WebFilter(filterName = "UserFilter",urlPatterns = {"/manager/*"})
public class UserFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("过滤了:"+request.getRequestURI());
		String user = (String) request.getSession().getAttribute("loginUser");
		System.out.println(user);
		if (user != null && (user.equals("admin") || user.equals("teacher")||user.equals("student") )){
			if(request.getSession().getAttribute("admin")==null&&request.getSession().getAttribute("teacher")==null&&request.getSession().getAttribute("admin")==null){
				response.sendRedirect(request.getContextPath() + "/"); 
			}else{
				filterChain.doFilter(request, response);
			}
			
		}else{
			
			response.sendRedirect(request.getContextPath() + "/"); 
		}
		
	}

}