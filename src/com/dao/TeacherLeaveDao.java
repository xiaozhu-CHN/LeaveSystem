package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.opensymphony.xwork2.ActionContext;
import com.shiti.DbClass;
import com.shiti.DbTeacherLeave;

public class TeacherLeaveDao implements IBaseDao<DbTeacherLeave> {

	@Override
	public int insert(DbTeacherLeave dbteacherleave) {
		// TODO 辅导员销假方法
		String sql = null; 
		int number=0;
		sql ="UPDATE db_leave SET xj_state=2 WHERE id=?";
		Object params[] ={dbteacherleave.getId()};
		number=JdbcPoolUtils.dbCUD(sql,params);
		return number;
	}

	@Override
	public DbTeacherLeave find(DbTeacherLeave o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbTeacherLeave oneFind(DbTeacherLeave dbteacherleave) {
		// TODO 辅导员按照假条编号查询假条
		String sql = null;
		@SuppressWarnings("unchecked")
		ArrayList<DbClass>  eclass=(ArrayList<DbClass>) ActionContext.getContext().getSession().get("eclass");
		sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where (db_student.eclass="+eclass.get(0).getEclass();
		for(int i = 1; i < eclass.size(); i++) {
			sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
		}
		sql=sql+") and db_leave.id="+dbteacherleave.getId();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherLeave> findClass(int condition) {
		// TODO 审核条件查询  1 待审核 2待销假 3审核通过 4 审核不通过 5已销假
		String sql = null;
		@SuppressWarnings("unchecked")
		ArrayList<DbClass>  eclass=(ArrayList<DbClass>) ActionContext.getContext().getSession().get("eclass");
		if(condition==1) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where (db_student.eclass="+eclass.get(0).getEclass();
			for(int i = 1; i < eclass.size(); i++) {
				sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
			}
			sql=sql+") and fdy_state=1";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="待审核的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==2) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where (db_student.eclass="+eclass.get(0).getEclass();
			for(int i = 1; i < eclass.size(); i++) {
				sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
			}
			sql=sql+") and xj_state=1";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="待销假的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==3) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where (db_student.eclass="+eclass.get(0).getEclass();
			for(int i = 1; i < eclass.size(); i++) {
				sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
			}
			sql=sql+") and fdy_state=2";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="审核通过的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==4) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where (db_student.eclass="+eclass.get(0).getEclass();
			for(int i = 1; i < eclass.size(); i++) {
				sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
			}
			sql=sql+") and fdy_state=3";
			ActionContext.getContext().getSession().put("sql",sql);
			String file="审核不通过的全部假条";
			ActionContext.getContext().getSession().put("file",file);
		}
		
		if(condition==5) {
			sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
					" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
					" LEFT JOIN db_college on db_student.college=db_college.college" + 
					" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
					" where (db_student.eclass="+eclass.get(0).getEclass();
			for(int i = 1; i < eclass.size(); i++) {
				sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
			}
			sql=sql+") and xj_state=2";
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
		@SuppressWarnings("unchecked")
		ArrayList<DbClass>  eclass=(ArrayList<DbClass>) ActionContext.getContext().getSession().get("eclass");
		sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where (db_student.eclass="+eclass.get(0).getEclass();
		for(int i = 1; i < eclass.size(); i++) {
			sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
		}
		sql=sql+") and db_leave.student="+student;
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
	public ArrayList<DbTeacherLeave> teacherEclass(ArrayList<DbClass> eclass) {
		// TODO 老师主页查询所负责班级所有假条方法
		
		String file=eclass.get(0).getName();
		String sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where db_student.eclass="+eclass.get(0).getEclass();
		for(int i = 1; i < eclass.size(); i++) {
			sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
			file=file+"、"+eclass.get(i).getName();
		}
		ActionContext.getContext().getSession().put("sql",sql);
		file=file+"的全部假条";
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
	public int modify(DbTeacherLeave o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int examine, int id,String reason) {
		// TODO 辅导员审核假条方法
		String sql = null; 
		int number=0;
		if(examine==2) {
			sql ="UPDATE db_leave SET fdy_state=2,xyld_state=1 WHERE id=?";
			Object params[] ={id};
			number=JdbcPoolUtils.dbCUD(sql,params);
		}
		if(examine==3) {
			sql ="UPDATE db_leave SET fdy_state=3,remarks=? WHERE id=?";
			Object params[] ={reason,id};
			number=JdbcPoolUtils.dbCUD(sql,params);
		}
		
		return number;
	}

	@Override
	public ArrayList<DbTeacherLeave> findAll(String name) {
		// TODO 辅导员按照学生姓名查询假条
		String sql = null;
		@SuppressWarnings("unchecked")
		ArrayList<DbClass>  eclass=(ArrayList<DbClass>) ActionContext.getContext().getSession().get("eclass");
		sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where (db_student.eclass="+eclass.get(0).getEclass();
		for(int i = 1; i < eclass.size(); i++) {
			sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
		}
		sql=sql+") and db_student.name LIKE '%"+name+"%'";
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
	public ArrayList<DbTeacherLeave> EclassAll(int EclassId) {
		// TODO 根据班级查询假条
		String sql = null;
		String file=null;
		@SuppressWarnings("unchecked")
		ArrayList<DbClass>  eclass=(ArrayList<DbClass>) ActionContext.getContext().getSession().get("eclass");
		sql="select db_leave.id,db_leave.student,db_student.`name`,db_student.sex,db_college.`name`,db_class.`name`,db_leave.timeStarr,db_leave.timeEnd,db_leave.timeChange,db_leave.reason,db_leave.fdy_state,db_leave.xyld_state,db_leave.xgc_state,db_leave.xj_state,db_leave.remarks" + 
				" from db_leave LEFT JOIN db_student on db_leave.student=db_student.student" + 
				" LEFT JOIN db_college on db_student.college=db_college.college" + 
				" LEFT JOIN db_class on db_student.eclass=db_class.eclass" + 
				" where (db_student.eclass="+eclass.get(0).getEclass();
		for(int i = 1; i < eclass.size(); i++) {
			sql=sql+" or db_student.eclass="+eclass.get(i).getEclass();
		}
		sql=sql+") and db_class.eclass="+EclassId;
		ActionContext.getContext().getSession().put("sql",sql);
		for(int i = 0; i < eclass.size(); i++) {
			if(eclass.get(i).getEclass()==EclassId) {
				file=eclass.get(i).getName();
			}
		}
		file=file+"的全部假条";
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
	public int adddata(String a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletedata(int a) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
