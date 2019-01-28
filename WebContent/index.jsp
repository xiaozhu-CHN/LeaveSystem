<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>请假管理系统</title>
<style>
.footer{ position: absolute;
    height: 50px;
    text-align: center;
    line-height: 50px;
    width: 100%;}
</style>
<link rel="stylesheet" href="/LeaveSystem/css/reset.css" />
		<link rel="stylesheet" href="/LeaveSystem/css/common.css" />
		<link rel="stylesheet" href="/LeaveSystem/css/font-awesome.min.css" />

</head>
<body>
<div class="wrap login_wrap">
			<div class="content">
				<div class="logo"></div>
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							请假管理系统
						</div>
<font color="red"><s:property  value="msg" /></font><br>
<div class="form_btn">
								<button type="button" onclick="javascript:window.location.href='/LeaveSystem/student/login.jsp'">学生系统</button>
							</div>
<div class="form_btn">
								<button type="button" onclick="javascript:window.location.href='/LeaveSystem/teacher/login.jsp'">教师系统</button>
							</div>
							<div class="form_reg_btn">
								<span>世界那么大，我想去看看！</span><a href="register.html"> </a>
							</div>
							
							
					</div>
					</div>
              </div>
              <div class="footer"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274141539'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274141539%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script></div>
</div>


</body>
</html>