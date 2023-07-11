package com.bs.spring.config;
// 클래스방식으로 bean등록해서 사용하기
// pojo클래스를 configuration으로 사용할 수 있음 -> @Contiguration어노테이션이용

import javax.websocket.RemoteEndpoint.Basic;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;

import com.bs.spring.bintest.Animal;
import com.bs.spring.bintest.Department;
import com.bs.spring.bintest.Employee;

@Configuration // 무조건 contiguration 안에서 해야함
@ComponentScan(basePackages = "com.bs.spring",
			  includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.bs.spring.include.*"})}, //어노테이션표시가 없더라도 해당이되면 bean으로 등록함
			  excludeFilters = {} // 등록된것을 제외시키는 것
				
		) // 내가 등록되어있는 bean들을 범위를 설정해서 가져올수 있음
//@Import()// 다른 configuration을 가져와 처리하는
public class BeanTestConfiguration {
	//springbeanconfiguration.xml과 동일한 기능
	
	//spring에서 사용할 bean을 자바코드로 등록할 수 있다.
	//@Bean어노테이션을 이용
	//method선언을 통해 등록함.
	@Bean
	@Order(1)//bean우선순위를 설정할수 있다.
	public Animal ani() { // 메소드명 ani가 servlet-context에서 id값이 됨
		return  Animal.builder().name("킥킥").age(5).height(80).build();
	}
	@Bean
	//등록된 bean에 특정 id값 부여하기
	@Qualifier("sol") // 이렇게 여기에 id값을 부여할수 있다.
	public Employee getEmployee(@Qualifier("sal") Department d) { // dept의 의존관계를 이렇게 넣을수 있다.
		return Employee.builder().name("최솔").age(27).address("경기도 안양시").salary(200).dept(d).build();
	}
	@Bean
	public Department sal() {
		return Department.builder().deptCode(2L).deptTitle("영업부").deptLocation("서울").build();
	}
	// 원래는 이렇게 source를 사용해서 만들수 있다.
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	/* gson객체를 계속 생성하지 않고 이렇게 클래스를 선언하면 내부에서 사용이 가능하다.		
	 * @Bean public Gson gsion() { return new Gson(); }
	 */
}
