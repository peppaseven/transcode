package PTJ4.transcode.module;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author vwings
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
	private Integer userId;
	
	@Column(name="u_name")
	private String username;
	
	@Column(name="u_pass")
	private String password;
	
	@Column(name="e_mail")
	private String email;
	
	/**
	 * 1.基本登录用户
	 * 2.facebook <废除>
	 * 3.google登录用户 <废除>
	 */
	@Column(name="u_type")
	private Integer accountType;
	
	/**
	 * 1,本地来源
	 * 2,网络图片
	 */
	@Column(name="u_avatar_type")
	private Integer avatarType;
	
	@Column(name="u_avatar")
	private String avatar;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
    ,fetch = FetchType.LAZY ,mappedBy = "user")
	private Set<SrcVideo> srcVideos = new HashSet<SrcVideo>();
	
	public User()
	{
		
	}
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	

	public User(String email, String password) {
		
		this.email = email;
		this.password = password;
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Set<SrcVideo> getSrcVideos() {
		return srcVideos;
	}
	public void setSrcVideos(Set<SrcVideo> srcVideos) {
		this.srcVideos = srcVideos;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getAvatarType() {
		return avatarType;
	}
	public void setAvatarType(Integer avatarType) {
		this.avatarType = avatarType;
	}
	
}
