package surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.model.User;
import surveypark.service.UserService;
import surveypark.util.DataUtil;
import surveypark.util.ValidatorUtil;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User>{

	private static final long serialVersionUID = 1L;
	private User model=new User();
	
	private String confirmPassword;
	@Resource
	private UserService userService;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public User getModel() {
		return model;
	}
	
	// arrive signup page
	@SkipValidation
	public String toPage(){
		return "toPage" ;
	}
	
	public String signup(){
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		
		return "signup";
	}
	
	public void validate(){
		// nothing is empty
		if(!ValidatorUtil.isValid(getModel().getEmail()))
			addFieldError("email", "Email should not be empty");
		if(!ValidatorUtil.isValid(getModel().getPassword()))
			addFieldError("password", "Password should not be empty");
		if(!ValidatorUtil.isValid(getModel().getNickName()))
			addFieldError("nickName", "Nickname should not be empty");
		
		if(hasErrors()){
			return;
		}
		// check two passwords
		if(!getModel().getPassword().equals(confirmPassword))
		{
			addFieldError("password", "The password does not match.");
			return;
		}
		// if e-mail is available
		System.out.println("checking email");
		if(!userService.emailCheck(getModel().getEmail())){
			addFieldError("email", "Email used");
			return;
		}
		//signup();
	}
}
