package surveypark.model;

import java.util.Arrays;

import surveypark.util.StringUtil;
import surveypark.util.ValidatorUtil;

public class Question extends BaseEntity{
	
	private static final String RN= "\r\n";
	private Integer id;
	private Integer questionType;
	private String description;
	private String title;
	
	private String options;
	private String[] optionArr;
	
	private boolean other;
	
	private int otherStyle;
	
	private String otherSelectOptions;
	private String[] otherSelectOptionArr;
	
	private String matrixRowTitle;
	private String[] matrixRowTitleArr;
	
	private String matrixColTitle;
	private String[]matrixColTitleArr;
	
	private String matrixSelectOptions;
	private String[]matrixSelectOptionArr;
	
	private Page page;
	// sid keep missing
	private Survey survey;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
		this.optionArr=StringUtil.strToArr(options, RN);
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) { // to split up option
		this.optionArr = optionArr;
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr=StringUtil.strToArr(otherSelectOptions, RN);
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
		this.otherSelectOptionArr = otherSelectOptionArr;
	}

	public String getMatrixRowTitle() {
		return matrixRowTitle;
	}

	public void setMatrixRowTitle(String matrixRowTitle) {
		this.matrixRowTitle = matrixRowTitle;
		this.matrixRowTitleArr=StringUtil.strToArr(matrixRowTitle, RN);
	}

	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}

	public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
		this.matrixRowTitleArr = matrixRowTitleArr;
	}

	public String getMatrixColTitle() {
		return matrixColTitle;
	}

	public void setMatrixColTitle(String matrixColTitle) {
		this.matrixColTitle = matrixColTitle;
		this.matrixColTitleArr=StringUtil.strToArr(matrixColTitle, RN);
	}

	public String[] getMatrixColTitleArr() {
		return matrixColTitleArr;
	}

	public void setMatrixColTitleArr(String[] matrixColTitleArr) {
		this.matrixColTitleArr = matrixColTitleArr;
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionArr=StringUtil.strToArr(matrixSelectOptions, RN);
	}

	public String[] getMatrixSelectOptionArr() {
		return matrixSelectOptionArr;
	}

	public void setMatrixSelectOptionArr(String[] matrixSelectOptionArr) {
		this.matrixSelectOptionArr = matrixSelectOptionArr;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// getter and setter for survey
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	
	
	
}
