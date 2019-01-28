package com.action;

import java.util.ArrayList;

import com.dao.CollegeDao;
import com.dao.EclassDao;
import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbClass;
import com.shiti.DbCollege;
import com.shiti.DbTeacher;
import com.shiti.DbTeacherEclass;

public class TeacherAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private DbTeacher dbteacher;
	private String msg;
	private String re_password;
	private EclassDao eclassdao = new EclassDao();
	private TeacherDao teacherdao = new TeacherDao();
	private ArrayList<DbClass>  eclass = new ArrayList<DbClass>();
	private ArrayList<DbCollege> college = new ArrayList<DbCollege>();
	private DbCollege dbcollege;
	private CollegeDao collegedao = new CollegeDao();
	private String submit;
	
	public String teacherLogin() throws Exception{
		/*
		 * 老师登录方法
		 */
		String forward=null;
		DbTeacher dbteacher2 = teacherdao.find(dbteacher);
		DbTeacherEclass dbteachereclass = new DbTeacherEclass();
		String sql=null;
		int fanye=0;
		if(dbteacher2!=null){
			dbcollege = collegedao.findInt(dbteacher2.getCollege());
			ActionContext.getContext().getSession().put("college", dbcollege);
			ActionContext.getContext().getSession().put("teacher", dbteacher2);
			ActionContext.getContext().getSession().put("sql", sql);
			ActionContext.getContext().getSession().put("fanye", fanye);
			ActionContext.getContext().getSession().put("dbteachereclass", dbteachereclass);
						if(dbteacher2.getJurisdiction()==1) {
							eclass=eclassdao.fdyselect(dbteacher.getTeacher());				
							ActionContext.getContext().getSession().put("eclass",eclass);							
							forward="fdyteacher";
								}else {
									if(dbteacher2.getJurisdiction()==2) {
										forward="xyldteacher";
											}else {
												if(dbteacher2.getJurisdiction()==3) {
													forward="xgcteacher";
														}else {
															forward="input";
														}
									}
								}			
		}else{
			msg="老师不存在或者密码错误，登录失败，请重新登录！";
			forward="failure";
		}
		return forward;		
	}
	
	public String teacherRegister() throws Exception{
		/*
		 * 老师注册方法
		 */	
		int collegenumber = dbteacher.getCollege();
		String forward=null;	
		if(submit.equals("获取学院")){
			college = collegedao.findAll();
			//msg="获取学院数据成功";
			forward="error";
			}
		
		if(submit.equals("注册")) {
		int flag=0;
		college = collegedao.findAll();
		if(collegenumber==0) {
			msg="请选择学院！";
			forward="error";
		}else {
			if(teacherdao.oneFind(dbteacher)!=null){
				
				msg="该工号已经存在，请重新注册！";
				forward="error"; //学号已经被占用的标记值
				}else{
					flag=teacherdao.insert(dbteacher);
					if(flag==1){forward="success";
						}else{msg="数据库读写错误！！";forward="error";}
					}
				}
			
		}
			return forward;
	}

	
	
	
	
	
	
	

	
	
	public DbTeacher getDbteacher() {
		return dbteacher;
	}

	public void setDbteacher(DbTeacher dbteacher) {
		this.dbteacher = dbteacher;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

	public TeacherDao getTeacherdao() {
		return teacherdao;
	}

	public void setTeacherdao(TeacherDao teacherdao) {
		this.teacherdao = teacherdao;
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

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public EclassDao getEclassdao() {
		return eclassdao;
	}

	public void setEclassdao(EclassDao eclassdao) {
		this.eclassdao = eclassdao;
	}

	public ArrayList<DbClass> getEclass() {
		return eclass;
	}

	public void setEclass(ArrayList<DbClass> eclass) {
		this.eclass = eclass;
	}
	
	
}
