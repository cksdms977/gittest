package com.bs.spring.demo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

@Service // spring bean으로 설정한것
public class DemoServiceImpl implements DemoService {
	/* private DemoDao dao = new DemoDaoImpl(); 원래 이렇게 선언하는 것을 밑에 처럼 선언학게 됨*/
	@Autowired
	private DemoDao dao; 
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertDemo(Demo demo) {
		return dao.insertDemo(session, demo);
	}

	@Override
	public List<Demo> selectDemoAll() {
		return dao.selectDemoAll(session);
	}

	@Override
	public int updateDemo(Demo demo) {
		return dao.updateDemo(session, demo);
	}
}
