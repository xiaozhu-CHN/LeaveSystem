package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.shiti.DbClass;
import com.shiti.DbTeacherEclass;

public class TeacherEclassDao implements IBaseDao<DbTeacherEclass> {

	@Override
	public int insert(DbTeacherEclass o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DbTeacherEclass find(DbTeacherEclass o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbTeacherEclass oneFind(DbTeacherEclass o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherEclass> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherEclass> findAll(String o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherEclass> EclassAll(int college) {
		// TODO 查询学院的班级的辅导员
		String sql = "SELECT db_class.eclass,db_teacher.teacher,db_class.`name`,db_teacher.`name` FROM db_class LEFT JOIN db_teacher ON db_class.teacher=db_teacher.teacher WHERE db_class.college=?";
		Connection con;
		ResultSet rs =null;
		ArrayList<DbTeacherEclass> teachereclass = new ArrayList<DbTeacherEclass>();
		Object params[]={college};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			while(rs.next()){
				DbTeacherEclass dbteachereclass = new DbTeacherEclass();
				dbteachereclass.setEclass(rs.getInt("db_class.eclass"));
				dbteachereclass.setName(rs.getString("db_class.name"));
				dbteachereclass.setName1(rs.getString("db_teacher.name"));
				dbteachereclass.setTeacher(rs.getInt("db_teacher.teacher"));
				teachereclass.add(dbteachereclass);
			}
			JdbcPoolUtils.close(rs,null,con);
		}catch(SQLException e){e.printStackTrace();}
		return teachereclass;
	}

	@Override
	public ArrayList<DbTeacherEclass> findClass(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbTeacherEclass findInt(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherEclass> fenselect(int o, int a, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherEclass> fdyselect(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbTeacherEclass> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(DbTeacherEclass o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int o, int p, String reason) {
		// TODO Auto-generated method stub
		return 0;
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
