package com.bs.helloboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.bs.helloboot.dto.MyAuthority;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private DBConnectProvider provider;
	public SecurityConfig(DBConnectProvider probider) {
		this.provider=probider;
	}
	
	//boot에서 security적용하기
	//1.인증처리할 bean을 등록을 해야함
	//	인증관련 설정하는 bean -> securityFilterChain 객체를 반환하는 bean을 등록
	//2.인층처리할때 인증방법에 대한 클래스를 등록
	//	inMemory, DB연동방식 -> provider를 등록
	
	@Bean
	public SecurityFilterChain authenticationPath(HttpSecurity http) throws Exception{
		
		
		return http.csrf().disable()
				.formLogin()
				.successForwardUrl("/sucessLogin")
				.failureForwardUrl("/erroLogin")
				.usernameParameter("userId")
				.passwordParameter("pw")
				.loginProcessingUrl("/login.do")
				.loginPage("/loginpage")
				.and()
				.authorizeHttpRequests() //interceptor를 등록하는 것과 동일한 기능
				 .antMatchers("/loginpage").permitAll()
				 .antMatchers("/**").hasAnyAuthority(MyAuthority.ADMIN.name(), MyAuthority.USER.name())
				.and()
				.logout()
					.logoutSuccessUrl("/logout")
					.logoutUrl("/logout.do")
				.and()
				.authenticationProvider(provider)
				.build();
		
	}
	
	
	
	
}
