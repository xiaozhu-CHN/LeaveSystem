package com.edu.dao;

import java.util.ArrayList;

import com.shiti.DbClass;

public interface IBaseDao<T> {
	public int insert(T o); //将对象o,添加到数据库内	
	public T find(T o);    //查找对象o
	
	public T oneFind(T o);  //一个数据查询
	
	public ArrayList<T> findAll();       //查找所有对象集合1
	
	public ArrayList<T> findAll(String o);
	
	public ArrayList<T> EclassAll(int o);
	
	public ArrayList<T> findClass(int o);   //数字查询一个对象集合
	
	public T findInt(int o);   //数字查询一个对象
	
	public ArrayList<T> fenselect(int o,int a,String sql);
	
	public ArrayList<T> fdyselect(int o);  //数字查询一个对象集合
	
	public ArrayList<T> teacherEclass(ArrayList<DbClass> o);
	
	public int modify(T o);  //修改数据返回int
	
	public int delete(int o);
	
	public int delete(int o,int p,String reason);
	
	public int adddata(String a,int b);
	
	public int deletedata(int a);
}
