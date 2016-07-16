package PTJ4.transcode.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 暂存的目录结构
 * /tmp/transcode/{username}/{videoFileWithoutextions}/split/[split files]
 * /tmp/transcode/{username}/{videoFileWithoutextions}/[tmp video file]
 * @author vwings
 */
public class FileUploadUtil {
	
	
	private final static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	/**
    *
    * @param files 上传的文件
    * @param username  用户名
    */
   public static Map<String,Object> saveFile(CommonsMultipartFile file,String baseUserNameFolder,String tmpSaveFilename)
   {
	  
	   boolean canUpload = false;
	   boolean canBeginSplit = false;
	   BufferedOutputStream stream = null;
       
       if(!file.isEmpty())
       {
           try {
        	   //上传之前建立好目录
        	   String baseTempVideoPathString = "/tmp/transcode/"+baseUserNameFolder+"/"+getFileNameWithoutExt(tmpSaveFilename);
        	   String baseSplitVideoPathStirng = baseTempVideoPathString + "/split";
        	   
        	   File baseDir = new File(baseTempVideoPathString);
        	   File baseSplitDir = new File(baseSplitVideoPathStirng);
        	   
        	   //no folder exist
        	   if(!(baseDir.exists() && baseDir.isDirectory()))
        	   { 
        		   canUpload = baseDir.mkdirs();
        	   }
        	   //already have the folder
        	   else
        	   {
        		   canUpload = true;
        	   }
        	   
        	   if(!(baseSplitDir.exists() && baseSplitDir.isDirectory()))
        	   { 
        		   canBeginSplit = baseSplitDir.mkdirs();
        	   }
        	   //already have the folder
        	   else
        	   {
        		   canBeginSplit = true;
        	   }
        	   
        	   if(canUpload && canBeginSplit)
        	   {
        		   String videoPath = baseTempVideoPathString + "/" + tmpSaveFilename;
        		   stream = new BufferedOutputStream(
                		   new FileOutputStream(videoPath));
                   
                   FileCopyUtils.copy(file.getInputStream(), stream);
                   
                   String uploadHdfsLocalPath = null;
                  
                   return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS, "File Uploaded!", baseTempVideoPathString);

        	   }else
        	   {
        		   throw new Exception("cannot make floder to save tmp file");
        	   }
                          
           } catch (Exception e) {
        	   
        	   logger.error("file upload error {}",e.getMessage());
           }finally{
        	  
        	   try {
					stream.close();
        	   } catch (IOException e) {
        		   logger.error("close stream error");
        		   return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "close stream error");
				}
           }

       }
       
       return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "file is empty");
   }
 
   
   /**
    * get network video
    * @param remoteurl
    * @param currentUsername
    * @param tmpSaveFileName
    */
   public static Map<String,Object> downloadNetVideo(String remoteurl, String baseUserNameFolder, String tmpSaveFileName) {
	   
	   boolean canUpload = false;
	   boolean canBeginSplit = false;
	   BufferedOutputStream stream = null;
       
       if(!remoteurl.isEmpty())
       {
           try {
        	   //上传之前建立好目录
        	   String baseTempVideoPathString = "/tmp/transcode/"+baseUserNameFolder+"/"+getFileNameWithoutExt(tmpSaveFileName);
        	   String baseSplitVideoPathStirng = baseTempVideoPathString + "/split";
        	   
        	   File baseDir = new File(baseTempVideoPathString);
        	   File baseSplitDir = new File(baseSplitVideoPathStirng);
        	   
        	   //no folder exist
        	   if(!(baseDir.exists() && baseDir.isDirectory()))
        	   { 
        		   canUpload = baseDir.mkdirs();
        	   }
        	   //already have the folder
        	   else
        	   {
        		   canUpload = true;
        	   }
        	   
        	   if(!(baseSplitDir.exists() && baseSplitDir.isDirectory()))
        	   { 
        		   canBeginSplit = baseSplitDir.mkdirs();
        	   }
        	   //already have the folder
        	   else
        	   {
        		   canBeginSplit = true;
        	   }
        	   
        	   if(canUpload && canBeginSplit)
        	   {
        		   String videoPath = baseTempVideoPathString + "/" + tmpSaveFileName;
        		   int filesize = HttpClientUtil.getVideo(remoteurl,videoPath);
        		   
                   if(filesize > 0){
                	   Map<String,String> fileinfo = new HashMap<String,String>();
                	   fileinfo.put("baseVideoPath", baseTempVideoPathString);
                	   fileinfo.put("filesize", String.valueOf(filesize));
                	   return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS, "Remote File Download!", fileinfo);
                	   
                   }else{
                	   return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "Can not finished download remote file");

                   }
                  

        	   }else
        	   {
        		   throw new Exception("cannot make floder to save tmp file");
        	   }
                          
           } catch (Exception e) {
        	   
        	   logger.error("file upload error {}",e.getMessage());
           }finally{
        	  
           }

       }
       
       return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "no remote file");
   }
  
   /** 返回不包括扩展名的文件名
	 * @param orignFilename
	 * @return
	 */
   public static String getFileNameWithoutExt(String orignFilename)
   {
	   return orignFilename.substring(0,orignFilename.lastIndexOf("."));
   }

}
