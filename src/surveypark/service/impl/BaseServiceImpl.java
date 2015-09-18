package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import surveypark.dao.BaseDao;
import surveypark.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T>{

	private BaseDao<T> baseDao;
	
	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objs) {
		baseDao.batchEntityByHQL(hql, objs);
	}

	@Override
	public T loadEntity(Integer id) {
		return baseDao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return baseDao.getEntity(id);
	}

	@Override
	public List<T> findEntitiesByHQL(String hql, Object... objects) {
		return baseDao.findEntitiesByHQL(hql, objects);
	}

	public Object uniqueResult(String hql, Object...objects){
		return baseDao.uniqueResult(hql, objects);
	}
}
