package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.dao.impl.PageDaoImpl;
import surveypark.dao.impl.QuestionDaoImpl;
import surveypark.dao.impl.SurveyDaoImpl;
import surveypark.model.Page;
import surveypark.model.Question;
import surveypark.model.Survey;
import surveypark.model.User;
import surveypark.service.SurveyService;

@Service("surveyService")
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService{

	@Resource(name="surveyDao")
	private SurveyDaoImpl surveyDao; 
	
	@Resource(name="pageDao")
	private PageDaoImpl pageDao;
	
	@Resource(name="surveyDao")
	public void setBaseDao(BaseDao<Survey> baseDao) {
		super.setBaseDao(baseDao);
	}
	@Resource(name="questionDao")
	private QuestionDaoImpl questionDao;

	@Override
	public List<Survey> findSurveys(User user) {
		String hql="from Survey s where s.user.id= ?";
		return this.findEntitiesByHQL(hql, user.getId());
	}


	@Override
	public Survey newSurvey(User user) {
		Survey s=new Survey();
		Page p=new Page();
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}


	@Override
	public Survey getSurvey(Integer sid) {
		//System.out.println("get survey: "+surveyDao.getEntity(sid));
		return surveyDao.getEntity(sid);
	}


	@Override
	public Survey getSurveyWithChildren(Integer sid) {
		Survey survey=this.getSurvey(sid);
		for(Page p:survey.getPages()){
			p.getQuestions().size();
		}
		return survey;
	}


	@Override
	public void saveOrUpdatePage(Page page) {
		pageDao.saveOrUpdateEntity(page);
	}


	@Override
	public Page getPage(Integer pId) {
		return pageDao.getEntity(pId);
	}


	@Override
	public void saveOrUpdateQuestion(Question question) {
		questionDao.saveOrUpdateEntity(question);
	}

}
