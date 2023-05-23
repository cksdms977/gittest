package com.filtertest.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListnerTest implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		톰켓의 servletContext객체가 소멸됐을때 실헹
//		톰켓종료시
		System.out.println("서버 죽음");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		톰켓의 servletContext객체가 생성됐을때 실행
//		톰멧실행시
		System.out.println("서버 실행");
	}	
}
