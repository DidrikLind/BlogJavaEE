package io.github.didriklind.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class GenericDAO<T> {
  
 
    @PersistenceContext(unitName = "TestBlogProjectPU")
    protected EntityManager em;
 
    private Class<T> entityClass;
    
    
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    public void save(T entity) {
        em.persist(entity);
    }
 
    public void delete(T entity) {
    	em.remove(entity);
    }
 
    public T update(T entity) {
        return em.merge(entity);
    }
 
    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }
 
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query query = em.createQuery("SELECT t FROM " + entityClass.getSimpleName() +" t");
		return query.getResultList();
    }
}
