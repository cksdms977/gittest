package com.bs.spring.jpa.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;
import com.bs.spring.jpa.entity.Address;
import com.bs.spring.jpa.entity.BoardEntity;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.LockerEntity;
import com.bs.spring.jpa.entity.StudentEntity;
@Repository
public class JpaDaoImpl implements JpaDao {
	
	
	@Override
	public void basictest(EntityManager em) {
		//em메소드 이용하기....
//		1. JpaMember클래스 영속성 등록하기
		JpaMember m = JpaMember.builder()
					  .memberId("202001201")
					  .memberPwd("rhrhrh22")
					  .age(new BigDecimal(27))
					  .height(178.2)
					  .level(Level.DIAMOND)
					  .role(Role.ADMIN)
					  .birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
					  .startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
					  .addr(Address.builder().statement("경기도").detailAddress("시흥시 배곧동").zipcode("123-456").build())
					  .build(); // -> 비영속성
//		영속화 처리하기
		em.persist(m); // 매개변수로 전달된 객체가 영속성 먼텍스트에 저장이 되고 
//		                 영속성 컨택스트에 새로 저장되면 insert sql문을 자동을 생성
		JpaMember m2 = JpaMember.builder()
				  .memberId("2020012032")
				  .memberPwd("rhrhrh22")
				  .age(new BigDecimal(27))
				  .height(178.2)
				  .level(Level.SILVER)
				  .role(Role.USER)
				  .build(); 
		em.persist(m2);
		
		System.out.println(m);
		//저장된 객체를 불러오기 -> select문
		JpaMember selectM = em.find(JpaMember.class, 1L);
		System.out.println(selectM);
		
	
	}

	@Override
	public void manytoone(EntityManager em) {
		
		JpaMember m = JpaMember.builder()
		.memberId("202001201")
		.memberPwd("rhrhrh22")
		.age(new BigDecimal(27))
		.height(178.2)
		.level(Level.DIAMOND)
		.role(Role.ADMIN)
		.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
		.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
		.addr(Address.builder().statement("경기도").detailAddress("시흥시 배곧동").zipcode("123-456").build())
		.build();
		em.persist(m);
		BoardEntity b = BoardEntity.builder()
						.boardTitle("첫번째 게시글")
						.boardContent("나의 첫 게시글")
						.writeDate(java.sql.Date.valueOf(LocalDate.now()))
						.boardWriter(m).build();
//		이렇게 boardEntity에 대한 것도 넣어주ㅓ야 ㅎ된 ㄷ.
		m.setBoards(new ArrayList<>());
		m.getBoards().add(b);
		em.persist(b);
						
	}

	@Override
	public void boardById(EntityManager em, long no) {
		BoardEntity b = em.find(BoardEntity.class, no);
		System.out.println("boardEntity조회결과");
		System.out.println(b);
		JpaMember m = em.find(JpaMember.class, no);
		System.out.println("jpaMember조회결과");
//		System.out.println(m.getMemberId()+m.getMemberPwd()+m.getMemberNo());
		System.out.println(m);
		
		System.out.println("jpaMember 등록 게시글 조회");
		System.out.println(m.getBoards());
	}

	@Override
	public void insertStudent(EntityManager em) {
		StudentEntity s=StudentEntity.builder()
				.studentNo(1)
				.studentName("유병승")
				.classNumber(3)
				.grade(1)
				.build();
		LockerEntity l=LockerEntity.builder()
				.lockerNo(1).lockerColor("파랑")
				.lockerPosition("3층 E강의장")
				.build();
		s.setMylocker(l);
		
		s.setMylocker(l);
		em.persist(s);
		
		StudentEntity search = em.find(StudentEntity.class, 1L);
		System.out.println(search);
	}

	@Override
	public void selectStudentById(EntityManager em, long no) {
		StudentEntity search=em.find(StudentEntity.class, 1L);
		System.out.println(search);
	}

	@Override
	public void deleteStudent(EntityManager em, long no) {
		LockerEntity l = em.find(LockerEntity.class, no);
		em.remove(l); // 이게 삭제이다.
	}
}
