<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>	
	上传文件 <input id="videofile" type="file" /><br>		
	<button id="uploadButton">上传</button>
	
	<div id="filename"></div>
	<div id="filesize"></div>
	<div id="filetype"></div>
	<br />
	上传进度：<span id="progress">0</span>
	
	<br />
	<br />
	<button id="cancelUpload">取消上传</button>
		
	<script>
		
			//选择文件显示文件信息
			$("#videofile").on("change",function()
			{
				var file = $("#videofile").get(0).files[0];
				console.log($("#videofile"));
				if(file)
				{
					var fileSizeDisplay = formatFileDisplay(file);
					
					$("#filename").text("文件名称: " + file.name);
					$("#filesize").text("文件大小: " + fileSizeDisplay);
					$("#filetype").text("文件类型: " + file.type);
					
				}
			});
			
			var xhr = $.ajaxSettings.xhr();
			
			//jquery ajax上传文件(显示进度)
			$("#uploadButton").on("click",function()
			{
				console.log("shangchuan");
				
				
				var fd = new FormData();
				//param name
				fd.append("file", $("#videofile").get(0).files[0]);
				
				
				console.log(fd);
				$.ajax({
					type: "POST",
					processData: false,
					contentType: false,
					data: fd,
					url: "${ctx}/uploadvideo",
					xhr: function(){
				        // get the native XmlHttpRequest object
				        //var xhr = $.ajaxSettings.xhr();
				        // set the onprogress event handler
				        // xhr.onprogress 是从服务器取回数据的时候触发
				        xhr.upload.onprogress = function(evt){ 
				        	//console.log(evt);
				        	var rate = evt.loaded/evt.total*100;
				        	rate = rate.toFixed(2);
				        	$("#progress").text(rate + " %");
				        	//console.log('progress', evt.loaded/evt.total*100) 
				        } ;
				        
				        xhr.upload.onabort = function(evt)
				        {
				        	console.log("上传被取消")
				        };
				        // set the onload event handler
				        xhr.upload.onload = function(){ console.log('DONE!') } ;
				        // return the customized object
				        return xhr ;
				    },
					success: function(d) {
						//上传完成
						console.log(d);
						
					}
				
				});
				
			});
			
			
			$("#cancelUpload").on("click",function()
			{
				xhr.abort();
			});
			
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
			
			
		</script>
</body>
</html>