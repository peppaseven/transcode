package PTJ4.transcode.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.common.collect.Lists;


public class HdfsUtil {

	private static FileSystem dfs = null;
	
	static{
		//初始化
		try {
			hdfsInit();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * hdfs初始化
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */
	private static void hdfsInit() throws IOException, InterruptedException, URISyntaxException{
		Configuration conf = new Configuration();
		//集群环境下得到激活的那台虚拟机
		
		String uploadNamenode = null;
		String result = ExecUtil.getNamenodeState();
		if(result.equals("active"))
		{
			uploadNamenode = "nn1";
		}else
		{
			uploadNamenode = "nn2";
		}
		conf.set("fs.defaultFS", "hdfs://"+uploadNamenode+":9000/");
		dfs = FileSystem.get(new URI("hdfs://"+uploadNamenode+":9000/"), conf, "transcode");
	}
	
	
	
	/**
	 * 未切分文件上传
	 * @param tmpuploadFilePath 不包括文件名的临时文件目录
	 * @param userFolderName 用户名
	 * @param videoBaseName 视频去除扩展名
	 * @param videoName 视频名称
	 * @param targetformat 转码目标格式
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static Map<String,Object> upload(String tmpUploadFilePath,String userFolderName,String videoBaseName,String videoName,String targetformat) 
	{
		try {
			//转码之后的目录
			String transedString = "/transcode/"+userFolderName+"/"+videoBaseName+"/transed";
			Path transedPath = new Path(transedString);
			//上传文件信息的存储
			String inputInfoString = "/transcode/"+userFolderName+"/"+videoBaseName+"/inputinfo";
			Path inputInfoPath = new Path(inputInfoString);
			//转码之后的输出文件信息存储
			String outputInfoString = "/transcode/"+userFolderName+"/"+videoBaseName+"/outputinfo";
			Path outputInfoPath = new Path(outputInfoString);
			
			boolean transedFolderExist = dfs.exists(transedPath);
			boolean inputinfoFolderExist = dfs.exists(inputInfoPath);
			boolean outputinfoFolderExist = dfs.exists(outputInfoPath);
			
			if(!transedFolderExist)
			{
				dfs.mkdirs(transedPath);
			}
			if(!inputinfoFolderExist)
			{
				dfs.mkdirs(inputInfoPath);
			}
			
			dfs.copyFromLocalFile(new Path(tmpUploadFilePath + "/"+ videoName), new Path("/transcode/"+userFolderName+"/"+videoBaseName));
			
			
			String inputInfoPathString = "/transcode/"+userFolderName+"/"+videoBaseName+"/inputinfo/input.txt";
			Path inputInfo = new Path(inputInfoPathString);
			
			BufferedWriter br;
			if(dfs.exists(inputInfo))
			{
				dfs.delete(inputInfo,true);
			}
			br = new BufferedWriter(new OutputStreamWriter(dfs.create(inputInfo)));
			
			String filePath = "/transcode/"+userFolderName+"/"+videoBaseName+"/"+videoName;
			String trancodeName = videoBaseName + "." +targetformat;
			//输出一行文件表格
			br.write(videoBaseName + " " + filePath + " " + trancodeName);
			br.write("\n");
			br.close();
			
			Map<String,String> info = new HashMap<String,String>();
			//-output path these arguments used for submit yarn jobs
			info.put("output", outputInfoString);
			//-input file path
			info.put("input", inputInfoPathString);
			info.put("downloadpath",transedString+"/"+videoBaseName+"."+targetformat);
			
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS, "file success deployed on server!", info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "fail deploy to server");
	}

	
	/**
	 * 切分后的多文件上传
	 * @param tmpSplitUploadFilePath 要上传文件的基本目录
	 * @param userFolderName 用户名
	 * @param videoBaseName 视频去除扩展名的文件名
	 * @param targetformat 转码目标格式
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static Map<String,Object> upload(String tmpUploadFilePath,String userFolderName,String videoBaseName,String targetformat) {
		try {
			
			File uploadSplitFolder = new File(tmpUploadFilePath + "/split");
			//获得所有的切割文件
			File[] splitVideos = uploadSplitFolder.listFiles();
			
			//转码之后的目录
			String transedString = "/transcode/"+userFolderName+"/"+videoBaseName+"/transed";
			Path transedPath = new Path(transedString);
			//上传文件信息的存储(reducer 输出结果)
			String inputInfoString = "/transcode/"+userFolderName+"/"+videoBaseName+"/inputinfo";
			Path inputInfoPath = new Path(inputInfoString);
			//转码之后的输出文件信息存储
			String outputInfoString = "/transcode/"+userFolderName+"/"+videoBaseName+"/outputinfo";
			Path outputInfoPath = new Path(outputInfoString);
			
			boolean transedFolderExist = dfs.exists(transedPath);
			boolean inputinfoFolderExist = dfs.exists(inputInfoPath);
			boolean outputinfoFolderExist = dfs.exists(outputInfoPath);
			
			if(!transedFolderExist)
			{
				dfs.mkdirs(transedPath);
			}
			if(!inputinfoFolderExist)
			{
				dfs.mkdirs(inputInfoPath);
			}
			
			List<String> uploadFiles = new ArrayList<String>();
			//上传每一个文件
			for(File sv : splitVideos)
			{
				dfs.copyFromLocalFile(new Path(sv.getAbsolutePath()), new Path("/transcode/"+userFolderName+"/"+videoBaseName));
				String dfsPath = "/transcode/"+userFolderName+"/"+videoBaseName+"/"+ sv.getName();
				uploadFiles.add(dfsPath);
			}
			
			String inputInfoPathString = "/transcode/"+userFolderName+"/"+videoBaseName+"/inputinfo/input.txt";
			
			Path inputInfo = new Path(inputInfoPathString);
			
			BufferedWriter br;
			if(dfs.exists(inputInfo))
			{
				dfs.delete(inputInfo,true);
			}
			br = new BufferedWriter(new OutputStreamWriter(dfs.create(inputInfo)));

			Collections.sort(uploadFiles);
			//写入上传文件信息
			for(int i = 0; i< uploadFiles.size();i++)
			{
				//hdfs上存储的文件位置
				String filePath = uploadFiles.get(i);
				//每个切分的文件转码之后的名称
				String spllitTrancodeName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf(".")+1)+targetformat;
				//输出一行文件表格
				br.write(videoBaseName + " " + filePath + " " + spllitTrancodeName);
//				if(i!=uploadFiles.size()-1)
//				{
					br.write("\n");
				
			}
			
			br.close();
			
			Map<String,String> info = new HashMap<String,String>();
			//-output path these arguments used for submit yarn jobs
			info.put("output", outputInfoString);
			//-input file path
			info.put("input", inputInfoPathString);
			info.put("downloadpath",transedString+"/"+videoBaseName+"."+targetformat);
			
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS, "file success deployed on server!", info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "fail deploy to server");
	}
	
	public static boolean transcodeUpload(String transcodeFileLocalPath, String hdfsTransedPath)
	{
		try {
			dfs.copyFromLocalFile(new Path(transcodeFileLocalPath), new Path(hdfsTransedPath));
			
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 根据转码后的文件名来下载hdfs上的文件
	 * @param transedFilename
	 * @return
	 */
	public static void download(String hdfsFilePath,String localPath)
	{
		try {
			dfs.copyToLocalFile(new Path(hdfsFilePath), new Path(localPath));

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 从hdfs下载文件
	 * @param transcodeVideo hdfs上的文件路径
	 * @return
	 */
	public static InputStream download(String transcodeVideo) {
		try {
			
			FSDataInputStream is = dfs.open(new Path(transcodeVideo));
			
			return is;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
