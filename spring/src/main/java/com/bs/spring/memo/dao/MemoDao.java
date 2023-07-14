package com.bs.spring.memo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoDao {
	List<Memo> Memolist(SqlSessionTemplate session);
	int insertMemo(SqlSessionTemplate session, Memo m);
}
