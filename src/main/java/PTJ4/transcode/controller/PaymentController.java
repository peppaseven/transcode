package PTJ4.transcode.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import PTJ4.transcode.util.HttpClientUtil;
import PTJ4.transcode.util.JsonUtil;

@Controller
public class PaymentController {


	
	@Value("${identitytoken}")
	private String identitytoken;
	/**
	 * paypal 返回的transcationID
	 * @param transcationId
	 */
	@RequestMapping(value="/api/paypaltransactionback",method = RequestMethod.GET)
	public ModelAndView transactionBack(@RequestParam(value="tx") String transactionId,HttpServletRequest request)
	{
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("cmd","_notify-synch");
		params.put("tx", transactionId);
		params.put("at", identitytoken);
		
		String responseString = HttpClientUtil.post("https://www.sandbox.paypal.com/cgi-bin/webscr", params);
		String paystatus = responseString.split("\n")[0];
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("paysuccess");
		if(paystatus.equals("SUCCESS")){
			request.getSession().setAttribute("paystatus", "success");
		}else {
			request.getSession().setAttribute("paystatus", "fail");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value="/api/checkpaystatus",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> checkPayStatus(HttpServletRequest request){
		String paystatus = (String)request.getSession().getAttribute("paystatus");
		if(paystatus != null && paystatus.equals("success")){
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS);
		}else {
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS);
		}
	}
	@RequestMapping(value="/api/clearpaystatus",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> clearPayStatus(HttpServletRequest request){
		request.getSession().removeAttribute("paystatus");
		
		return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS);
	}
	
}
