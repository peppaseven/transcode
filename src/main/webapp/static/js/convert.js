//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
var ctx = $("#ctx").val();
//文件的地址
var netResource = $("#netResource");
var localResource = $("#file");
var currentButtonClicked = "";



//step 3相关处理:

//取消
$("#changevideo,#giveupbtn").on("click",function()
{
	//返回到转换首页
	window.location.href = ctx + "/convert";
});


//$("#transbtn").on("click",function(){
//	
//	$.ajax({
//		type : "get",
//		url : ctx+ "/s",
//		success : function(res) {
//			if(res.status == 1) {
//				console.log("clear the status");
//				//close modal
//				$("#closeModalBtn").trigger("click");
//				//upload
//				uploadvideo();
//			}
//		}
//	});
//})
//上传并转码
$("#uploadconvert").on("click",function()
{
	
});

//paypal button
//$("#gotopay").on("click",function()
//{
//	$("#paypalbutton").hide();
//	
//	$("#payfinished").show();
//	$("#payfail").show();
//	
//	$("#myModalLabel").text("Don't close this page until you finish payment")
//	
////	$("#payclose").hide()
//});

function changePayButton()
{
	//$('#payModal').modal({backdrop: 'static', keyboard: false});
	
	$("#paypalbutton").hide();
	
	$("#payfinished").show();
	$("#payfail").show();
	
	$("#myModalLabel").text("Don't close this page until you finish payment")
	
	return true;
//	$("#payclose").hide()
}

$("#payfinishedbtn").on("click",function(){
	$.ajax({
		type: "get",
		url : ctx+ "/api/checkpaystatus",
		success : function(response){
			//if pay really success
			if(response.status == 1){
				//upload video
				//uploadvideo();
				//close the modal
				console.log("you have paid!!!");
				
				$.ajax({
					type : "get",
					url : ctx+ "/api/clearpaystatus",
					success : function(res) {
						if(res.status == 1) {
							console.log("clear the status");
							//close modal
							$("#closeModalBtn").trigger("click");
							
							if($("#netResource").val() != ""){
								//download
								console.log("a network video");
								networkDownloadVideo();
							}else{
								//upload
								uploadvideo();
							}
						}
					}
				});
				
			}else{
				console.log("haven't paid");
			}
		}
	});
});

$("#lefile").on("change",function()
{
	$.closeErrorTip();
	localResource.removeClass("error");
	
	if(isVideo($("#file").val()))
	{

	}else
	{
		localResource.focus();
		localResource.errorTip("please select a video file!");
	}
});
	
function beginTranscode(response)
{
	if(response.status == 1 && response.data)
	{	
		//hide upload
		$("#uploadbtn").hide();
		
		//$("#afterTransBtn").show(); // show the give up button for now
		$("#clientuploadinfo h3").html("video has been successfully uploaded, transcode job is running...");
		
		console.log("input"+response.data.input);
		console.log("output"+response.data.output);
		console.log("output"+response.data.downloadpath);
		console.log("success : " + response.message);
		
		
		$("#downloadbtn").attr("downloadlink", response.data.downloadpath);
		
		var jobinfo = {
				"input" : response.data.input,
				"output" : response.data.output,
				"link" : response.data.downloadpath
		}
		$.ajax({
			type: "POST",
			data : jobinfo,
			url : ctx+ "/api/submitjob",
			success : function(transres){
				if(transres.status == 1){
					$("#afterTransBtn").show();
					$("#clientuploadinfo h3").html("video has been successfully transcoded ,you can download the video now!");
				}else {
					$("#clientuploadinfo h3").html("video transcode error!");
				}
			}
		})
	}
}
var xhr = $.ajaxSettings.xhr();

