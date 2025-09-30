package com.xushu.one;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author 徐庶
 */
@ComponentScan("com.xushu")
@Configuration
@EnableWebMvc	// 注入springmvc基本组件的bean  <mvc:annotation-driven/>
public class AppConfig  implements WebMvcConfigurer {
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/jsp/");
		return viewResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor());
	}

	public HandlerInterceptor handlerInterceptor() {
		return new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				System.out.println("请求前");
				return true;
			}

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
				HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
				System.out.println("请求后");
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
				System.out.println("异常"+ex);
				HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
			}
		};
	}


	// 设置哪些静态资源放行， 不拦截。
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("xushu.css")
				.addResourceLocations("classpath:/WEB-INF/resource/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/xushu/**")
				.allowedMethods("GET");
	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(new MappingJackson2HttpMessageConverter());
//	}
}
