package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.shiti.DbClass;
import com.shiti.DbCollege;


public class CollegeDao implements IBaseDao<DbCollege> {

	@Override
	public int insert(DbCollege collegedb) {
		// TODO 添加学院
		String sql = "insert into db_college(name)values(?)";
		Object params[] ={collegedb.getName()};
		return JdbcPoolUtils.dbCUD(sql,params);
	}

	@Override
	public DbCollege find(DbCollege o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbCollege> findAll() {
		/*
		 * 查询所有机构信息
		 */
		Connection con;
		String sql="select * from db_college";
		ResultSet rs =null;
		ArrayList<DbCollege> college1 = new ArrayList<DbCollege>();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				DbCollege college2 = new DbCollege();
				college2.setCollege(rs.getInt("college"));
				college2.setName(rs.getString("name"));
				college1.add(college2);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return college1;
	}

	@Override
	public DbCollege oneFind(DbCollege o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbCollege> findClass(int college) {
		// 查询非学工处学院
		Connection con;
		String sql="select * from db_college where db_college.college!="+college;
		ResultSet rs =null;
		ArrayList<DbCollege> college1 = new ArrayList<DbCollege>();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				DbCollege college2 = new DbCollege();
				college2.setCollege(rs.getInt("college"));
				college2.setName(rs.getString("name"));
				college1.add(college2);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return college1;
	}

	@Override
	public DbCollege findInt(int college) {
		// 查询学院
		Connection con;
		String sql="select * from db_college where college=?";
		ResultSet rs =null;
		DbCollege dbcollege2 = null;
		Object params[]={college};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbcollege2 = new DbCollege();
				dbcollege2.setCollege(rs.getInt("college"));
				dbcollege2.setName(rs.getString("name"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbcollege2;
	}

	@Override
	public ArrayList<DbCollege> fenselect(int o, int a, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(DbCollege o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int college) {
		// TODO 删除学院
		String sql = "DELETE FROM db_college WHERE college=?";
		Object params[] ={college};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public ArrayList<DbCollege> fdyselect(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbCollege> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int o, int p, String reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DbCollege> findAll(String o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbCollege> EclassAll(int o) {
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
