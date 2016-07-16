package transcode.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import PTJ4.transcode.util.HttpClientUtil;

public class HttpClientTest {
	
	@Test
	public void httptest()
	{
		
		int targetId  = 156;
		Map<String,String> paramsMap  = new HashMap<String,String>();
		paramsMap.put("type", String.valueOf(1));
		paramsMap.put("targetId", String.valueOf(156));
		paramsMap.put("timePeriod", String.valueOf(4750));
		paramsMap.put("endTime", "2016-3-21 16:02");

		String response = HttpClientUtil.post("http://reportlocal.tingyun.com/tingyun-report-alarm/alarm/"+targetId+"/json/event/list", paramsMap);
		//system.out.println(response);
	}
	
	
	@Test
	public void httpGet()
	{
//		String testSSLUrl = "https://api.github.com/users/defunkt";
//		String response = HttpClientUtil.get(testSSLUrl);
	}
	
	@Test
	public void downLoadVideo()
	{
//		String videoUrl = "http://www.w3school.com.cn/i/movie.ogg";
//		boolean response = HttpClientUtil.getVideo(videoUrl);
//		System.out.println(response);
	}
}
