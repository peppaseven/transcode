package PTJ4.transcode.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplitVideoUtil
{
	private final static Logger logger = LoggerFactory.getLogger(SplitVideoUtil.class);
	
	public static final long splitSize = 52428800; //byte  
	
	public static final long readableSplitSize = 50;
	
	public static boolean isMoreThanOneSplit(long fileSize)
	{
		//50mb +
		if(fileSize <= splitSize)
		{
			return false;
		}else
		{
			return true;
		}
	}
	
	/**
	 * @param tmpUploadfilePath 上传在本地临时文件的路径（不包括视频文件本身）
	 * @param baseVideoName 视频文件没有扩展名
	 * @param tmpSavevideo 视频文件名
	 * @return
	 */
	public static boolean split(String tmpUploadfilePath,String baseVideoName,String tmpSaveVideo)
	{
		try{
			String videoNameWithPath = tmpUploadfilePath + "/" + tmpSaveVideo;
			boolean splitFinished = ExecUtil.run("mkvmerge --split size:"+readableSplitSize+"m "+videoNameWithPath+" -o "+tmpUploadfilePath+"/split/"+baseVideoName+".%03d.mp4");
			
			if(splitFinished)
			{
				return true;
			}else
			{
				throw new Exception("split error!");
			}

		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return false;
		
	}
	
}