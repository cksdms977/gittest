package com.bs.helloboot.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.bs.helloboot.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	@Select("SELECT * FROM MEMBER")
	List<MemberDto> selectMemberAll();
	
	@Select("SELECT * FROM MEMBER WHERE USERID=#{id}")
	MemberDto selectMemberById(String id);
	
//	@Select(value="SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || #{name}||'%'AND")
	
	//동적으로 만드는SQl문
	@SelectProvider(type=MemberSelectBuilder.class, method="selectMemberByWhere")
	List<MemberDto> selectMemberByWhere(Map<String, Object> param);
	
}
