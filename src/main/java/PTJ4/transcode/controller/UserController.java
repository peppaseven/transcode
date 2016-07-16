package PTJ4.transcode.controller;

import java.io.IOException;
import java.security.Key;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import PTJ4.transcode.bean.TokenInfo;
import PTJ4.transcode.module.User;
import PTJ4.transcode.service.MailService;
import PTJ4.transcode.service.UserService;
import PTJ4.transcode.util.BeanValueExchangeUtil;
import PTJ4.transcode.util.FileUploadUtil;
import PTJ4.transcode.util.JsonUtil;
import PTJ4.transcode.util.TokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;

	
	/**
	 * 普通用户登录
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String ,Object> login(
			@RequestParam(value = "identifier", required = true) String identifier,
			@RequestParam(value = "pass", required = true) String password,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		Map<String,Object> loginResultJson = userService.login(identifier, password);
		
		if(((Integer)loginResultJson.get(JsonUtil.RETURN_STATUS)) == 1)
		{
			//成功登陆加入session
			request.getSession().setAttribute("user", (User)loginResultJson.get(JsonUtil.RETURN_DATA));	
		}
		
		return loginResultJson;
	}
	/**
	 * 普通用户注册
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> register(	
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "pass", required = true) String password,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "usertype" ,required = false) Integer userType,
			@RequestParam(value = "avatar" ,required = false) String avatar,
			HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		if(userType == null) {
			//普通注册账户
			userType = 1;
		}
		if(avatar == null) {
			//用户账户为空
			avatar = "";
		}
		Map<String , Object> registerResultJson = userService.register(email,password,username,userType,avatar);
		
		int status = (Integer)registerResultJson.get("status");
		if(status == 1)
		{
			//mailService.sendRegisterEmail(email);
			
			//注册完直接登陆
			request.getSession().setAttribute("user", (User)registerResultJson.get(JsonUtil.RETURN_DATA));	

		}
		
		return registerResultJson;
	}
	
	
	@RequestMapping("/registeroauth")
	public ModelAndView oauthRegister(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "avatarurl", required = true) String avatarurl,
			@RequestParam(value = "usertype", required = true) String userType
			)
	{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bindaccount");
		
		mav.addObject("email",email);
		mav.addObject("avatarUrl",avatarurl);
		mav.addObject("userType",userType);
		
		return mav;
	}
	
	@RequestMapping("/checkbind")
	@ResponseBody
	public Map<String,Object> checkBind(@RequestParam(value = "email" ) String email,
			@RequestParam(value = "usertype" ) Integer usertype,
			HttpServletRequest request)
	{
		User user = userService.getByEmail(email);
		if(user != null) {
			//登陆系统
			if(user.getAccountType() <= 1){
				//change normal account to google account
				user.setAccountType(usertype);
				user = userService.setUserType(email, usertype);
			}
			
			request.getSession().setAttribute("user", user);
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS);
		}else {
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS);
			
		}
		
	}
	
	
	/**
	 * 普通用户登出
	 * @throws IOException 
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String,Object> logout(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		User user = (User)request.getSession().getAttribute("user");
		Integer userType = user.getAccountType();
		
		request.getSession().removeAttribute("user");
		return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS,userType);
	}
	
	/**
	 * 更新用户信息
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> updateUser(@RequestParam(value="avatar",required = false) CommonsMultipartFile avatarfile,
			@RequestParam(value = "username",required = false) String username,
			@RequestParam(value = "password",required = false) String password,
			HttpServletRequest request)
	{
		User user = (User)request.getSession().getAttribute("user");
		User newUser = new User();
		BeanValueExchangeUtil.exchangeBeanValue(newUser, user);
		if(avatarfile!=null)
		{
			//设置为本地头像
			newUser.setAvatarType(1);
			String avatarFileName = user.getEmail() + "_avatar"+ avatarfile.getOriginalFilename().substring(avatarfile.getOriginalFilename().lastIndexOf("."));
			newUser.setAvatar(avatarFileName);
			//上传头像
			FileUploadUtil.saveFile(avatarfile,request.getSession().getServletContext().getRealPath("/static/useravatar/"), avatarFileName);
		}
		
		
		newUser.setPassword(password);
		newUser.setUsername(username);
		

		userService.updateUserInfo(newUser, newUser.getUserId());
		
		request.getSession().setAttribute("user", userService.getById(user.getUserId()));
		return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS);
	}
	
}
