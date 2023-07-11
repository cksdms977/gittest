package com.bs.spring;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.bintest.Animal;
import com.bs.spring.bintest.Employee;
import com.bs.spring.bintest.FunctionalTest;
import com.bs.spring.include.TagetComponent;

@Controller
public class HomController {
	//springbean으로 동록된 객체는 필드로 가져와 사용할 수 있음
	@Autowired // Animal에 있는 개체를 넣어줘 라는 뜻, autowired를 하면 type을 보고 집어넣음 그래서 타입이 2개이면 필드명을 id값이랑 똑같이 바꿔주면 거기에 맞도록 들어간다. 
	// 중복된 타입이 있는 경우 @Qualifier어노테이션을 이용해서 특정 bean을 선택할수 있음
	@Qualifier("dog")
	//	private Anumal a;
//	private Animal bbo;
	private Animal a;
	@Autowired
	@Qualifier("bbo")
//	private Animal dog;
	private Animal b;
	
	//springBean으로 등록되지않는 객체를 Autowired하면??? --> 에러가 발생함
	@Autowired(required = false) // required false를 두면 bean에 있으면 넣고 없으면 넣지 마라 라는 기준 ture이면 무조건 다 넣어라 해서 bean에 없으면 에러뜸 
	private Employee emp;
	
	@Autowired
	public Employee emp2;
	
	//자바로 등록한 bean가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c;
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	List<Animal> animals;
	
	@Autowired
	private TagetComponent tc;
	
	//@어노테이션으로 bean등록
	@Autowired
	private FunctionalTest ft;
	
	//basepackage 외부에 있는 @Component
	@Autowired
	private Test test;
	
	
	@RequestMapping("/test") // servlet에서 doget부분이라고보면된다.
	public String home() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(c);
		System.out.println(sol);
		animals.forEach(System.out::println);
		System.out.println(tc);
		System.out.println("functionalTest출력");
		System.out.println(ft);
		System.out.println(ft.getA());
		
		//spring에서 파일을 불러올 수 있는 Resource객체를 제공
		org.springframework.core.io.Resource resource = new ClassPathResource("mydata.properties");
		try {
			Properties prop = PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			resource = new FileSystemResource("C:\\Users\\GD\\git\\repository2\\gittest\\gittest\\spring\\src\\main\\resources\\test.txt");
			Files.lines(Paths.get(resource.getURI()),Charset.forName("UTF-8")).forEach(System.out::println);
			resource = new UrlResource("https://www.naver.com");
			InputStream is = resource.getInputStream();
			int d = 0;
			StringBuffer sb=new StringBuffer();
			while((d=is.read())!= -1) {
				sb.append((char)d);
			}
			System.out.println(sb);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
