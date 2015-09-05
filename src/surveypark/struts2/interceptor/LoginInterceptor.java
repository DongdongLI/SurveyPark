package surveypark.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import surveypark.model.User;
import surveypark.struts2.UserAware;
import surveypark.struts2.action.BaseAction;
import surveypark.struts2.action.LoginAction;
import surveypark.struts2.action.RegAction;

public class LoginInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action=(BaseAction) arg0.getAction();
		if(action instanceof LoginAction || action instanceof RegAction){
			return arg0.invoke();
		}else{
			User user=(User)arg0.getInvocationContext().getSession().get("user");
			if(user==null){// not login
				return "login";
			}else{
				if(action instanceof UserAware)
					((UserAware)action).setUser(user);
				return arg0.invoke(); // let go
			}
		}
	
	}

	
}
