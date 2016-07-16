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
	<meta name="description" content="Supinfo video converter" />
	<meta name="keywords" content="Supinfo video converter" />
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

	<!-- Animate.css -->
	<link rel="stylesheet" href="${ctx}/static/css/animate.css">
	<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
	<script src="${ctx}/static/js/jquery.min.js"></script>
	<!-- iCheck-->
    <link href="${ctx}/static/css/minimal/yellow.css" rel="stylesheet">
	<!-- spinner -->
	<link rel="stylesheet" href="${ctx}/static/css/bootstrap-spinner.css">
    <script src="${ctx}/static/js/jquery.spinner.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
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
	<!-- Bootstrap -->
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${ctx}/static/js/jquery.easing.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="${ctx}/static/js/respond.min.js"></script>
	<![endif]-->

	<!-- 插件 -->
	
	<script src="${ctx}/static/js/icheck.min.js?v=1.0.2"></script>
	<script src="${ctx}/static/js/custom.min.js?v=1.0.2"></script>
	
	<script src="${ctx}/static/js/bind.js"></script>
	<script src="${ctx}/static/js/util.js"></script>
	
	</head>
	<body>
	<script src="${ctx}/static/js/facebooksdk.js"></script>
	<script src="${ctx}/static/js/googleloadsdk.js"></script>
	
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
	
	<div class="register-speacer"></div>
		<div id="msform" style="margin-top:45px;">
				<!-- progressbar -->
				<ul id="progressbar" >
					<li class="active">Choose File</li>
					<li>Convert Setting</li>
					<li>Submit</li>
				</ul>
				<!-- fieldsets -->
					<fieldset>
						<h2 class="fs-title">Put URL of the resources <br/> or Upload a file in your PC</h2>
						<h3 class="fs-subtitle"></h3>
						<input type="text" name="URL" id="netResource" placeholder="URL of the resources" />
						<input id="lefile" type="file" style="display:none">
						<div class="input-group">
							<div class="row">
							<div class="col-md-10">
								<input id="file" class="input" type="text" placeholder="Upload a file in your PC">
							</div>
							<div class="col-md-2">
								<span></span><a class="btn upload-button" onclick="$('input[id=lefile]').click();" >Browse</a></span>
							</div>
						</div>
							
						</div>
						<input type="button" id="step1Next" class="next action-button" value="Next" />
					</fieldset>
					<fieldset>
						<h2 class="fs-title">convert settings</h2>
						<h3 class="fs-subtitle"></h3>
						<div class="dropdown">
							<button class="btn btn-primary btn-lg dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
								<span id="formatChoosen" type="0">choose formate</span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu text-right" role="menu" aria-labelledby="dropdownMenu1" id="formatsdropdown" style="margin-left:100px; margin-top:-20px;width:248px;">
								<li role="presentation" class="dropdown-header">choose formate</li>	
							</ul> 
					    </div>
