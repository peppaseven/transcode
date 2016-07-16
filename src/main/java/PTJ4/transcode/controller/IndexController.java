package PTJ4.transcode.controller;

import java.security.Key;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * @author Vwings
 *
 */
@Controller
public class IndexController {
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET )
	public String register()
	{
		return "register";
	}
	
	@RequestMapping( value ="/login",method=RequestMethod.GET )
	public String login()
	{
		return "login";
	}
	@RequestMapping("/videos")
	public String videos()
	{
		return "videos";
	}
	@RequestMapping("/convert")
	public String convert()
	{
		return "convert";
	}
	
	@RequestMapping("/profile")
	public String profile()
	{	
		return "editprofile";
	}
	
	@RequestMapping("/bindaccount")
	public String bind()
	{
		return "bindaccount";
	}
	
	
	@RequestMapping("/paypal")
	public String paypal()
	{
		return "paypal";
	}
	
	
//	@RequestMapping("jwttest")
//	@ResponseBody
//	public String jwt()
//	{
//		Key key = MacProvider.generateKey();
//		String token = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, key).compact();
//		
//		
//		
//		return token;
//	}
	


}
