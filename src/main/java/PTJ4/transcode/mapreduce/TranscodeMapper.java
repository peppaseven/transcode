package PTJ4.transcode.mapreduce;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import PTJ4.transcode.util.ExecUtil;
import PTJ4.transcode.util.HdfsUtil;

/**
 * 
 * @author vwings
 *
 */
public class TranscodeMapper extends Mapper<Text, Text, Text, Text>{

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		//获得需要转码的信息
		String[] inputInfos = StringUtils.split(line," ");
		//文件除去扩展名
		String videoBaseName = inputInfos[0];
		//hdfs上的文件路径
		String hdfsFilePath = inputInfos[1];
		//要转码的文件名
		String transcodeSplitFileName = inputInfos[2];
		
		String splitFileName = hdfsFilePath.substring(hdfsFilePath.lastIndexOf("/")+1);
		String splitFileLocalPath = "/tmp/"+splitFileName;
		String transcodeFileLocalPath = "/tmp/" + transcodeSplitFileName;
		System.out.println("splitFileLocalPath: " + splitFileLocalPath);
		System.out.println("transcodeFileLocalPath: " + transcodeFileLocalPath);
		//下载文件到本地
		HdfsUtil.download(hdfsFilePath,splitFileLocalPath);
		boolean transcodeSuccess = ExecUtil.run(new String[]{"ffmpeg","-i",splitFileLocalPath,transcodeFileLocalPath});
		
		System.out.println("transcodeSucces: " + transcodeSuccess);
		if(transcodeSuccess)
		{
			String targetExt = transcodeFileLocalPath.substring(transcodeFileLocalPath.lastIndexOf("."));
			//key: /transcode/username/username_timestamp_videoname/transed/username_timestamp_videoname.flv. : value ={username_timestamp_videoname.01.flv,username_timestamp_videoname.02.flv....}
			context.write(new Text(hdfsFilePath.substring(0, hdfsFilePath.lastIndexOf("/")+1)+"transed/"+videoBaseName+targetExt), new Text(transcodeFileLocalPath));
			
		}
		
	}
	
	
	
}