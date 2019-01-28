<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<style>
.footer{ position: absolute;
    height: 50px;
    text-align: center;
    line-height: 50px;
    width: 100%;}
</style>


</head>
<script type="text/javascript">
	function allfake()
	{
		location.href="/LeaveSystem/leave/student/stdentLoginLeave";
	}
	function pages(id)
	{
		location.href="/LeaveSystem/leave/student/limitleave?button="+id;
	}
	function shenqing()
	{
		location.href="/LeaveSystem/leave/student/shenqing";
	}
	function audited()
	{
		location.href="/LeaveSystem/leave/student/audited";
	}
	function failure()
	{
		location.href="/LeaveSystem/leave/student/failure";
	}
	function Auditsuccess()
	{
		location.href="/LeaveSystem/leave/student/Auditsuccess";
	}
	function Tobesoldoff()
	{
		location.href="/LeaveSystem/leave/student/Tobesoldoff";
	}
	function Alreadysoldoff()
	{
		location.href="/LeaveSystem/leave/student/Alreadysoldoff";
	}
	function StudentExit()
	{
		location.href="/LeaveSystem/leave/student/StudentExit";
	}
	function executeDownLoad()
	{
		location.href="/LeaveSystem/leave/student/executeDownLoad";
	}
	</script>



<body>
	<h3 align="center">请假管理系统</h3>
	<font color="red"><s:property  value="msg" /></font><br>
	<div align="center">姓名：${dbstudent.name} 学院：${dbcollege.name} 班级：${dbeclass.name} 学号：${dbstudent.student} 
	<input type='button' value='退出系统' onclick="StudentExit()" align="right"></div><br/>
	<table align="center">
		<tr>
			<td><input type='button' value='全部假条' onclick="allfake()"></td>
			<td><input type='button' value='请假申请' onclick="shenqing()"></td>
			<td><input type='button' value='待审核' onclick="audited()"></td>
			<td><input type='button' value='审核失败' onclick="failure()"></td>
			<td><input type='button' value='审核成功' onclick="Auditsuccess()"></td>
			<td><input type='button' value='待销假' onclick="Tobesoldoff()"></td>
			<td><input type='button' value='已销假' onclick="Alreadysoldoff()"></td>
			<td><input type='button' value='下载全部假条' onclick="executeDownLoad()"></td>
		</tr>	 
	</table>
	<table align="center" width="100%" height="100%">
		<tr>
		<td height="10px" align="center"></td>
		<td height="10px" align="center">请假编号</td>
		<td style="word-wrap:break-word; word-break:break-all" align="center">开始时间</td>
		<td style="word-wrap:break-word; word-break:break-all" align="center">结束时间</td>
		<td style="word-wrap:break-word; word-break:break-all" align="center">申请时间</td>
		<td style="word-wrap:break-word; word-break:break-all" align="center">请假理由</td>
		<td height="15px" align="center">辅导员</td>
		<td height="15px" align="center">学院领导</td>
		<td height="15px" align="center">学工处</td>
		<td height="15px" align="center">销假</td>
		<td style="word-wrap:break-word; word-break:break-all" align="center">备注</td>
		<td height="10px" align="center">操作</td>
		</tr>
		<c:if test="${!empty leave}">
		<c:forEach items="${leave}"  var="jiatiao" >
		<tr>
		<td align="center"><input type="checkbox" name="checkList" value="${jiatiao.id}"></td>
		<td align="center">${jiatiao.id}</td>
		<td align="center">${jiatiao.timeStarr}</td>
		<td align="center">${jiatiao.timeEnd}</td>
		<td align="center">${jiatiao.timeChange}</td>
		<td align="center">${jiatiao.reason}</td>
		<td align="center"><c:if test="${jiatiao.fdyState==1}" >待审核</c:if>
			<c:if test="${jiatiao.fdyState==2}" >审核通过</c:if>
			<c:if test="${jiatiao.fdyState==3}" >审核不通过</c:if>
		</td>
		<td align="center">
			<c:if test="${jiatiao.xyldState==1}" >待审核</c:if>
			<c:if test="${jiatiao.xyldState==2}" >审核通过</c:if>
			<c:if test="${jiatiao.xyldState==3}" >审核不通过</c:if></td>
		<td align="center">
			<c:if test="${jiatiao.xgcState==1}" >待审核</c:if>
			<c:if test="${jiatiao.xgcState==2}" >审核通过</c:if>
			<c:if test="${jiatiao.xgcState==3}" >审核不通过</c:if></td>
		<td align="center">
			<c:if test="${jiatiao.xjState==1}" >待销假</c:if>
			<c:if test="${jiatiao.xjState==2}" >已销假</c:if></td>
		<td align="center">${jiatiao.remarks}</td>
		<td align="center"><c:if test="${jiatiao.fdyState==3||jiatiao.xyldState==3||jiatiao.xgcState==3}" >
		<a href="/LeaveSystem/leave/student/Leavemodify?dbleave.id=${jiatiao.id}">修改</a>
		<a href="/LeaveSystem/leave/student/studentdelete?dbleave.id=${jiatiao.id}">删除</a>
		</c:if></td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	<table align="center">
		<tr><td><input type='button' value='上一页' onclick="pages('1')" <c:if test="${fanye==0}">style="display:none"</c:if>></td>
		<td><input type='button' value='下一页' onclick="pages('2')" <c:if test="${leave.size()<5}">style="display:none"</c:if>></td></tr>
		</table>
		
		
		
<div class="footer"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274141539'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274141539%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script></div>
</body>
</html>