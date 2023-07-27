package com.bs.helloboot.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.helloboot.common.interceptor.LoggerInterceptor;
import com.bs.helloboot.websocket.ChattingServer;

@Configuration
@EnableWebSocket
@EnableWebMvc
public class MyWebMvcConfigration implements WebMvcConfigurer, WebSocketConfigurer{
	
	private ChattingServer chatting;
	
	public MyWebMvcConfigration(ChattingServer chatting) {
		this.chatting=chatting;
	}
	// view에 대한 설정
	// 기본 화면전환에 대한 설정을 하는 메소드를 재정의 할 수 있다.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index"); // 그냥 페이지 view만 전환용으로 사용할때는 이렇게 사용해도 된다.
		registry.addViewController("/test").setViewName("test"); //이렇게 view 페이지 전환용은 이렇게 한번에 연결해서 사용이 가능하다.
		registry.addViewController("/chattingpage").setViewName("chattingpage");
	}
	// Interceptor설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/member/*");
	}
	// cors에 대한 허용설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
	}
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatting, "/chatting");
	}
	
	//HandlerExceptionResolver를 이용해서 spring에서 발생하는 에러 처리하기
	@Bean
	public HandlerExceptionResolver handleExceptionResolver() {
		Properties exceptionProp = new Properties();
		exceptionProp.setProperty(IllegalAccessException.class.getName(), "error/accessException");
	SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
	resolver.setExceptionMappings(exceptionProp);
	resolver.setDefaultErrorView("error/error"); // 여기는 위에 타입의 exception이 아닌것들이 발생했을때 보여주는 화면 
	
	return resolver;
	}
}
