package transcode.test;

import org.junit.Test;

import PTJ4.transcode.util.SplitVideoUtil;

public class SplitTest{
	

	@Test
	public void split()
	{
		SplitVideoUtil.split("/tmp/transcode/vwings/vwings_1464053121078_22", "vwings_1464053121078_22", "vwings_1464053121078_22.mp4");

	}
	
	
	
}