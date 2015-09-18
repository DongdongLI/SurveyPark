package surveypark.dao;

import java.util.List;

public interface BaseDao<T> {
	
	// write
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql,Object... objs);
	
	// read
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntitiesByHQL(String hql, Object...objects);
	// need to make sure the result exists and there is only one
	public Object uniqueResult(String hql, Object...objects);
}
