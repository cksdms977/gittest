package com.bs.spring.jpa.model.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {
	
	private EntityManager em;
	private JpaDao dao;
	
	public JpaServiceImpl(EntityManager em, JpaDao dao) {
		this.em=em;
		this.dao=dao;
	}
	
	@Override
	public void basictest() {
		EntityTransaction et = em.getTransaction();
		et.begin(); // 트렌젝션 시작~~~~
			dao.basictest(em);
		et.commit();
//		et.rollback();
	}

	@Override
	public void manytoone() {
		EntityTransaction et = em.getTransaction();
		et.begin();
		dao.manytoone(em);
		et.commit();
//		em.clear(); // 여기서 영속성 컨테스트를 클리어 해주면 된다.
		dao.boardById(em, 1);
		
	}

	@Override
	public void insertStudent() {
		EntityTransaction et = em.getTransaction();
		dao.insertStudent(em);
		et.commit();
		em.clear();
		dao.selectStudentById(em, 1);
	}

	@Override
	public void deleteStudent(long no) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		dao.deleteStudent(em,no);
		et.commit();
	}
}
