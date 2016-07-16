package PTJ4.transcode.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import PTJ4.transcode.util.HdfsUtil;
import PTJ4.transcode.util.JoinVideoUtil;

public class TranscodeReducer extends Reducer<Text, Text, Text, Text>{

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Reducer#reduce(KEYIN, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
	 * key : 转换的目标文件(路径)
	 */
	@Override
	protected void reduce(Text key, Iterable<Text> value, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//最后的下载路径(hdfs转码后的文件)
		String transTragetPath = key.toString();
		String localJoinVideoPath = "/tmp/"+transTragetPath.substring(transTragetPath.lastIndexOf("/")+1);
		System.out.println("localJoinVideoPath" + localJoinVideoPath);
		if(JoinVideoUtil.join(localJoinVideoPath,value))
		{
			//合并完成后上传
			if(HdfsUtil.transcodeUpload(localJoinVideoPath, transTragetPath))
			{
				context.write(key , new Text("success"));
			}
		}else {
			context.write(key, new Text("failure"));
		}
	}
	
}