package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDataSource {
	
	@Test
	public void getConnection() throws BeansException, SQLException{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-bean.xml");
		System.out.println(ctx.getBean(DataSource.class).getConnection());
	}
	
}
