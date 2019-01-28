package com.leave.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.edu.db_util.DbTeacherExcel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shiti.DbTeacher;

public class DownloadTeacherAction extends ActionSupport {

	
	private static final long serialVersionUID = 1L;	
	
	   //以下4个属性必须提供getter方法
		private String contentType;//指定下载文件的类型,默认值为 text/plain
		private long contentLength;//被下载的文件的大小，以字节为单位
		// 属性：contentDisposition：指定文件下载的处理方式，当为attachment（附件方式）会弹出文件保存对话框，是默认方式，
		// 其格式是attachment;filename="${fileName}
		private String contentDisposition;//指定下载文件的下载方式，并指定保存文件的默认文件名
		private InputStream inputStream;//Action 中提供的文件的输入流。默认值为 inputStream
		
		private String fileName;  //指定下载的文件名	
		
		public String getContentType() {
			return contentType;
		}

		public long getContentLength() {
			return contentLength;
		}

		public String getContentDisposition() {
			return contentDisposition;
		}

		public InputStream getInputStream() {
			return inputStream;
		}
		
		public String executeDownLoad() throws Exception {
			String[] fieldList={"id","student","db_student.name","sex","db_college.name","db_class.name","timeStarr","timeEnd","timeChange","reason","fdy_state","xyld_state","xgc_state","xj_state","remarks"};
			String[] titles={"请假编号","学号","姓名","性别","学院","班级","请假开始时间","请假结束时间","请假申请时间","请假理由","辅导员审核","学院领导审核","学工处审核","销假状态","审核备注"};		
			HttpSession session=ServletActionContext.getRequest().getSession();
			 contentType = "application/octet-stream";//指定为任意类型的文件
			//指定下载后要保存的默认文件名,并通过编码转化，使之支持汉字文件名
			 DbTeacher dbteacher=(DbTeacher) ActionContext.getContext().getSession().get("teacher");
			 String sql=(String) session.getAttribute("sql");
			 fileName=(String) session.getAttribute("file");
			 fileName=dbteacher.getName()+"老师——"+fileName+".xls";
			 String file=fileName+".xls";
		     String name=java.net.URLEncoder.encode(fileName, "UTF-8");
			 contentDisposition = "attachment;filename="+name;
			 ServletContext servletContext =ServletActionContext.getServletContext(); 
			 String fileName2 = servletContext.getRealPath("/download/"+file);	 
			File downloadfile=new File(fileName2);
			if(!downloadfile.exists()){
				downloadfile.getParentFile().mkdirs();
			}
			
			DbTeacherExcel.dBToExcel(fieldList,titles,sql,fileName2);
			 inputStream = new FileInputStream(fileName2);
			 contentLength = inputStream.available();		
			 return SUCCESS;	
		}
}
