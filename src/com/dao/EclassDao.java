package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.shiti.DbClass;

public class EclassDao implements IBaseDao<DbClass> {

	@Override
	public int insert(DbClass o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DbClass find(DbClass o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbClass oneFind(DbClass o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbClass> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ArrayList<DbClass> findClass(int eclass) {
		/*
		 * 查询学院的班级信息
		 */
		Connection con;
		String sql="select * from db_class where college=?";
		ResultSet rs =null;
		ArrayList<DbClass> eclass1 = new ArrayList<DbClass>();
		Object params[]={eclass};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			while(rs.next()){
				DbClass eclass2 = new DbClass();
				eclass2.setEclass(rs.getInt("eclass"));
				eclass2.setCollege(rs.getInt("college"));
				eclass2.setName(rs.getString("name"));
				eclass2.setTeacher(rs.getInt("teacher"));
				eclass1.add(eclass2);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return eclass1;
	}

	@Override
	public DbClass findInt(int eclass) {
		// 查询班级
		Connection con;
		String sql="select * from db_class where eclass=?";
		ResultSet rs =null;
		DbClass dbclass2 = null;
		Object params[]={eclass};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbclass2 = new DbClass();
				dbclass2.setEclass(rs.getInt("eclass"));
				dbclass2.setCollege(rs.getInt("college"));
				dbclass2.setName(rs.getString("name"));
				dbclass2.setTeacher(rs.getInt("teacher"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbclass2;
		
	}


	@Override
	public int modify(DbClass o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DbClass> fenselect(int o, int a, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbClass> fdyselect(int teacher) {
		// TODO 查询老师所管理的班级
		Connection con;
		String sql="select * from db_class where teacher=?";
		ResultSet rs =null;
		ArrayList<DbClass> eclass1 = new ArrayList<DbClass>();
		Object params[]={teacher};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			while(rs.next()){
				DbClass eclass2 = new DbClass();
				eclass2.setEclass(rs.getInt("eclass"));
				eclass2.setCollege(rs.getInt("college"));
				eclass2.setName(rs.getString("name"));
				eclass2.setTeacher(rs.getInt("teacher"));
				eclass1.add(eclass2);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return eclass1;
	}

	@Override
	public ArrayList<DbClass> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int o, int p, String reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DbClass> findAll(String o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbClass> EclassAll(int o) {
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
