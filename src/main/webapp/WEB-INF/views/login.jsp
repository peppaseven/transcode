<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>    
<html>
<head>
<title>login simple(token)</title>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
	标记<input name="identifier" id="name"><br>
	密码<input name="password" id="pass"><br>
	<button id= "login">OK</button>
	<button id="showvlist">show videoList</button>
	
	<script>
	
		$("#login").on("click",function()
		{
			var name = $("#name").val();
			var pass = $("#pass").val();
			
			var data = {
					"identifier" : name,
					"password" : pass
			};
			
			
			$.ajax({
				type : "POST",
				url : "${ctx}/user/login",
				data : data,
				success : function(res)
				{
					window.localStorage.setItem("tr_token",res.data.token);
					console.log(res.data.token);
				}
				
			})
		});
		
		$("#showvlist").on("click",function()
		{
			var tokenHeader = window.localStorage.getItem("tr_token");
			$.ajax({
				type : "GET",
				url : "${ctx}/video/list",
				headers : {"tr_token" : tokenHeader}
			})
		})
	</script>
</body>
</html>