package surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.model.User;
import surveypark.service.UserService;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{
	
	private static final long serialVersionUID = 1L;

	private User model=new User();
	
	@Resource
	private UserService userService;
	
	public User getModel() {
		return model;
	}
	
	public String toPage(){
		return "toPage";
	}
	
	public String signin(){
		
		return "signin";
	}
	
	@Override
	public void validate() {
		User temp=userService.validateLoginInfo(model.getEmail(),model.getPassword());
		if(temp==null)
			addActionError("Email or password error");
		else
			sessionMap.put("user", temp);
	}

	private Map<String, Object> sessionMap;
	public void setSession(Map<String, Object> map) {
		this.sessionMap=map;
	}
}
