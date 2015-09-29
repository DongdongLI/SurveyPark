package surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.model.Answer;
import surveypark.model.Page;
import surveypark.model.Survey;
import surveypark.service.SurveyService;
import surveypark.util.DataUtil;
import surveypark.util.StringUtil;
import surveypark.util.ValidatorUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware,ParameterAware{

	private static final long serialVersionUID = 1L;
	public static final String CURRENT_SURVEY="current_survey";
	public static final String ALL_PARAMS_MAP="all_params";
	
	private List<Survey> surveys;
	@Resource
	private SurveyService surveyService;
	private Integer sid;
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	
	private Page curPage;
	
	public Page getCurPage() {
		return curPage;
	}

	public void setCurPage(Page curPage) {
		this.curPage = curPage;
	}

	private Integer curPid;
	
	
	public Integer getCurPid() {
		return curPid;
	}

	public void setCurPid(Integer curPid) {
		this.curPid = curPid;
	}

	private ServletContext servletContext; 
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}
	
	@Override
	public Survey getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	
	// find all opened surveys
	public String findAllAvailableSurveys(){
		this.surveys=surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	// get logo path url
	public String getImageUrl(String path){
		if(ValidatorUtil.isValid(path)){
			String absPath=servletContext.getRealPath(path);
			File f=new File(absPath);
			if(f.exists()){
				return servletContext.getContextPath()+path;
			}
		}
		return servletContext.getContextPath() + "/question.bmp";
	}
	
	// start the survey
	/*
	 * get the first page of the survey and display
	 * (needs sid)
	 * need to keep 'curPage' to keep track where the user is*/
	public String entry(){
		// get the first page of current survey
		this.curPage=surveyService.getFirstPage(sid);
		// put the current survey into session
		sessionMap.put(CURRENT_SURVEY, curPage.getSurvey());
		sessionMap.put(ALL_PARAMS_MAP, new HashMap<Integer,Map<String, String[]>>());
		return "engageSurveyPage";
	}
	
	public String doEngageSurvey(){
		String submitName=getSubmitName();
		// previous step
		if(submitName.endsWith("pre")){
			mergeParamsIntoSession();
			this.curPage=surveyService.getPrePage(curPid);
			return "engageSurveyPage";
		}
		// next
		else if(submitName.endsWith("next")){
			mergeParamsIntoSession();
			this.curPage=surveyService.getNextPage(curPid);
			return "engageSurveyPage";
		}
		// finish
		else if(submitName.endsWith("done")){
			mergeParamsIntoSession();
			// save the answers into DB
			surveyService.saveAnswer(processAnswers());
			clearSessionData();
			return "engageSurveyAction";
		}
		else if(submitName.endsWith("exit")){
			
		}
		
		return null;
	}
	private List<Answer> processAnswers() {
		Map<Integer, String> matrixRadioMap=new HashMap<Integer,String>();
		
		List<Answer> answers=new ArrayList<>();
		Answer answer=null;
		String key=null;
		String[] value=null;
		Map<Integer, Map<String,String[]>> allMap=getAllParametersMap();
		for(Map<String, String[]> map:allMap.values()){
			for(Entry<String, String[]> entry:map.entrySet()){
				key=entry.getKey();
				value=entry.getValue();
				if(key.contains("q")){
					// no other options. Just regular param
					if(!key.contains("_") && !key.contains("other")){
						answer=new Answer();
						answer.setAnswerIds(StringUtil.arr2Str(value)); // answerid
						answer.setQuestionId(getQid(key)); // question id
						answer.setSurveyId(getCurrentSurvey().getId()); // survey id
						answer.setOtherAnswer(StringUtil.arr2Str(map.get(key+"other"))); // other answer
						answers.add(answer);
					}else if(key.contains("_")){
						Integer radioQid=getMatrixRadioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if(oldValue==null){
							matrixRadioMap.put(radioQid, StringUtil.arr2Str(value));
						}else{
							matrixRadioMap.put(radioQid,oldValue+","+StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		processMatrixRadioMap(matrixRadioMap,answers);
		return answers;
	}
	private void processMatrixRadioMap(Map<Integer, String> matrixRadioMap, List<Answer> answers) {
		Answer answer=null;
		Integer key=null;
		String value=null;
		
		for(Entry<Integer, String> entry:matrixRadioMap.entrySet()){
			key=entry.getKey();
			value=entry.getValue();
			answer=new Answer();
			answer.setAnswerIds(value);
			answer.setSurveyId(getCurrentSurvey().getId());
			answers.add(answer);
		}
	}

	// get question id for matrix style question
	// id->q12_0 --- > 12
	private Integer getMatrixRadioQid(String key) {
		return Integer.parseInt(key.substring(1,key.lastIndexOf("_")));
	}

	private Survey getCurrentSurvey() {
		return (Survey)sessionMap.get(CURRENT_SURVEY);
	}

	//id->q12->12
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}

	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}

	// merge the parameters into session
	private void mergeParamsIntoSession() {
		Map<Integer,Map<String,String[]>> allParamsMap=getAllParametersMap();
		allParamsMap.put(curPid, paramMap);
		System.out.println("after merge: ");
		System.out.println(allParamsMap.keySet());
	}

	private Map<Integer, Map<String, String[]>> getAllParametersMap() {
		return (Map<Integer, Map<String, String[]>>)sessionMap.get(ALL_PARAMS_MAP);
	}

	private String getSubmitName() {
		for(String key:paramMap.keySet()){
			if(key.startsWith("submit_")){
				return key;
			}
		}
		return null;
	}

	private Map<String,Object> sessionMap;
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=sessionMap;
	}

	Map<String, String[]> paramMap=null;
	//Storing all the submitted parameter map
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.paramMap=arg0;
	}
	// for radio, checkbox, select to display the choices after coming back
	public String setTag(String name,String value,String tag){
		Map<String, String[]> map=getAllParametersMap().get(curPage.getId());
		String[] values=map.get(name);
		if(StringUtil.contain(values,value)){
			return tag;
		}
		return "";
	}
	
	public String setText(String name){
		Map<String, String[]> map=getAllParametersMap().get(curPage.getId());
		String[] values=map.get(name);
		return "value='"+values[0]+"'";
	}
	// store the answer into db.
	
}
