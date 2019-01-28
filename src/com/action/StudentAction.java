package com.action;

import java.util.ArrayList;


import com.dao.CollegeDao;
import com.dao.EclassDao;
import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbClass;
import com.shiti.DbCollege;
import com.shiti.DbStudent;


public class StudentAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private DbStudent dbstudent;
	private String msg;
	private String re_password;
	private StudentDao studentdao = new StudentDao();
	
	private ArrayList<DbCollege> college = new ArrayList<DbCollege>();
	private DbCollege dbcollege;
	private CollegeDao collegedao = new CollegeDao();
	
	private ArrayList<DbClass> eclass = new ArrayList<DbClass>();
	private EclassDao eclassdao = new EclassDao();
	private DbClass dbeclass;
	
	private String submit;
	
	public String studentLogin() throws Exception{
		/*
		 * 学生登录方法
		 */
		String forward=null;		
		DbStudent dbstudent2 = studentdao.find(dbstudent);
		if(dbstudent2!=null){
			String sql=null;
			int fanye=0;
			forward="success";
			dbcollege = collegedao.findInt(dbstudent2.getCollege());
			dbeclass = eclassdao.findInt(dbstudent2.getEclass());
			ActionContext.getContext().getSession().put("student", dbstudent2);
			ActionContext.getContext().getSession().put("college", dbcollege);
			ActionContext.getContext().getSession().put("eclass", dbeclass);
			ActionContext.getContext().getSession().put("sql", sql);
			ActionContext.getContext().getSession().put("fanye", fanye);
		}else{
			msg="学生不存在或者密码错误，登录失败，请重新登录！";
			forward="failure";
		}
		return forward;		
	}
	public String StudentRegister() throws Exception{
		/*
		 * 学生注册方法
		 */	
		int collegenumber = dbstudent.getCollege();
		String forward=null;	
		if(submit.equals("获取学院")){
			college = collegedao.findAll();
			//msg="获取学院数据成功";
			forward="error";
			}
		
		if(submit.equals("获取班级")){
			if(collegenumber!=0) {
				college = collegedao.findAll();
				eclass = eclassdao.findClass(collegenumber);
				//msg="获取班级数据成功";
				forward="error";
				}else {
					msg="请先选择学院！";
					forward="error";
					}
			}
		
		if(submit.equals("注册")) {
		int flag=0;
		college = collegedao.findAll();
		eclass = eclassdao.findClass(collegenumber);
		if(collegenumber==0) {
			msg="请选择学院！";
			forward="error";
		}else {if(dbstudent.getEclass()==0) {
			msg="请选择班级！";
			forward="error";
		}else {
			if(studentdao.oneFind(dbstudent)!=null){
				
				msg="该学号已经存在，请重新注册！";
				forward="error"; //学号已经被占用的标记值
				}else{
					flag=studentdao.insert(dbstudent);
					if(flag==1){forward="success";
						}else{msg="数据库读写错误！！";forward="error";}
					}
				}
			
			}
			
		}
		return forward;
	}
	
	
	
	public DbStudent getDbstudent() {
		return dbstudent;
	}
	public void setDbstudent(DbStudent dbstudent) {
		this.dbstudent = dbstudent;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public StudentDao getStudentdao() {
		return studentdao;
	}
	public void setStudentdao(StudentDao studentdao) {
		this.studentdao = studentdao;
	}
	public ArrayList<DbCollege> getCollege() {
		return college;
	}
	public void setCollege(ArrayList<DbCollege> college) {
		this.college = college;
	}
	public DbCollege getDbcollege() {
		return dbcollege;
	}
	public void setDbcollege(DbCollege dbcollege) {
		this.dbcollege = dbcollege;
	}
	public CollegeDao getCollegedao() {
		return collegedao;
	}
	public void setCollegedao(CollegeDao collegedao) {
		this.collegedao = collegedao;
	}
	public ArrayList<DbClass> getEclass() {
		return eclass;
	}
	public void setEclass(ArrayList<DbClass> eclass) {
		this.eclass = eclass;
	}
	public EclassDao getEclassdao() {
		return eclassdao;
	}
	public void setEclassdao(EclassDao eclassdao) {
		this.eclassdao = eclassdao;
	}
	public DbClass getDbeclass() {
		return dbeclass;
	}
	public void setDbeclass(DbClass dbeclass) {
		this.dbeclass = dbeclass;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getRe_password() {
		return re_password;
	}
	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}
	
	
	
	
	
}
