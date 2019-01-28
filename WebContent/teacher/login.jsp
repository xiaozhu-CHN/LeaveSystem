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
<title>老师登陆页面</title>
<style>
.footer{ position: absolute;
    height: 50px;
    text-align: center;
    line-height: 50px;
    width: 100%;}
    
    
    
</style>
</head>
<body>
<s:fielderror cssStyle="color: red"></s:fielderror>
	<font color="red"><s:property value="msg" /></font>
	<br>

	<div align="center"><form method="post" action="<%=basePath%>teacher/teacherLogin" >
		<table>
			<tr>
				<th colspan="2">老师登录</th>
			</tr>
			<tr>
				<td align="right">工号：</td>
				<td><input type="text" name="dbteacher.teacher"
					value="${dbteacher.teacher}" /></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td><input type="password" name="dbteacher.password" /></td>
			</tr>
			<tr>
				<td align="left"><input type="submit" value="登录" /></td>
				<td>未注册者，请先注册，单击 <a href="<%=basePath%>teacher/register.jsp">注册</a></td>
			</tr>
		</table>
	</form>
	</div>
<div class="footer"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274141539'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274141539%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script></div>
</body>
</html>