package surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.model.Page;
import surveypark.model.Survey;
import surveypark.model.User;
import surveypark.service.SurveyService;
import surveypark.struts2.UserAware;

@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware{
	private static final long serialVersionUID = 1L;
	
	private Integer srcPid;
	private List<Survey> mySurveys;
	private Integer targPid;
	private Integer pos;
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Resource
	private SurveyService surveyService;
	private User user;
	
	
	
	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}

	public Page getModel() {
		// TODO Auto-generated method stub
		return null;
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

	public String toSelectTargetPage(){
		this.setMySurveys(surveyService.getSurveyWithPages(user));
		return "toSelectTargetPage";
	}

	public String doMoveOrCopy(){
		// srcPid, targPid, pos, sid
		System.out.println("srcPid: "+srcPid);
		System.out.println("targPid: "+targPid);
		System.out.println("pos: "+pos);
		surveyService.moveOrCopyPage(srcPid,targPid,pos);
		return "designSurveyAction";
	}
	
	@Override
	public void setUser(User user) {
		this.user=user;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
}
