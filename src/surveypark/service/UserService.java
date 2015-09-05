package surveypark.service;

import surveypark.model.User;

public interface UserService extends BaseService<User>{

	public boolean emailCheck(String email);

	public User validateLoginInfo(String email, String password);

}
