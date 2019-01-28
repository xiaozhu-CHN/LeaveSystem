package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.opensymphony.xwork2.ActionContext;
import com.shiti.DbClass;
import com.shiti.DbCollege;
import com.shiti.DbTeacherEclass;
import com.shiti.DbTeacherLeave;

public class CollegeLeaveDao implements IBaseDao<DbTeacherLeave> {

	DbCollege dbcollege= (DbCollege) ActionContext.getContext().getSession().get("college");
	@Override
	public int insert(DbTeacherLeave o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DbTeacherLeave find(DbTeacherLeave o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbTeacherLeave oneFind(DbTeacherLeave dbteacherleave) {
		// TODO 学院领导按照假条编号查询假条
		String sql = null;
		sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where db_college.college="+dbcollege.getCollege()+" and db_leave.id="+dbteacherleave.getId();
		ActionContext.getContext().getSession().put("sql",sql);
		String file="编号："+dbteacherleave.getId()+"的假条";
		ActionContext.getContext().getSession().put("file",file);
		sql=sql+" LIMIT 0,5";
		Connection con;
		ResultSet rs =null;
		DbTeacherLeave dbteacherleave1 = new DbTeacherLeave();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				dbteacherleave1.setId(rs.getInt("id"));
				dbteacherleave1.setStudent(rs.getInt("student"));
				dbteacherleave1.setName(rs.getString("db_student.name"));
				dbteacherleave1.setSex(rs.getString("sex"));
				dbteacherleave1.setName1(rs.getString("db_college.name"));
				dbteacherleave1.setName2(rs.getString("db_class.name"));
				dbteacherleave1.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbteacherleave1.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbteacherleave1.setTimeChange(rs.getTimestamp("timeChange"));
				dbteacherleave1.setReason(rs.getString("reason"));
				dbteacherleave1.setFdyState(rs.getInt("fdy_state"));
				dbteacherleave1.setXyldState(rs.getInt("xyld_state"));
				dbteacherleave1.setXgcState(rs.getInt("xgc_state"));
				dbteacherleave1.setXjState(rs.getInt("xj_state"));
				dbteacherleave1.setRemarks(rs.getString("remarks"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbteacherleave1;
	}

	@Override
	public ArrayList<DbTeacherLeave> findAll() {
		// TODO 学院领导获取学院的全部假条
		String file=dbcollege.getName()+"的全部假条";
		String sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where db_college.college="+dbcollege.getCollege()+" order by db_leave.id desc";
		ActionContext.getContext().getSession().put("sql",sql);
		ActionContext.getContext().getSession().put("file",file);
		sql=sql+" LIMIT 0,5";
		Connection con;
		ResultSet rs =null;
		ArrayList<DbTeacherLeave> teacherleave = new ArrayList<DbTeacherLeave>();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				DbTeacherLeave dbteacherleave = new DbTeacherLeave();
				dbteacherleave.setId(rs.getInt("id"));
				dbteacherleave.setStudent(rs.getInt("student"));
				dbteacherleave.setName(rs.getString("db_student.name"));
				dbteacherleave.setSex(rs.getString("sex"));
				dbteacherleave.setName1(rs.getString("db_college.name"));
				dbteacherleave.setName2(rs.getString("db_class.name"));
				dbteacherleave.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbteacherleave.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbteacherleave.setTimeChange(rs.getTimestamp("timeChange"));
				dbteacherleave.setReason(rs.getString("reason"));
				dbteacherleave.setFdyState(rs.getInt("fdy_state"));
				dbteacherleave.setXyldState(rs.getInt("xyld_state"));
				dbteacherleave.setXgcState(rs.getInt("xgc_state"));
				dbteacherleave.setXjState(rs.getInt("xj_state"));
				dbteacherleave.setRemarks(rs.getString("remarks"));
				teacherleave.add(dbteacherleave);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return teacherleave;	
	}

	@Override
	public ArrayList<DbTeacherLeave> findAll(String name) {
		// TODO 学院领导按照学生姓名查询假条
		String sql = null;
		sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where db_college.college="+dbcollege.getCollege()+" and db_student.name LIKE '%"+name+"%'"+" order by db_leave.id desc";
		ActionContext.getContext().getSession().put("sql",sql);
		String file="查询姓名："+name+"的全部假条";
		ActionContext.getContext().getSession().put("file",file);
		sql=sql+" LIMIT 0,5";
		Connection con;
		ResultSet rs =null;
		ArrayList<DbTeacherLeave> teacherleave = new ArrayList<DbTeacherLeave>();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				DbTeacherLeave dbteacherleave = new DbTeacherLeave();
				dbteacherleave.setId(rs.getInt("id"));
				dbteacherleave.setStudent(rs.getInt("student"));
				dbteacherleave.setName(rs.getString("db_student.name"));
				dbteacherleave.setSex(rs.getString("sex"));
				dbteacherleave.setName1(rs.getString("db_college.name"));
				dbteacherleave.setName2(rs.getString("db_class.name"));
				dbteacherleave.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbteacherleave.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbteacherleave.setTimeChange(rs.getTimestamp("timeChange"));
				dbteacherleave.setReason(rs.getString("reason"));
				dbteacherleave.setFdyState(rs.getInt("fdy_state"));
				dbteacherleave.setXyldState(rs.getInt("xyld_state"));
				dbteacherleave.setXgcState(rs.getInt("xgc_state"));
				dbteacherleave.setXjState(rs.getInt("xj_state"));
				dbteacherleave.setRemarks(rs.getString("remarks"));
				teacherleave.add(dbteacherleave);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return teacherleave;
	}

	@Override
	public ArrayList<DbTeacherLeave> EclassAll(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherLeave> findClass(int condition) {
		// TODO 审核条件查询  1 待审核 2未销假 3审核通过 4 审核不通过 5已销假
		String sql=null;
		if(condition==1) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where db_college.college="+dbcollege.getCollege()+" and xyld_state=1"+" order by db_leave.id desc";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="待审核的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==2) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where db_college.college="+dbcollege.getCollege()+" and xj_state=1"+" order by db_leave.id desc";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="待销假的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==3) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where db_college.college="+dbcollege.getCollege()+" and xyld_state=2"+" order by db_leave.id desc";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="审核通过的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==4) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where db_college.college="+dbcollege.getCollege()+" and xyld_state=3"+" order by db_leave.id desc";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="审核不通过的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==5) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where db_college.college="+dbcollege.getCollege()+" and xj_state=2"+" order by db_leave.id desc";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="已销假的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		
		sql=sql+" LIMIT 0,5";
		Connection con;
		ResultSet rs =null;
		ArrayList<DbTeacherLeave> teacherleave = new ArrayList<DbTeacherLeave>();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				DbTeacherLeave dbteacherleave = new DbTeacherLeave();
				dbteacherleave.setId(rs.getInt("id"));
				dbteacherleave.setStudent(rs.getInt("student"));
				dbteacherleave.setName(rs.getString("db_student.name"));
				dbteacherleave.setSex(rs.getString("sex"));
				dbteacherleave.setName1(rs.getString("db_college.name"));
				dbteacherleave.setName2(rs.getString("db_class.name"));
				dbteacherleave.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbteacherleave.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbteacherleave.setTimeChange(rs.getTimestamp("timeChange"));
				dbteacherleave.setReason(rs.getString("reason"));
				dbteacherleave.setFdyState(rs.getInt("fdy_state"));
				dbteacherleave.setXyldState(rs.getInt("xyld_state"));
				dbteacherleave.setXgcState(rs.getInt("xgc_state"));
				dbteacherleave.setXjState(rs.getInt("xj_state"));
				dbteacherleave.setRemarks(rs.getString("remarks"));
				teacherleave.add(dbteacherleave);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return teacherleave;
	}

	@Override
	public DbTeacherLeave findInt(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherLeave> fenselect(int o, int fanye, String sql) {
		// TODO 假条翻页方法
				Connection con;
				ResultSet rs =null;
				sql=sql+" LIMIT ?,5";
				ArrayList<DbTeacherLeave> teacherleave = new ArrayList<DbTeacherLeave>();
				Object params[]={fanye};
				try{
					con = JdbcPoolUtils.getConnection();
					rs = JdbcPoolUtils.query(con,sql,params);
					while(rs.next()){
						DbTeacherLeave dbteacherleave = new DbTeacherLeave();
						dbteacherleave.setId(rs.getInt("id"));
						dbteacherleave.setStudent(rs.getInt("student"));
						dbteacherleave.setName(rs.getString("db_student.name"));
						dbteacherleave.setSex(rs.getString("sex"));
						dbteacherleave.setName1(rs.getString("db_college.name"));
						dbteacherleave.setName2(rs.getString("db_class.name"));
						dbteacherleave.setTimeStarr(rs.getTimestamp("timeStarr"));
						dbteacherleave.setTimeEnd(rs.getTimestamp("timeEnd"));
						dbteacherleave.setTimeChange(rs.getTimestamp("timeChange"));
						dbteacherleave.setReason(rs.getString("reason"));
						dbteacherleave.setFdyState(rs.getInt("fdy_state"));
						dbteacherleave.setXyldState(rs.getInt("xyld_state"));
						dbteacherleave.setXgcState(rs.getInt("xgc_state"));
						dbteacherleave.setXjState(rs.getInt("xj_state"));
						dbteacherleave.setRemarks(rs.getString("remarks"));
						teacherleave.add(dbteacherleave);
					}
					JdbcPoolUtils.close(rs,null,con);
				}catch(SQLException e){e.printStackTrace();}
					
				return teacherleave;
	}

	@Override
	public ArrayList<DbTeacherLeave> fdyselect(int student) {
		// TODO 按照学生学号查询假条
				String sql = null;
				sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
						" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
						" LEFT JOIN db_college on db_student.college=db_college.college" + 
						" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
						" where db_college.college="+dbcollege.getCollege()+" and db_leave.student="+student+" order by db_leave.id desc";
				ActionContext.getContext().getSession().put("sql",sql);
				String file="学号："+student+"的全部假条";
				ActionContext.getContext().getSession().put("file",file);
				sql=sql+" LIMIT 0,5";
				Connection con;
				ResultSet rs =null;
				ArrayList<DbTeacherLeave> teacherleave = new ArrayList<DbTeacherLeave>();
				try{
					con = JdbcPoolUtils.getConnection();
					rs = JdbcPoolUtils.dbsql(con,sql);
					while(rs.next()){
						DbTeacherLeave dbteacherleave = new DbTeacherLeave();
						dbteacherleave.setId(rs.getInt("id"));
						dbteacherleave.setStudent(rs.getInt("student"));
						dbteacherleave.setName(rs.getString("db_student.name"));
						dbteacherleave.setSex(rs.getString("sex"));
						dbteacherleave.setName1(rs.getString("db_college.name"));
						dbteacherleave.setName2(rs.getString("db_class.name"));
						dbteacherleave.setTimeStarr(rs.getTimestamp("timeStarr"));
						dbteacherleave.setTimeEnd(rs.getTimestamp("timeEnd"));
						dbteacherleave.setTimeChange(rs.getTimestamp("timeChange"));
						dbteacherleave.setReason(rs.getString("reason"));
						dbteacherleave.setFdyState(rs.getInt("fdy_state"));
						dbteacherleave.setXyldState(rs.getInt("xyld_state"));
						dbteacherleave.setXgcState(rs.getInt("xgc_state"));
						dbteacherleave.setXjState(rs.getInt("xj_state"));
						dbteacherleave.setRemarks(rs.getString("remarks"));
						teacherleave.add(dbteacherleave);
					}
					JdbcPoolUtils.close(rs, null, con);
				}catch(SQLException e){e.printStackTrace();}
				return teacherleave;
	}

	@Override
	public ArrayList<DbTeacherLeave> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(DbTeacherLeave o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int teacher) {
		// TODO 任命老师为班级辅导员方法
		int number=0;
		String sql = "UPDATE db_class SET teacher=? WHERE eclass=?"; 
		DbTeacherEclass dbteachereclass = (DbTeacherEclass) ActionContext.getContext().getSession().get("dbteachereclass");
		Object params[] ={teacher,dbteachereclass.getEclass()};
		number=JdbcPoolUtils.dbCUD(sql,params);
		return number;
	}

	@Override
	public int delete(int examine, int id,String reason) {
		// TODO 学院领导审核假条方法
		String sql = null; 
		int number=0;
		if(examine==2) {
			sql ="UPDATE db_leave SET xyld_state=2,xgc_state=1 WHERE id=?";
			Object params[] ={id};
			number=JdbcPoolUtils.dbCUD(sql,params);
		}
		if(examine==3) {
			sql ="UPDATE db_leave SET xyld_state=3,remarks=? WHERE id=?";
			Object params[] ={reason,id};
			number=JdbcPoolUtils.dbCUD(sql,params);
		}
		return number;
	}

	@Override
	public int adddata(String name, int college) {
		// TODO 添加班级方法
		String sql = "insert into db_class(college,name)values(?,?)"; 
		Object params[] = {college,name};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public int deletedata(int eclass) {
		// TODO 删除班级方法
		String sql = "DELETE FROM db_class WHERE eclass=?";
		Object params[] ={eclass};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	


}
