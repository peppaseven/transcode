package PTJ4.transcode.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import PTJ4.transcode.module.User;
import PTJ4.transcode.service.MailService;
import PTJ4.transcode.util.ExecUtil;
import PTJ4.transcode.util.JsonUtil;

@Controller
public class TranscodeController {

	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/api/submitjob",method =  { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> distributeTranscode(
		@RequestParam("input") String input,
		@RequestParam("output") String output,
		@RequestParam("link") String downloadLink,
		HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("user");
		boolean result =  ExecUtil.submitJob(input,output);
		String videoName = output.substring(output.lastIndexOf("/")+1);
		if(result){
			
			mailService.sendTranscodeFinishEmail(user.getUsername(),videoName , downloadLink, user.getEmail());
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS);
		}else
		{
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS);
		}
	}
}
