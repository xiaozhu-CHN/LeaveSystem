package com.shiti;

import java.util.Date;

public class DbTeacherLeave implements java.io.Serializable {

	private static final long serialVersionUID =1L;
	private int id;
	private int student;
	private String name;
	private String sex;
	private String name1;
	private String name2;
	private Date timeStarr;
	private Date timeEnd;
	private Date timeChange;
	private String reason;
	private Integer fdyState;
	private Integer xyldState;
	private Integer xgcState;
	private Integer xjState;
	private String remarks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public Date getTimeStarr() {
		return timeStarr;
	}
	public void setTimeStarr(Date timeStarr) {
		this.timeStarr = timeStarr;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	public Date getTimeChange() {
		return timeChange;
	}
	public void setTimeChange(Date timeChange) {
		this.timeChange = timeChange;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getFdyState() {
		return fdyState;
	}
	public void setFdyState(Integer fdyState) {
		this.fdyState = fdyState;
	}
	public Integer getXyldState() {
		return xyldState;
	}
	public void setXyldState(Integer xyldState) {
		this.xyldState = xyldState;
	}
	public Integer getXgcState() {
		return xgcState;
	}
	public void setXgcState(Integer xgcState) {
		this.xgcState = xgcState;
	}
	public Integer getXjState() {
		return xjState;
	}
	public void setXjState(Integer xjState) {
		this.xjState = xjState;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
