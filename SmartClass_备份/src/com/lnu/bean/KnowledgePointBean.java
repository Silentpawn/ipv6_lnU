package com.lnu.bean;

/**
 * @Description: 知识点的实体�??
 * @author：hzc
 */
public class KnowledgePointBean extends BaseBean {

	// 每个知识点的情况（真题试卷才有知识点相关信息�?? knowledgePoints
	// 1�??知识点名称：knowledgePointName
	// 2�??知识点ID：knowledgePointId
	// 3�??是否答完：isFinishAnswer�??- 做完 �??- 未做完）
	// 4�??题数：questionNumber
	// 5�??�??��的一次答题的正确率：correctRateOfLastTime
	// 6�??已答的题数：answerNumber
	// 7�??答过的次数：answerTimes

	private String knowledgePointName;// 知识点名�??
	private String knowledgePointId;// 知识点id
	private int isFinishAnswer; // 是否答完
	private int questionNumber; // 题数
	private double correctRateOfLastTime;// �??��的一次答题的正确�??
	private int answerNumber;// 已答的题�??
	private int answerTimes;// 答过的次�??

	public KnowledgePointBean() {
		super();
	}

	public KnowledgePointBean(String knowledgePointName,
			String knowledgePointId, int isFinishAnswer, int questionNumber,
			double correctRateOfLastTime, int answerNumber, int answerTimes) {
		super();
		this.knowledgePointName = knowledgePointName;
		this.knowledgePointId = knowledgePointId;
		this.isFinishAnswer = isFinishAnswer;
		this.questionNumber = questionNumber;
		this.correctRateOfLastTime = correctRateOfLastTime;
		this.answerNumber = answerNumber;
		this.answerTimes = answerTimes;
	}

	public String getKnowledgePointName() {
		return knowledgePointName;
	}

	public void setKnowledgePointName(String knowledgePointName) {
		this.knowledgePointName = knowledgePointName;
	}

	public String getKnowledgePointId() {
		return knowledgePointId;
	}

	public void setKnowledgePointId(String knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}

	public int getIsFinishAnswer() {
		return isFinishAnswer;
	}

	public void setIsFinishAnswer(int isFinishAnswer) {
		this.isFinishAnswer = isFinishAnswer;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public double getCorrectRateOfLastTime() {
		return correctRateOfLastTime;
	}

	public void setCorrectRateOfLastTime(double correctRateOfLastTime) {
		this.correctRateOfLastTime = correctRateOfLastTime;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}

	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}

	public int getAnswerTimes() {
		return answerTimes;
	}

	public void setAnswerTimes(int answerTimes) {
		this.answerTimes = answerTimes;
	}

	@Override
	public String toString() {
		return "KnowledgePointBean [knowledgePointName=" + knowledgePointName
				+ ", knowledgePointId=" + knowledgePointId
				+ ", isFinishAnswer=" + isFinishAnswer + ", questionNumber="
				+ questionNumber + ", correctRateOfLastTime="
				+ correctRateOfLastTime + ", answerNumber=" + answerNumber
				+ ", answerTimes=" + answerTimes + "]";
	}

}
