package PTJ4.transcode.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import PTJ4.transcode.dao.UserDao;
import PTJ4.transcode.module.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public User getByName(String name) {
		try{
			Query query = em.createQuery("select u from User u where u.username =:name");
	        query.setParameter("name",name);
	        User user = (User)query.getSingleResult();
	        return user;
		}catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public User getByEmail(String email) {
		try{
			Query query = em.createQuery("select u from User u where u.email =:email");
	        query.setParameter("email",email);
	        User user = (User)query.getSingleResult();
	        return user;
		}catch (NoResultException e)
		{
			return null;
		}
	}

}
