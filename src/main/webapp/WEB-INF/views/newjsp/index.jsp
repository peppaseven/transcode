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
	
	<!-- google oauth -->
	<script src="https://apis.google.com/js/api:client.js"></script>
	
	<!-- jQuery -->
	<script src="${ctx}/static/js/jquery.min.js"></script>
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<link rel="stylesheet" media="screen" href="${ctx}/static/css/bootstrap.min.css" />
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
	
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" media="screen" href="${ctx}/static/css/bootstrap.min.css" />
	
	</head>
	<body>
		<script src="${ctx}/static/js/facebooksdk.js"></script>
		<script src="${ctx}/static/js/googleloadsdk.js"></script>
	
	<header id="fh5co-header" role="banner">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="height:62px;">
			<div class="container-fluid">
				<div class="navbar-header"> 
				<!-- Mobile Toggle Menu Button -->
				<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle" data-toggle="collapse" data-target="#fh5co-navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
				<a class="navbar-brand" href="${ctx }" style="margin-top:5px;">Supinfo Video Converter</a>
				</div>
				<div id="fh5co-navbar" class="navbar-collapse collapse" style="margin-top:4px;">
					<ul class="nav navbar-nav navbar-right" >
						<li class="active"><a href="${ctx }"><span>Home <span class="border"></span></span></a></li>
						
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
	<div class="copyrights">Supinfo<a href="http://www.supinfo.com/" >Transcode big project</a></div>
	<div class="fh5co-slider">
		<div class="owl-carousel owl-carousel-fullwidth">
		    <div class="item" style="background-image:url(${ctx}/static/images/slide-1.jpg)">
		    	<div class="fh5co-overlay"></div>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-md-8 col-md-offset-2">
			    			<div class="fh5co-owl-text-wrap">
						    	<div class="fh5co-owl-text text-center to-animate">
						    		<h1 class="fh5co-lead">Convert</h1>
									<h2 class="fh5co-sub-lead"> Transcode is a online media conversion application, which allows you to reocord, convert and download nearly any audio or video URL to common formats. </h2>
						    	</div>
						    </div>
					    </div>
		    		</div>
		    	</div>
		    </div>
		    <div class="item" style="background-image:url(${ctx}/static/images/slide-3.jpg)">
		    	<div class="fh5co-overlay"></div>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-md-8 col-md-offset-2">
			    			<div class="fh5co-owl-text-wrap">
						    	<div class="fh5co-owl-text text-center to-animate">
						    		<h1 class="fh5co-lead">Convert Between 9 Formats</h1>
									<h2 class="fh5co-sub-lead">Converter supports all popular and rare formats: MP4, AVI, MKV, WMV, MOV, 3GP, FLV, MPG, VOB etc. </a></h2>
						    	</div>
						    </div>
					    </div>
		    		</div>
		    	</div>
		    </div>
		    <div class="item" style="background-image:url(${ctx}/static/images/slide_3.jpg)">
		    	<div class="fh5co-overlay"></div>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-md-8 col-md-offset-2">
			    			<div class="fh5co-owl-text-wrap">
						    	<div class="fh5co-owl-text text-center to-animate">
						    		<h1 class="fh5co-lead">Just three steps</h1>
									<h2 class="fh5co-sub-lead">The website allow users to submit transcoding
