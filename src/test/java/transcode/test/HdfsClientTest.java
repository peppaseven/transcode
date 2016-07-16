package transcode.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * hdfs测试类
 * @author Vwings
 *
 */
public class HdfsClientTest {

	private FileSystem fs = null;
	
	@Before
	public void getFs() throws IOException, InterruptedException, URISyntaxException
	{
		Configuration conf = new Configuration();
		//集群环境下得到激活的那台虚拟机
		conf.set("fs.defaultFS", "hdfs://nn1:9000/");
		fs = FileSystem.get(new URI("hdfs://nn1:9000/"), conf, "transcode");
	}
	
	
	/**
	 * 上传文件
	 */
	@Test
	public void testUpload()
	{
		
		
		try {
			
			Path destFile = new Path("hdfs://nn2:9000/22_videopychopass.mp4");
			FSDataOutputStream os = fs.create(destFile);
			
			FileInputStream is = new FileInputStream("G:/transcodevideo/22.mp4");
			
			IOUtils.copy(is, os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUploadSimple()
	{
//		fs.copyFromLocalFile(, dst);
	}
	
	@Test
	public void testDwonload() throws IllegalArgumentException, IOException
	{
		FSDataInputStream is = fs.open(new Path("hdfs://nn2:9000/22_vwings.mp4"));
		FileOutputStream os = new FileOutputStream("G:/hadoopdown/video_fromhdfs.mp4");
		
		IOUtils.copy(is, os);
	}
	
	
	@Test
	public void toTmp() throws IllegalArgumentException, IOException
	{
		fs.copyToLocalFile(new Path("/transcode/vwings/vwings_1464444382278_video1/vwings_1464444382278_video1.mp4"), new Path("/tmp/a.flv"));
	}
	
}
