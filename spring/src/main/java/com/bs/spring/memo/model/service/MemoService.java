package com.bs.spring.memo.model.service;

import java.util.List;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoService {
	List<Memo> MemoList();
	int insertMemo(Memo m);
}
