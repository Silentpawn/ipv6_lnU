package com.lnu.bean;

import java.util.List;

/**
 * @Description:�����������
 * @author lgx
 */
public class QuestionBean extends BaseBean {


	private String questionId;// ID
	private String description;// �������
	private int questionType;// ��������
	private List<QuestionOptionBean> questionOptions; // ����ѡ��

	public QuestionBean() {
		super();
	}

	public QuestionBean(String questionId, String description,
						int questionType, List<QuestionOptionBean> questionOptions) {
		super();
		this.questionId = questionId;
		this.description = description;
		this.questionType = questionType;
		this.questionOptions = questionOptions;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}


	public List<QuestionOptionBean> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(List<QuestionOptionBean> questionOptions) {
		this.questionOptions = questionOptions;
	}

	@Override
	public String toString() {
		return "QuestionBean [questionId=" + questionId + ", description="
				+ description + ", questionType=" + questionType
				+ ", questionOptions=" + questionOptions + "]";
	}

}
