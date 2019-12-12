package com.ecard;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement // 启注解事务管理
public class LsyyptApplicationTests {
	

	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	@org.springframework.transaction.annotation.Transactional
	public void TEST() {

	}
}
