package com.bs.helloboot.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bs.helloboot.dao.MemberDao;
import com.bs.helloboot.dto.MemberDto;

@Component
public class DBConnectProvider implements AuthenticationProvider{

	private MemberDao dao;
	private SqlSessionTemplate session;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	public DBConnectProvider(MemberDao dao) {
		this.dao=dao;
	}
	
//	public DBConnectProvider(MemberDao dao, SqlSessionTemplate session) {
//		this.dao=dao;
//		this.session=session;
//	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String password = (String)authentication.getCredentials();
		MemberDto loginMember = dao.selectMemberById(session, userId);
		if (loginMember == null || !encoder.matches(password, loginMember.getPassword())) {
			//로그인  실패
			throw new BadCredentialsException("인증싪패");
		}
		return new UsernamePasswordAuthenticationToken(loginMember, loginMember.getPassword(), loginMember.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
