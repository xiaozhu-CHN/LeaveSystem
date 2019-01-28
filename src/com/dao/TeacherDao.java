package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.shiti.DbClass;
import com.shiti.DbTeacher;

public class TeacherDao implements IBaseDao<DbTeacher> {

	@Override
	public int insert(DbTeacher dbteacher) {
		// 老师注册方法
		String sql = "insert into db_teacher(teacher,password,name,sex,college,jurisdiction)values(?,?,?,?,?,?)";
		Object params[] = {dbteacher.getTeacher(),dbteacher.getPassword(),dbteacher.getName(),dbteacher.getSex(),dbteacher.getCollege(),dbteacher.getJurisdiction()};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public DbTeacher find(DbTeacher dbteacher) {
		// 老师登陆方法
		Connection con;
		String sql="select * from db_teacher where teacher=? and password=?";
		ResultSet rs =null;
		DbTeacher dbteacher2 = null;
		Object params[]={dbteacher.getTeacher(),dbteacher.getPassword()};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbteacher2 = new DbTeacher();
				dbteacher2.setTeacher(rs.getInt("teacher"));
				//dbteacher2.setPassword(rs.getString("password"));
				dbteacher2.setName(rs.getString("name"));
				dbteacher2.setSex(rs.getString("sex"));
				dbteacher2.setCollege(rs.getInt("college"));
				dbteacher2.setJurisdiction(rs.getInt("jurisdiction"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbteacher2;
	}

	@Override
	public DbTeacher oneFind(DbTeacher dbteacher) {
		// 老师是否注册查询方法
		Connection con;
		String sql="select * from db_teacher where teacher=?";
		ResultSet rs =null;
		DbTeacher dbteacher2 = null;
		Object params[]={dbteacher.getTeacher()};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbteacher2 = new DbTeacher();
				dbteacher2.setTeacher(rs.getInt("teacher"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbteacher2;
	}

	@Override
	public ArrayList<DbTeacher> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacher> findClass(int college) {
		// TODO 根据学院查找老师权限为1的！
		String sql="select * from db_teacher where college=? and jurisdiction=1";
		ArrayList<DbTeacher> teacher = new ArrayList<DbTeacher>();
		Connection con;
		ResultSet rs =null;
		Object params[]={college};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			while(rs.next()){
				DbTeacher dbteacher = new DbTeacher();
				dbteacher.setTeacher(rs.getInt("teacher"));
				//dbteacher2.setPassword(rs.getString("password"));
				dbteacher.setName(rs.getString("name"));
				dbteacher.setSex(rs.getString("sex"));
				dbteacher.setCollege(rs.getInt("college"));
				dbteacher.setJurisdiction(rs.getInt("jurisdiction"));
				teacher.add(dbteacher);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return teacher;
	}

	@Override
	public DbTeacher findInt(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacher> fenselect(int o, int a, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(DbTeacher teacherdb) {
		// TODO 修改老师权限方法
		String sql="UPDATE db_teacher SET jurisdiction=? WHERE teacher=?";
		Object params[] = {teacherdb.getJurisdiction(),teacherdb.getTeacher()};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public int delete(int o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DbTeacher> fdyselect(int college) {
		// TODO 根据学院查找老师权限为1的！
				String sql="select * from db_teacher where college=? and (jurisdiction=1 or jurisdiction=2)";
				ArrayList<DbTeacher> teacher = new ArrayList<DbTeacher>();
				Connection con;
				ResultSet rs =null;
				Object params[]={college};
				try{
					con = JdbcPoolUtils.getConnection();
					rs = JdbcPoolUtils.query(con,sql,params);
					while(rs.next()){
						DbTeacher dbteacher = new DbTeacher();
						dbteacher.setTeacher(rs.getInt("teacher"));
						//dbteacher2.setPassword(rs.getString("password"));
						dbteacher.setName(rs.getString("name"));
						dbteacher.setSex(rs.getString("sex"));
						dbteacher.setCollege(rs.getInt("college"));
						dbteacher.setJurisdiction(rs.getInt("jurisdiction"));
						teacher.add(dbteacher);
					}
					JdbcPoolUtils.close(rs, null, con);
				}catch(SQLException e){e.printStackTrace();}
				return teacher;
	}

	@Override
	public ArrayList<DbTeacher> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int o, int p, String reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DbTeacher> findAll(String o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacher> EclassAll(int o) {
		// TODO Auto-generated method stub
		return null;
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
