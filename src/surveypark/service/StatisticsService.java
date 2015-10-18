package surveypark.service;

import surveypark.model.statistics.QuestionStatisticsModel;

public interface StatisticsService {
	
	public QuestionStatisticsModel statistics(Integer qid);
	
}
