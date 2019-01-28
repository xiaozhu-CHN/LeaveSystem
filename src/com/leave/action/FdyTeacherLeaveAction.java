package com.leave.action;

import java.util.ArrayList;

import com.dao.TeacherLeaveDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbClass;
import com.shiti.DbCollege;
import com.shiti.DbTeacher;
import com.shiti.DbTeacherLeave;

public class FdyTeacherLeaveAction extends ActionSupport {
		
		
		private static final long serialVersionUID = 1L;
		private DbTeacher dbteacher=(DbTeacher) ActionContext.getContext().getSession().get("teacher");
		private DbCollege dbcollege= (DbCollege) ActionContext.getContext().getSession().get("college");
		private DbClass dbeclass = new DbClass();
		@SuppressWarnings("unchecked")
		private ArrayList<DbClass>  eclass=(ArrayList<DbClass>) ActionContext.getContext().getSession().get("eclass");
		private TeacherLeaveDao teacherleavedao = new TeacherLeaveDao();
		private ArrayList<DbTeacherLeave> teacherleave = new  ArrayList<DbTeacherLeave>();
		private DbTeacherLeave dbteacherleave = new DbTeacherLeave();
		private int fanye = (int) ActionContext.getContext().getSession().get("fanye");
		private String sql = (String) ActionContext.getContext().getSession().get("sql");
		private String msg;
		private int button;
		
		public String fdyteacherLoginLeave() throws Exception{
			/*
			 * 辅导员登陆进入主页面方法
			 */
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
				teacherleave=teacherleavedao.teacherEclass(eclass);
						if(teacherleave.size()!=0) {
							forward="success";
						}else {
							msg="没有学生提交假条！";
							forward="failure";
						}			
			}else {
				msg="辅导员没有管理的班级！请联系相应人员授权！";
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
				teacherleave=teacherleavedao.fenselect(0,fanye,sql);
				forward="success";
			}
			if(button==1) {
				if(fanye>=5){
					fanye=fanye-5;
					ActionContext.getContext().getSession().put("fanye", fanye);
					teacherleave=teacherleavedao.fenselect(0,fanye,sql);
					forward="success";
					}else {
						msg="翻页错误";
						forward="input";}
			}
			return forward;
		}
		
		public String examine() throws Exception{
			//审核假条action
			String forward=null;
			int number;
			if(dbteacherleave.getFdyState()!=0&&dbteacherleave.getId()!=0) {
				number=teacherleavedao.delete(dbteacherleave.getFdyState(),dbteacherleave.getId(),dbteacherleave.getRemarks());
				if(number>0) {
					forward="success";
					msg="编号"+dbteacherleave.getId()+"假条审核成功！";
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
		
		public String ToBeAudited() throws Exception{
			//待审核假条查询action
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
			teacherleave=teacherleavedao.findClass(1);
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有待审核假条！";
				forward="input";
			}}else {
				msg="辅导员没有管理的班级！请联系相应人员授权！";
				forward="input";
			}
			return forward;
		}
		
		public String ToBeASoldOff() throws Exception{
			//待销假假条查询action
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
			teacherleave=teacherleavedao.findClass(2);
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有待销假的假条！";
				forward="input";
			}}else {
				msg="辅导员没有管理的班级！请联系相应人员授权！";
				forward="input";
			}
			return forward;
		}

		public String Adopt() throws Exception{
			//审核通过假条查询action
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
			teacherleave=teacherleavedao.findClass(3);
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有审核通过的假条！";
				forward="input";
			}}else {
				msg="辅导员没有管理的班级！请联系相应人员授权！";
				forward="input";
			}
			return forward;
		}
		
		public String NoAdopt() throws Exception{
			//审核不通过假条查询action
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
			teacherleave=teacherleavedao.findClass(4);
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有审核不通过的假条！";
				forward="input";
			}}else {
				msg="辅导员没有管理的班级！请联系相应人员授权！";
				forward="input";
			}
			return forward;
		}
		
		public String AlreadySoldOff() throws Exception{
			//已销假假条查询action
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
			teacherleave=teacherleavedao.findClass(5);
			if(teacherleave.size()!=0) {
				forward="success";
			}else {
				msg="没有已销假的假条！";
				forward="input";
			}}else {
				msg="辅导员没有管理的班级！请联系相应人员授权！";
				forward="input";
			}
			return forward;
		}
		
		public String LeaveStudent() throws Exception{
			//按照学号查询假条的action
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
				teacherleave=teacherleavedao.fdyselect(dbteacherleave.getStudent());
				if(teacherleave.size()!=0) {
					forward="success";
				}else {
					msg="学号："+dbteacherleave.getStudent()+"的学生没有假条！";
					forward="input";
				}}else {
					msg="辅导员没有管理的班级！请联系相应人员授权！";
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
			dbteacherleave1=teacherleavedao.oneFind(dbteacherleave);
			if(eclass.size()!=0) {
				if(dbteacherleave1.getId()!=0) {
					teacherleave1.add(dbteacherleave1);
					teacherleave=teacherleave1;
					forward="success";
				}else {
					teacherleave=teacherleave1;
					msg="没有编号："+dbteacherleave.getId()+"的假条！";
					forward="input";
				}}else {
					msg="辅导员没有管理的班级！请联系相应人员授权！";
					forward="input";
				}
				return forward;
		}
		
		public String LeaveName() throws Exception{
			//按照学生姓名查询假条
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
				teacherleave=teacherleavedao.findAll(dbteacherleave.getName());
				if(teacherleave.size()!=0) {
					forward="success";
				}else {
					msg="没有姓名："+dbteacherleave.getName()+"的假条！";
					forward="input";
				}}else {
					msg="辅导员没有管理的班级！请联系相应人员授权！";
					forward="input";
				}
				return forward;
		}
		
		public String EclassId() throws Exception{
			//按照班级查询假条
			String forward=null;
			fanye=0;
			ActionContext.getContext().getSession().put("fanye", fanye);
			if(eclass.size()!=0) {
				teacherleave=teacherleavedao.EclassAll(dbeclass.getEclass());
				if(teacherleave.size()!=0) {
					forward="success";
				}else {
					msg="没有"+dbeclass.getName()+"的假条！";
					forward="input";
				}}else {
					msg="辅导员没有管理的班级！请联系相应人员授权！";
					forward="input";
				}
				return forward;
		}
		
		public String SellOff() throws Exception{
			//辅导员给学生销假
			String forward=null;
			int number;
			if(eclass.size()!=0) {
				number=teacherleavedao.insert(dbteacherleave);
				if(number==1) {
					msg="编号："+dbteacherleave.getId()+"销假成功！";
					forward="success";
				}else {
					msg="编号："+dbteacherleave.getId()+"销假失败！系统错误！";
					forward="input";
				}}else {
					msg="辅导员没有管理的班级！请联系相应人员授权！";
					forward="input";
				}
				return forward;
		}
		
		public String InstructorExit() throws Exception{
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


		public ArrayList<DbClass> getEclass() {
			return eclass;
		}


		public void setEclass(ArrayList<DbClass> eclass) {
			this.eclass = eclass;
		}


		public TeacherLeaveDao getTeacherleavedao() {
			return teacherleavedao;
		}


		public void setTeacherleavedao(TeacherLeaveDao teacherleavedao) {
			this.teacherleavedao = teacherleavedao;
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


		public DbClass getDbeclass() {
			return dbeclass;
		}


		public void setDbeclass(DbClass dbeclass) {
			this.dbeclass = dbeclass;
		}
		
		
		
		
		
}
