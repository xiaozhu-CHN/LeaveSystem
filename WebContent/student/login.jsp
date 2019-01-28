<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><s:text name="title"></s:text></title>
</head>
<body>
	<s:fielderror cssStyle="color: red"></s:fielderror>
	<font color="red"><s:property value="msg" /></font>
	<br>				
	<div align="center"><s:form method="post" action="/student/studentLogin" >
		<div class="login_title">
				<s:text name="title2" ></s:text>
			</div>
				<s:textfield name="dbstudent.student" key="username" ></s:textfield>
			
				<s:password name="dbstudent.password" key="password" ></s:password>
			
				<s:submit key="denglu" align="center"></s:submit>		
	</s:form>
	</div>
	<p align="center"><s:a href="/LeaveSystem/student/register.jsp">未注册？单击此处注册！</s:a> </p>


<div class="footer"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274141539'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274141539%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script></div>
</body>
</html>