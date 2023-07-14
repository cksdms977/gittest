package com.bs.spring.memo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	@Autowired
	private MemoDao dao; 
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Memo> MemoList() {
		return dao.Memolist(session);
	}
	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}

}
