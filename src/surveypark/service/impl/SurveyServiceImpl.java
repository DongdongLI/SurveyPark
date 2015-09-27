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
import surveypark.util.DataUtil;

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


	@Override
	public List<Survey> getSurveyWithPages(User user) {
		String hql="from Survey s where s.user.id= ?";
		List<Survey> list=surveyDao.findEntitiesByHQL(hql, new Object[]{user.getId()});
		for(Survey s:list){
			s.getPages().size();
		}
		return list;
	}


	@Override
	public void moveOrCopyPage(Integer srcPid, Integer targPid, Integer pos) {
		// determine if it is copy or move (compare if it within the same survey)
		// use source Pid to get source page and then get the source survey
		// use target Pid to get target page and target survey
		// compare the source survey and target survey
		Page srcPage=this.getPage(srcPid);
		Survey srcSurvey=srcPage.getSurvey();
		
		//System.out.println("srcPage: "+srcPage); // page get successfully
		
		Page tarPage=this.getPage(targPid);
		Survey tarSurvey=tarPage.getSurvey();
		
		//System.out.println("targPage: "+tarPage); // page get successfully
		
		if(srcSurvey.getId().equals(tarSurvey.getId())){// it is moving
			setOrderNo(srcPage,tarPage,pos);
		}else{
			srcPage.getQuestions().size();
			
			Page copyPage=(Page)DataUtil.deeplyCopy(srcPage);
			copyPage.setSurvey(tarSurvey);
			pageDao.saveEntity(copyPage);
			for(Question q:copyPage.getQuestions()){
				questionDao.saveEntity(q);
			}
			setOrderNo(copyPage,tarPage,pos);
		}
		
	}


	private void setOrderNo(Page srcPage, Page tarPage, Integer pos) {
		// before
		if(pos==0){
			// determine if the target page is the first page
			if(isFirstPage(tarPage)){
				srcPage.setOrderNo(tarPage.getOrderNo()-0.01f);
			}else{
				Page prePage=getPrePage(tarPage);
				srcPage.setOrderNo((tarPage.getOrderNo()+prePage.getOrderNo())/2);
			}
		}else{
			// determine if the target page is the last page
			//System.out.println("srcPage:"+srcPage); // this guy is missing
			//System.out.println("targPage:"+tarPage); // survive
			if(isLastPage(tarPage)){
				srcPage.setOrderNo(tarPage.getOrderNo()+0.01f);
			}else{
				Page nextPage=getNextPage(tarPage);
				srcPage.setOrderNo((tarPage.getOrderNo()+nextPage.getOrderNo())/2);
			}
		}
		// after
	}


	private Page getNextPage(Page page) {
		String hql="from Page p where p.survey.id=? and p.orderNo > ? order by p.orderNo asc";
		List<Page> prevPages=pageDao.findEntitiesByHQL(hql, new Object[]{page.getSurvey().getId(),page.getOrderNo()});
		return prevPages.get(0);
	}


	private boolean isLastPage(Page page) {
		String hql= "select count(*) from Page p where p.survey.id= ? and p.orderNo> ?";
		Long count=(Long)pageDao.uniqueResult(hql,page.getSurvey().getId(),page.getOrderNo());
		if(count==0)
			return true;
		return false;
	}

	// get the previous page in the same survey
	private Page getPrePage(Page page) {
		String hql="from Page p where p.survey.id=? and p.orderNo < ? order by p.orderNo desc";
		List<Page> prevPages=pageDao.findEntitiesByHQL(hql, new Object[]{page.getSurvey().getId(),page.getOrderNo()});
		return prevPages.get(0);
	}


	private boolean isFirstPage(Page page) {
		// determine if a page is the first page of its survey
		String hql= "select count(*) from Page p where p.survey.id= ? and p.orderNo< ?";
		Long count=(Long)pageDao.uniqueResult(hql,page.getSurvey().getId(),page.getOrderNo());
		if(count==0)
			return true;
		return false;
	}


	@Override
	public List<Survey> findAllAvailableSurveys() {
		String hql="from Survey s where s.closed= ?";
		return surveyDao.findEntitiesByHQL(hql, new Object[]{false});
	}


	@Override
	public Page getFirstPage(Integer sid) {
		String hql="from Page p where p.survey.id= ? order by p.orderNo asc";
		List<Page> list=pageDao.findEntitiesByHQL(hql, new Object[]{sid});
		Page page=list.get(0);
		page.getQuestions().size();
		// need to get the survey as well
		page.getSurvey().getTitleTxt();
		return page;
	}
	
}
