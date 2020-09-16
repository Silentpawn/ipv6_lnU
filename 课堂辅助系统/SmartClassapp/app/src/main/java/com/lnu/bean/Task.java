/**
 *
 */
package com.lnu.bean;

/**
 * @author Administrator
 * ��question optionתΪ��task�ṹ���Ƶģ�Ȼ���Ƚ���ʽ��������������
 *
 */

public class Task {

	private Integer id;
	private String title;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private Integer Tid;
	private boolean update;
	private boolean delete;

	/**
	 *
	 */
	public Task() {
		// TODO Auto-generated constructor stub
	}
	/**
	 *
	 */

	public Task(Integer id,String title,String option1,String option2,String option3,String option4,String answer) {
		super();
		this.id=id;
		this.title=title;
		this.option1=option1;
		this.option2=option2;
		this.option3=option3;
		this.option4=option4;
		this.answer=answer;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the option1
	 */
	public String getOption1() {
		return option1;
	}
	/**
	 * @param option1 the option1 to set
	 */
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	/**
	 * @return the option2
	 */
	public String getOption2() {
		return option2;
	}
	/**
	 * @param option2 the option2 to set
	 */
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	/**
	 * @return the option3
	 */
	public String getOption3() {
		return option3;
	}
	/**
	 * @param option3 the option3 to set
	 */
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	/**
	 * @return the option4
	 */
	public String getOption4() {
		return option4;
	}
	/**
	 * @param option4 the option4 to set
	 */
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the tid
	 */
	public Integer getTid() {
		return Tid;
	}
	/**
	 * @param tid the tid to set
	 */
	public void setTid(Integer tid) {
		Tid = tid;
	}
	/**
	 * @return the update
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update the update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
	 * @return the delete
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete the delete to set
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}




}
