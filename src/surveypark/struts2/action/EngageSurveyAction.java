package surveypark.struts2.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.model.Page;
import surveypark.model.Survey;
import surveypark.service.SurveyService;
import surveypark.util.DataUtil;
import surveypark.util.ValidatorUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware{

	private static final long serialVersionUID = 1L;
	public static final String CURRENT_SURVEY="current_survey";
	
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
		
		return "engageSurveyPage";
	}

	private Map<String,Object> sessionMap;
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=sessionMap;
	}
}
