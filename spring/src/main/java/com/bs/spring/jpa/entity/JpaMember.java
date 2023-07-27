package com.bs.spring.jpa.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//일반 pojo클래스를 Entity로 등록하기 위해서는
// @Entity를 이용한다 -> 클래스 선언부에 선언.
//기본생성자는 필수로 있어야 함, final클래스(enum, interface, inner)는 사용불가, 필드에 final사용 불가
@Entity //(name = "")
//@Table이용하기 -> Table에 대한 설정을 하는어노테이션
//schema, catalog, 테이블레벨 제약조건, DB에 생성될 테이블명 설정(생략하면 클래스명으로 테이블을 생성함)
//@Table()
//@SequenceGenerator -> DB에서 사용할 sequence를 생성하는 어노테이션, ID값을 자동부여할때 사용함
// name : 생성기이름, sequenceName : DB의 시퀀스이름, initValue : 시작값, allocationSize : 증가되는 값
//@TableGenerator -> ID값을 중복없이 발급하는 테이블을 생성해서 ID를 부여하는 용도

//@JsonIdentityInfo -> Entity객체를 가져올때 양방향으로 일대다, 
//					   다대일 관계에 있으면 무한루핑현상이 발생을 차단해주는 어노테이션
//@Table(name = "memberjpa")
@SequenceGenerator(name="seq_jpamemberno",sequenceName = "seq_jpamemberno", initialValue=1,allocationSize=1)
public class JpaMember {
	@Id // entity를 식별하는 식별자, DB에서는 Primary key 제약조건이 설정이 된다.
	@GeneratedValue(generator="seq_jpamemberno", strategy = GenerationType.SEQUENCE)
	@Column(name="member_no")
	private Long memberNo;
	@Column(name="member_id", unique = true, nullable=false, length=20)
	private String memberId;
	@Column(name="member_pwd", nullable=false, length=20)
	private String memberPwd;
//	Bigdeciaml타입에 사용하는 숫자설정
//	precistion : 전체자리수
//	scale : 소수점자리수
	@Column(precision = 10, scale=3)
//	private Integer age;
	private BigDecimal age; // bigdeciaml로 선언을 하면 int값에 대한 설정을 해줄수 있다.
	@Column(columnDefinition = "number default 100.0")
	private double height;
//	EnumType을 이용해서 처리하기
	@Column(name="member_role")
	@Enumerated(EnumType.STRING) // 문자열자체를 저장 주로 String으로 Enum타입을 설정하면 더좋다.
	private Role role;
	@Column(name="member_level")
	@Enumerated(EnumType.ORDINAL) // 문자열과 연결되어있는 숫자값 저장
	private Level level;
	
//	날짜타입에 대해 설정하기
	@Temporal(TemporalType.DATE)
	private Date birthDay;
	@Temporal(TemporalType.TIMESTAMP) //시간까지 저장
	private Date startDate;
	
//	lob타입설정하기
//	긴글을 사용할때 
	@Lob
	private String info; // varchart2
	@Lob
	private byte[] dataSample;
	
//	DB컬럼 대상에서 제외하기
	@Transient
	private String tempData;
	
	@Embedded
	private Address addr;
}
