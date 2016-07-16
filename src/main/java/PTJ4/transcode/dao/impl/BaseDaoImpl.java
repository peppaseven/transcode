package PTJ4.transcode.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import PTJ4.transcode.dao.BaseDao;
import PTJ4.transcode.util.BeanValueExchangeUtil;

@Repository
public class BaseDaoImpl implements BaseDao {

	@PersistenceContext
	protected EntityManager  em;
	
	@Override
	public void save(Object entity) {
		em.persist(entity);
		
	}

	

	@Override
	public <T> void delete(Class<T> entityClass, Serializable entityId) {
		T t = this.getById(entityClass, entityId);
		em.remove(t);
		
	}

	@Override
	public <T> T getById(Class<T> entityClass, Serializable entityId) {
		return em.find(entityClass,entityId);
	}

	@Override
	public <T> List<T> getAll(String entityClassName) {
		List<T> results =  em.createQuery("select t from "+entityClassName+" t").getResultList();
        return results;
	}



	@Override
	public <T> void update(Class<T> entityClass,T newEntity, Serializable entityId) {
		T oldEntity = this.getById(entityClass, entityId);
		BeanValueExchangeUtil.exchangeBeanValue(oldEntity, newEntity);
		
	}
	
}
