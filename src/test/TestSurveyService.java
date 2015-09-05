package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import surveypark.model.User;
import surveypark.service.SurveyService;
import surveypark.service.UserService;

public class TestSurveyService {
	
	private static UserService userService;
	private static SurveyService ss;
	
	static{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-bean.xml");
		userService=(UserService) ctx.getBean("userService");
		ss=(SurveyService) ctx.getBean("surveyService");
	}
	
	@Test
	public void insertUser(){
		User user=new User();
		user.setEmail("a@z.com");
		user.setPassword("123");
		user.setNickName("操你妈");
		userService.saveEntity(user);
	}
	
	@Test
	public void testNewSurvey(){
		User user=new User();
		user.setId(1);
		ss.newSurvey(user);
	}
}
