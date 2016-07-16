<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Supinfo Video Converter </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Supinfo video converter" />
	<meta name="keywords" content="Supinfo video converter" />
	<meta name="author" content="XU Tongkun" />
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

	<link rel="shortcut icon" href="${ctx}/static/favicon.png">
	<link rel="stylesheet" media="screen" href="${ctx}/static/css/register.css" />
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="${ctx}/static/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="${ctx}/static/css/icomoon.css">
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
	
	<!-- jQuery -->
	<script src="${ctx}/static/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${ctx}/static/js/jquery.easing.min.js"></script>
	<!-- Bootstrap -->
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<!-- Owl carousel -->
	<script src="${ctx}/static/js/owl.carousel.min.js"></script>
	<!-- Waypoints -->
	<script src="${ctx}/static/js/jquery.waypoints.min.js"></script>
	<!-- Magnific Popup -->
	<script src="${ctx}/static/js/jquery.magnific-popup.min.js"></script>
	<!-- Main JS -->
	<script src="${ctx}/static/js/main.js"></script>


	<script src="${ctx}/static/js/bind.js"></script>
	<script src="${ctx}/static/js/util.js"></script>
	</head>
	<body>
		<script src="${ctx}/static/js/facebooksdk.js"></script>
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
						<c:if test="${empty sessionScope.user}">
							<li><a href="register"><span>Register<span class="border"></span></span></a></li>
							<li><a href="login"><span>Login<span class="border"></span></span></a></li>
						</c:if>
						
						
						
						<li><a href="convert"><span>Convert <span class="border"></span></span></a></li>
						<c:if test="${!empty sessionScope.user}">
							<li class="dropdown">
					            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					               ${sessionScope.user.username }
					               <b class="caret"></b>
					            </a>
					            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
					               <li class="active"><a href="profile">profile</a></li>
					               <li><a href="videos">videos</a></li>
					               <li class="divider"></li>
					               <li><a href="javascript:userlogout()">logout</a></li>
					            </ul>
         					</li>
         					<li>
         					    <a href="profile"><img class="userphoto" src="${ctx}/static/useravatar/${sessionScope.user.avatar}"></a>
         					</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- END .header -->
	<aside class="fh5co-page-heading">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="fh5co-page-heading-lead icon-book">
						${sessionScope.user.username } Profile
						<span class="fh5co-border"></span>
					</h1>
					
				</div>
			</div>
		</div>
	</aside>
	
	<div id="fh5co-main">
		
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-push-3">
					<div class="row">
						<div class="col-md-3">
							<p><img src="${ctx}/static/useravatar/${sessionScope.user.avatar}" class="img-responsive img-rounded" id="userimg"></p>
						</div>
						<div class="col-md-9">
						   	<input id="lefile" type="file" style="display:none">
						    <div class="input-group">
								<div class="row">
									<div class="col-md-12">
										<input placeholder="Upload a file in your PC" id="filename"  class="form-control input-lg" type="hidden" value="">
										<span></span><a class="btn btn-primary" style="margin-top:12px;" onclick="$('input[id=lefile]').click();" >Change Avatar</a></span>
									</div>
							    </div>						    
						    </div>
						    <div class="row">
								<div>
									<div class="col-md-11">
										<div class="form-group">
											<label for="name" class="sr-only">Email</label>
											<input placeholder="Email" id="email" type="text" class="form-control input-lg" readonly="readonly" value="${sessionScope.user.email }">
										</div>	
									</div>
									<div class="col-md-11">
										<div class="form-group">
											<label for="email" class="sr-only">Username</label>
											<input placeholder="Username" id="username" type="text" class="form-control input-lg"  value="${sessionScope.user.username }">
										</div>	
									</div>
									<div class="col-md-11">
									<a href="javascript:togglePassInput()" style="text-decoration: none; font-size:20px;">
									<span class="icon-sm icon-edit"></span>
									Change Password
									</a>
									</div>
									<div class="col-md-11" style="display :none" id="passwordInput">
										<div class="form-group">
											<label for="email" class="sr-only">password</label>
											<input placeholder="new password" id="password" type="password" class="form-control input-lg" >
										</div>	
									</div>
									<div class="col-md-11" style="display :none" id="confirmPasswordInput">
										<div class="form-group">
											<label for="email" class="sr-only">password again</label> 
											<input placeholder="new password again" id="confirmpassword" type="password" class="form-control input-lg">
										</div>	
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input type="submit" class="btn btn-primary" id="save" value="Save" >
											<input type="reset" class="btn btn-outline" id="reset" value="Reset" >
										</div>	
									</div>
									
									<input type="hidden" id="userId" value="${sessionScope.user.userId }"> 
								</div>	
							</div>	
						</div>
					</div>		
				</div>	
			</div>
		</div>
	</div>
	
	<div class="fh5co-spacer fh5co-spacer-sm"></div>

		<footer id="fh5co-footer">
		
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-sm-10">
					<div class="fh5co-footer-widget">
						<h2 class="fh5co-footer-logo">Supinfo Video Converter</h2>
						<p>"âFree, fast, converts to and from almost any video content known to man and burns DVDs and Blu-Ray. Freemake Video Converter is highly recommended, and only gets better with each new versionâ.<br/>
						"âWith all video converting apps out there, it can get a bit overwhelming. Freemake combines almost everything you need into a clean, simple, one-stop-shop for all your video converting, editing, and burning needsâ<br/></p>
						<h3>If you like this please share with your friends. Come On!</h3>
					</div>
					<div class="fh5co-footer-widget">
						<ul class="fh5co-social">
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-twitter"></i></a></li>
							<li><a href="#"><i class="icon-instagram"></i></a></li>
							<li><a href="#"><i class="icon-linkedin"></i></a></li>
							<li><a href="#"><i class="icon-youtube"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-2 col-sm-2">
					<div class="fh5co-footer-widget top-level">
						<h4 class="fh5co-footer-lead ">Company</h4>
						<ul class="fh5co-list-check">
							<li><a href="http://www.supinfo.com/">About</a></li>
							<li><a href="http://supinfo.com/fr/contact.aspx">Contact</a></li>
							<li><a href="http://www.supinfo.com/Cursus/">News</a></li>
							<li><a href="http://supinfo.com/articles/">Support</a></li>
							
						</ul>
					</div>
				</div>
				
			</div>

			<div class="row fh5co-row-padded fh5co-copyright">
				<div class="col-md-12">
					<p><small>&copy; Supinfo Video Converter. All Rights Reserved.
					<br></a>SUPINFO International University<br/>
						Ecole d'Informatique - IT School<br/>
						Ãcole SupÃ©rieure d'Informatique de Paris, leader en France La Grande Ecole de l'informatique, du numÃ©rique et du management	FondÃ©e en 1965, reconnue par l'Ãtat. Titre Bac+5 certifiÃ© au niveau I. SUPINFO International University is globally operated by EDUCINVEST Belgium - Avenue Louise, 534 - 1000 Brussels</small></p>
				</div>
			</div>
		</div>

	</footer>


	
	
	
	<script>
	
	
		function readURL(input) {
	
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
	
		        reader.onload = function (e) {
		            $('#userimg').attr('src', e.target.result);
		        }
	
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		
	
		$("#lefile").on("change",function()
		{
			$("#filename").val("select");
			console.log($("#lefile").get(0).files[0]);
			readURL(this);
		});
		
		function togglePassInput()
		{
			$("#passwordInput").toggle();
			$("#confirmPasswordInput").toggle();
		}
		
		
		$("#reset").on("click",function()
		{
			window.location.href = "${ctx}/profile";
		});
		
		
		
		$("#save").on("click",function()
		{
			console.log("save ");
			
			$.closeErrorTip();
			
			var validateDomain = "^.{6,24}$";
			
			if($.trim($("#username").val()) == "")
			{
				$("#username").focus();
				$("#username").errorTip("username cannot be null!");
				return false;
			}else if($.trim($("#password").val())!="" && !$.trim($("#password").val()).match(validateDomain))
			{
				$("#password").focus();
				$("#password").errorTip("password must be 6-24 length");
				return false;
			}
			else if($.trim($("#password").val())!=$.trim($("#confirmpassword").val()))
			{
				$("#confirmpassword").focus();
				$("#confirmpassword").errorTip("two password are not same");
				return false;
			}else
			{
				var fd = new FormData();
				
				if($("#filename").val() != "")
				{
					if($("#lefile").get(0).files[0].name.match(/\.(jpg|jpeg|png|gif)$/))
					{
						//is an image
						fd.append("avatar",$("#lefile").get(0).files[0])
					}
				}
				
				fd.append("username",$("#username").val());
				if($("#password").val() != "")
				{
					fd.append("password",$("#password").val());
				}
				
				
				$.ajax({
					type : "POST",
					url : "${ctx}/user/update", //update url
					processData: false,
					contentType: false,
					data: fd,
					success : function(response)
					{
						if(response.status == 1)
						{
							//成功
							window.location.href = "${ctx}/profile";
						}
					}
				});
			}
			
			
					
		});
		
		
		//用户登出方法
		function userlogout()
		{
			$.ajax({
				type : "GET",
				url : "${ctx}/user/logout", //update url
				success : function(response)
				{
					if(response.status == 1)
					{
						if(response.status == 1) {
							window.location.href = "${ctx}";
						} else if(response.data == 2) {
							//登出facebook
							FB.logout(function(response) {
								window.location.href = "${ctx}";
							});	
						} else if(response.data == 3) {
							//登出google
							var auth2 = gapi.auth2.getAuthInstance();
						    auth2.signOut().then(function () {
						    	window.location.href = "${ctx}";
						    });
						}
									
					}
				}
			});
		}
		
	
	</script>
	</body>
</html>
