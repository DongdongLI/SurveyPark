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
	public void deleteQuestion(Integer qId);
	public void deletePage(Integer pid);
	public void deleteSurvey(Integer sid);
	public Question getQuestion(Integer qId);
	public void clearAnswers(Integer sid);
	public void toggleStatue(Integer sid);
	public void updateLogoPhotoPath(Integer sid, String string);
	public List<Survey> getSurveyWithPages(User user);
	public void moveOrCopyPage(Integer srcPid, Integer targPid, Integer pos);
	public List<Survey> findAllAvailableSurveys();
	public Page getFirstPage(Integer sid);
}
