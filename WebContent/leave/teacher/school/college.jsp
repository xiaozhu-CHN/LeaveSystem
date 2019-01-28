<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学院管理</title>
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
        #CollegeInterface { 
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
        #addcollege { 
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
		location.href="/LeaveSystem/leave/teacher/school/SchoolLoginLeave";
	}
	function DeleteCollege(xueyuancollege,xueyuanname)
	{
		if(confirm('确定删除？')){
			location.href="/LeaveSystem/leave/teacher/school/DeleteCollege?collegedb.college="+xueyuancollege+"&collegedb.name="+xueyuanname;
		}
	}
	function SchoolApp(schoolteacher,schoolname,schoolJN,schoolcollege,schoolname1)
	{
		if(confirm('确定？')){
		$.ajax({
		    type:"post",
		    url:"/LeaveSystem/leave/teacher/school/ModifyTeacherJN",
		    data:{
		    	"teacherdb.teacher":schoolteacher,
		    	"teacherdb.name":schoolname,
		    	"teacherdb.jurisdiction":schoolJN
		    },
			dataType:"json",
			success:function(data){
				alert(data);
				$("#CollegeTeacher").html("");
				$.ajax({
				    type:"post",
				    url:"/LeaveSystem/leave/teacher/school/QueryTeacher",
				    data:{
				    	"collegedb.college":schoolcollege,
				    	"collegedb.name":schoolname1
				    },
					dataType:"json",
					success:function(data){
							var studata = eval("(" + data + ")");  //将数据转化成json
							var s = "";
							var head="<tr><td height='20px' align='center'>工号</td>" + 
							"<td height='20px' align='center'>姓名</td>" + 
							"<td height='20px' align='center'>权限</td>" + 
							"<td height='20px' align='center'>操作</td></tr>";
							$("#CollegeTeacher").append(head);
					        for(var i = 0; i < studata.length; i++) {
					        	//if(studata[i].jurisdiction)  	
					        	s +='<tr><td align="center">'+studata[i].teacher;
					        	s +='</td><td align="center">'+studata[i].name;
					        	if(studata[i].jurisdiction==1){
						        	s +='</td><td align="center">辅导员</td>';
						        	s +='<td align="center"><input type="button" value="任命"  onclick=SchoolApp("'+studata[i].teacher+'","'+studata[i].name+'","2","'+schoolcollege+'","'+schoolname1+'")></td></tr>';      
						     		}
					        	if(studata[i].jurisdiction==2){
						        	s +='</td><td align="center">学院领导</td>';
						        	s +='<td align="center"><input type="button" value="取消任命"  onclick=SchoolApp("'+studata[i].teacher+'","'+studata[i].name+'","1","'+schoolcollege+'","'+schoolname1+'")></td></tr>';      
						     		}
					        
					        	}
					        $("#CollegeTeacher").append(s); 
					},
				    error:function(){
				    	alert("该学院没有老师！");
				    	}
				    });
			},
			error:function(){
		    	alert("系统错误！请稍后再试！");
		    	}
			
		});	
		}
	}
	
	$(document).ready(function(){
		$("#fade").hide();
		$("#addcollege").hide();
		$("#CollegeInterface").hide();
		$("#exit2").click(function() {
			$("#fade").hide();
			$("#addcollege").hide();
	    });
		$("#addxueyuan").click(function() {
			$("#fade").show();
			$("#addcollege").show();
	    });
		$("#exit").click(function() {
			$("#fade").hide();
			$("#CollegeInterface").hide();
			$("#CollegeTeacher").html("");
			$("#biaoti").html("");
	    });
		
		$(".btn").bind("click",function(){
			$("#fade").show();
			$("#CollegeInterface").show();
			var btncollege = $(this).attr("data-college");
			var btnname = $(this).attr("data-name");
				$.ajax({
				    type:"post",
				    url:"/LeaveSystem/leave/teacher/school/QueryTeacher",
				    data:{
				    	"collegedb.college":btncollege,
				    	"collegedb.name":btnname
				    },
					dataType:"json",
					success:function(data){
							var studata = eval("(" + data + ")");  //将数据转化成json
							var s = "";
							$("#biaoti").append(btnname+"的任命");
							var head="<tr><td height='20px' align='center'>工号</td>" + 
							"<td height='20px' align='center'>姓名</td>" + 
							"<td height='20px' align='center'>权限</td>" + 
							"<td height='20px' align='center'>操作</td></tr>";
							$("#CollegeTeacher").append(head);
					        for(var i = 0; i < studata.length; i++) {
					        	//if(studata[i].jurisdiction)  	
					        	s +='<tr><td align="center">'+studata[i].teacher;
					        	s +='</td><td align="center">'+studata[i].name;
					        	if(studata[i].jurisdiction==1){
						        	s +='</td><td align="center">辅导员</td>';
						        	s +='<td align="center"><input type="button" value="任命"  onclick=SchoolApp("'+studata[i].teacher+'","'+studata[i].name+'","2","'+btncollege+'","'+btnname+'")></td></tr>';      
						     		}
					        	if(studata[i].jurisdiction==2){
						        	s +='</td><td align="center">学院领导</td>';
						        	s +='<td align="center"><input type="button" value="取消任命"  onclick=SchoolApp("'+studata[i].teacher+'","'+studata[i].name+'","1","'+btncollege+'","'+btnname+'")></td></tr>';      
						     		}
					        
					        	}
					        $("#CollegeTeacher").append(s); 
					},
				    error:function(){
				    	alert("该学院没有老师！");
				    	}
				    });
			});
		
	});	
	
	</script>
<body>

<h3 align="center">学院管理</h3>
	<font color="red"><s:property  value="msg" /></font><br>
	<div align="right"><input type='button' value='返回主页' onclick="allfake()"></div>
	<table align="center"><tr><td><input type='button' value='添加学院' id="addxueyuan" ></td></tr></table>
	<table align="center" width="70%" height="100%">
		<tr>
			<td height="10px" align="center"></td>
			<td height="20px" align="center">学院名称</td>
			<td height="20px" align="center">操作</td>
		</tr>
		<c:forEach items="${college}"  var="xueyuan" >
		<tr>
		<td align="center"><input type="checkbox" name="checkList" value="${xueyuan.college}"></td>
		<td align="center">${xueyuan.name}</td>
		<td align="center">
		<input type='button' value='删除学院' onclick="DeleteCollege('${xueyuan.college}','${xueyuan.name}')">
		<input type='button' class="btn" value='学院任命' data-college="${xueyuan.college}" data-name="${xueyuan.name}" >
		</td> 
		</tr>
		</c:forEach>
	</table>
	
	<div id="CollegeInterface">
	<p align="right"><input type='button' id="exit" value='关闭窗口' ></p>
	<div align="center" >
	<h4 id="biaoti"></h4>
		<table id="CollegeTeacher" align="center" width="60%">
		</table>
	</div>
	</div>
	
	
	<div id="addcollege">
	<p align="right"><input type='button' id="exit2" value='关闭窗口' ></p>
	<div align="center">
	<h4>增加学院</h4>
	<form  action="/LeaveSystem/leave/teacher/school/AddCollege" method="get" >
	<table >
	<tr><td>学院名称</td><td><input type="text"  name="collegedb.name" placeholder="清输入学院名称！" ></td></tr>
	</table>
	<input type='submit' value='添加'>
	</form>
	</div>
	</div>
	<div id="fade" class="black_overlay"></div>

</body>
</html>