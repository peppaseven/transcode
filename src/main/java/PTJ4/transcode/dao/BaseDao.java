package PTJ4.transcode.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {

	/**
     * 
     * @param entity
     */
    void save(Object entity);

    /**
     * 
     * @param entity
     */
    <T> void update(Class<T> entityClass,T newEntity, Serializable entityId);
    /**
     * 
     * @param entityClass
     * @param entityId
     * @param <T>
     */
    <T> void delete(Class<T> entityClass,Serializable entityId);

    /**
     * 
     * @param entityClass
     * @param entityId
     * @return
     */
    <T> T getById(Class<T> entityClass,Serializable entityId);

    /**
     * 
     * @param entityClassName
     * @param <T>
     * @return
     */
    <T> List<T> getAll(String entityClassName);
}
