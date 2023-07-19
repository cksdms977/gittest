package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	int insertMember(SqlSessionTemplate session, Member m);
	Member selectMemberById(SqlSessionTemplate session, Map<String, Object> param);
	List<Member> memberlist(SqlSessionTemplate session);
//	List<Member> mypage(SqlSessionTemplate session, String userId);
}
