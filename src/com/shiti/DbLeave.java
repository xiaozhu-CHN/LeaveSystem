package com.shiti;
// Generated 2018-6-29 17:30:31 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * DbLeave generated by hbm2java
 */
public class DbLeave implements java.io.Serializable {

	private static final long serialVersionUID =1L;
	private int id;
	private int student;
	private Date timeStarr;
	private Date timeEnd;
	private Date timeChange;
	private String reason;
	private Integer fdyState;
	private Integer xyldState;
	private Integer xgcState;
	private Integer xjState;
	private String remarks;

	public DbLeave() {
	}

	public DbLeave(int id, int student, Date timeStarr, Date timeEnd, String reason) {
		this.id = id;
		this.student = student;
		this.timeStarr = timeStarr;
		this.timeEnd = timeEnd;
		this.reason = reason;
	}

	public DbLeave(int id, int student, Date timeStarr, Date timeEnd, Date timeChange, String reason, Integer fdyState,
			Integer xyldState, Integer xgcState, Integer xjState, String remarks) {
		this.id = id;
		this.student = student;
		this.timeStarr = timeStarr;
		this.timeEnd = timeEnd;
		this.timeChange = timeChange;
		this.reason = reason;
		this.fdyState = fdyState;
		this.xyldState = xyldState;
		this.xgcState = xgcState;
		this.xjState = xjState;
		this.remarks = remarks;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent() {
		return this.student;
	}

	public void setStudent(int student) {
		this.student = student;
	}

	public Date getTimeStarr() {
		return this.timeStarr;
	}

	public void setTimeStarr(Date timeStarr) {
		this.timeStarr = timeStarr;
	}

	public Date getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Date getTimeChange() {
		return this.timeChange;
	}

	public void setTimeChange(Date timeChange) {
		this.timeChange = timeChange;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getFdyState() {
		return this.fdyState;
	}

	public void setFdyState(Integer fdyState) {
		this.fdyState = fdyState;
	}

	public Integer getXyldState() {
		return this.xyldState;
	}

	public void setXyldState(Integer xyldState) {
		this.xyldState = xyldState;
	}

	public Integer getXgcState() {
		return this.xgcState;
	}

	public void setXgcState(Integer xgcState) {
		this.xgcState = xgcState;
	}

	public Integer getXjState() {
		return this.xjState;
	}

	public void setXjState(Integer xjState) {
		this.xjState = xjState;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
