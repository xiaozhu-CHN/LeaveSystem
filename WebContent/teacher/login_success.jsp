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
<title>学生登陆成功</title>
</head>
<body>
	欢迎你，${dbteacher.name} 老师，你登入成功！！<br>
       进入学生请假管理系统，请点击<a href="<%=basePath%>card/find">学生请假管理系统</a>
</body>
</html>