package com.shiti;

public class DbTeacherEclass implements java.io.Serializable {

	/**
	 * 老师和班级显示的实体类
	 */
	private static final long serialVersionUID = 1L;
	private int eclass,teacher;
	private String name;
	private String name1;
	public int getEclass() {
		return eclass;
	}
	public void setEclass(int eclass) {
		this.eclass = eclass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public int getTeacher() {
		return teacher;
	}
	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}
	
	
	
}
