/**

 *
 */
package com.lnu.bean;

import java.util.Arrays;
import java.util.Date;

import java.util.List;


/**
 * @author Administrator
 *
 */

public class CourseBean {

	private Integer id;
	private String name;
	//	2019-9-0 09:45
	private Date starttime;
	private Date endtime;
	private String location;
	private Integer studentNumber;
	private Double credit;
	private List<String> sno;//���и��Ĺ���ע�������ݿ�
	private String managerName;
	private String info;
	private String tno;//���й�����ע����ϸ���������
	private Integer countStudents;
	private String status;
	private boolean update;
	private boolean delete;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}
	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	/**
	 * @return the endtime
	 */
	public Date getEndtime() {
		return endtime;
	}
	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the studentNumber
	 */
	public Integer getStudentNumber() {
		return studentNumber;
	}
	/**
	 * @param studentNumber the studentNumber to set
	 */
	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	/**
	 * @return the credit
	 */
	public Double getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}/*
	 *//**
	 * @return the teacher
	 */
	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the tno
	 */
	public String getTno() {
		return tno;
	}
	/**
	 * @param tno the tno to set
	 */
	public void setTno(String tno) {
		this.tno = tno;
	}
	/**
	 * @return the countStudents
	 */
	public Integer getCountStudents() {
		return countStudents;
	}
	/**
	 * @param countStudents the countStudents to set
	 */
	public void setCountStudents(Integer countStudents) {
		this.countStudents = countStudents;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the students
	 */
	public List<String> getSno() {
		return sno;
	}
	/**
	 * @param students the students to set
	 */
	public void setSno(String sno) {
		this.sno =Arrays.asList(sno.split(","));
	}

}
