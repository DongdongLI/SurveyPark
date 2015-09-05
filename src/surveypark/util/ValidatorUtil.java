package surveypark.util;

public class ValidatorUtil {
	
	// check the string
	public static boolean isValid(String src){
		return !(src==null||src.trim().length()==0);
	}
	
}
