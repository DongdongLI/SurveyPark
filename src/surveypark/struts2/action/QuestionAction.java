package surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import surveypark.model.Page;
import surveypark.model.Question;
import surveypark.model.Survey;
import surveypark.service.SurveyService;

public class QuestionAction extends BaseAction<Question>{

	
	private static final long serialVersionUID = 1L;

	private Question question=new Question();
	
	private Integer sid;
	private Integer pId;
	
	private Integer qId;
	
	@Resource
	private SurveyService surveyService;
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	
	public Integer getQId() {
		return qId;
	}

	public void setQId(Integer qId) {
		this.qId = qId;
	}

	@Override
	public Question getModel() {
		return question;
	}
	// select the question type
	public String toSelection(){
		
		return "selectQuestionType";
	}
	
	// jump to question design page
	public String toDesignQuestionPage(){
		// get poarameters: sid, pId, model
		// different choice leads to different pages
		return ""+getModel().getQuestionType();
	}
	
	public String saveOrUpdateQuestion(){
		// sid, pId, model
		System.out.println("save OR UPDATE ques: pid"+pId+" sid: "+sid); // get it
		Survey survey=new Survey();
		survey.setId(sid);
		Page page=new Page(); 
		page.setId(pId);
		
		//System.out.println("save or update question: :"+getModel());
		//System.out.println("size: "+page.getQuestions().size());
		page.getQuestions().add(getModel());
		//System.out.println("size: "+page.getQuestions().size());
		getModel().setPage(page);
		getModel().setSurvey(survey);
//		System.out.println("***save or update**** ");
//		System.out.println("survey "+getModel().getSurvey().getId());
//		System.out.println("page "+getModel().getPage().getId());
//		System.out.println("*********************");
		surveyService.saveOrUpdateQuestion(getModel());
		return "designSurveyAction";
	}
	
	public String deleteQuestion(){
		// sid, qid
		surveyService.deleteQuestion(qId);
		return "designSurveyAction";
	}
	public String editQuestion(){
		this.question=surveyService.getQuestion(qId);
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.pop();
		stack.push(getModel());
		//System.out.println("qt: "+getModel().getQuestionType());
		return ""+getModel().getQuestionType();
	}
	
}
