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
	<title>Convert</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="pay success" content="success page for payment" />
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

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="${ctx}/static/favicon.png">
	<link rel="stylesheet" media="screen" href="${ctx}/static/css/register.css" />

	<script src="${ctx}/static/js/jquery.min.js"></script>
	<!-- spinner -->
	<link rel="stylesheet" href="${ctx}/static/css/bootstrap-spinner.css">
    <script src="${ctx}/static/js/jquery.spinner.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<!-- Bootstrap -->
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="${ctx}/static/css/icomoon.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="${ctx}/static/css/magnific-popup.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
	<!-- google oauth -->
	<script src="https://apis.google.com/js/api:client.js"></script>
	
	<!-- Modernizr JS -->
	<script src="${ctx}/static/js/modernizr-2.6.2.min.js"></script>
	<script src="${ctx}/static/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${ctx}/static/js/jquery.easing.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="${ctx}/static/js/respond.min.js"></script>
	<![endif]-->
	<!-- æä»¶ -->
	
	<script src="${ctx}/static/js/icheck.min.js?v=1.0.2"></script>
	<script src="${ctx}/static/js/custom.min.js?v=1.0.2"></script>
	
	<script src="${ctx}/static/js/bind.js"></script>
	<script src="${ctx}/static/js/util.js"></script>
	
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
						<li ><a href="${ctx }"><span>Home <span class="border"></span></span></a></li>
						<c:if test="${empty sessionScope.user}">
							<li><a href="register"><span>Register<span class="border"></span></span></a></li>
							<li><a href="login"><span>Login<span class="border"></span></span></a></li>
						</c:if>
						
						<c:if test="${empty sessionScope.user}">
							<li><a href="register"><span>Register<span class="border"></span></span></a></li>
							<li><a href="login"><span>Login<span class="border"></span></span></a></li>
						</c:if>
						
						<li class="active"><a href="convert"><span>Convert <span class="border"></span></span></a></li>
						<c:if test="${!empty sessionScope.user}">
							<li class="dropdown">
					            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					               ${sessionScope.user.username }
					               <b class="caret"></b>
					            </a>
					            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
					               <li><a href="profile">profile</a></li>
					               <li><a href="videos">videos</a></li>
					               <li class="divider"></li>
					               <li><a href="javascript:userlogout()">logout</a></li>
					            </ul>
         					</li>
         					<li>
         					    <c:if test="${sessionScope.user.avatarType == 1}">
         						
         					    	<a href="profile"><img class="userphoto" src="${ctx}/static/useravatar/${sessionScope.user.avatar}"></a>
         						</c:if>
         						<c:if test="${sessionScope.user.avatarType == 2}">
         							<a href="profile"><img class="userphoto" src="${sessionScope.user.avatar}"></a>
         						</c:if>
         					</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- END .header -->

	<div class="register-speacer"></div>
			
			<form id="msform2">
				<!-- fieldsets -->
				<fieldset>
                        <h1 style="font-size:40px; color:#008000;">Pay Success!</h1>
				        <img src="${ctx }/static/images/check.png" style=" width:250px;"> 
				</fieldset>
			</form>
						
		
	</body>
</html>
