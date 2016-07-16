package PTJ4.transcode.bean;

import java.util.Date;

import PTJ4.transcode.module.User;

public class TokenInfo {

	private String token;
	
	private Date expires;
	
	private User user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
