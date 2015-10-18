package surveypark.struts2.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;

import surveypark.model.Survey;
import surveypark.model.User;
import surveypark.service.SurveyService;
import surveypark.struts2.UserAware;
import surveypark.util.ValidatorUtil;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,SessionAware,ServletContextAware{

	private User user;
	
	private Survey survey=new Survey();
	
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	private List<Survey> mySurveys;
	
	private Integer sid;
	
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		System.out.println("get sid: "+sid);
		this.sid = sid;
	}
	// the original and target page id
	private Integer srcPid;
	private Integer targPid;
	// 0 means before, 1 means after
	private Integer pos;
	
	
	
	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}

	

	public Integer getTargPid() {
		return targPid;
	}

	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	// error page redirect
	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

	@Resource
	private SurveyService surveyService;
	
	public List<Survey> getMySurveys() {
		
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	
	
	// look up surveys
	public String mySurveys(){
		this.mySurveys=surveyService.findSurveys(user);
		//System.out.println(mySurveys);
		return "mySurveys";
	}

	
	public String newSurvey(){
		this.survey=surveyService.newSurvey(user);
		sid=survey.getId();// without it there will be no sid updated
		return "newSurvey";
	}

	// insert user object
	public void setUser(User user) {
		this.user=user;
	}

	@Override
	public Survey getModel() {
		//System.out.println("in getModel:"+survey);
		//this.model = surveyService.getSurvey(sid); // should never add this two lines here, or there will be IllegalArgumentException
		//this.model = surveyService.getSurveyWithChildren(sid);
		return survey;
	}
	
//	public void prepareDesignSurvey(){
//		//this.model = surveyService.getSurveyWithChildren(sid);
//	}
	
	public String designSurvey(){
		//System.out.println("get sid!: "+sid);
		this.survey = surveyService.getSurveyWithChildren(sid);
		//sessionMap.put("model", survey);
		ValueStack valueStack=ActionContext.getContext().getValueStack();
		valueStack.pop();
		valueStack.push(survey);
		//this.model = surveyService.getSurvey(sid);
		//System.out.println("model:"+survey);
		return "designSurvey";
	}
	// this method is only invoked before designSurvey()
//	public void prepareDesignSurvey(){
//		this.model=surveyService.getSurvey(sid);
//	}
	
	private Map<String, Object> sessionMap;
	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap=arg0;
	}
	
	public String editSurvey(){
		this.survey = surveyService.getSurveyWithChildren(sid);
		//sessionMap.put("model", survey);
		ValueStack valueStack=ActionContext.getContext().getValueStack();
		valueStack.pop();
		valueStack.push(survey);
		return "editSurvey";
	}
	// only get called before updateSurvey method
	public void prepareUpdateSurvey(){
		setInputPage("/editSurvey.jsp");
	}
	
	public String updateSurvey(){
		
		//System.out.println("in update"+this.getModel());
		//this.user=getModel().getUser();//make sure the userid is not null
		//System.out.println("updateSurvey: user: "+this.user);
		getModel().setUser(user);
		surveyService.updateEntity(getModel());
		ValueStack valueStack=ActionContext.getContext().getValueStack();
		valueStack.pop();
		valueStack.push(survey);
		return "designSurveyAction";
	}
	
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "mySurveysAction";
	}
	
	public String clearAnswers(){
		// sid
		surveyService.clearAnswers(sid);
		return "mySurveysAction";
	}
	
	public String toggleStatus(){
		surveyService.toggleStatue(sid);
		return "mySurveysAction";
	}
	
	public String toAddLogoPage(){
		return "addLogoPage";
	}
	// for logo upload
	private File logoPhoto;
	private String logoPhotoFileName;
	
	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}
	// only get called before doAddLogo method
	public void prepareDoAddLogo(){
		setInputPage("/addLogo.jsp");
	}
	
	public String doAddLogo(){
		setInputPage("/addLogo.jsp");
		if(ValidatorUtil.isValid(logoPhotoFileName)){
			// get the real path of "upload" folder
			String dir=servletContext.getRealPath("/upload");
			System.out.println("abspath for upload: "+dir);
			// file extension
			String ext=logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			// new file name
			long l=System.nanoTime();
			File newFile=new File(dir,l+ext);
			
			logoPhoto.renameTo(newFile);
			surveyService.updateLogoPhotoPath(sid,"/upload/"+l+ext);
			System.out.println("absolute path: "+logoPhoto.getAbsolutePath());
			System.out.println("object path: "+survey.getLogoPhotoPath());
		}
		return "designSurveyAction";
	}

	private ServletContext servletContext;
	public void setServletContext(ServletContext arg0) {
		
		servletContext=arg0;
	}
	
	public boolean photoExists(){
		String path=getModel().getLogoPhotoPath();
		if(ValidatorUtil.isValid(path)){
			String absPath=servletContext.getRealPath(path);
			System.out.println("in photoExist: "+absPath);
			File file=new File(absPath);
			return file.exists();
		}
		return false;
	}
	
	public String doMoveOrCopy(){
		surveyService.moveOrCopyPage(srcPid, targPid, pos);
		return "designSurveyAction";
	}
	
	public String analyzeSurvey(){
		survey=surveyService.getSurveyWithChildren(sid);
		surveyService.updateEntity(getModel());
		ValueStack valueStack=ActionContext.getContext().getValueStack();
		valueStack.push(survey);
		return ""
				+ "";
	}
}
/*
 * public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage" ;
	}*/
 