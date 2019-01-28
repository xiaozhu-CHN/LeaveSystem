package com.leave.action;

import java.util.ArrayList;

import com.dao.CollegeLeaveDao;
import com.dao.TeacherDao;
import com.dao.TeacherEclassDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbCollege;
import com.shiti.DbTeacher;
import com.shiti.DbTeacherEclass;
import com.shiti.DbTeacherLeave;

import net.sf.json.JSONArray;

public class CollegeLeaveAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private DbTeacher dbteacher=(DbTeacher) ActionContext.getContext().getSession().get("teacher");
	private DbCollege dbcollege= (DbCollege) ActionContext.getContext().getSession().get("college");
	private CollegeLeaveDao colleageleavedao = new CollegeLeaveDao();
	private ArrayList<DbTeacherLeave> teacherleave = new  ArrayList<DbTeacherLeave>();
	private TeacherEclassDao teachereclassDao = new TeacherEclassDao();
	private ArrayList<DbTeacherEclass> teachereclass = new ArrayList<DbTeacherEclass>();
	private DbTeacherEclass dbteachereclass =(DbTeacherEclass) ActionContext.getContext().getSession().get("dbteachereclass");
	private DbTeacherLeave dbteacherleave = new DbTeacherLeave();
	private int fanye = (int) ActionContext.getContext().getSession().get("fanye");
	private String sql = (String) ActionContext.getContext().getSession().get("sql");
	private String msg;
	private int button;
	private String result;
	
	public String CollegeLoginLeave() throws Exception{
		/*
		 * 学院领导登陆进入主页面方法
		 */
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
			teacherleave=colleageleavedao.findAll();
					if(teacherleave.size()!=0) {
						forward="success";
					}else {
						msg="没有学生提交假条！";
						forward="failure";
					}			
		}else {
			msg="没有管理的学院！请联系相应人员授权！";
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
			teacherleave=colleageleavedao.fenselect(0,fanye,sql);
			forward="success";
		}
		if(button==1) {
			if(fanye>=5){
				fanye=fanye-5;
				ActionContext.getContext().getSession().put("fanye", fanye);
				teacherleave=colleageleavedao.fenselect(0,fanye,sql);
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
		if(dbcollege.getCollege()!=0) {
			teacherleave=colleageleavedao.fdyselect(dbteacherleave.getStudent());
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="学号："+dbteacherleave.getStudent()+"的学生没有假条！";
				forward="input";
			}}else {
				msg="没有管理的学院！请联系相应人员授权！";
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
		dbteacherleave1=colleageleavedao.oneFind(dbteacherleave);
		if(dbcollege.getCollege()!=0) {
			if(dbteacherleave1.getId()!=0) {
				teacherleave1.add(dbteacherleave1);
				teacherleave=teacherleave1;
				forward="success";
			}else {
				teacherleave=teacherleave1;
				msg="没有编号："+dbteacherleave.getId()+"的假条！";
				forward="input";
			}}else {
				msg="没有管理的学院！请联系相应人员授权！";
				forward="input";
			}
			return forward;
	}
	
	public String LeaveName() throws Exception{
		//按照学生姓名查询假条
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
			teacherleave=colleageleavedao.findAll(dbteacherleave.getName());
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有姓名："+dbteacherleave.getName()+"的假条！";
				forward="input";
			}}else {
				msg="没有管理的学院！请联系相应人员授权！";
				forward="input";
			}
			return forward;
	}
	
	public String ToBeAudited() throws Exception{
		//待审核假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
		teacherleave=colleageleavedao.findClass(1);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有待审核的假条！";
			forward="input";
		}}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	
	public String ToBeASoldOff() throws Exception{
		//未销假假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
		teacherleave=colleageleavedao.findClass(2);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有未销假的假条！";
			forward="input";
		}}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
		
	}
	
	public String Adopt() throws Exception{
		//审核通过假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
		teacherleave=colleageleavedao.findClass(3);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有审核通过的假条！";
			forward="input";
		}}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	
	public String NoAdopt() throws Exception{
		//审核不通过假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
		teacherleave=colleageleavedao.findClass(4);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有审核不通过的假条！";
			forward="input";
		}}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	
	public String AlreadySoldOff() throws Exception{
		//已销假假条查询action
		String forward=null;
		fanye=0;
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbcollege.getCollege()!=0) {
		teacherleave=colleageleavedao.findClass(5);
		if(teacherleave.size()!=0) {
			forward="success";
		}else {
			msg="没有已销假的假条！";
			forward="input";
		}}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	
	public String examine() throws Exception{
		//审核假条action
		String forward=null;
		int number;
		if(dbteacherleave.getXyldState()!=0&&dbteacherleave.getId()!=0) {
			number=colleageleavedao.delete(dbteacherleave.getXyldState(),dbteacherleave.getId(),dbteacherleave.getRemarks());
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
	
	public String CollegeExit() throws Exception{
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
	
	public String TeacherEclass() throws Exception{
		//班级管理页面
		String forward=null;
		if(dbteacher.getCollege()!=0) {
			teachereclass = teachereclassDao.EclassAll(dbteacher.getCollege());
			if(teachereclass.size()!=0) {
				forward="success";
			}else {
				msg="获取班级数据错误！";
				forward="input";
			}
			}else {
				msg="没有管理的学院！请联系相应人员授权！";
				forward="input";
		}
		return forward;
	}
	
	public String CollegeTeacher() throws Exception{
		//查询班级转化为josn
		String forward=null;
		ActionContext.getContext().getSession().put("dbteachereclass",dbteachereclass);
		ArrayList<DbTeacher> teacher = new ArrayList<DbTeacher>();
		TeacherDao teacherdao = new TeacherDao();
		if(dbteacher.getCollege()!=0) {
			teacher = teacherdao.findClass(dbteacher.getCollege());
			if(teacher.size()!=0) {
				//System.out.println(teacher.get(1).getName());
				JSONArray json = JSONArray.fromObject(teacher); 
				result = json.toString();
				forward="success";
			}else {
				//msg="获取数据错误！";
				forward="error";
			}
		}else {
			//msg="没有管理的学院！请联系相应人员授权！";
			forward="error";
		}
		return forward;
	}
	
	public String TeacherEclassAppointment() throws Exception{
		//将老师任命为班级辅导员
		String forward=null;
		int number=0;
		if(dbteacher.getCollege()!=0) {
		number = colleageleavedao.delete(dbteachereclass.getTeacher());
		if(number!=0) {
			msg="班级"+dbteachereclass.getName()+"任命辅导员："+dbteachereclass.getName1()+"成功！";
			forward="success";
		}else {
			msg="任命失败！";
			forward="input";
		}
		}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	public String AddEclass() throws Exception{
		//学院领导添加班级
		String forward=null;
		if(dbteacher.getCollege()!=0) {
			int number= colleageleavedao.adddata(dbteachereclass.getName(), dbcollege.getCollege());
			if(number!=0) {
				msg="添加班级："+dbteachereclass.getName()+"成功！";
				forward="success";
			}else {
				msg="添加失败！";
				forward="input";
			}
		}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	public String DeleteEclass() throws Exception{
		//删除班级action
		String forward;
		if(dbteacher.getCollege()!=0) {
			int number = colleageleavedao.deletedata(dbteachereclass.getEclass());
			if(number!=0) {
				msg="删除班级："+dbteachereclass.getName()+"成功！";
				forward="success";
			}else {
				msg="删除失败！";
				forward="input";
			}
		}else {
			msg="没有管理的学院！请联系相应人员授权！";
			forward="input";
		}
		return forward;
	}
	
	public DbTeacher getDbteacher() {
		return dbteacher;
	}
	public void setDbteacher(DbTeacher dbteacher) {
		this.dbteacher = dbteacher;
	}
	public DbCollege getDbcollege() {
		return dbcollege;
	}
	public void setDbcollege(DbCollege dbcollege) {
		this.dbcollege = dbcollege;
	}
	public CollegeLeaveDao getColleageleavedao() {
		return colleageleavedao;
	}
	public void setColleageleavedao(CollegeLeaveDao colleageleavedao) {
		this.colleageleavedao = colleageleavedao;
	}
	public ArrayList<DbTeacherLeave> getTeacherleave() {
		return teacherleave;
	}
	public void setTeacherleave(ArrayList<DbTeacherLeave> teacherleave) {
		this.teacherleave = teacherleave;
	}
	public DbTeacherLeave getDbteacherleave() {
		return dbteacherleave;
	}
	public void setDbteacherleave(DbTeacherLeave dbteacherleave) {
		this.dbteacherleave = dbteacherleave;
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

	public ArrayList<DbTeacherEclass> getTeachereclass() {
		return teachereclass;
	}

	public void setTeachereclass(ArrayList<DbTeacherEclass> teachereclass) {
		this.teachereclass = teachereclass;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public DbTeacherEclass getDbteachereclass() {
		return dbteachereclass;
	}

	public void setDbteachereclass(DbTeacherEclass dbteachereclass) {
		this.dbteachereclass = dbteachereclass;
	}
	
}
