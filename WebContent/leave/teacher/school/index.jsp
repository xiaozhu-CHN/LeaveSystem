<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学工处主页</title>
</head>
<script type="text/javascript">
		function pages(id)
		{
			location.href="/LeaveSystem/leave/teacher/school/limitleave?button="+id;
		}
		function allfake()
		{
			location.href="/LeaveSystem/leave/teacher/school/SchoolLoginLeave";
		}
		function ToBeAudited()
		{
			location.href="/LeaveSystem/leave/teacher/school/ToBeAudited";
		}
		function ToBeASoldOff()
		{
			location.href="/LeaveSystem/leave/teacher/school/ToBeASoldOff";
		}
		function Adopt()
		{
			location.href="/LeaveSystem/leave/teacher/school/Adopt";
		}
		function NoAdopt()
		{
			location.href="/LeaveSystem/leave/teacher/school/NoAdopt";
		}
		function AlreadySoldOff()
		{
			location.href="/LeaveSystem/leave/teacher/school/AlreadySoldOff";
		}
		function adopt(leave)
		{
			if(confirm('审核通过？')){
			location.href="/LeaveSystem/leave/teacher/school/examine?dbteacherleave.id="+leave+"&dbteacherleave.xgcState=2";
			}
		}
		function noadopt(leave)
		{
			var t=prompt("不通过理由（100字以内）","学工处审核不通过理由：")
			  if (t!=null && t!="")
			    {
				  location.href="/LeaveSystem/leave/teacher/school/examine?dbteacherleave.id="+leave+"&dbteacherleave.xgcState=3&dbteacherleave.remarks="+t;
			    }
		}
		function SchoolExit()
		{
			location.href="/LeaveSystem/leave/teacher/school/SchoolExit";
		}
		function executeDownLoad()
		{
			location.href="/LeaveSystem/leave/teacher/school/executeDownLoad";
		}
		function CollegeAdmin()
		{
			location.href="/LeaveSystem/leave/teacher/school/CollegeAdmin";
		}
</script>


<body>

	<h3 align="center">请假管理系统</h3>
	<font color="red"><s:property  value="msg" /></font><br>
	<div align="center">老师姓名：${dbteacher.name} 机构：${dbcollege.name}  工号：${dbteacher.teacher} 
	<input type='button' value='退出系统' onclick="SchoolExit()" align="right"><br/>
	</div>
	<div align="center">
	<table>
	<tr>
		<td>
		<form action="/LeaveSystem/leave/teacher/school/LeaveStudent" method="get">
		学号：<input type="text" name="dbteacherleave.student" placeholder="输入学号进行查询！" pattern="[0-9]{9}">
		<input type="submit" value="查询">
		</form>
		</td>
		<td>
		<form action="/LeaveSystem/leave/teacher/school/LeaveId" method="get">
		请假编号：<input type="text" name="dbteacherleave.id" placeholder="输入请假编号进行查询！" pattern="^[0-9]*$">
		<input type="submit" value="查询">
		</form>
		</td>
		<td>
		<form action="/LeaveSystem/leave/teacher/school/LeaveName" method="get">
		姓名：<input type="text" name="dbteacherleave.name" placeholder="输入学生姓名进行查询！" pattern="[\u4e00-\u9fa5]{2,7}">
		<input type="submit" value="查询">
		</form>
		</td>
	</table>
	</div>
	<table align="center">
		<tr>
			<td><input type='button' value='全部假条' onclick="allfake()"></td>
			<td><input type='button' value='待审核' onclick="ToBeAudited()"></td>
			<td><input type='button' value='未销假' onclick="ToBeASoldOff()"></td>
			<td><input type='button' value='审核通过' onclick="Adopt()"></td>
			<td><input type='button' value='审核不通过' onclick="NoAdopt()"></td>
			<td><input type='button' value='已销假' onclick="AlreadySoldOff()"></td>
			<td><input type='button' value='下载当前假条' onclick="executeDownLoad()"></td>
			<td><input type='button' value='学院管理' onclick="CollegeAdmin()"></td>
		</tr>	 
	</table>
	
	<table align="center" width="100%" height="100%">
		<tr>
		<td height="10px" align="center"></td>
		<td height="10px" align="center">请假编号</td>
		<td height="10px" align="center">学号</td>
		<td height="10px" align="center">姓名</td>
		<td height="5px" align="center">性别</td>
		<td height="10px" align="center">学院</td>
		<td height="5px" align="center">班级</td>
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
		<c:if test="${!empty teacherleave}">
		<c:forEach items="${teacherleave}"  var="jiatiao" >
		<tr>
		<td align="center"><input type="checkbox" name="checkList" value="${jiatiao.id}"></td>
		<td align="center">${jiatiao.id}</td>
		<td align="center">${jiatiao.student}</td>
		<td align="center">${jiatiao.name}</td>
		<td align="center">${jiatiao.sex}</td>
		<td align="center">${jiatiao.name1}</td>
		<td align="center">${jiatiao.name2}</td>		
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
		<td align="center"><c:if test="${jiatiao.xgcState==1}" >
		<input type='button' value='通过' onclick="adopt('${jiatiao.id}')">
		<input type='button' value='不通过' onclick="noadopt('${jiatiao.id}')">
		</c:if>
		
		</td>
		</tr>
		</c:forEach>
		</c:if>
		</table>
		
		
		<table align="center">
		<tr><td><input type='button' value='上一页' onclick="pages('1')" <c:if test="${fanye==0}">style="display:none"</c:if>></td>
		<td><input type='button' value='下一页' onclick="pages('2')" <c:if test="${teacherleave.size()<5}">style="display:none"</c:if>></td></tr>
		</table>
</body>
</html>