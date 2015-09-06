package surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import surveypark.model.Page;
import surveypark.model.Survey;
import surveypark.service.SurveyService;

public class PageAction extends BaseAction<Page>{
	
	private static final long serialVersionUID = 1L;
	
	private Page page=new Page();
	private Integer sid;
	private Integer pId;
	
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String addPage(){
		
		return "addPage";
	}

	@Override
	public Page getModel() {
		
		return page;
	}
	public String saveOrUpdate(){
		Survey s=new Survey();
		s.setId(sid);
		//System.out.println(getModel());// has attribute but no id
		getModel().setSurvey(s);
		surveyService.saveOrUpdatePage(getModel());
		//System.out.println(getModel());//with id
		//System.out.println("the survey after new page: "+surveyService.getSurvey(sid));
		surveyService.getSurvey(sid).getPages().add(page);
		//System.out.println("pages: "+surveyService.getSurvey(sid).getPages());
		return "designSurveyAction";
	}
	
	public String editPage(){
		this.page=surveyService.getPage(pId);
		//System.out.println("in editPage: "+ page);
		ValueStack valueStack=ActionContext.getContext().getValueStack();
		valueStack.pop();
		valueStack.push(page);
		return "editPage";
	}
	
	public String deletePage(){
		surveyService.deletePage(pId);
		return "designSurveyAction";
	}
}
