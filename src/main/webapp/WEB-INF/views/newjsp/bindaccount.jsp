<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bind Account | Supinfo video Converter</title>
<link rel="shortcut icon" href="${ctx}/static/favicon.png">
<style type="text/css">body{font-family:'Microsoft Yahei', 'Helvetica Neue', Helvetica, Arial, sans-serif;}</style>
<link rel="stylesheet" href="${ctx}/static/css/style.css">
<link rel="stylesheet" media="screen" href="${ctx}/static/css/register.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/iconfont.css">
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/bind.css">
<script src="${ctx}/static/js/ga.js" async="" type="text/javascript"></script>
	
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/jquery.easing.min.js"></script>
<script src="${ctx}/static/js/bind.js"></script>
<script src="${ctx}/static/js/util.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<style>
.binderror {
	color:red ;
	display : none
}
.bindsuccess {
	color:green ;
	display : none
}


</style>
</head>
<body>
	<header id="fh5co-header" role="banner">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header"> 
				<!-- Mobile Toggle Menu Button -->
				<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle" data-toggle="collapse" data-target="#fh5co-navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
				<a class="navbar-brand" href="${ctx }">Supinfo Video Converter</a>
				</div>
				<div id="fh5co-navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="${ctx }"><span>Home <span class="border"></span></span></a></li>
						
						<li><a href="register"><span>Register<span class="border"></span></span></a></li>
						<li><a href="login"><span>Login<span class="border"></span></span></a></li>
						
						<li><a href="convert"><span>Convert <span class="border"></span></span></a></li>
						<c:if test="${!empty sessionScope.user}">
							<li class="dropdown">
					            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					               ${sessionScope.user.username }
					               <b class="caret"></b>
					            </a>
					            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
					               <li><a href="profile">profile</a></li>
					               <li><a href="videos">uploaded videos</a></li>
					               <li><a href="videos">translated videos</a></li>
					               <li class="divider"></li>
					               <li><a href="logout">logout</a></li>
					            </ul>
         					</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- END .header -->
<div class="bind-bg"></div>
<div class="clear"></div>
<div class="bind-main-center">
<div class="signin-bind-tip">
<div class="tit">Please complete your register</div>
<div class="des">This is the first time you login with facebook or google,please set your username and password.</div>
<div class="des" id="message"></div>

</div>
<div class="signin-bind-container">

<div action="${ctx }/user/register" method="post" id="signin_bind_form" onsubmit="return submitBinding()">
	<div class="bind_item" style="margin-top:25px;">
	<input id="bind_email" class="signin-txt" name="email"  placeholder="email" type="text" readonly="readonly" value="${email }">
	</div>
	<div class="bind_item" style="margin-top:25px;">
	<input id="bind_username" class="signin-txt" name="username"  placeholder="username" type="text" >
	</div>
	<div class="bind_item" style="margin-top:20px;">
	<input id="bind_password" class="signin-txt" name="pass"  placeholder="password" type="password">
	</div>
	
	<input type="hidden" id="usertype" name="usertype" value="${userType }">
	<input type="hidden" id="avatar" name="avatar" value="${avatarUrl }">
	<div id="bind_submit_div" style="margin-top:20px;">
	<input id="bind_submit" class="bind-submit" name="submit" value="submit" type="submit">
	</div>
</div>
</div>
<div class="singin-bind-arrow">
<img src="${ctx}/static/images/double_arrow.png">
</div>
<div class="default_photo">
<img id="bind_photo" src="${avatarUrl}">

</div>
</div>
<script type="text/javascript">
var validateDomain = "^.{6,24}$";

	$("#bind_submit").on("click",function(){
		var bindEmail = $("#bind_email").val();
		var bindUsername = $("#bind_username");
		var pass = $("#bind_password");
		var usertype = $("#usertype").val();
		var avatar = $("#avatar").val();
		
		if(($.trim(bindUsername.val()) == "" || $.trim(pass.val()) == "")) {
			if($.trim(bindUsername.val()) == ""){
				bindUsername.focus();
				bindUsername.errorTip("please enter username");
				return ;
			}
			if($.trim(pass.val()) == ""){
				pass.focus();
				pass.errorTip("please enter password");
				return ;
			}
		}else{
			if($.trim(bindUsername.val()) != "" && $.trim(pass.val()) != ""){
				$.closeErrorTip();
				bindUsername.removeClass("error");
				pass.removeClass("error");
				if(!$.trim(pass.val()).match(validateDomain)){
					pass.focus();
					pass.errorTip("password must be 6-24 length");
					return ;
				}
				
				
				//check success
				var data = {
						"email" : bindEmail,
						"pass" : pass.val(),
						"username" : bindUsername.val(),
						"usertype" : usertype,
						"avatar" : avatar	
				}
				$.ajax({
					type : "POST",
					url : "${ctx}/user/register",
					data: data,
					success : function(response)
					{
						//登陆成功
						if(response.status == 1)
						{
							$("#message").attr("class","des bindsuccess");
							$("#message").text("bind success! Redirecting to loginPage...");
							$("#message").show();
							
							//1秒后跳转主页
							setTimeout( function()
							{
								window.location.href = "${ctx}";
							}, 1000 );
							
						}else {
							$("#message").attr("class","des binderror");
							$("#message").text(response.message);
							$("#message").show();
							
						}
					}
				});
				
			}else{
				return ;
			}
		}
	})

	function post(path, params, method) {
	    method = method || "post";

	    var form = document.createElement("form");
	    form.setAttribute("method", method);
	    form.setAttribute("action", path);

	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);

	            form.appendChild(hiddenField);
	         }
	    }

	    document.body.appendChild(form);
	    form.submit();
	}
		
	
</script>


</body></html>