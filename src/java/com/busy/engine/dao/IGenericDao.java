package com.busy.engine.dao;

import com.busy.engine.data.Column;
import java.util.ArrayList;

/**
 *
 * @author Sourena
 * @param <T>
 */
public interface IGenericDao<T, ID>
{
    T find(ID id);
    T findWithInfo(ID id);
    ArrayList<T> findAll(Integer limit, Integer offset);
    ArrayList<T> findAllWithInfo(Integer limit, Integer offset);    
    ArrayList<T> findByColumn(String columnName, String columnValue, Integer limit, Integer offset);
    ArrayList<T> findByColumns(Column... columns);
    
    int add(T obj);
    T update(T obj);    
    
    int getAllCount();    
    void getRelatedInfo(T obj);
    void getRelatedObjects(T obj);
    
    boolean remove(T obj);
    boolean removeById(ID id);
    boolean removeAll();
    boolean removeByColumn(String columnName, String columnValue);    
}
