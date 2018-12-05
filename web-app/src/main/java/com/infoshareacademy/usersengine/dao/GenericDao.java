package com.infoshareacademy.usersengine.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.StringJoiner;

public class GenericDao<T, K> {

    private static final Integer ARGUMENTS_INDEX = 0;
    private static final String QUERY_SELECT = "SELECT";
    private static final String QUERY_FROM = "FROM";
    private static final String QUERY_ALIAS = "e";
    private static final String WHITESPACE = " ";

    @PersistenceContext
    private EntityManager entityManager;

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(K id) {
        final T entity = findById(id);
        if (isEntityPresent(entity)) {
            entityManager.remove(entity);
        }
    }

    public T findById(K id) {
        return entityManager.find(getEntityClass(), id);
    }

    public List<T> findAll(){
        final Query query = entityManager.createQuery(prepareQuery());
        return query.getResultList();
    }

    private Class<T> getEntityClass() {
        return (Class<T>) (getParameterizedType(getClass())).getActualTypeArguments()[ARGUMENTS_INDEX];
    }

    private ParameterizedType getParameterizedType(Class entityClass) {
        return (ParameterizedType) entityClass.getGenericSuperclass();
    }

    private Boolean isEntityPresent(T entity) {
        return entity != null;
    }

    private String prepareQuery() {
        final StringJoiner query = new StringJoiner(WHITESPACE);
        query.add(QUERY_SELECT);
        query.add(QUERY_ALIAS);
        query.add(QUERY_FROM);
        query.add(getEntitySimpleName());
        query.add(QUERY_ALIAS);
        return query.toString();
    }

    private String getEntitySimpleName() {
        return getEntityClass().getSimpleName();
    }


}
