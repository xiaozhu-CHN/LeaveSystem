<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>老师登陆失败</title>
</head>
<body>
抱歉，${dbteacher.name} 老师，你登入主页失败！！<br>
       请点击<a href="/leave/teacher/college/index.jsp">学生请假管理系统</a>再次尝试进入
</body>
</html>