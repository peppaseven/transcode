package PTJ4.transcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VideoController {
	
	
//	@RequestMapping("videos")
//	public void videoList()
//	{
//		
//	}
	@RequestMapping("/detail")
	public String detail()
	{
		return "detail";
	}
}
