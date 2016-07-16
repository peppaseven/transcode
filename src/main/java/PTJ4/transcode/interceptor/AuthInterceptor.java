package PTJ4.transcode.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import PTJ4.transcode.module.User;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User currentUser = (User)request.getSession().getAttribute("user");
		if(currentUser != null)
		{
			response.sendRedirect(request.getContextPath());
			return false;
		}else
		{
			return true;
		}
		
	}

	
}
