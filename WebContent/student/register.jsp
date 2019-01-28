<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath()+"/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生注册页面</title>
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
<font color="red"><s:property  value="msg" /></font><br>
	
	<h3 align="center">欢迎注册我们的系统，请认真填写您的信息</h3>
	<form  action="/LeaveSystem/student/StudentRegister" method="post"  name="StudentRegister" >
		<table>
			<tr>
				<td align="right" >学号：</td>
				<td><input type="text" name="dbstudent.student" value="${dbstudent.student}"></td>
			</tr>
			<tr>
				<td align="right">为您的账户设置密码：</td>
				<td><input type="password" name="dbstudent.password" value="${dbstudent.password}"></td>
			</tr>

			<tr>
				<td align="right">再次确认您的密码：</td>
				<td><input type="password" name="re_password" value="${re_password}" ></td>
				
			</tr>
			<tr>
				<td align="right">真实姓名：</td>
				<td><input type="text" name="dbstudent.name" value="${dbstudent.name}"></td>
				
			</tr>
			<tr>
				<td align="right">性别：</td>
				<td><input type="radio" name="dbstudent.sex" value="男" <c:if test="${dbstudent.sex eq '男'}">checked</c:if>  />男
				<input type="radio" name="dbstudent.sex" value="女" <c:if test="${dbstudent.sex eq '女'}">checked</c:if> />女</td>					
			</tr>
			<tr>
			   <td align="right" >学院：</td>
			   <td><select name="dbstudent.college" >
			   
			  
			   <c:forEach items="${college}"  var="u" begin="1">
			   		<option value="${u.college}" <c:if test="${u.college==dbstudent.college }" >selected="selected"</c:if>>${u.name}</option>
			   </c:forEach>
			   </select> 
			   <input type="submit" value="获取学院" name="submit">
			  </td>
			</tr>
			<tr>
			   <td align="right">班级：</td>
			   <td><select name="dbstudent.eclass" >
						<c:forEach items="${eclass}"  var="e">
			   		<option value="${e.eclass}" <c:if test="${e.eclass==dbstudent.eclass }" >selected="selected"</c:if>>${e.name}</option>
			 		  	</c:forEach>
						</select> 
						<input type="submit" value="获取班级" name="submit">
						
						</td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="注册" name="submit" >
				</td>
									
				<td colspan="2"><input type="reset" value="重新填写" ></td>
			</tr>
			
			
		</table>
	</form>
	
	
	
<div class="footer"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274141539'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274141539%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script></div>	
</body>
</html>