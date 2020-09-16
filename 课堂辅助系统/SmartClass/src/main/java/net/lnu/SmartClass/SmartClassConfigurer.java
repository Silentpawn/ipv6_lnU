/**
 * 
 */
package net.lnu.SmartClass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import net.lnu.SmartClass.interceptor.UserInterceptor;


/**
 * @author Administrator
 *
 */
@Configuration
public class SmartClassConfigurer extends WebMvcConfigurationSupport{
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		
		List<String> paths= new ArrayList<String>();
		paths.add("/courseAPI/**");
		paths.add("/studentAPI/**");
		paths.add("/userAPI/**");
		registry.addInterceptor(userInterceptor()).addPathPatterns(paths)
		.excludePathPatterns("login/**","/*"); 
		super.addInterceptors(registry);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/manager/**").addResourceLocations("classpath:/static/manager/");
		registry.addResourceHandler("/index/**").addResourceLocations("classpath:/static/index/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
	public UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}

}
