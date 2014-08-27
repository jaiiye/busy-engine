package com.busy.engine.util;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Sourena
 */
public class ConcurrentLruCache<K, V> implements LruCache<K, V>
{
    private final ReentrantLock lock = new ReentrantLock();
    private final Map<K, V> map = new ConcurrentHashMap<K, V>(16, 0.9f, 1);
    private final Deque<K> queue = new LinkedList<K>();
    private final int limit;

    public ConcurrentLruCache(int limit)
    {
        this.limit = limit;
    }

    @Override
    public void put(K key, V value)
    {
        V oldValue = map.put(key, value);
        
        if (oldValue != null)
        {
            removeThenAddKey(key);
        }
        else
        {
            addKey(key);
        }
        
        if (map.size() > limit)
        {
            map.remove(removeLast());
        }
    }

    @Override
    public V get(K key)
    {
        removeThenAddKey(key);
        return map.get(key);
    }
    
    @Override
    public void remove(K key)
    {
        removeFirstOccurrence(key);
        map.remove(key);
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public Collection<V> getValues() 
    {
        return map.values();    
    }
    
    @Override
    public Set<Entry<K,V>> getEntries() 
    {
        return map.entrySet();    
    }
    
    @Override
    public void clear()
    {
       map.clear();
       queue.clear();
    }
    
    private void addKey(K key)
    {
        lock.lock();
        try
        {
            queue.addFirst(key);
        }
        finally
        {
            lock.unlock();
        }
    }

    private K removeLast()
    {
        lock.lock();
        try
        {
            final K removedKey = queue.removeLast();
            return removedKey;
        }
        finally
        {
            lock.unlock();
        }
    }

    private void removeThenAddKey(K key)
    {
        lock.lock();
        try
        {
            queue.removeFirstOccurrence(key);
            queue.addFirst(key);
        }
        finally
        {
            lock.unlock();
        }
    }

    private void removeFirstOccurrence(K key)
    {
        lock.lock();
        try
        {
            queue.removeFirstOccurrence(key);
        }
        finally
        {
            lock.unlock();
        }
    }

    
    public String toString()
    {
        return map.toString();
    }    
}
