package PTJ4.transcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecUtil
{
	
	private final static Logger logger = LoggerFactory.getLogger(ExecUtil.class);
	
	
//	ssh core1 /home/transcode/hadoop/hadoop-2.5.2/bin/hadoop jar /home/transcode/hadoop/hadoop-streaming-2.5.2.jar -input /transcode/user1/user1_1464777019531_fifa/inputinfo/input.txt -output /transcode/user1/user1_1464777019531_fifa/output -mapper /home/transcode/hadoop/mapper.sh -reducer /home/transcode/hadoop/reducer.sh -file /home/transcode/hadoop/mapper.sh -file /home/transcode/hadoop/reducer.sh

	public static boolean submitJob(String input,String output){
		
		boolean executeResult = ExecUtil.run(new String[]{"ssh","core1","/home/transcode/hadoop/hadoop-2.5.2/bin/hadoop jar /home/transcode/hadoop/hadoop-streaming-2.5.2.jar -input "+input+" -output "+output+" -mapper /home/transcode/hadoop/mapper.sh -reducer /home/transcode/hadoop/reducer.sh -file /home/transcode/hadoop/mapper.sh -file /home/transcode/hadoop/reducer.sh"});
		
		return executeResult;
	}
	
	/**
	 * 传入命令执行并返回执行结果
	 * @param command
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String getNamenodeState() throws IOException, InterruptedException
	{
		Runtime r = Runtime.getRuntime();
        Process process = r.exec(new String[]{
				"ssh","transcode@nn1","/home/transcode/hadoop/hadoop-2.5.2/bin/hdfs haadmin -getServiceState nn1"
		});
        
        StringBuilder processOutput = new StringBuilder();

        try (BufferedReader processOutputReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));)
        {
            String readLine;

            while ((readLine = processOutputReader.readLine()) != null)
            {
                processOutput.append(readLine + System.lineSeparator());
            }

            process.waitFor();
        }

        return processOutput.toString().trim();
	}
	
	/**
	 * 获得yarn的运行状态
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String getYarnState() throws IOException, InterruptedException
	{
		Runtime r = Runtime.getRuntime();
        Process process = r.exec(new String[]{
				"ssh","transcode@core1","/home/transcode/hadoop/hadoop-2.5.2/bin/yarn rmadmin -getServiceState rm1"
		});
        
        StringBuilder processOutput = new StringBuilder();

        try (BufferedReader processOutputReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));)
        {
            String readLine;

            while ((readLine = processOutputReader.readLine()) != null)
            {
                processOutput.append(readLine + System.lineSeparator());
            }

            process.waitFor();
        }

        return processOutput.toString().trim();
	}
	
	public static boolean run(String command)
	{
		try{
			logger.info(command);
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(command);
			p.waitFor();
			
			return true;
		}catch(Exception e)
		{
			
		}
		return false;
	}
	
	/**
	 * 运行多参数的本地程序
	 * @param command
	 * @return
	 */
	public static boolean run(String[] command)
	{
		try{
			logger.info(Arrays.toString(command));
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(command);
			p.waitFor();
			
			return true;
		}catch(Exception e)
		{
			
		}
		return false;
	}

	
}