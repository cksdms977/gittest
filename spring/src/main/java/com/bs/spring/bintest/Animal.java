package com.bs.spring.bintest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
	private String name;
	private int age;
	private double height;
}