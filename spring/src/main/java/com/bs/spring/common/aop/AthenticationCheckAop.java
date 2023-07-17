package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.exception.AthenticationException;
import com.bs.spring.member.model.dto.Member;

@Component
@Aspect
public class AthenticationCheckAop {
	@Before("execution(* com.bs.spring.memo..*(..))")
	public void checkcheck(JoinPoint jp) {
		// 로그인정보를 확인하여 아이디 확인하여 admin이면 통과! admin 아니면 막기
		// spring이 제공하는 RequestContextHolder클래스를 이용해서 session값을 가져올 수 있다.
		HttpSession session = (HttpSession)RequestContextHolder
		.currentRequestAttributes()
		.resolveReference(RequestAttributes.REFERENCE_SESSION);
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getUserId().equals("admin")) {
			throw new AthenticationException("서비스 이용 권한이 부족합니다.");
		}
	}

}
