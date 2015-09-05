package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.model.User;
import surveypark.service.UserService;
import surveypark.util.DataUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public boolean emailCheck(String email) {
		String hql="from User u where u.email=?";
		List<User> list=this.findEntitiesByHQL(hql, email);
		//System.out.println("the size is: "+list.size());
		if(list==null || list.size()==0)
			return true;
		return false;
	}// return true means available

	@Override
	public User validateLoginInfo(String email, String password) {
		String hql="from User u where u.email=? and u.password=?";
		List<User> list=this.findEntitiesByHQL(hql, email,DataUtil.md5(password));
		//System.out.println("the size is: "+list.size());
		if(list==null || list.size()==0)
			return null;
		return list.get(0);
	}
	
}
