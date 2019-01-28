package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.shiti.DbClass;
import com.shiti.DbLeave;


public class LeaveDao implements IBaseDao<DbLeave> {
	

	@Override
	public int insert(DbLeave dbleave) {
		// 插入一条请假记录
		String sql = "insert into db_leave(student,timeStarr,timeEnd,timeChange,reason,fdy_state)values(?,?,?,?,?,?)";
		Object params[] ={dbleave.getStudent(),dbleave.getTimeStarr(),dbleave.getTimeEnd(),dbleave.getTimeChange(),dbleave.getReason(),1};
		return JdbcPoolUtils.dbCUD(sql,params);		
	}	

	@Override
	public DbLeave find(DbLeave o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbLeave oneFind(DbLeave o) {
		// 
		return null;
	}

	@Override
	public ArrayList<DbLeave> findAll() {
		// 学工处查询所有假条方法
		Connection con;
		String sql="select * from db_leave";
		ResultSet rs =null;
		ArrayList<DbLeave> leave1 = new ArrayList<DbLeave>();
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.dbsql(con,sql);
			while(rs.next()){
				DbLeave dbleave1 = new DbLeave();
				dbleave1.setId(rs.getInt("id"));
				dbleave1.setStudent(rs.getInt("student"));
				dbleave1.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbleave1.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbleave1.setTimeChange(rs.getTimestamp("timeChange"));
				dbleave1.setReason(rs.getString("reason"));
				dbleave1.setFdyState(rs.getInt("fdy_state"));
				dbleave1.setXyldState(rs.getInt("xyld_state"));
				dbleave1.setXgcState(rs.getInt("xgc_state"));
				dbleave1.setXjState(rs.getInt("xj_state"));
				dbleave1.setRemarks(rs.getString("remarks"));
				leave1.add(dbleave1);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return leave1;
	}

	@Override
	public ArrayList<DbLeave> findClass(int student) {
		// 查询学生全部假条的方法(暂时不用）
		Connection con;
		String sql="select * from db_leave where student=? LIMIT 0,5";
		ResultSet rs =null;
		ArrayList<DbLeave> leave1 = new ArrayList<DbLeave>();
		Object params[]={student};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			
			while(rs.next()){
				DbLeave dbleave1 = new DbLeave();
				dbleave1.setId(rs.getInt("id"));
				dbleave1.setStudent(rs.getInt("student"));
				dbleave1.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbleave1.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbleave1.setTimeChange(rs.getTimestamp("timeChange"));
				dbleave1.setReason(rs.getString("reason"));
				dbleave1.setFdyState(rs.getInt("fdy_state"));
				dbleave1.setXyldState(rs.getInt("xyld_state"));
				dbleave1.setXgcState(rs.getInt("xgc_state"));
				dbleave1.setXjState(rs.getInt("xj_state"));
				dbleave1.setRemarks(rs.getString("remarks"));
				leave1.add(dbleave1);
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return leave1;
	}

	@Override
	public DbLeave findInt(int id) {
		// 查询单个假条
		Connection con;
		String sql="select * from db_leave where id=?";
		ResultSet rs =null;
		DbLeave dbleave1 = null;
		Object params[]={id};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			if(rs.next()){
				dbleave1 = new DbLeave();
				dbleave1.setId(rs.getInt("id"));
				dbleave1.setStudent(rs.getInt("student"));
				dbleave1.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbleave1.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbleave1.setTimeChange(rs.getTimestamp("timeChange"));
				dbleave1.setReason(rs.getString("reason"));
				dbleave1.setFdyState(rs.getInt("fdy_state"));
				dbleave1.setXyldState(rs.getInt("xyld_state"));
				dbleave1.setXgcState(rs.getInt("xgc_state"));
				dbleave1.setXjState(rs.getInt("xj_state"));
				dbleave1.setRemarks(rs.getString("remarks"));
			}
			JdbcPoolUtils.close(rs, null, con);
		}catch(SQLException e){e.printStackTrace();}
		return dbleave1;
	}

	@Override
	public ArrayList<DbLeave> fenselect(int student, int dtartno,String sql) {
		// 翻页查询方法
		Connection con;
		ResultSet rs =null;
		ArrayList<DbLeave> leave1 = new ArrayList<DbLeave>();
		Object params[]={student,dtartno};
		try{
			con = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(con,sql,params);
			
			while(rs.next()){
				DbLeave dbleave1 = new DbLeave();
				dbleave1.setId(rs.getInt("id"));
				dbleave1.setStudent(rs.getInt("student"));
				dbleave1.setTimeStarr(rs.getTimestamp("timeStarr"));
				dbleave1.setTimeEnd(rs.getTimestamp("timeEnd"));
				dbleave1.setTimeChange(rs.getTimestamp("timeChange"));
				dbleave1.setReason(rs.getString("reason"));
				dbleave1.setFdyState(rs.getInt("fdy_state"));
				dbleave1.setXyldState(rs.getInt("xyld_state"));
				dbleave1.setXgcState(rs.getInt("xgc_state"));
				dbleave1.setXjState(rs.getInt("xj_state"));
				dbleave1.setRemarks(rs.getString("remarks"));
				leave1.add(dbleave1);
			}
			JdbcPoolUtils.close(rs,null,con);
		}catch(SQLException e){e.printStackTrace();}
		return leave1;
	}

	@Override
	public int modify(DbLeave dbleave) {
		// 修改假条的方法
		String sql = "UPDATE db_leave SET timeStarr=?,timeEnd=?,timeChange=?,reason=?,fdy_state=1,xyld_state=NULL,xgc_state=NULL,xj_state=NULL,remarks=NULL WHERE id=?";
		Object params[] ={dbleave.getTimeStarr(),dbleave.getTimeEnd(),dbleave.getTimeChange(),dbleave.getReason(),dbleave.getId()};
		return JdbcPoolUtils.dbCUD(sql,params);	
	}

	@Override
	public int delete(int id) {
		//删除假条方法
		String sql = "DELETE FROM db_leave WHERE id=?";
		Object params[] ={id};
		return JdbcPoolUtils.dbCUD(sql,params);	
	}

	@Override
	public ArrayList<DbLeave> fdyselect(int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbLeave> teacherEclass(ArrayList<DbClass> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int o, int p, String reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DbLeave> findAll(String o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DbLeave> EclassAll(int o) {
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
