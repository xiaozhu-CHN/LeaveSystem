package com.edu.db_util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.edu.db_util.JdbcPoolUtils;

public class DbToExcel {
	/**
	 * 该方法实现将电子表中的数据导入对应的数据库内
	 * 
	 * @param excelpath:电子表路径
	 * @param table    ：数据库数据表名
	 * @param fieldList   ：数据库字段名串，在插入数据库中，各字段信息，并且用逗号间隔，
	 * @param columnCount    ：要添加的字段的个数
	 * @throws Exception
	 */
	public static void excelToDb(String excelpath, String table,
			String fieldList, int columnCount) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		Workbook workbook = null;
		Sheet sheet = null;
		conn = JdbcPoolUtils.getConnection();
		String sql = "insert into " + table + " " + fieldList + "  values (";
		for (int i = 1; i < columnCount; i++) {
			sql += "?,";
		}
		sql += "?) ";
		ps = conn.prepareStatement(sql);
		workbook = Workbook.getWorkbook(new File(excelpath));
		sheet = workbook.getSheet(0);
		int r = sheet.getRows();
		for (int i = 1; i < r; i++) {
			for (int j = 0; j < columnCount; j++)
				ps.setString(j + 1, sheet.getCell(j, i).getContents());
			ps.addBatch();
		}
		ps.executeBatch();
		workbook.close();
		JdbcPoolUtils.close(null, ps, conn);
	}

	/**
	 * 该方法实现将数据库中的某数据表数据形成电子表Excel
	
	 * @param fieldList
	 *            ：数据表字段名，采用字符串数组依次存放
	 * @param titles
	 *            ：所形成的电子表表头字段信息，采用字符串数组存放
	 * @param sql
	 *            ：查询sql语句
	 * @param file
	 *            ：电子表名字
	 * @throws Exception
	 */
	public static void dBToExcel(String[] fieldList,
			String[] titles, String sql, String file)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		WritableWorkbook wwb = null;
		WritableSheet ws = null;

		conn = JdbcPoolUtils.getConnection();
		int fl = fieldList.length;	
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		wwb = Workbook.createWorkbook(new File(file));
		ws = wwb.createSheet("sheet1", 0);

		for (int i = 0; i < fl; i++) {
			ws.addCell(new Label(i, 0, titles[i]));
		}
		int count = 1;
		while (rs.next()) {
			for (int j = 0; j < fl; j++) {
				if(j>=6&&j<=8) {
							if(rs.getString(j + 1)==null) {
								ws.addCell(new Label(j, count, rs.getString(j + 1)));	
							}else {
								if(rs.getString(j + 1).equals("1")) {
									ws.addCell(new Label(j, count,"待审核"));
								}else {
									if(rs.getString(j + 1).equals("2")) {
										ws.addCell(new Label(j, count,"审核通过"));
									}else {
										ws.addCell(new Label(j, count,"审核不通过"));							
									}
								}
							}
				}else {if(j==9) {
											if(rs.getString(j + 1)==null) {
												ws.addCell(new Label(j, count, rs.getString(j + 1)));	
											}else {
												if(rs.getString(j + 1).equals("1")) {
													ws.addCell(new Label(j, count,"待销假"));
												}else {
													ws.addCell(new Label(j, count,"已销假"));
												}
										}
								}else {
									ws.addCell(new Label(j, count, rs.getString(j + 1)));	
										}
					}
			}
			count++;
		}
		wwb.write();

		if (wwb != null)
			wwb.close();
		JdbcPoolUtils.close(null, ps, conn);

	}

}
