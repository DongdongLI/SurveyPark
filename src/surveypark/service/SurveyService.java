package surveypark.service;

import java.util.List;

import surveypark.model.Page;
import surveypark.model.Question;
import surveypark.model.Survey;
import surveypark.model.User;

public interface SurveyService extends BaseService<Survey>  {

	public List<Survey> findSurveys(User user);
	public Survey newSurvey(User user);
	public Survey getSurvey(Integer sid);
	public Survey getSurveyWithChildren(Integer sid);
	public void saveOrUpdatePage(Page page);
	public Page getPage(Integer pId);
	public void saveOrUpdateQuestion(Question question);
}
