package com.shiti;
// Generated 2018-6-29 17:30:31 by Hibernate Tools 5.2.3.Final

/**
 * DbCollege generated by hbm2java
 */
public class DbCollege implements java.io.Serializable {

	private static final long serialVersionUID =1L;
	private int college;
	private String name;

	public DbCollege() {
	}

	public DbCollege(int college) {
		this.college = college;
	}

	public DbCollege(int college, String name) {
		this.college = college;
		this.name = name;
	}

	public int getCollege() {
		return this.college;
	}

	public void setCollege(int college) {
		this.college = college;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
