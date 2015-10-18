package surveypark.model.statistics;

public class OptionStatisticsModel {
	private Integer optionIndex;
	private String optionLabel;
	
	// how many people have answered this question
	private Integer count;

	private Integer matrixRowIndex;
	private String matrixRowLabel;
	
	private Integer matrixColIndex;
	private String matrixColLabel;
	
	private Integer matrixSelectIndex;
	private String matrixSelectLabel;
	public Integer getOptionIndex() {
		return optionIndex;
	}
	public void setOptionIndex(Integer optionIndex) {
		this.optionIndex = optionIndex;
	}
	public String getOptionLabel() {
		return optionLabel;
	}
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getMatrixRowIndex() {
		return matrixRowIndex;
	}
	public void setMatrixRowIndex(Integer matrixRowIndex) {
		this.matrixRowIndex = matrixRowIndex;
	}
	public String getMatrixRowLabel() {
		return matrixRowLabel;
	}
	public void setMatrixRowLabel(String matrixRowLabel) {
		this.matrixRowLabel = matrixRowLabel;
	}
	public Integer getMatrixColIndex() {
		return matrixColIndex;
	}
	public void setMatrixColIndex(Integer matrixColIndex) {
		this.matrixColIndex = matrixColIndex;
	}
	public String getMatrixColLabel() {
		return matrixColLabel;
	}
	public void setMatrixColLabel(String matrixColLabel) {
		this.matrixColLabel = matrixColLabel;
	}
	public Integer getMatrixSelectIndex() {
		return matrixSelectIndex;
	}
	public void setMatrixSelectIndex(Integer matrixSelectIndex) {
		this.matrixSelectIndex = matrixSelectIndex;
	}
	public String getMatrixSelectLabel() {
		return matrixSelectLabel;
	}
	public void setMatrixSelectLabel(String matrixSelectLabel) {
		this.matrixSelectLabel = matrixSelectLabel;
	}
	
	
}
