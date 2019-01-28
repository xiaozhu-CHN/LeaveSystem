package com.leave.action;

import java.util.ArrayList;

import com.dao.CollegeDao;
import com.dao.SchoolLeaveDao;
import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbCollege;
import com.shiti.DbTeacher;
import com.shiti.DbTeacherLeave;

import net.sf.json.JSONArray;

public class SchoolLeaveAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private DbTeacher dbteacher=(DbTeacher) ActionContext.getContext().getSession().get("teacher");
	private DbCollege dbcollege= (DbCollege) ActionContext.getContext().getSession().get("college");
	private SchoolLeaveDao schoolleavedao = new SchoolLeaveDao();
	private ArrayList<DbTeacherLeave> teacherleave = new  ArrayList<DbTeacherLeave>();
	private ArrayList<DbCollege> college = new ArrayList<DbCollege>();
	private DbCollege collegedb = new DbCollege();
	private DbTeacher teacherdb = new DbTeacher();
	private DbTeacherLeave dbteacherleave = new DbTeacherLeave();
	private int fanye = (int) ActionContext.getContext().getSession().get("fanye");
	private String sql = (String) ActionContext.getContext().getSession().get("sql");
	private String msg,result;
	private int button;
	
	public String SchoolLoginLeave() throws Exception{
		/*
		 * 学工处老师登陆进入主页面方法
		 */
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave = schoolleavedao.findAll();
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有学生提交假条！";
			forward="failure";
		}	
		return forward;
	}
	public String limitleave() throws Exception{
		//翻页action
		String forward=null;
		if(button==2) {
			fanye=fanye+5;
			ActionContext.getContext().getSession().put("fanye", fanye);
			teacherleave=schoolleavedao.fenselect(0,fanye,sql);
			forward="success";
		}
		if(button==1) {
			if(fanye>=5){
				fanye=fanye-5;
				ActionContext.getContext().getSession().put("fanye", fanye);
				teacherleave=schoolleavedao.fenselect(0,fanye,sql);
				forward="success";
				}else {
					msg="翻页错误";
					forward="input";}
		}
		return forward;
	}
	public String LeaveStudent() throws Exception{
		//按照学号查询假条的action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave=schoolleavedao.fdyselect(dbteacherleave.getStudent());
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="学号："+dbteacherleave.getStudent()+"的学生没有假条！";
				forward="input";
			}
			return forward;
	}
	
	public String LeaveId() throws Exception{
		//按照假条编号ID查询假条
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		ArrayList<DbTeacherLeave> teacherleave1 = new  ArrayList<DbTeacherLeave>();
		DbTeacherLeave dbteacherleave1 = new DbTeacherLeave();
		dbteacherleave1=schoolleavedao.oneFind(dbteacherleave);
			if(dbteacherleave1.getId()!=0) {
				teacherleave1.add(dbteacherleave1);
				teacherleave=teacherleave1;
				forward="success";
			}else {
				teacherleave=teacherleave1;
				msg="没有编号："+dbteacherleave.getId()+"的假条！";
				forward="input";
			}
			return forward;
	}
	
	public String LeaveName() throws Exception{
		//按照学生姓名查询假条
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
			teacherleave=schoolleavedao.findAll(dbteacherleave.getName());
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有姓名："+dbteacherleave.getName()+"的假条！";
				forward="input";
			}
			return forward;
	}
	
	public String ToBeAudited() throws Exception{
		//待审核假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave=schoolleavedao.findClass(1);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有待审核的假条！";
			forward="input";
		}
		return forward;
	}
	
	public String ToBeASoldOff() throws Exception{
		//未销假假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave=schoolleavedao.findClass(2);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有未销假的假条！";
			forward="input";
		}
		return forward;
		
	}
	
	public String Adopt() throws Exception{
		//审核通过假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave=schoolleavedao.findClass(3);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有审核通过的假条！";
			forward="input";
		}
		return forward;
	}
	
	public String NoAdopt() throws Exception{
		//审核不通过假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave=schoolleavedao.findClass(4);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有审核不通过的假条！";
			forward="input";
		}
		return forward;
	}
	
	public String AlreadySoldOff() throws Exception{
		//已销假假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		teacherleave=schoolleavedao.findClass(5);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有已销假的假条！";
			forward="input";
		}
		return forward;
	}
	
	public String examine() throws Exception{
		//审核假条action
		String forward=null;
		int number;
		if(dbteacherleave.getXgcState()!=0&&dbteacherleave.getId()!=0) {
			number=schoolleavedao.delete(dbteacherleave.getXgcState(),dbteacherleave.getId(),dbteacherleave.getRemarks());
			if(number>0) {
				forward="success";
				msg="编号"+dbteacherleave.getId()+"假条审核完成！";
			}else {
				forward="input";
				msg="审核假条失败！";
			}
		}else {
			forward="input";
			msg="未知错误！";
		}
		return forward;
	}
	
	public String SchoolExit() throws Exception{
		//退出系统
		String forward=null;
		ActionContext.getContext().getSession().remove("teacher");
		ActionContext.getContext().getSession().clear();;
		if(ActionContext.getContext().getSession().get("teacher")==null) {
			msg="退出成功！";
			forward = "success";
		}else {
			msg="服务器错误，退出系统失败！";
			forward = "input";
		}
		return forward;
	}
	
	public String CollegeAdmin() throws Exception{
		//学院管理
		String forward=null;
		CollegeDao collegedao = new CollegeDao();
		college = collegedao.findClass(dbteacher.getCollege());
		if(college.size()!=0) {
			forward = "success";
		}else {
			msg="服务器错误！";
			forward = "input";
		}
		return forward;
	}
	
	public String DeleteCollege() throws Exception{
		//删除学院
		CollegeDao collegedao = new CollegeDao();
		String forward=null;
		int number = collegedao.delete(collegedb.getCollege());
		if(number!=0) {
			msg="删除"+collegedb.getName()+"成功！";
			forward = "success";
		}else {
			msg="删除失败！";
			forward = "input";
		}
		return forward;
	}
	
	public String AddCollege() throws Exception{
		//添加学院
		CollegeDao collegedao = new CollegeDao();
		String forward=null;
		int number = collegedao.insert(collegedb);
		if(number!=0) {
			msg="添加"+collegedb.getName()+"成功！";
			forward = "success";
		}else {
			msg="添加失败！";
			forward = "input";
		}
		return forward;
	}
	
	public String QueryTeacher() throws Exception{
		//查询老师的方法(权限为1和2）
		String forward=null;
		TeacherDao teacherdao = new TeacherDao();
		ArrayList<DbTeacher> teacher = new ArrayList<DbTeacher>();
		teacher = teacherdao.fdyselect(collegedb.getCollege());
		if(teacher.size()!=0) {
			JSONArray json = JSONArray.fromObject(teacher); 
			result = json.toString();
			forward="success";
		}else {
			forward="error";
		}
		return forward;
	}
	
	public String ModifyTeacherJN() throws Exception{
		//修改老师权限1和2
		String forward=null;
		if(teacherdb.getJurisdiction()==1) {
			result="取消"+teacherdb.getName()+"的权限";
		}
		if(teacherdb.getJurisdiction()==2) {
			result="任命"+teacherdb.getName()+"权限为学院领导";
		}
		TeacherDao teacherdao = new TeacherDao();
		int number = teacherdao.modify(teacherdb);
		if(number!=0) {
			result=result+"成功！";
			forward="success";
		}else {
			result=result+"失败！";
			forward="input";
		}
		return forward;
	}
	
	public DbCollege getDbcollege() {
		return dbcollege;
	}
	public void setDbcollege(DbCollege dbcollege) {
		this.dbcollege = dbcollege;
	}
	public ArrayList<DbTeacherLeave> getTeacherleave() {
		return teacherleave;
	}
	public void setTeacherleave(ArrayList<DbTeacherLeave> teacherleave) {
		this.teacherleave = teacherleave;
	}
	public int getFanye() {
		return fanye;
	}
	public void setFanye(int fanye) {
		this.fanye = fanye;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getButton() {
		return button;
	}
	public void setButton(int button) {
		this.button = button;
	}
	public DbTeacher getDbteacher() {
		return dbteacher;
	}
	public void setDbteacher(DbTeacher dbteacher) {
		this.dbteacher = dbteacher;
	}
	public DbTeacherLeave getDbteacherleave() {
		return dbteacherleave;
	}
	public void setDbteacherleave(DbTeacherLeave dbteacherleave) {
		this.dbteacherleave = dbteacherleave;
	}
	public ArrayList<DbCollege> getCollege() {
		return college;
	}
	public void setCollege(ArrayList<DbCollege> college) {
		this.college = college;
	}
	public DbCollege getCollegedb() {
		return collegedb;
	}
	public void setCollegedb(DbCollege collegedb) {
		this.collegedb = collegedb;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public DbTeacher getTeacherdb() {
		return teacherdb;
	}
	public void setTeacherdb(DbTeacher teacherdb) {
		this.teacherdb = teacherdb;
	}
	
	
	
}
