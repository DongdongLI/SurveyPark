package surveypark.util;

import surveypark.struts2.action.QuestionAction;

public class StringUtil {
	// split String based on the tag
	public static String[] strToArr(String str,String split){
		if(ValidatorUtil.isValid(str)){
			return str.split(split);
		}
		return null;
	}
	// check if the value str is contained in values arr
	public static boolean contain(String[] values, String value) {
		if(values==null || values.length==0)
			return false;
		
		for(String v:values){
			if(v.equals(value))
				return true;
		}
		return false;
	}
	
	public static String arr2Str(String[] arr){
		String temp="";
		if(!(arr==null || arr.length==0)){
			for(String s:arr){
				temp=temp+s+",";
			}
			// delete the last ","
			return temp.substring(0,temp.length()-1);
		}
		return temp;
	}
}
