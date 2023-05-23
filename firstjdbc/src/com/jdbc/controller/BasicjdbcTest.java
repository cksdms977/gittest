package com.jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jdbc.model.vo.Member;

public class BasicjdbcTest {

	public static void main(String[] args) {
		// jdbc를 이용해서 오라클과 연동해보기
		// 1. 오라클에서 제공하는 ojdbc.jar파일을 버전에 맞춰서 다운로드
		// 2. 이클립스에서 프로젝트를 생성하고 생성된 프로젝트 라이브러리에 다운받은 jar파일을 추가한다.
		// 그리고 드라이브가 비어있는지 체크하기
		// 그리고 url, 아이디, 비밀번호 알기
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 프로젝트(애플리케이션)에서 DB에 접속하기
		// 1. jar파일이 제공하는 클래스가 있는지 확인하기
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. DriverManager 클래스를 이용해서 접속하는 객체를 만들어 준다.
		// DriverManager 클래스가 제공하는 getConnection() static 메소드를 이용해서 
		// Connection객체를 가져온다. -> getConnection() 메소드는 Connection객체를 반환한다.
		// getConnection이용하기 -> 3개의 매개변수가 선언되어있음
		// 첫번째 인수 : 접속할 DB의 주소, 버전정보, 포트번호 String
		// 접속할 DBMS별로 문자열 패턴을 정해놓음!!!
		// 오라클 : jdbc:oracle:thin:@ip주소:포트번호:버전
		
		// 두번째 인수 : DB접속 계정명 String 
		// 세번째 인수 : DB접속 계정 비밀번호 String 
	
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "student");
			System.out.println("DB접속 확인 완료");
			
			//트렌젝션을 개발자가 직접 처리하겠다.
			conn.setAutoCommit(false);
			System.out.println("DB접속 확인 완료!!");
			
			// 3. 접속된 DB에 SQL문을 실행하고 결과를 가져와야 함
			// SQL문을 실행하기 위해서는 실행할 객체가 필요함.
			// Statement, PreparedStatement : 문자열로 작성한 sql구문을 연결된 DB에서 실행하는 객체 
			// sql문을 실행하려면 Statement의 멤버메소드 executeQuery(), executeUpdate() 메소드를 이용한다.
			// SELECT : executeQuery("sql문")실행 
			// INSERT, UPDATE, DELETE  : executeUpdate()실행 -> int 반환;
			
			// 1). 쿼리문 작성하기
			// member테이블에 있는 아이디가 admin인 사원 조회하기
//			SELECT * FROM MEMBER WHERE MEMEBER_ID = 'admin';
			// sql문에 저장할때는 ;을 생략한다.
//			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = 'admin'";
			
			// 등록된 회원전체가져오기
			String sql = "SELECT * FROM MEMBER";
			
			
			// 2) Statement객체 가져오기
			// 가져올때는 Connection클래스가 제공하는 멤버메소드인 createStatement()메소드를 이용
			stmt = conn.createStatement();
			
			// 3) 쿼리문 실행 시키기
			// Statement제공하는 executeQuery()실행하고 반환은 ResultSet객체로 받는다.
			rs = stmt.executeQuery(sql);
			System.out.println(rs);
			
			// 4) ResultSet 이용하기
			// 반환된 SELECT문의 실행결과는 ResultSet 객체가 제공하는 메소드를 이용해서
			// 컬럼별 값을 가져온다.
			// 첫번째 메소드 next(): 데이터의 row를 지정한다. -> row데이터를 가져온다. 반환형 : boolean
			// get자료형[String, Int, Date]("컬럼명" || 인덱스번호) : 
			// getString() : char, varchar2, nvarchar2 자료형을 가져올때 씀 
			// getInt()/ getDouble() : Number자료형 가져올때 씀
			// getDate() : date자료형에 대해서 가져올때 씀
		
			while(rs.next()) {
//			rs.next(); // 1번째 row를 지칭한것
			String memberId = rs.getString("member_id");
			String memberPwd = rs.getString("member_pwd");
			int age = rs.getInt("age");
			Date enrollDate = rs.getDate("enroll_date");
			System.out.println(memberId + " " + memberPwd + " " + enrollDate);
			}
			
//			System.out.println(rs.next()); // 이걸 true false를 반환하고 있음
//			memberId = rs.getString("member_id");
//			memberPwd = rs.getString("member_pwd");
//			age = rs.getInt("age");
//			enrollDate = rs.getDate("enroll_date");
//			System.out.println(memberId + " " + memberPwd + " " + enrollDate);
		
			// DB의 row를 가져왔을때 자바에서는 클래스를 저장해서 관리 
			
//			if(rs.next()) {
//			m.setMemberId(rs.getString("member_pwd"));
//			m.setMemberName(rs.getString("member_name"));
//			m.setGender(rs.getString("gender"));
//			m.setAge(rs.getInt("age"));
//			m.setEmail(rs.getString("email"));
//			m.setPhone(rs.getString("phone"));
//			m.setAddress(rs.getString("address"));
//			m.setHobby(rs.getString("hobby"));
//			m.setEnrollDate(rs.getDate("enrolldate"));
//		
//		}
//		System.out.println(m);
			
			ArrayList<Member> member = new ArrayList<>();
			
			while(rs.next()) {
			Member m = new Member(); // 무조건 while문 안에다가 선언해야함 왜냐하면 밖에다 하면 생성이 한번만 되기때문에 최종값 하나만 생성이 되기때문에 
				m.setMemberId(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				member.add(m);
			}
			member.forEach((m) -> System.out.println(m));
				
			// DML구문 실행하기
			// insert, update, delete문
			// 트렌젝션처리를 해줘야한다.
			// 1. sql문작성 - > (주의)리터럴형태에 맞춰서 작성을 해야한다.
			sql = "INSERT INTO MEMBER VALUES('inhoru1', 'inhoru', " + " '최인호', 'M', 26, 'inhoru@inhoru.com', " + "'0101234145', '금천구', '영화감상, 애니감상, 코딩', SYSDATE)";
			int result = stmt.executeUpdate(sql);
			
			if(result >= 0) conn.commit();
			else conn.rollback();
			
			System.out.println(result);
			
			//5. 생성한 객체를 반드시 반환해줘야 한다.
			// Connection, Statement, [ResultSet]
			// close() 메소드를 이용해서 반환을 한다.
			// 생성한 역순으로 반환을 해준다.
//			rs.close(); -- 여기 넣으면 try안에서 클로저가 되기때문에 안됨
//			stmt.close();
//			conn.close();

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}

}