//network download
function networkDownloadVideo(){
	var remoteUrl = $("#netResource").val();
	var targetformat = $("#formatChoosen").text();
	var data = {
		"remoteurl" : remoteUrl,
		"targetformat" : targetformat
	}
	$("#clientuploadinfo").show();
	$("#clientuploadinfo h3").html("video is downloading from internet");
	$.ajax({
		type: "POST",
		data : data,
		url : ctx + "/downnetvideo",
		success : function(response){
			$("#uploadbtn").hide();
			$("#changebtn").hide();
			//隐藏回退选择格式的按钮
			$("#lastprevious").hide();
			
			//$("#clientuploadinfo").show();
			beginTranscode(response);
		}
	})
	
}
function uploadvideo(){
	//显示进度条 初始化为0
	$(".submitprogress-in").css("width","0%");
	$("#submitprogressbar").show();
	
	var fd = new FormData();
	
	fd.append("file", $("#lefile").get(0).files[0]);
	fd.append("filename",$("#filename span").text());
	fd.append("filesize",$("#lefile").get(0).files[0].size);
	fd.append("srcformat",$("#filetype span").text());
	fd.append("targetformat",$("#targetType span").text());
	
	//隐藏按钮
	$("#uploadbtn").hide();
	$("#changebtn").hide();
	//隐藏回退选择格式的按钮
	$("#lastprevious").hide();
	//显示取消上传的按钮
	$("#cancelbtn").show();
	
	$.ajax({
		type: "POST",
		processData: false,
		contentType: false,
		data: fd,
		url:  ctx + "/uploadvideo",
		xhr: function(){
	        // get the native XmlHttpRequest object
	        //var xhr = $.ajaxSettings.xhr();
	        // set the onprogress event handler
	        // xhr.onprogress 是从服务器取回数据的时候触发
	        xhr.upload.onprogress = function(evt){ 
	        	var rate = evt.loaded/evt.total*100;
	        	rate = rate.toFixed(2);
	        	$(".submitprogress-val").text(rate + " %");
	        	$(".submitprogress-in").css("width",rate+"%");
	        	
	        } ;
	        //终止上传
	        xhr.upload.onabort = function(evt)
	        {
	        	console.log("upload canceled")
	        };
	        // set the onload event handler
	        xhr.upload.onload = function(){
	        	console.log('DONE!');
	        	//上传完成，显示服务器正在分配资源====此时文件将传输到hadoop集群
	        	$("#videoinfo").hide();
	        	$("#localfileSubmitInfo").hide();
	        	$("#networkResourceLoading").hide();
	        	$("#cancelbtn").hide();
	        	
	        	$("#clientuploadinfo h3").html("video <b>"+$("#filename span").text()+"</b> has been uploaded ! Waitting for the server to allocate resources...");
	        	$("#clientuploadinfo").show();
	        	
	        } ;
	        // return the customized object
	        return xhr ;
	    },
	    //upload to hdfs finished
		success: function(response) {
			
			beginTranscode(response);
			
		}
	
	});
	
}




