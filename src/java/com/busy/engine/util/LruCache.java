package com.busy.engine.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sourena
 */
public interface LruCache<K, V> 
{
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    int size();
    Collection<V> getValues();
    Set<Map.Entry<K,V>> getEntries();
    void clear();
}
