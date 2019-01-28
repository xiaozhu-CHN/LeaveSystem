package com.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.shiti.DbClass;
import com.shiti.DbStudent;

public class StudentDao implements IBaseDao<DbStudent> {
	public int insert(DbStudent dbstudent){
		String sql = "insert into db_student(student,password,name,sex,college,eclass)values(?,?,?,?,?,?)";
		Object params[] ={dbstudent.getStudent(),dbstudent.getPassword(),dbstudent.getName(),dbstudent.getSex(),dbstudent.getCollege(),dbstudent.getEclass()};
		return JdbcPoolUtils.dbCUD(sql,params);
	}
	public DbStudent find(DbStudent dbstudent){
		Connection con;
		String sql="select * from db_student where student=? and password=?";
		ResultSet rs =null;
		DbStudent dbstudent2 = null;
		Object params[]={dbstudent.getStudent(),dbstudent.getPassword()};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbstudent2 = new DbStudent();
				dbstudent2.setStudent(rs.getInt("student"));
				dbstudent2.setPassword(rs.getString("password"));
				dbstudent2.setName(rs.getString("name"));
				dbstudent2.setSex(rs.getString("sex"));
				dbstudent2.setCollege(rs.getInt("college"));
				dbstudent2.setEclass(rs.getInt("eclass"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbstudent2;
	}
	@Override
	public ArrayList<DbStudent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DbStudent oneFind(DbStudent dbstudent) {
		Connection con;
		String sql="select * from db_student where student=?";
		ResultSet rs =null;
		DbStudent dbstudent2 = null;
		Object params[]={dbstudent.getStudent()};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbstudent2 = new DbStudent();
				dbstudent2.setStudent(rs.getInt("student"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbstudent2;
	}
	@Override
	public ArrayList<DbStudent> findClass(int eclass) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DbStudent findInt(int o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<DbStudent> fenselect(int o, int a, String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int modify(DbStudent o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(int o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<DbStudent> fdyselect(int o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<DbStudent> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int delete(int o, int p, String reason) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<DbStudent> findAll(String o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<DbStudent> EclassAll(int o) {
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
