package surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
	
	
	public static Serializable deeplyCopy(Serializable src){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			
			byte[] bytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
