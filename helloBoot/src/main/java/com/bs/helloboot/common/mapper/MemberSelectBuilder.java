package com.bs.helloboot.common.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class MemberSelectBuilder {
	// mybatis SQL문을 를 동적으로 만들기 위한 로직
	public static String selectMemberByWhere(Map<String,Object> param) {
		return new SQL() {{
			SELECT("*");
			FROM("MEMBER");
			if(param.get("userName")!=null && !param.get("userName").equals("")) {
				WHERE("USERNAME like '%'||#{userName}||'%'");
			}
		}}.toString();
	}
}
