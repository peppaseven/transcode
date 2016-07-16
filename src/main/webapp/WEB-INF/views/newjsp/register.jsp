<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> 
<html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Register</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Register" />
	<meta name="keywords" content="Register" />
  <!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="${ctx}/static/favicon.png">
	<link rel="stylesheet" media="screen" href="${ctx}/static/css/register.css" />
	
	<link rel="stylesheet" media="screen" href="${ctx}/static/css/bootstrap.min.css" />
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="${ctx}/static/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="${ctx}/static/css/icomoon.css">
	<!-- Owl Carousel -->
	<link rel="stylesheet" href="${ctx}/static/css/owl.carousel.min.css">
	<link rel="stylesheet" href="${ctx}/static/css/owl.theme.default.min.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="${ctx}/static/css/magnific-popup.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
	<!-- Modernizr JS -->
	<script src="${ctx}/static/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="${ctx}/static/js/respond.min.js"></script>
	<![endif]-->	
	<!-- google oauth -->
	<script src="https://apis.google.com/js/api:client.js"></script>
	<script src="${ctx}/static/js/jquery.min.js"></script>
	<script src="${ctx}/static/js/jquery.easing.min.js"></script>
	<script src="${ctx}/static/js/bind.js"></script>
	<script src="${ctx}/static/js/util.js"></script>
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	</head>
	<body>
	<script src="${ctx}/static/js/facebooksdk.js"></script>
	<script src="${ctx}/static/js/googlesdk.js"></script>
	<script>

	function attachSignin(element) {
	    auth2.attachClickHandler(element, {},
	        function(user) {
	            var email = user.getBasicProfile().getEmail();
	            var imgurl = user.getBasicProfile().getImageUrl();
	            
	    		loginGoogle(email,imgurl);
	        }, function(error) {
	          alert(JSON.stringify(error, undefined, 2));
	        });
	  }
  
	function loginGoogle(email,imgurl) {
		 
		var data = {
		    		"email" : email
	    }
		$.ajax({
			type : "POST",
			url : "${ctx}/user/checkbind",
			data: data,
			success : function(r1)
			{
				//已经绑定
				if (r1.status == 1){
					window.location.href = "${ctx}";
				} else {
				//没有该账户跳转绑定界面,执行注册绑定步骤
					var params = {
						"email" : email,
	    		        "avatarurl" : imgurl,
	    		        "usertype" : 3
	      			}  
			      	post("${ctx}/user/registeroauth",params);
				}
			}
		});
		
	}
	
	function loginFacebook()
	{
		FB.getLoginStatus(function(response) {
			if (response && response.status !== 'connected') {
				FB.login(function(r1) {
					//登陆结果
		    		if(r1&& r1.status === 'connected') {
		    			//检测是否绑定该账户
		    			checkLoginInfo();
	    			}
	    		});
	        }
		});
	}
	
	function checkLoginInfo()
	{
		
		FB.api('/me' ,{fields: 'email,picture'}, function(response) {
		    var data = {
		    		"email" : response.email
		    }
			$.ajax({
					type : "POST",
					url : "${ctx}/user/checkbind",
					data: data,
					success : function(r1)
					{
						//已经绑定
						if (r1.status == 1){
							window.location.href = "${ctx}";
						} else {
							//没有该账户跳转绑定界面,执行注册绑定步骤
							var params = {
						    		  "email" : response.email,
						    		  "avatarurl" : response.picture.data.url
						      }
						    
					      	post("${ctx}/user/registeroauth",params);
						}
					}
				});
	    });
	}
	
	
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
	<header id="fh5co-header" role="banner">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header"> 
				<!-- Mobile Toggle Menu Button -->
				<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle" data-toggle="collapse" data-target="#fh5co-navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
				<a class="navbar-brand" href="${ctx }">Supinfo Video Converter</a>
				</div>
				<div id="fh5co-navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li ><a href="${ctx }"><span>Home <span class="border"></span></span></a></li>
						
						<li class="active"><a href="register"><span>Register<span class="border"></span></span></a></li>
						<li><a href="login"><span>Login<span class="border"></span></span></a></li>
						
						<li><a href="convert"><span>Convert <span class="border"></span></span></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	
	<!-- END .header -->

	<div class="register-speacer"></div>
			
			<div id="msform1" method="post" action="${ctx}/user/register"  onsubmit="return submitBinding()">
				<ul id="navbar">
					<li class="icon-bookmark">Create your account</li>
					<li class="icon-facebook"><a id="rfbbtn" href="javascript:loginFacebook()"  style="text-decoration: none;"><span>Register with facebook</span></a></li>
					<li class="icon-google"><a id="glbtn" href="javascript:void(0)" style="text-decoration: none;"><span>Register with google+</span></a></li>
				</ul>
				<!-- fieldsets -->
					<fieldset>
					
						<div class="alert alert-warning" style = "display: none;" id="registerMessage">
						<a href="#" class="close" data-dismiss="alert"> &times; </a> <strong><span id="hint"></span></strong><span id="hintcontent"></span>
						</div>
				
						<h2 class="fs-title">Create your account</h2>
						<h3 class="fs-subtitle"></h3>
						<input id="bind_email" type="text" name="email" placeholder="Email" />
						<input id="bind_password" type="password" name="pass" placeholder="Password" />
						<input id="bind_username" id="submit" type="text" name="username" placeholder="Username" />
						<input type="submit" id="submit" class="next action-button" value="Register">
					</fieldset>
				</div>
						
			
			<script>
			var validateDomain = "^.{6,24}$";
			$("#submit").on("click",function() {
				console.log("hey");
				var bindEmail = $("#bind_email");
				var pass = $("#bind_password");
				var username = $("#bind_username");
			
				
				//邮箱或密码为空,邮箱格式不正确
				if(($.trim(bindEmail.val()) == "" || $.trim(bindEmail.val()).isEmail() == false) || $.trim(pass.val()) == ""|| $.trim(username.val())== ""){
					if($.trim(bindEmail.val()) == "" || $.trim(bindEmail.val()).isEmail() == false){
						bindEmail.focus();
						bindEmail.errorTip("please enter right email");
						return ;
					}
					if($.trim(pass.val()) == ""){
						pass.focus();
						pass.errorTip("please enter password");
						return ;
					}
					if($.trim(username.val())== "")
					{
						username.focus();
						username.errorTip("please enter username");
						return ;
					}
				}else{
					if($.trim(bindEmail.val()) != "" && $.trim(bindEmail.val()).isEmail() && $.trim(pass.val()) != "" && $.trim(username.val())!=""){
						$.closeErrorTip();
						bindEmail.removeClass("error");
						pass.removeClass("error");
						//检查密码长度
						if(!$.trim(pass.val()).match(validateDomain)){
							pass.focus();
							pass.errorTip("password must be 6-24 length");
							return ;
						}
						
						//普通注册
						var data = {
								"email" : bindEmail.val(),
								"pass" : pass.val(),
								"username" : username.val(),
						}
						$.ajax({
							type : "POST",
							url : "${ctx}/user/register",
							data: data,
							success : function(response)
							{
								console.log(response)
								//登陆成功
								if(response.status == 1)
								{
									$("#registerMessage").attr("class","alert alert-success");
									$("#hint").text("success! ");
									$("#hintcontent").text("register success! Redirecting to loginPage...");
									$("#registerMessage").show();
									
									//1秒后跳转主页
									setTimeout( function()
									{
										window.location.href = "${ctx}";
									}, 1000 );
									
								}else {
									$("#registerMessage").attr("class","alert alert-danger");
									$("#hint").text("error! ");
									$("#hintcontent").text(response.message);
									$("#registerMessage").show();
									
								}
							}
						});
					}else{
						return ;
					}
				}
			})
			
			
			
				
			
			</script>
	</body>
</html>
