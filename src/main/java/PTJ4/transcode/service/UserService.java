package PTJ4.transcode.service;

import java.util.List;
import java.util.Map;

import PTJ4.transcode.module.User;

public interface UserService {

	/**
     * 用户注册方法
     * @param user 保存的新用户对象
     */
	Map<String, Object> register(String email,String password,String username,Integer userType,String avatar);
    	
	/**
	 * 用户登录
	 * @param identifier
	 * @param password
	 * @return
	 */
	Map<String,Object> login(String identifier,String password);
	
	boolean checkPassword(User user,String password);	
	
	/**
	 * 删除用户
	 * @param id
	 */
	void delete(int id);
	
	/**
	 * 更新用户信息
	 * @param newuUser
	 * @param id
	 */
	void updateUserInfo(User newUser,int id);

    List<User> getAll();

    User getByName(String name);

    User getByEmail(String email);
    
    User getById(int id);
    
    User setUserType(String email, Integer usertype);
    
	
}
