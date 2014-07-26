package com.busy.engine.dao.base;

import java.util.ArrayList;

/**
 *
 * @author Sourena
 * @param <T>
 */
public interface IGenericDao<T, ID>
{
    T find(ID id);
    ArrayList<T> findAll(Integer limit, Integer offset);
    ArrayList<T> findAllWithInfo(Integer limit, Integer offset);    
    ArrayList<T> findByColumn(String columnName, String columnValue, Integer limit, Integer offset);
    
    void add(T obj);
    T update(T obj);    
    
    int getAllCount();    
    T getRelatedInfo(T obj);
    T getRelatedObjects(T obj);
    
    void remove(T obj);
    void remove(Integer id);
    void removeAll();
    void removeByColumn(String columnName, String columnValue);    
}
