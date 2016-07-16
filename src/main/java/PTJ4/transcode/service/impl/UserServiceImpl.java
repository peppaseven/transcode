package PTJ4.transcode.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PTJ4.transcode.dao.UserDao;
import PTJ4.transcode.module.User;
import PTJ4.transcode.service.UserService;
import PTJ4.transcode.util.EncryptionUtil;
import PTJ4.transcode.util.JsonUtil;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public Map<String, Object> register(String email,String password,String username,Integer userType,String avatar) {
		
		if(email.isEmpty())
		{
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "username CANNOT be null");
		}
		if(password.isEmpty())
		{
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "password CANNOT be bull");
		}
		if(username.isEmpty())
		{
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "username CANNOT be null");
		}
		
		if(this.getByEmail(email)!=null )
		{
			//邮箱已被注册
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS,"Email Already been registered!");
			
		}else
		{
			//加密密码
			User user = new User(username,EncryptionUtil.getHash(password),email);
			
			user.setAccountType(userType);
			if(userType == 1) {
				user.setAvatar("user-default.png");
				//本地头像
				user.setAvatarType(1);
			} else if (userType == 2 || userType == 3) {
				user.setAvatar(avatar);
				//网络头像
				user.setAvatarType(2);
			}
			userDao.save(user);
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS,"register ok!",user);
		}
	}

	@Override
	public Map<String, Object> login(String identifier, String password) {
		User user = null;
    	if((user = this.getByName(identifier)) != null || (user = this.getByEmail(identifier)) != null)
    	{
    		if(this.checkPassword(user, password))
    		{
    			//login ok
				return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS,100,"登录成功",user);

    		}
    		else{
    			//fail password error
                return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS,-102,"密码错误");
    		}
    		
    	}
    	else{
    		//no user
            return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS,-101,"没有此用户");
    		
    	}
	}

	@Override
	public boolean checkPassword(User user, String password) {
		if(user.getPassword().equals(EncryptionUtil.getHash(password)))
        {
            return true;
        }else
            return false;
	}

	
	@Override
	@Transactional
	public void delete(int id) {
        userDao.delete(User.class,id);
    }

	@Override
    public List<User> getAll() {
        return userDao.getAll("User");
    }

	@Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

	@Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

	@Override
	@Transactional
	public void updateUserInfo(User newUser, int id) {
		//密码不空加密
		if(newUser.getPassword()!=null)
		{
			newUser.setPassword(EncryptionUtil.getHash(newUser.getPassword()));
		}
		userDao.update(User.class,newUser, id);
	}

	@Override
	public User getById(int id) {	
		return userDao.getById(User.class, id);
	}
	
	@Override
	@Transactional
	public User setUserType(String email, Integer usertype){
		User user = userDao.getByEmail(email);
		user.setAccountType(usertype);
		
		return user;
	}
	
}
