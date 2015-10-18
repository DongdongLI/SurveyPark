package surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.dao.impl.AnswerDaoImpl;
import surveypark.dao.impl.PageDaoImpl;
import surveypark.dao.impl.QuestionDaoImpl;
import surveypark.dao.impl.SurveyDaoImpl;
import surveypark.model.Question;
import surveypark.model.Survey;
import surveypark.model.statistics.OptionStatisticsModel;
import surveypark.model.statistics.QuestionStatisticsModel;
import surveypark.service.StatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{

	@Resource(name="surveyDao")
	private SurveyDaoImpl surveyDao; 
	
	@Resource(name="pageDao")
	private PageDaoImpl pageDao;
	
	@Resource(name="questionDao")
	private QuestionDaoImpl questionDao;
	@Resource(name="answerDao")
	private AnswerDaoImpl answerDao;
	
	@Override
	public QuestionStatisticsModel statistics(Integer qid) {
		Question question = questionDao.getEntity(qid);
		QuestionStatisticsModel qsm=new QuestionStatisticsModel();
		qsm.setQuestion(question);
		
		// how many people answer this question
		String hql="select count(*) from Answer a where a.questionId = ?";
		Long qcount=(Long) answerDao.uniqueResult(hql, qid);
		qsm.setCount(qcount.intValue());
		
		String ohql="select count(*) from Answer a where a.questionId= ? and concat(',',a.answerIds,',') like ?";
		
		// options?
		int qt=question.getQuestionType();
		switch (qt) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			String[] arr=question.getOptionArr();
			OptionStatisticsModel osm=null;
			for(int i=0;i<arr.length;i++){
				osm = new OptionStatisticsModel();
				osm.setOptionLabel(arr[i]);
				osm.setOptionIndex(i);
				
				Long ocount=(Long)answerDao.uniqueResult(ohql, qid,"%,"+i+",%");
				osm.setCount(ocount.intValue());
				qsm.getOsm().add(osm);
			}
			// containing other
			if(question.isOther()){
				osm = new OptionStatisticsModel();
				osm.setOptionLabel("other");
				
				Long ocount=(Long)answerDao.uniqueResult(ohql, qid,"%,"+"other"+",%");
				osm.setCount(ocount.intValue());
				qsm.getOsm().add(osm);
			}
			break;
		// matrix
		case 6:
		case 7:
		case 8:
			String[] rowsTitle=question.getMatrixRowTitleArr();
			String[] colsTitle=question.getMatrixColTitleArr();
			String[] options=question.getMatrixSelectOptionArr();
			
			for(int i=0;i<rowsTitle.length;i++){
				for(int j=0;j<colsTitle.length;j++){
					// radio or checkbox
					if(qt!=8){
						osm=new OptionStatisticsModel();
						
						osm.setMatrixRowIndex(i);
						osm.setMatrixRowLabel(rowsTitle[i]);
						
						osm.setMatrixColIndex(j);
						osm.setMatrixRowLabel(colsTitle[j]);
						
						Long count=(Long)answerDao.uniqueResult(ohql, qid,"%,"+i+"_"+j+",%");
						osm.setCount(count.intValue());
						qsm.getOsm().add(osm);
					}else{// multiple choice
						for(int k=0;k<options.length;k++){
							osm=new OptionStatisticsModel();
							
							osm.setMatrixRowIndex(i);
							osm.setMatrixRowLabel(rowsTitle[i]);
							
							osm.setMatrixColIndex(j);
							osm.setMatrixRowLabel(colsTitle[j]);
							
							Long count=(Long)answerDao.uniqueResult(ohql, qid,"%,"+i+"_"+j+"_"+k+",%");
							osm.setCount(count.intValue());
							qsm.getOsm().add(osm);
						}
					}
				}
			}
		default:
			break;
		}
		return qsm;
	}
	
}
