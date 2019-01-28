<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学院班级管理</title>
</head>

	 <style> 
        .black_overlay{ 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 45%; 
            height: 55%; 
            padding: 3px; 
            border: 5px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
        #addclass { 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 45%; 
            height: 55%; 
            padding: 3px; 
            border: 5px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        }
      </style>
    
    <script type="text/javascript" src="/LeaveSystem/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	function allfake()
	{
		location.href="/LeaveSystem/leave/teacher/college/CollegeLoginLeave";
	}
	function DeleteEclass(deleeclass,delename)
	{
		if(confirm('确定删除？')){
		location.href="/LeaveSystem/leave/teacher/college/DeleteEclass?dbteachereclass.eclass="+deleeclass+"&dbteachereclass.name="+delename;
		}
	}
	
	
	$(document).ready(function(){
		$("#fade").hide();
		$("#light").hide();
		$("#addclass").hide();
		
		$("#exit").click(function() {
			$("#fade").hide();
			$("#light").hide();
	        $("#renming").html("");
	    });
		$("#exit2").click(function() {
			$("#fade").hide();
			$("#addclass").hide();
	    });
		
		$(".btn").bind("click",function(){
			$("#fade").show();
			$("#light").show();
				$.ajax({
				    type:"post",
				    url:"/LeaveSystem/leave/teacher/college/CollegeTeacher",
				    data:{
				    	"dbteachereclass.eclass":$(this).attr("data-eclass"),
				    	"dbteachereclass.name":$(this).attr("data-name")
				    },
					dataType:"json",
					success:function(data){
						var studata = eval("(" + data + ")");  //将数据转化成json
						var s = "";
						var head="<tr><td height='20px' align='center'>工号</td>" + 
						"<td height='20px' align='center'>姓名</td>" + 
						"<td height='20px' align='center'>操作</td></tr>";
						$("#renming").append(head);
				        for(var i = 0; i < studata.length; i++) {
							s ="<tr><td align='center'>" + studata[i].teacher + "</td><td align='center'>" + studata[i].name + "</td><td align='center'><input type='button' class='TeacherEclassAppointment' value='任命' data-teacher=\"" + studata[i].teacher + "\" data-name1=\"" + studata[i].name + "\" ></td></tr>";
							$("#renming").append(s);
						}
					},
				    error:function(){
				    	alert("系统异常，请稍后重试！");
				    	}
				    });
			});
		
		$("#addeclass").click(function() {
			$("#fade").show();
			$("#addclass").show();
	    });
		
		$(document).on("click",".TeacherEclassAppointment",function(){ 
			//alert($(this).attr("data-teacher"));
			window.location="/LeaveSystem/leave/teacher/college/TeacherEclassAppointment?dbteachereclass.teacher="+$(this).attr("data-teacher")+"&dbteachereclass.name1="+$(this).attr("data-name1");
		});
	});
	</script>
<body>
	<h3 align="center">${dbcollege.name}班级管理</h3>
	<font color="red"><s:property  value="msg" /></font><br>
	<div align="right"><input type='button' value='返回主页' onclick="allfake()"></div>
	<table align="center"><tr><td><input type='button' value='添加班级' id="addeclass" ></td></tr></table>
	<table align="center" width="70%" height="100%">
		<tr>
			<td height="10px" align="center"></td>
			<td height="20px" align="center">班级名称</td>
			<td height="20px" align="center">辅导员</td>
			<td height="20px" align="center">操作</td>
		</tr>
		<c:forEach items="${teachereclass}"  var="teacher" >
		<tr>
		<td align="center"><input type="checkbox" name="checkList" value="${teacher.eclass}"></td>
		<td align="center">${teacher.name}</td>
		<td align="center">${teacher.name1}</td>
		<td align="center">
		<input type='button' value='删除班级' onclick="DeleteEclass('${teacher.eclass}','${teacher.name}')">
		<c:if test="${teacher.name1==null}" >
		<input type='button' class="btn" value='任命' data-eclass="${teacher.eclass}" data-name="${teacher.name}" >
		</c:if>
		<c:if test="${teacher.name1!=null}" >
		<input type='button' class="btn" value='重新任命' data-eclass="${teacher.eclass}" data-name="${teacher.name}" >
		</c:if>
		</td> 
		</tr>
		</c:forEach>
	</table>
	
	<div id="light" class="white_content">
	<p align="right"><input align="right" type='button' id="exit" value='关闭窗口' >
	</p>
	<table id="renming" align="center" width="60%" >
	</table>
	</div>
	<div id="addclass">
	<p align="right"><input type='button' id="exit2" value='关闭窗口' ></p>
	<div align="center">
	<h4>添加班级</h4>
	<form  action="/LeaveSystem/leave/teacher/college/AddEclass" method="get" >
	<table ><tr><td>学院：</td><td>${dbcollege.name}</td>
	</tr>
	<tr><td>班级名称</td><td><input type="text"  name="dbteachereclass.name" placeholder="清输入班级名称！" ></td></tr>
	</table>
	<input type='submit' value='添加'>
	</form>
	</div>
	</div>
	<div id="fade" class="black_overlay"></div>
</body>
</html>