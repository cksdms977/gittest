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
		
		
		
		public MemberDto() {
			// TODO Auto-generated constructor stub
		}



		public MemberDto(String userId, String password, String userName, int age, char gender, String email,
				String phone, String address, String[] hobby, Date enrollDate) {
			super();
			this.userId = userId;
			this.password = password;
			this.userName = userName;
			this.age = age;
			this.gender = gender;
			this.email = email;
			this.phone = phone;
			this.address = address;
			this.hobby = hobby;
			this.enrollDate = enrollDate;
		}



		public String getUserId() {
			return userId;
		}



		public void setUserId(String userId) {
			this.userId = userId;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getUserName() {
			return userName;
		}



		public void setUserName(String userName) {
			this.userName = userName;
		}



		public int getAge() {
			return age;
		}



		public void setAge(int age) {
			this.age = age;
		}



		public char getGender() {
			return gender;
		}



		public void setGender(char gender) {
			this.gender = gender;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPhone() {
			return phone;
		}



		public void setPhone(String phone) {
			this.phone = phone;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public String[] getHobby() {
			return hobby;
		}



		public void setHobby(String[] hobby) {
			this.hobby = hobby;
		}



		public Date getEnrollDate() {
			return enrollDate;
		}



		public void setEnrollDate(Date enrollDate) {
			this.enrollDate = enrollDate;
		}



		@Override
		public String toString() {
			return "MemberDto [userId=" + userId + ", password=" + password + ", userName=" + userName + ", age=" + age
					+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", address=" + address
					+ ", hobby=" + Arrays.toString(hobby) + ", enrollDate=" + enrollDate + "]";
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(hobby);
			result = prime * result
					+ Objects.hash(address, age, email, enrollDate, gender, password, phone, userId, userName);
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MemberDto other = (MemberDto) obj;
			return Objects.equals(address, other.address) && age == other.age && Objects.equals(email, other.email)
					&& Objects.equals(enrollDate, other.enrollDate) && gender == other.gender
					&& Arrays.equals(hobby, other.hobby) && Objects.equals(password, other.password)
					&& Objects.equals(phone, other.phone) && Objects.equals(userId, other.userId)
					&& Objects.equals(userName, other.userName);
		}
		
		
	
}
