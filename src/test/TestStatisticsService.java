package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import surveypark.model.User;
import surveypark.service.StatisticsService;
import surveypark.service.SurveyService;
import surveypark.service.UserService;

public class TestStatisticsService {
	
	private static UserService userService;
	private static StatisticsService ss;
	
	static{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-bean.xml");
		userService=(UserService) ctx.getBean("userService");
		ss=(StatisticsService) ctx.getBean("statisticsService");
	}
	
	
	@Test
	public void statistics(){
		System.out.println(ss.statistics(10));
	}
}
