package PTJ4.transcode.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import PTJ4.transcode.bean.ProcessInfo;
import PTJ4.transcode.module.SrcVideo;
import PTJ4.transcode.module.TransedVideo;
import PTJ4.transcode.module.User;
import PTJ4.transcode.service.SrcVideoService;
import PTJ4.transcode.service.TransedVideoService;
import PTJ4.transcode.service.UserService;
import PTJ4.transcode.util.FileUploadUtil;
import PTJ4.transcode.util.HdfsUtil;
import PTJ4.transcode.util.HttpClientUtil;
import PTJ4.transcode.util.JsonUtil;
import PTJ4.transcode.util.SplitVideoUtil;

@Controller
public class StreamController {

	
	private final static Logger logger = LoggerFactory.getLogger(StreamController.class);
	
	@Autowired
	private UserService us;
	
	@Autowired
	private SrcVideoService svs;
	
	@Autowired
	private TransedVideoService tvs;
	
	@RequestMapping("/upload")
	public String index()
	{
		return "upload";
	}
	
	/**
	 * 上传视频
	 * @param file
	 * @param filename
	 * @param filesize
	 * @param srcformat
	 * @param targetformat
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadvideo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upload(@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("filename") String filename,
			@RequestParam("filesize") Long filesize, 
			@RequestParam("srcformat") String srcformat,
			@RequestParam("targetformat") String targetformat,
			HttpServletRequest request
			)
	{
		
		boolean splitFlag = false;
		boolean uploadFlag = false;
		User currentUser = (User) request.getSession().getAttribute("user");
		String currentUsername = currentUser.getUsername();
		//临时保存文件名
		String tmpSaveFileName = currentUsername +"_"+ System.currentTimeMillis()+"_" + filename;
		//转码输出文件名
		targetformat = targetformat.toLowerCase();
		String targetFileName = currentUsername +"_"+ System.currentTimeMillis()+"_" + filename.substring(0,filename.lastIndexOf(".")+1) + targetformat;
		
		//源文件
		SrcVideo sv = new SrcVideo(file.getOriginalFilename(), srcformat, filesize);
		//转码目标文件
		TransedVideo tv = new TransedVideo(targetFileName);
		
		//保存上传记录
		svs.addVideo(sv, currentUsername);
		
		tvs.addTransedVideo(tv, sv);
		
		
		Map<String,Object> resultJson = null;
		//上传临时文件
		Map<String,Object> fileUploadMessage = FileUploadUtil.saveFile(file,currentUsername,tmpSaveFileName);
		
		if((Integer)fileUploadMessage.get(JsonUtil.RETURN_STATUS) == JsonUtil.SUCCESS_STATUS)
		{
			
			
			//文件去扩展名的名字
			String videoBaseName = FileUploadUtil.getFileNameWithoutExt(tmpSaveFileName);
			//大于分割的临界值才会分割
			if(SplitVideoUtil.isMoreThanOneSplit(filesize))
			{
				if(SplitVideoUtil.split((String)fileUploadMessage.get(JsonUtil.RETURN_DATA),videoBaseName,tmpSaveFileName))
				{
					splitFlag = true;
					//上传切分的文件
					resultJson = HdfsUtil.upload((String)fileUploadMessage.get(JsonUtil.RETURN_DATA),currentUsername,videoBaseName, targetformat);
					
					if((Integer)resultJson.get(JsonUtil.RETURN_STATUS) == JsonUtil.SUCCESS_STATUS)
					{
						uploadFlag = true;
					}
				}
			}else
			{
				//不需要切分,直接上传临时文件
				splitFlag = true;
				resultJson = HdfsUtil.upload((String)fileUploadMessage.get(JsonUtil.RETURN_DATA),currentUsername,videoBaseName, tmpSaveFileName,targetformat);
				
				if((Integer)resultJson.get(JsonUtil.RETURN_STATUS) == JsonUtil.SUCCESS_STATUS)
				{
					uploadFlag = true;
				}
			}
		}else
		{
			//上传临时文件失败返回错误
			return fileUploadMessage;
		}
		
		
		if (splitFlag && uploadFlag) {
			return resultJson;
		} else if (splitFlag == false) {
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "split faied");
		} else {
			//hdfs upload failed
			return resultJson;
		}
		
	}
	@RequestMapping(value = "/downnetvideo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> downnetvideo(
			@RequestParam("remoteurl") String remoteurl,
			@RequestParam("targetformat") String targetformat,
			HttpServletRequest request){
		
		
		boolean splitFlag = false;
		boolean uploadFlag = false;
		
		String filename = remoteurl.substring(remoteurl.lastIndexOf("/")+1);
		
		User currentUser = (User) request.getSession().getAttribute("user");
		String currentUsername = currentUser.getUsername();
		//临时保存文件名
		String tmpSaveFileName = currentUsername +"_"+ System.currentTimeMillis()+"_" + filename;
		//转码输出文件名
		targetformat = targetformat.toLowerCase();
		String targetFileName = currentUsername +"_"+ System.currentTimeMillis()+"_" + filename.substring(0,filename.lastIndexOf(".")+1) + targetformat;
		
		
		
		Map<String,Object> resultJson = null;
		//上传临时文件
		Map<String,Object> fileDownloadMessage = FileUploadUtil.downloadNetVideo(remoteurl,currentUsername,tmpSaveFileName);
		
		if((Integer)fileDownloadMessage.get(JsonUtil.RETURN_STATUS) == JsonUtil.SUCCESS_STATUS)
		{
			
			
			//文件去扩展名的名字
			String videoBaseName = FileUploadUtil.getFileNameWithoutExt(tmpSaveFileName);
			String filesize = ((Map<String,String>)fileDownloadMessage.get(JsonUtil.RETURN_DATA)).get("filesize");
			//temp save video path (no file)
			String  baseVideoPath = ((Map<String,String>)fileDownloadMessage.get(JsonUtil.RETURN_DATA)).get("baseVideoPath");
			//大于分割的临界值才会分割
			if(SplitVideoUtil.isMoreThanOneSplit(Long.parseLong(filesize)))
			{
				if(SplitVideoUtil.split(baseVideoPath,videoBaseName,tmpSaveFileName))
				{
					splitFlag = true;
					//上传切分的文件
					resultJson = HdfsUtil.upload(baseVideoPath,currentUsername,videoBaseName, targetformat);
					
					if((Integer)resultJson.get(JsonUtil.RETURN_STATUS) == JsonUtil.SUCCESS_STATUS)
					{
						uploadFlag = true;
					}
				}
			}else
			{
				splitFlag = true;
				//不需要切分,直接上传临时文件
				resultJson = HdfsUtil.upload(baseVideoPath,currentUsername,videoBaseName, tmpSaveFileName,targetformat);
				
				if((Integer)resultJson.get(JsonUtil.RETURN_STATUS) == JsonUtil.SUCCESS_STATUS)
				{
					uploadFlag = true;
				}
			}
		}else
		{
			//上传临时文件失败返回错误
			return fileDownloadMessage;
		}
		
		
		if (splitFlag && uploadFlag) {
			return resultJson;
		} else if (splitFlag == false) {
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "split faied");
		} else {
			//hdfs upload failed
			return resultJson;
		}
		
	}
	
	
	/**
	 * 下载视频{transedVideoName:.+}
	 * @param transedVideoName
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/api/download", method = RequestMethod.POST)
	public void download( @RequestParam("videoname") String transedVideoName,
			HttpServletRequest request,
		    HttpServletResponse response)
	{
		try {
			
				InputStream in = HdfsUtil.download(transedVideoName);
				
				String mimeType = URLConnection.guessContentTypeFromName(transedVideoName);
				if(mimeType == null)
				{
					mimeType = "application/octet-stream";
				}
				
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition",String.format("attachment; filename=\"" + transedVideoName +"\""));
				response.setContentLength(in.available());
								
				FileCopyUtils.copy(in, response.getOutputStream());
			} catch (IOException e) {
				logger.info("File Download failed!");
			}
		
		
	}

	
}
