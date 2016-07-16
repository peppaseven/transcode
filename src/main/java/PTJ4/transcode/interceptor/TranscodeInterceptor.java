package PTJ4.transcode.interceptor;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import PTJ4.transcode.dao.impl.UserDaoImpl;
import PTJ4.transcode.module.User;
import PTJ4.transcode.service.UserService;
import PTJ4.transcode.util.TokenUtil;
import io.jsonwebtoken.Jwts;

/**
 * 请求一些关键接口时会经过此拦截器
 * @author Administrator
 *
 */
public class TranscodeInterceptor extends HandlerInterceptorAdapter{

	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		

		User user = (User)request.getSession().getAttribute("user");
		
		if(user == null)
		{
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		else
		{
			return true;
		}

	}
	
	/**
	 * 验证token
	 * @return
	 */
	private boolean validateToken(Key key,String token)
	{
		try{

			boolean userExist = checkUserExist(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getIssuer());
			boolean expired = checkExpired(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getExpiration());
			
			if(!expired && userExist)
			{
				//验证成功
				return true;
			}else
			{
				//验证失败
				return false;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			//not valid token
			return false;
		}
		

		
	}
	
	/**
	 * 验证token是否过期
	 * @return
	 */
	private boolean checkExpired(Date expires)
	{
		if(expires.getTime() < new Date().getTime())
		{
			//过期
			return true;
		}else
		{
			//没过期
			return false;
		}
		
	}
	
	/**
	 * 检查用户存在
	 * @param userIdString
	 * @return
	 */
	private boolean checkUserExist(String userIdString)
	{
		int userId = Integer.parseInt(userIdString);
		
		User user = userService.getById(userId);
		
		if(user != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
