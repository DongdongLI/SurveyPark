package surveypark.model.statistics;

import java.util.ArrayList;
import java.util.List;

import surveypark.model.Question;

public class QuestionStatisticsModel {
	private Question question;
	private Integer count;

	private List<OptionStatisticsModel> osm= new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsm() {
		return osm;
	}

	public void setOsm(List<OptionStatisticsModel> osm) {
		this.osm = osm;
	}
	
	
}
