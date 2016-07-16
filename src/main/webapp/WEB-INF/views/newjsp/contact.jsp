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
	<title>Supinfo Video Converter</title>
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
						<li><a href="videos"><span>Video Show <span class="border"></span></span></a></li>
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

	<footer id="fh5co-footer">
		
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-sm-10">
					<div class="fh5co-footer-widget">
						<h2 class="fh5co-footer-logo">Supinfo Video Converter</h2>
						<p>"âFree, fast, converts to and from almost any video content known to man and burns DVDs and Blu-Ray. Freemake Video Converter is highly recommended, and only gets better with each new versionâ.<br/>
						"âWith all video converting apps out there, it can get a bit overwhelming. Freemake combines almost everything you need into a clean, simple, one-stop-shop for all your video converting, editing, and burning needsâ<br/></p>
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


	<!-- jQuery -->
	<script src="${ctx}/static/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${ctx}/static/js/jquery.easing.1.3.js"></script>
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

	
	</body>
</html>
