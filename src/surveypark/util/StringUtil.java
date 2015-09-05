package surveypark.util;

import surveypark.struts2.action.QuestionAction;

public class StringUtil {
	public static String[] strToArr(String str,String split){
		if(ValidatorUtil.isValid(str)){
			return str.split(split);
		}
		return null;
	}
}
