package com.leave.action;

import java.sql.Timestamp;
import java.util.ArrayList;



import com.dao.CollegeDao;
import com.dao.EclassDao;
import com.dao.LeaveDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbClass;
import com.shiti.DbCollege;
import com.shiti.DbLeave;
import com.shiti.DbStudent;


public class StudentLeaveAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private DbStudent dbstudent=(DbStudent) ActionContext.getContext().getSession().get("student");
	private ArrayList<DbLeave> leave = new ArrayList<DbLeave>();
	private DbLeave dbleave;
	private LeaveDao leavedao = new LeaveDao();
	private CollegeDao collegedao = new CollegeDao();
	private EclassDao eclassdao = new EclassDao();
	private DbCollege dbcollege=(DbCollege) ActionContext.getContext().getSession().get("college");
	private DbClass dbeclass=(DbClass) ActionContext.getContext().getSession().get("eclass");
	private int button;
	private String msg;
	private int fanye = (int) ActionContext.getContext().getSession().get("fanye");
	private String sql = (String) ActionContext.getContext().getSession().get("sql");
	
	
	public String stdentLoginLeave() throws Exception{
		/*
		 * 学生登陆进入主页面方法
		 */
		
		String forward=null;
		fanye=0;
		sql="select * from db_leave where student=? LIMIT ?,5";
		ActionContext.getContext().getSession().put("sql", sql);
		leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(dbstudent.getStudent()!=0) {
			forward="success";
		}else {
			forward="failure";
		}
		return forward;	
	}

	public String limitleave() throws Exception{
		/*
		 * 翻页查询总请假数
		 */
		
		String forward=null;
		if(button==2) {
			//fanye=(int) ActionContext.getContext().getSession().get("fanye");
			fanye=fanye+5;
			ActionContext.getContext().getSession().put("fanye", fanye);
			leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
			forward="success";
		}
		if(button==1) {
			//fanye=(int) ActionContext.getContext().getSession().get("fanye");
			if(fanye>=5){
			fanye=fanye-5;
			ActionContext.getContext().getSession().put("fanye", fanye);
			leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
			forward="success";
			}else {
				msg="翻页错误";
				forward="failure";}
		}
		return forward;
		
	}
	
	public String applyLeave() throws Exception{
		/*
		 * 提交假条
		 */
		String forward=null;
		int flag=0;
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Timestamp time2 = new Timestamp(dbleave.getTimeStarr().getTime());
		Timestamp time3 = new Timestamp(dbleave.getTimeEnd().getTime());
		dbleave.setTimeChange(time);
		dbleave.setTimeStarr(time2);
		dbleave.setTimeEnd(time3);
		flag=leavedao.insert(dbleave);
		if(flag==1) {
			dbleave=null;
			forward="success";
		}else {
			forward="failure";
			msg="对不起，提交申请失败！";
		}
		return forward;
	}
	
	public String shenqing() throws Exception{
		/*
		 * 申请假条
		 */
		String forward=null;
		forward="success";
		return forward;
	}
	
	public String audited() throws Exception{
		/*
		 * 待审核
		 */
		fanye=0;
		sql="select * from db_leave where student=? AND (fdy_state=1 OR xyld_state =1 OR xgc_state=1) LIMIT ?,5";
		ActionContext.getContext().getSession().put("sql", sql);
		leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(leave.size()==0) {
			msg="没有待审核的假条喔";
		}
		String forward = "success";
		
		return forward;	
	}
	
	public String failure() throws Exception{
		/*
		 * 审核失败
		 */
		fanye=0;
		sql="select * from db_leave where student=? AND (fdy_state=3 OR xyld_state =3 OR xgc_state=3) LIMIT ?,5";
		ActionContext.getContext().getSession().put("sql", sql);
		leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(leave.size()==0) {
			msg="没有审核不通过的假条喔";
		}
		String forward = "success";		
		return forward;			
	}

	public String Auditsuccess() throws Exception{
		/*
		 * 审核成功
		 */
		fanye=0;
		sql="select * from db_leave where student=? AND xgc_state=2 LIMIT ?,5";
		ActionContext.getContext().getSession().put("sql", sql);
		leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(leave.size()==0) {
			msg="没有审核成功的假条喔";
		}
		String forward = "success";		
		return forward;	
	}
	
	public String Tobesoldoff() throws Exception{
		/*
		 * 待销假
		 */
		fanye=0;
		sql="select * from db_leave where student=? AND xj_state=1 LIMIT ?,5";
		ActionContext.getContext().getSession().put("sql", sql);
		leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(leave.size()==0) {
			msg="没有待销假的假条喔";
		}
		String forward = "success";		
		return forward;
	}
	
	public String Alreadysoldoff() throws Exception{
		/*
		 * 已销假
		 */
		fanye=0;
		sql="select * from db_leave where student=? AND xj_state=2 LIMIT ?,5";
		ActionContext.getContext().getSession().put("sql", sql);
		leave=leavedao.fenselect(dbstudent.getStudent(),fanye,sql);
		ActionContext.getContext().getSession().put("fanye", fanye);
		if(leave.size()==0) {
			msg="没有已销假的假条喔";
		}
		String forward = "success";		
		return forward;
	}
	
	public String Leavemodify() throws Exception{
		/*
		 * 修改假条
		 */
		String forward=null;
		int id = dbleave.getId();
		dbleave=null;
		dbleave = leavedao.findInt(id);
		if(dbleave!=null) {
			forward = "success";
		}else {
			msg="数据库访问错误";
			forward = "failure";
		}
		return forward;
		
	}
	
	public String Amendafake() throws Exception{
		/*
		 * 提交假条修改
		 */
		int flag=0;
		String forward=null;
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Timestamp time2 = new Timestamp(dbleave.getTimeStarr().getTime());
		Timestamp time3 = new Timestamp(dbleave.getTimeEnd().getTime());
		dbleave.setTimeChange(time);
		dbleave.setTimeStarr(time2);
		dbleave.setTimeEnd(time3);
		flag=leavedao.modify(dbleave);
		if(flag==1) {
			forward = "success";
		}else {
			msg="数据库访问错误";
			forward = "failure";
		}
		return forward;
	}
	
	public String StudentExit() throws Exception{
		//退出系统
		String forward=null;
		ActionContext.getContext().getSession().remove("student");
		ActionContext.getContext().getSession().clear();;
		if(ActionContext.getContext().getSession().get("student")==null) {
			msg="退出成功！";
			forward = "success";
		}else {
			msg="服务器错误，退出系统失败！";
			forward = "failure";
		}
		return forward;
	}
	
	public String studentdelete() throws Exception{
		/*
		 * 删除假条
		 */
		String forward=null;
		int flag=0;
		int id = dbleave.getId();
		flag=leavedao.delete(id);
		if(flag==1) {
			msg="删除成功";
			forward = "success";
		}else {
			msg="数据库访问错误";
			forward = "failure";
		}
		return forward;
	}
	

	public DbStudent getDbstudent() {
		return dbstudent;
	}

	public void setDbstudent(DbStudent dbstudent) {
		this.dbstudent = dbstudent;
	}

	public ArrayList<DbLeave> getLeave() {
		return leave;
	}

	public void setLeave(ArrayList<DbLeave> leave) {
		this.leave = leave;
	}

	public DbLeave getDbleave() {
		return dbleave;
	}

	public void setDbleave(DbLeave dbleave) {
		this.dbleave = dbleave;
	}

	public LeaveDao getLeavedao() {
		return leavedao;
	}

	public void setLeavedao(LeaveDao leavedao) {
		this.leavedao = leavedao;
	}

	public CollegeDao getCollegedao() {
		return collegedao;
	}

	public void setCollegedao(CollegeDao collegedao) {
		this.collegedao = collegedao;
	}

	public DbCollege getDbcollege() {
		return dbcollege;
	}

	public void setDbcollege(DbCollege dbcollege) {
		this.dbcollege = dbcollege;
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

	public int getFanye() {
		return fanye;
	}

	public void setFanye(int fanye) {
		this.fanye = fanye;
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

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	

}