<!-- 					    <div class="advance"> -->
<!-- 							<button class="btn btn-danger btn-lg abtn" data-toggle="modal"  -->
<!-- 							data-target="#myModal"> -->
<!-- 							Advance Seting -->
<!-- 							</button> -->
<!-- 							<div class="modal fade col-md-6 col-md-offset-3" id="myModal" tabindex="-1" role="dialog"  -->
<!-- 							aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:180px;"> -->
<!-- 								<div class="modal-content" > -->
<!-- 									<div class="modal-header"> -->
<!-- 										<button type="button" class="close"  -->
<!-- 										data-dismiss="modal" aria-hidden="true"> -->
<!-- 										&times; -->
<!-- 										</button> -->
<!-- 										<h4 class="modal-title" id="myModalLabel"> -->
<!-- 										set the video size -->
<!-- 										</h4> -->
<!-- 									</div> -->
<!-- 									<div class="modal-body"> -->
<!-- 								    <div class="size row"> -->
<!-- 							        <ul class="list"> -->
<!-- 					                <li> -->
<!-- 					                  <input tabindex="1" type="radio" id="minimal-radio-1" name="minimal-radio" checked> -->
<!-- 					                  <label for="minimal-radio-1">Original</label> -->
<!-- 					                </li> -->
<!-- 					                <li> -->
<!-- 					                  <input tabindex="2" type="radio" id="minimal-radio-2" name="minimal-radio" > -->
<!-- 					                  <label for="minimal-radio-2">480×320</label> -->
<!-- 					                </li> -->
<!-- 					                <li> -->
<!-- 					                  <input tabindex="3" type="radio" id="minimal-radio-1" name="minimal-radio"> -->
<!-- 					                  <label for="minimal-radio-1">640×480</label> -->
<!-- 					                </li> -->
<!-- 					                <li> -->
<!-- 					                  <input tabindex="4" type="radio" id="minimal-radio-2" name="minimal-radio"> -->
<!-- 					                  <label for="minimal-radio-2">800×480</label> -->
<!-- 					                </li>				            -->
<!-- 					              </ul> -->
<!-- 				                </div> -->
<!-- 							    <div class="row" style="margin-left:-259px;"> -->
<!-- 								<input tabindex="5" type="radio" id="minimal-radio-2" name="minimal-radio"> -->
<!-- 								<label for="minimal-radio-2"> &nbsp X &nbsp -->
<!-- 								<div class="input-group spinner" style="float:left;"> -->
<!-- 								<input type="text" class="form-control1" value="320"> -->
<!-- 								<div class="input-group-btn-vertical"> -->
<!-- 									<button class="btn btn-default" type="button"><i class="fa fa-caret-up"></i></button> -->
<!-- 									<button class="btn btn-default" type="button"><i class="fa fa-caret-down"></i></button> -->
<!-- 								</div> -->
<!-- 								</div>  -->
<!-- 								<div class="input-group spinner1" style="float:right;"> -->
<!-- 								<input type="text" class="form-control1" value="240"> -->
<!-- 								<div class="input-group-btn-vertical"> -->
<!-- 									<button class="btn btn-default" type="button"><i class="fa fa-caret-up"></i></button> -->
<!-- 									<button class="btn btn-default" type="button"><i class="fa fa-caret-down"></i></button> -->
<!-- 								</div> -->
<!-- 								</div> -->
<!-- 					            </label> -->
<!-- 					            </div> -->
					            
<!-- 							    <div class="source delete" style="margin-left:-230px;"> -->
<!-- 								  	<input tabindex="2" type="checkbox" id="sdelete" checked> -->
<!--                                     <label for="sdelete">delete source file after transcode </label> -->
<!--                                 </div> -->
<!-- 								<script> -->
								
<!-- 								</script> -->
<!-- 								</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-info"  -->
<!-- 										data-dismiss="modal" style="margin-top:20px; ">close -->
<!-- 										</button> -->
<!-- 										<button type="button" class="btn btn-primary"  data-dismiss="modal"> -->
<!-- 										save -->
<!-- 										</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div>		 -->
<!-- 					    </div> -->
						<input type="button" class="previous action-button" value="Previous" />
						<input type="button" id="step2Next" class="next action-button" value="Next" />
					</fieldset>
					<fieldset>
						<h2 class="fs-title">Submit Video!</h2>
						<!--<h3 class="fs-subtitle">the video will be upload</h3> -->
						<!--本地上传视频显示的信息 -->
						<div id="localfileSubmitInfo" style="display : none;">
							<h3 class="fs-subtitle">Please check your video Info below</h3>
						</div>
						
						<!--网络视频显示的信息 -->
						<div id="networkResourceLoading" style="display : none;" >
							<div id="loading" class="loading">You have select a net work video</div>
						</div>
						
						<!--视频信息 -->
						<div id="videoinfo" style="display : none; text-align:left ; padding-left :40px;" >
							<div id="filename"><h3 class='fs-subtitle'>File Name</h3><span><span></div>
							<div id="filesize"><h3 class='fs-subtitle'>File Size</h3><span><span></div>
							<div id="filetype"><h3 class='fs-subtitle'>Type</h3><span><span></div>
							<div id="targetType"><h3 class='fs-subtitle'>Target Type</h3><span><span></div>
						</div>
						<div id="clientuploadinfo" style="display : none">
							<h3 class="fs-subtitle"></h3>
						</div>
						<!--进度条 -->
						<div class="submitprogress" id="submitprogressbar" style="display : none;">
    						<span class="submitprogress-val">0%</span>
					    	<span class="submitprogress-bar"><span class="submitprogress-in" style="width: 84%"></span></span>
					 	</div>
					 	
<!-- 					 	position: fixed;top:40%;left:40%;width:650px;margin-left: -325px -->
					 	<div class="modal fade col-md-6 col-md-offset-3" id="payModal" tabindex="-1" role="dialog" 
							aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:180px; height:310px;">
								<div class="modal-content" >
									<div class="modal-header">
										<button type="button" class="close" id="closeModalBtn"
										data-dismiss="modal" aria-hidden="true">
										&times;
										</button>
										<h4 class="modal-title" id="myModalLabel">
										You need only 1 eur to submit your transcode job!
										</h4>
									</div>
									<div class="modal-body">
