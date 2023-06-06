package com.web.member.model.dto;

import java.sql.Date;

import java.util.Arrays;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
		private String userId;
		private String password;
		private String userName;
		private int age;
		private char gender;
		private String email;
		private String phone;
		private String address;
		private String[] hobby;
		private Date enrollDate;
		
		
	
}