$("#downloadbtn").on("click",function(){
	var downloadLink = $("#downloadbtn").attr("downloadLink");
	var downloadparam  = {
			"videoname" :downloadLink
	}
	post(ctx+"/api/download",downloadparam);
	
})
//停止上传
$("#cancel").on("click",function()
{
	xhr.abort();
});

	//支持的格式
	var supportFormatList = ["mp4","avi","wmv", "mkv","mov","3gp","flv", "mpg", "vob","webm"];
	
	function isVideo(filename)
	{
		var filenameArr = filename.split(".");
		var fileExt = filenameArr[filenameArr.length - 1];
		if($.inArray(fileExt,supportFormatList) != -1)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	
	//从数据库加载支持的数据格式
	function loadFormats()
	{
		$("#formatsdropdown").html("");
		$("#formatsdropdown").append("<li role='presentation' class='dropdown-header'>choose formate</li>");
		$.ajax({
			type : "GET",
	        cache:false,
			url : ctx+"/api/getformats",
			dataType : 'json',
			success : function (response)
			{
				
				$.each(response,function (i,v)
				{
					$("#formatsdropdown").append("<li role='presentation' class='format'><a role='menuitem' tabindex='-1' href='#'  type='"+v.id+"' data-module='"+v.name+"'>"+v.name+"</a></li>");
				});
			}
		});
	}
	
	//loadFormats();
	
//格式化视频信息
function formatFileDisplay(file)
{
	var fileSizeDisplay = 0;
	
	if(file.size > 1024 * 1024 * 1024)
	{
		fileSizeDisplay = (file.size / (1024 * 1024 * 1024)).toFixed(2) + " GB";
	}
	//大于 1 MB
	else if(file.size > 1024 * 1024)
	{
		fileSizeDisplay = (file.size / (1024 * 1024)).toFixed(2) + " MB";
	}
	//大于 1 KB
	else if(file.size > 1024)
	{
		fileSizeDisplay = (file.size / 1024).toFixed(2) + " KB";
	}
	//
	else
	{
		fileSizeDisplay = file.size.toFixed(2) + " B";
	}
	
	return fileSizeDisplay;
}
	
//显示视频文件信息
function showVideoInfo(file,targetType)
{
	if(file)
	{
		var fileSizeDisplay = formatFileDisplay(file);
		
		$("#filename span").text(file.name);
		$("#filesize span").text(fileSizeDisplay);
		$("#filetype span").text(file.type);
		$("#targetType span").text(targetType);
	}
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
//点击下一步事件
$(".next").click(function(){
	
	$.closeErrorTip();
	$(this).removeClass("error");
	
	
	if(animating) return false;
	
	
	
	var canContinue = true;

	
	if($(this).attr("id") == "step1Next")
	{
		currentButtonClicked = "button1"; 
		$.closeErrorTip();
		netResource.removeClass("error");
		localResource.removeClass("error");
		$(this).removeClass("error");
		
		//two inputs cannot be not null same time
		if((netResource.val() != "" && localResource.val()!= "") )
		{
			$(this).errorTip("You can select only one category once");
			canContinue = false;
		}
		else if (netResource.val() == "" && localResource.val() == "")
		{
			$(this).errorTip("please select a local or network video!");
			canContinue = false;
		}
		//判断提交内容是否合法
		else
		{
			//如果提交的是网络视频
			if(netResource.val() != "")
			{
				if(!isVideo(netResource.val()))
				{
					netResource.errorTip("please enter a video file!");
					canContinue = false;
				}
			}
			//如果是本地上传的视频
			else if(localResource.val() != "")
			{
				if(!isVideo(localResource.val()))
				{
					localResource.errorTip("please select a video file!");
					canContinue = false;
				}
				
			}
			//?未知情况
			else
			{
				
			}
		}
	}else if($(this).attr("id") == "step2Next")
	{
		currentButtonClicked = "button2";
		$.closeErrorTip();
		$(this).removeClass("error");
		
		if($("#formatChoosen").attr("type") <= 0)
		{
			$(this).errorTip("Please select a format!");
			canContinue = false;
		}else
		{
			//在将要跳转之前进行处理<提交之后全部隐藏>
			if($("#file").val() != "")
			{
				$("#localfileSubmitInfo").show();
				showVideoInfo($("#lefile").get(0).files[0],$("#formatChoosen").text());
				
				$("#videoinfo").show();
	
			}else if($("#netResource").val() !=  "")
			{
				//$("#networkResourceLoading").show();
				//send ajax request = >
			}
//			$("#submitprogressbar").hide();
//			$("#loading").hide();
//			//选择了格式
//			if($("#file").val()!="")
//			{
//				$("#submitprogressbar").show();
//				$("#loading").hide();
//			}else
//			{
//				$("#submitprogressbar").hide();
//				$("#loading").show();
//			}
		}
	
	}

	if(canContinue)
	{
		//进入第二栏的时候加载数据
		if(currentButtonClicked == "button1")
		{
			loadFormats();
		}
		
		animating = true;
		
		current_fs = $(this).parent();
		next_fs = $(this).parent().next();
		
		//activate next step on progressbar using the index of next_fs
		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
		
		//show the next fieldset
		next_fs.show(); 
		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now, mx) {
				//as the opacity of current_fs reduces to 0 - stored in "now"
				//1. scale current_fs down to 80%
				scale = 1 - (1 - now) * 0.2;
				//2. bring next_fs from the right(50%)
				left = (now * 50)+"%";
				//3. increase opacity of next_fs to 1 as it moves in
				opacity = 1 - now;
				current_fs.css({'transform': 'scale('+scale+')'});
				next_fs.css({'left': left, 'opacity': opacity});
			}, 
			duration: 800, 
			complete: function(){
				current_fs.hide();
				animating = false;
			},
			//this comes from the custom easing plugin
			easing: 'easeInOutBack'
		});
		
		//根据选的情况进行判断
		if(currentButtonClicked == "button2")
		{
			//选的本地视频
			if($("#file").val()!= "")
			{
				
			}
			//选的网络视频
			else if($("#netResource").val() !=  "")
			{
				
			}
		}
	}
	
	
	
});

$(".previous").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();
	
	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
	
	//show the previous fieldset
	previous_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});