<!-- 									<button id="payfinishedbtn">payfinished</button> -->
									<div id="payfinished" style="display :none;"><input type="button" class="action-button" id="payfinishedbtn" value="payfinished" /></div>
<!-- 									<div id="payfail" style="display :none;"><button id="payfailbtn">payfail</button></div> -->

									<!-- paypal button begin -->
										<div id="paypalbutton">
											<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_top" onsubmit="return changePayButton()">
												<input type="hidden" name="cmd" value="_s-xclick">
												<input type="hidden" name="hosted_button_id" value="N8QTR5U4HM882">
	<!--											<input type="submit" style="width: 40%;"value="Pay for transcode" name="submit" title="PayPal - The safer, easier way to pay online!" class="paypal_btn"> -->
	<!-- 											<input type="image" src="https://www.sandbox.paypal.com/en_US/C2/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!"> -->
												<input type="image" id="gotopay" formtarget="_blank"  src="${ctx}/static/images/pay.png"  name="submit" alt="PayPal - The safer, easier way to pay online!" style="width: 40%;border="0px;">
										        <img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
											</form>
										</div>
									</div>
									<div class="modal-footer" id="payclose" style="display: none;">
										<button type="button" class="btn btn-info" 
										data-dismiss="modal" style="margin-top:20px; ">close
										</button>
									</div>
								</div>
							</div>	

					 	<!-- 返回继续选择格式 -->
						<input type="button" class="previous action-button" value="Previous"  id="lastprevious"/>
						<!-- 上传 -->
						<button class="action-button" data-toggle="modal" 
							data-target="#payModal" id="uploadbtn">
							Upload
						</button>
<!-- 						<span id="uploadbtn"><input type="button" class="action-button" id="upload" value="Upload" /></span> -->
						<span id="afterTransBtn" style="display : none">
							<span id="downloadbtn" ><input type="button" class="action-button" id="download" value="Download"/></span>
							<span id="giveupbtn"><input type="button" class="action-button" id="giveup" value="Give Up" /></span>
						</span>
						<!-- 重新选择资源 -->
						<span id="changebtn"><input type="button" class="action-button" id="changevideo" value="Change Video" /></span>
						<!-- 取消上传 -->
						<span style="display : none" id="cancelbtn"><input type="button" class="action-button" id="cancel" value="Cancel Upload" /></span>

					</fieldset>
				</div>
						
			</div>
			
			
    <script type="text/javascript">
		
    	$(document).ready(function(){
		  $('input').iCheck({
		    checkboxClass: 'icheckbox_minimal-yellow',
		    radioClass: 'iradio_minimal-yellow',
		    increaseArea: '20%' // optional
		  });
		});
    
		$('input[id=lefile]').change(function() {
			$('#file').val($(this).get(0).files[0].name);
			
		});
		
		
		$("#formatsdropdown").on("click","li[class=format]",function(){
			
			$("#formatChoosen").attr("type",$(this).find("a").attr("type"));
			$("#formatChoosen").text($(this).find("a").text());
			//选择之后关掉错误提示
			if($("#formatChoosen").attr("type") >= 0)
			{
				$.closeErrorTip();
			}
		});
		

	(function ($) {
	$('.spinner .btn:first-of-type').on('click', function() {
		$('.spinner input').val( parseInt($('.spinner input').val(), 10) + 10);
		});
	$('.spinner .btn:last-of-type').on('click', function() {
		$('.spinner input').val( parseInt($('.spinner input').val(), 10) - 10);
		});
	$('.spinner1 .btn:first-of-type').on('click', function() {
		$('.spinner1 input').val( parseInt($('.spinner1 input').val(), 10) + 10);
		});
	$('.spinner1 .btn:last-of-type').on('click', function() {
		$('.spinner1 input').val( parseInt($('.spinner1 input').val(), 10) - 10);
		});
	})(jQuery);
	
	
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
	// $('#payModal').modal({backdrop: 'static', keyboard: false});
	//
	</script> 
	
	<input type="hidden" id="ctx" value="${ctx}">
	<!--提交转码信息相关脚本-->
	<script src="${ctx}/static/js/convert.js" ></script>
	
	</body>
</html>