tasks, either by uploading content or providing a URL that directly points to the content.</a></h2>
						    	</div>
						    </div>
					    </div>
		    		</div>
		    	</div>
		    </div>
		    <div class="item" style="background-image:url(${ctx}/static/images/slide-2.jpg)">
		    	<div class="fh5co-overlay"></div>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-md-8 col-md-offset-2">
			    			<div class="fh5co-owl-text-wrap">
						    	<div class="fh5co-owl-text text-center to-animate">
						    		<h1 class="fh5co-lead">Prices</h1>
									<h2 class="fh5co-sub-lead">The base price is 1 EUR to process 1 hour of video/sound.</a></h2>
						    	</div>
						    </div>
					    </div>
		    		</div>
		    	</div>
		    </div>
		</div>
	</div>	
	<div id="fh5co-main">
		<!-- Features -->

		<div id="fh5co-features">
			<div class="container">
				<div class="row text-center">
					<div class="col-md-8 col-md-offset-2">
						<h2 class="fh5co-section-lead">Key Features</h2>
						<h3 class="fh5co-section-sub-lead">Supinfo Video Converter supports all popular and rare formats:  MP4, AVI, MKV, WMV, MOV, 3GP, FLV, MPG, VOB etc. Import photos or audio files to turn multimedia to video. Convert multiple videos at once. The supporting architecture is reliable and efficient.</h3>
					</div>
					<div class="fh5co-spacer fh5co-spacer-md"></div>
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6 fh5co-feature-border">
						<div class="fh5co-feature">
							<div class="fh5co-feature-icon to-animate">
								<i class="icon-paypal"></i>
							</div>
							<div class="fh5co-feature-text">
								<h3>payment</h3>
								<p>PayPal is the faster, safer way to send money, make an online payment, receive money or set up a merchant account.</p>
								<p><a href="https://www.paypal.com/c2/webapps/mpp/home">Read more</a></p>
							</div>
						</div>
						<div class="fh5co-feature no-border">
							<div class="fh5co-feature-icon to-animate">
								<i class="icon-head"></i>
							</div>
							<div class="fh5co-feature-text">
								<h3>User Satisfaction</h3>
								<p>Supinfo Video Converter is the BEST VIDEO CONVERTER. Why?Tests by 93 Million Users prove Freemake to be the fastest video converter among others.
								</p>
								<p><a href="#">Read more</a></p>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6">
						<div class="fh5co-feature">
							<div class="fh5co-feature-icon to-animate">
								<i class="icon-microphone"></i>
							</div>
							<div class="fh5co-feature-text">
								<h3>Dumb-Easy</h3>
								<p>Supinfo Video Converter simplicity is stunning. Any video task requires 3 clicks maximum.</p>
								<p><a href="#">Read more</a></p>
							</div>
						</div>
						<div class="fh5co-feature no-border">
							<div class="fh5co-feature-icon to-animate">
								<i class="icon-clock2"></i>
							</div>
							<div class="fh5co-feature-text">
								<h3>7X24 Guaranteed</h3>
								<p>Your files will be permanently deleted form our server after one hour.No one has access to your files and privacy is 100% guranteed.</p>
								<p><a href="#">Read more</a></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Features -->


		<div class="fh5co-spacer fh5co-spacer-lg"></div>		
		<!-- Products -->
		<div class="container" id="fh5co-products">
			<div class="row text-left">
				<div class="col-md-6">
					<img src="${ctx}/static/images/converter.jpg"></h3>
				</div>
				<div class="col-md-6"> 
					<h2 class="fh5co-section-lead">Convert Between 500+ Formats</h2>
					<h2 class="fh5co-section-sub-lead">Freemake Video Converter supports all popular and rare formats: MP4, AVI, MKV, WMV, MP3, DVD, 3GP, SWF, FLV, HD, MOV, RM, QT, Divx, Xvid, TS, MTS, Fraps, etc. Import photos or audio files to turn multimedia to video. Convert multiple videos at once. All modern codecs are included: H.264, MKV, MPEG4, AAC.</h2>
				</div>
					
				<div class="fh5co-spacer fh5co-spacer-md"></div>
			</div>
		</div>
		<!-- Products -->
		<div class="fh5co-spacer fh5co-spacer-lg"></div>
				<div class="fh5co-bg-section" style="background-image: url(${ctx}/static/images/slide_1.jpg); background-attachment: fixed;">
			<div class="fh5co-overlay"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<div class="fh5co-hero-wrap">
							<div class="fh5co-hero-intro text-center">
								<h1 class="fh5co-lead"><span class="quo">&ldquo;</span>Look how Supinfo Converter is easy <span class="quo">&rdquo;</span></h1>
								<p class="author">&mdash; <cite>supinfo</cite></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="fh5co-clients">
			<div class="container">
				<div class="row">
					<div class="col-md-4 fh5co-client-logo text-center to-animate">
						<div class="row">
							<img src="${ctx}/static/images/num1.png"  class="col-md-3 img-responsive">
							<img src="${ctx}/static/images/step1.png"  class="col-md-9 img-responsive" style="padding-top:30px;">
						</div>
					</div>
					<div class="col-md-4 fh5co-client-logo text-center to-animate">
						<div class="row">
							<img src="${ctx}/static/images/num2.png"  class="col-md-3 img-responsive">
							<img src="${ctx}/static/images/step2.png"  class="col-md-9 img-responsive" style="padding-top:30px;">
						</div>
					</div>
					<div class="col-md-4 fh5co-client-logo text-center to-animate">
						<div class="row">
							<img src="${ctx}/static/images/num3.png"  class="col-md-4 img-responsive">
							<img src="${ctx}/static/images/step3.png"  class="col-md-8 img-responsive" style="display:inline-block; vertical-align:middle;padding-top:44px;">
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>

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
					<p><small>&copy; Supinfo<a href="http://www.supinfo.com/" >Transcode big project</a> Video Converter. All Rights Reserved.
					<br></a>SUPINFO International University<br/>
						Ecole d'Informatique - IT School<br/>
						Ãcole SupÃ©rieure d'Informatique de Paris, leader en France La Grande Ecole de l'informatique, du numÃ©rique et du management	FondÃ©e en 1965, reconnue par l'Ãtat. Titre Bac+5 certifiÃ© au niveau I. SUPINFO International University is globally operated by EDUCINVEST Belgium - Avenue Louise, 534 - 1000 Brussels</small></p>
				</div>
			</div>
		</div>

	</footer>
	<script>
	
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
					var userType = parseInt(response.data);
					if(userType == 1) {
						window.location.href = "${ctx}";
					} else if(userType == 2) {
						FB.getLoginStatus(function(r1) {
					        if (r1 && r1.status === 'connected') {
					            FB.logout(function(r2) {
					            	window.location.href = "${ctx}";
					            });
					        }
					    });
					} else if(userType == 3) {
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
