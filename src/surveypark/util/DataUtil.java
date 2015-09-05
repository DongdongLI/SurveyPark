package surveypark.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataUtil {
	
	public static String md5(String src){
		char[] cs=new char[16];
		for(int i=0;i<10;i++){
			cs[i]=(char)('0'+i);
		}
		for(int i=0;i<6;i++)
			cs[i]=(char)('A'+i);
		// set up the cs array
		String str="abc";
		
		byte[] bytes=str.getBytes();
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5"); // use the built-in algorithm
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encrypted=md.digest(bytes);
		
		StringBuilder sb=new StringBuilder();
		for(byte b:encrypted)
		{
			sb.append(cs[(b>>4)& 0x0F]); // transform the first 4 bits
			sb.append(cs[b & 0x0F]); // the last 4 bits
		}
		return sb.toString();
	}
	
}
