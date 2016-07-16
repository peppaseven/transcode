package transcode.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import PTJ4.transcode.module.User;
import PTJ4.transcode.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/configtest/transcode-servlet-test.xml"})
public class UserTest {
	
	@Autowired
	private UserService us;
	
	@Test
	public void registerUser()
	{
//		User u = new User("name2","thisguy","thispeop@ss.com");
//		us.register(u);
	}
	
	@Test
	public void login()
	{
		Map result = us.login("233@3322222.cnnd", "name 2");
		System.out.println(result);		
	}
	
	@Test
	public void getByEmail()
	{
		User u = us.getByEmail("233@3322222.cnn");
		System.out.println(u);
	}
	
	
	@Test
	public void deletePeople()
	{
		us.delete(2);
	}
	
	@Test
	public void update()
	{
		User newuser = new User("用户1","pass","p@b.c");
		us.updateUserInfo(newuser, 3);
		
	}
	
	
	

}