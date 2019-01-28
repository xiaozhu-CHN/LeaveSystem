<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>请假表修改</title>
<style>
.footer{ position: absolute;
    height: 50px;
    text-align: center;
    line-height: 50px;
    width: 100%;}
</style>

</head>
<body>
<h3 align="center">请假申请表</h3>
<font color="red"><s:property value="msg" /></font><br>
<a href="javascript:history.back(-1)">返回上一页</a>
<form  action="/LeaveSystem/leave/student/Amendafake" method="post"  >
	<table align="center">
		<tr>
				<td align="right" >假条编号：</td>
				<td>${dbleave.id}
				<input type="text" name="dbleave.id" value="${dbleave.id}" style="display:none">
				</td>
		</tr>
		<tr>
				<td align="right">姓名：</td>
				<td>${dbstudent.name}  
				<input type="text" name="dbleave.student" value="${dbstudent.student}" style="display:none"></td>
		</tr>
		<tr>
				<td align="right" >学院：</td>
				<td>${dbcollege.name}</td>
		</tr>
		<tr>
				<td align="right" >班级：</td>
				<td>${dbeclass.name}</td>
		</tr>
		<tr>
				<td align="right" >请假开始时间：</td>
				<td><input name="dbleave.timeStarr" type="datetime-local" /> </td>
		</tr>
		<tr>
				<td align="right" >请假结束时间：</td>
				<td><input name="dbleave.timeEnd" type="datetime-local"   /> </td>
		</tr>
		<tr>
				<td align="right" >请假理由（100字内）：</td>
				<td><textarea cols="55" rows="6" name="dbleave.reason" >${dbleave.reason}</textarea> </td>
		</tr>
		<tr>
				<td align="right"><input type="submit" value="重新提交申请" ></td>					
		</tr>
	</table>
</form>

<div class="footer"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274141539'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274141539%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script></div>
</body>
</html>