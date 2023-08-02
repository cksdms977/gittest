package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StudentEntity {
	@Id
	private long studentNo;
	
	private String studentName;
	
	private int grade;
	
	private int classNumber;
	
	//영속성 전이할때
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mylocker", nullable = false)
	private LockerEntity mylocker;
	
}
