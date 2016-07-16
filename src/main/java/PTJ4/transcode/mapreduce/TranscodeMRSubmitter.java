package PTJ4.transcode.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import PTJ4.transcode.util.ExecUtil;

public class TranscodeMRSubmitter {
	
	
	public static boolean submitJob(String hdfsInputInfoPath,String hdfsoutputInfoPath) throws IOException, ClassNotFoundException, InterruptedException
	{
		// create a configuration
		Configuration conf = new Configuration();
		// this should be like defined in your yarn-site.xml
		String submitTarget = null;
		String result = ExecUtil.getYarnState();
		if(result.equals("active"))
		{
			submitTarget = "core1";
		}else
		{
			submitTarget = "core2";
		}
		conf.set("yarn.resourcemanager.address", submitTarget+":8032");
		String uploadNamenode = null;
		String hdfsResult = ExecUtil.getNamenodeState();
		if(hdfsResult.equals("active"))
		{
			uploadNamenode = "nn1";
		}else
		{
			uploadNamenode = "nn2";
		}
		conf.set("fs.defaultFS", "hdfs://"+uploadNamenode+":9000/");

		// framework is now "yarn", should be defined like this in mapred-site.xm
		conf.set("mapreduce.framework.name", "yarn");
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(TranscodeMRSubmitter.class);
		
		//Mapper Class
		job.setMapperClass(TranscodeMapper.class);
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		//Reducer Class
		job.setReducerClass(TranscodeReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//文件夹级别
		FileInputFormat.setInputPaths(job,hdfsInputInfoPath);
		
		
		FileOutputFormat.setOutputPath(job, new Path(hdfsoutputInfoPath));

		boolean isSuccess = job.waitForCompletion(true);
		return isSuccess;
	}
}