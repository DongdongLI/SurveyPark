package surveypark.model;

import java.util.HashSet;
import java.util.Set;

public class Page extends BaseEntity{
	private Integer id;
	private String title="No Name";
	private String description;
	private Float orderNo;
	
	private Survey survey;
	private Set<Question> questions=new HashSet<>();
	
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	
	
	public Float getOrderNo() {
		if(this.orderNo==null)
			this.orderNo=(float)id;
		return orderNo;
	}
	public void setOrderNo(Float orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		if(this.orderNo==null)
			this.orderNo=(float)id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
