package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.dao.impl.AnswerDaoImpl;
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
	@Resource(name="answerDao")
	private AnswerDaoImpl answerDao;
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

	// delete the answers while deleting the question
	@Override
	public void deleteQuestion(Integer qId) {
		String hql="delete from Answer a where a.questionId=?";
		answerDao.batchEntityByHQL(hql, qId);
		hql="delete from Question q where q.id=?";
		questionDao.batchEntityByHQL(hql, qId);
	}


	@Override
	public void deletePage(Integer pid) {
		// delete answers
		String hql="delete from Answer a where a.questionId in (select q.id from Question q where q.page.id= ?)";
		answerDao.batchEntityByHQL(hql, pid);
		// delete questions
		hql="delete from Question q where q.page.id= ?";
		questionDao.batchEntityByHQL(hql, pid);
		// delete pages
		hql="delete from Page p where p.id= ?";
		pageDao.batchEntityByHQL(hql, pid);
	}

	// during delete, Hibernate do not allow allow 2 level or more connection
	@Override
	public void deleteSurvey(Integer sid) {
		// delete answer
		String hql="delete from Answer a where a.surveyId= ?";
		answerDao.batchEntityByHQL(hql, sid);
		// delete question
		hql="delete from Question q where q.survey.id= ?";
		questionDao.batchEntityByHQL(hql, sid);
		// delete page
		hql="delete from Page p where p.survey.id= ?";
		pageDao.batchEntityByHQL(hql, sid);
		// delete survey
		hql="delete from Survey s where s.id= ?";
		surveyDao.batchEntityByHQL(hql, sid);
	}


	@Override
	public Question getQuestion(Integer qId) {
		return questionDao.getEntity(qId);
	}


	@Override
	public void clearAnswers(Integer sid) {
		String hql="delete from Answer a where a.surveyId= ?";
		answerDao.batchEntityByHQL(hql, sid);
	}


	@Override
	public void toggleStatue(Integer sid) {
		Survey survey=getSurvey(sid);
		if(survey.getClosed()==null)
			survey.setClosed(true);
		String hql="update Survey s set s.closed= ? where s.id= ?";
		surveyDao.batchEntityByHQL(hql,!survey.getClosed(), sid);
	}


	@Override
	public void updateLogoPhotoPath(Integer sid, String string) {
		String hql="update Survey s set s.logoPhotoPath = ? where s.id= ? ";
		surveyDao.batchEntityByHQL(hql, string,sid);
	}
	
}
