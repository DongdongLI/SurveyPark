package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import surveypark.model.User;
import surveypark.service.UserService;

public class TestUser {
	
	private static UserService userService;
	
	
	static{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-bean.xml");
		userService=(UserService) ctx.getBean("userService");
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
	public void testMD5() throws NoSuchAlgorithmException{
		char[] cs=new char[16];
		for(int i=0;i<10;i++){
			cs[i]=(char)('0'+i);
		}
		for(int i=0;i<6;i++)
			cs[i]=(char)('A'+i);
		String str="abc";
		byte[] bytes=str.getBytes();
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] encrypted=md.digest(bytes);
		
		StringBuilder sb=new StringBuilder();
		for(byte b:encrypted)
		{
			sb.append(cs[(b>>4)& 0x0F]);
			sb.append(cs[b & 0x0F]);
		}
		System.out.println(sb.toString());
	}
}
