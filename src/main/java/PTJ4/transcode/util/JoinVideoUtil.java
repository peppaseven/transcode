package PTJ4.transcode.util;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;

import com.google.common.collect.Lists;

public class JoinVideoUtil {
	
	
	/**
	 * @param transedVideo 合并要输出的文件名
	 * @param transedSplitVideos 合并的文件名列表
	 */
	public static boolean join(String transedVideoName, Iterable<Text> transedSplitVideos)
	{
		StringBuilder joinFiles = new StringBuilder();
		
		List<Text> splitFileList = Lists.newArrayList(transedSplitVideos);
		
		for(int i = 0;i<splitFileList.size();i++)
		{
			joinFiles.append(splitFileList.get(i).toString());
			if(i != splitFileList.size() - 1)
			{
				joinFiles.append(" +");
			}
		}
		
	
		String[] joinFileParams = StringUtils.split(joinFiles.toString()," ");
		String[] preCommand = new String[]{
				"mkvmerge","-o",transedVideoName
		};
		String[] command = (String[]) ArrayUtils.addAll(preCommand, joinFileParams);
		
		boolean isJoinSuccess = ExecUtil.run(command);
		
		return isJoinSuccess;
		
	}
	
//	public static boolean join(String transedVideo, Iterable<String> transedSplitVideos)
//	{
//		return false;
//	}
}