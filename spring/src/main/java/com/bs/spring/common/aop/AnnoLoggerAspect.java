package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 빈으로 등록해야한다.
@Aspect // aspect의 역할이 부여됨
public class AnnoLoggerAspect {
	// pointcut에 대한 설정을 해줘야 함
	@Pointcut("within(com.bs.spring.member..*)") // member패키지 전체에만 해당됨
	public void loggerTest() {} // 이렇게 메소드를 한개 만들어 줌 
	
	// adviesor에 대한 설정을 해줘야함
	@Before("loggerTest()") 
	public void loggerBefore(JoinPoint jp) {
		log.debug("======= anntation.aop =======");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName() + " " + sig.getName());
		log.debug("=============================");
	}
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {}
	
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("======= anntation.aop After=======");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName() + " " + sig.getName());
		log.debug("============== aop after ===============");
	}
	
	//메소드 실행 전, 후에 특정 로직을 실행할때 사용
	@Around("execution(* com.bs.spring..*DaoImpl.*(..))")
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable {
		// 전,후 처리 로직은 한번에 설정할 수 있다.....
		// 전, 후를 구분하는 구문은 ProceedingJoinPoint클래스가 제공하는 proceed()메소드를 이용
		// proceed()메소드가 호출한 다음 라인은 후처리, 그전 라인은 전처리
		// proceed()메소드는 Object를 반환한다.
		// 메소드 실행시간 체크하기
		StopWatch stop = new StopWatch();
		stop.start();
		log.debug("========== around logger dao before ==========");
		log.debug("---------- 전처리 내용 구현 -----------");
		log.debug("=======================================");
		Signature sig = join.getSignature();
		String classMethod = sig.getDeclaringType().getName()+sig.getClass();
//		join.getTarget()
		Object obj = join.proceed();
		stop.stop();
		log.debug("========== around logger dao after ==========");
		log.debug("---------- 후처리 내용 구현 -----------");
		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		log.debug("=======================================");
		return obj;
	}
	
	@AfterThrowing(pointcut = "loggerTest()", throwing = "e")
	public void afterThrowinglogger(JoinPoint jo, Throwable e) {
		log.debug("에러발생!!!!!!");
		Signature sig = jo.getSignature();
		log.debug("{}", sig.getDeclaringTypeName()+ " " + sig.getName());
		log.debug("{}", e.getMessage());
		StackTraceElement[] stacktrace = e.getStackTrace();
		for(StackTraceElement element : stacktrace) {
			log.debug("{}", element);
		}
		
	}
	
	
	
}
