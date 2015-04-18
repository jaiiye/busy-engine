package com.busy.engine.dao;

import com.busy.engine.data.BasicConnection;
import com.busy.engine.entity.*;
import com.busy.engine.util.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map.Entry;
import java.lang.reflect.InvocationTargetException;

public class SiteImageDaoImpl extends BasicConnection implements Serializable, SiteImageDao
{

    private static final long serialVersionUID = 1L;
    private boolean cachingEnabled;

    public SiteImageDaoImpl()
    {
        cachingEnabled = false;
    }

    public SiteImageDaoImpl(boolean enableCache)
    {
        cachingEnabled = enableCache;
    }

    private static class SiteImageCache
    {

        public static final ConcurrentLruCache<Integer, SiteImage> siteImageCache = buildCache(findAll());
    }

    private void checkCacheState()
    {
        if (getCache().size() == 0)
        {
            System.out.println("Found the cache empty, rebuilding...");
            for (SiteImage i : findAll())
            {
                getCache().put(i.getSiteImageId(), i);
            }
        }
    }

    public static ConcurrentLruCache<Integer, SiteImage> getCache()
    {
        return SiteImageCache.siteImageCache;
    }

    protected Object readResolve()
    {
        return getCache();
    }

    public static ConcurrentLruCache<Integer, SiteImage> buildCache(ArrayList<SiteImage> siteImageList)
    {
        ConcurrentLruCache<Integer, SiteImage> cache = new ConcurrentLruCache<Integer, SiteImage>(siteImageList.size() + 1000);
        for (SiteImage i : siteImageList)
        {
            cache.put(i.getSiteImageId(), i);
        }
        return cache;
    }

    private static ArrayList<SiteImage> findAll()
    {
        ArrayList<SiteImage> siteImage = new ArrayList<>();
        try
        {
            getAllRecordsByTableName("siteImage");
            while (rs.next())
            {
                siteImage.add(SiteImage.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("SiteImage object's findAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return siteImage;
    }

    @Override
    public SiteImage find(Integer id)
    {
        return findByColumn("SiteImageId", id.toString(), null, null).get(0);
    }

    @Override
    public SiteImage findWithInfo(Integer id)
    {
        SiteImage siteImage = findByColumn("SiteImageId", id.toString(), null, null).get(0);

        try
        {

            getRecordById("ImageType", siteImage.getImageTypeId().toString());
            siteImage.setImageType(ImageType.process(rs));

            getRecordById("Site", siteImage.getSiteId().toString());
            siteImage.setSite(Site.process(rs));

        }
        catch (SQLException ex)
        {
            System.out.println("Object SiteImage method findWithInfo(Integer id) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return siteImage;
    }

    @Override
    public ArrayList<SiteImage> findAll(Integer limit, Integer offset)
    {
        ArrayList<SiteImage> siteImageList = new ArrayList<SiteImage>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            System.out.println("Find all operation for SiteImage, getting objects from cache...");
            checkCacheState();

            if (limit == null && offset == null)
            {
                siteImageList = new ArrayList<SiteImage>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }
        }

        if (!cachingEnabled || cacheNotUsed)
        {
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_image", limit, offset);
                while (rs.next())
                {
                    siteImageList.add(SiteImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteImage object's findAll method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return siteImageList;

    }

    @Override
    public ArrayList<SiteImage> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<SiteImage> siteImageList = new ArrayList<SiteImage>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            checkCacheState();

            System.out.println("Find all with info operation for SiteImage, getting objects from cache...");

            if (limit == null && offset == null)
            {
                siteImageList = new ArrayList<SiteImage>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }

            try
            {
                for (Entry e : getCache().getEntries())
                {
                    SiteImage siteImage = (SiteImage) e.getValue();

                    getRecordById("ImageType", siteImage.getImageTypeId().toString());
                    siteImage.setImageType(ImageType.process(rs));

                    getRecordById("Site", siteImage.getSiteId().toString());
                    siteImage.setSite(Site.process(rs));

                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteImage method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

        }

        if (!cachingEnabled || cacheNotUsed)
        {
            siteImageList = new ArrayList<SiteImage>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_image", limit, offset);
                while (rs.next())
                {
                    siteImageList.add(SiteImage.process(rs));
                }

                for (SiteImage siteImage : siteImageList)
                {

                    getRecordById("ImageType", siteImage.getImageTypeId().toString());
                    siteImage.setImageType(ImageType.process(rs));

                    getRecordById("Site", siteImage.getSiteId().toString());
                    siteImage.setSite(Site.process(rs));

                }

            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteImage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return siteImageList;
    }

    @Override
    public ArrayList<SiteImage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<SiteImage> siteImageList = new ArrayList<>();
        boolean cacheNotUsed = false;

        if (cachingEnabled)
        {
            if (limit == null && offset == null)
            {

                System.out.println("Find by column for SiteImage(" + columnName + "=" + columnValue + "), getting objects from cache...");
                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        SiteImage i = (SiteImage) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            siteImageList.add(i);
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                        siteImageList = null;
                    }
                }
            }
            else
            {
                cacheNotUsed = true;
            }
        }

        if (!cachingEnabled || cacheNotUsed)
        {
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_image", SiteImage.checkColumnName(columnName), columnValue, SiteImage.isColumnNumeric(columnName), limit, offset);
                while (rs.next())
                {
                    siteImageList.add(SiteImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteImage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return siteImageList;
    }

    @Override
    public int add(SiteImage obj)
    {
        boolean success = false;
        int id = 0;
        try
        {

            SiteImage.checkColumnSize(obj.getFileName(), 255);
            SiteImage.checkColumnSize(obj.getDescription(), 255);
            SiteImage.checkColumnSize(obj.getLinkUrl(), 255);

            openConnection();
            prepareStatement("INSERT INTO site_image(FileName,Description,LinkUrl,Rank,ImageTypeId,SiteId) VALUES (?,?,?,?,?,?);");
            preparedStatement.setString(1, obj.getFileName());
            preparedStatement.setString(2, obj.getDescription());
            preparedStatement.setString(3, obj.getLinkUrl());
            preparedStatement.setInt(4, obj.getRank());
            preparedStatement.setInt(5, obj.getImageTypeId());
            preparedStatement.setInt(6, obj.getSiteId());

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_image;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }

            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("SiteImage's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            obj.setSiteImageId(id);
            getCache().put(id, obj); //synchronizing between local cache and database
        }

        return id;
    }

    @Override
    public SiteImage update(SiteImage obj)
    {
        try
        {

            SiteImage.checkColumnSize(obj.getFileName(), 255);
            SiteImage.checkColumnSize(obj.getDescription(), 255);
            SiteImage.checkColumnSize(obj.getLinkUrl(), 255);

            openConnection();
            prepareStatement("UPDATE site_image SET FileName=?,Description=?,LinkUrl=?,Rank=?,ImageTypeId=?,SiteId=? WHERE SiteImageId=?;");
            preparedStatement.setString(1, obj.getFileName());
            preparedStatement.setString(2, obj.getDescription());
            preparedStatement.setString(3, obj.getLinkUrl());
            preparedStatement.setInt(4, obj.getRank());
            preparedStatement.setInt(5, obj.getImageTypeId());
            preparedStatement.setInt(6, obj.getSiteId());
            preparedStatement.setInt(7, obj.getSiteImageId());
            preparedStatement.executeUpdate();

            if (cachingEnabled)
            {
                getCache().put(obj.getSiteImageId(), obj);
            }
        }
        catch (Exception ex)
        {
            System.out.println("SiteImage's update error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return obj;
    }

    @Override
    public int getAllCount()
    {
        int count = 0;
        if (cachingEnabled)
        {
            count = getCache().size();
        }
        else
        {
            count = getAllRecordsCountByTableName("site_image");
        }
        return count;
    }

    @Override
    public void getRelatedInfo(SiteImage site_image)
    {

        try
        {

            getRecordById("ImageType", site_image.getImageTypeId().toString());
            site_image.setImageType(ImageType.process(rs));

            getRecordById("Site", site_image.getSiteId().toString());
            site_image.setSite(Site.process(rs));

        }
        catch (SQLException ex)
        {
            System.out.println("getRelatedInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

    }

    @Override
    public void getRelatedObjects(SiteImage site_image)
    {

    }

    @Override
    public boolean remove(SiteImage obj)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM site_image WHERE SiteImageId=" + obj.getSiteImageId() + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("SiteImage's remove error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            getCache().remove(obj.getSiteImageId());
        }

        return success;
    }

    @Override
    public boolean removeById(Integer id)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM site_image WHERE SiteImageId=" + id + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("removeById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            getCache().remove(id);
        }

        return success;
    }

    @Override
    public boolean removeAll()
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM site_image;");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("SiteImage's removeAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            getCache().clear();
        }

        return success;
    }

    @Override
    public boolean removeByColumn(String columnName, String columnValue)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM site_image WHERE " + SiteImage.checkColumnName(columnName) + "=" + columnValue + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("SiteImage's removeByColumn method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            ArrayList<Integer> keys = new ArrayList<Integer>();

            for (Entry e : getCache().getEntries())
            {
                try
                {
                    SiteImage i = (SiteImage) e.getValue();
                    if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                    {
                        keys.add(i.getSiteImageId());
                    }
                }
                catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                {
                    ex.printStackTrace();
                }
            }

            for (int id : keys)
            {
                getCache().remove(id);
            }
        }

        return success;
    }

    public boolean isCachingEnabled()
    {
        return cachingEnabled;
    }

    public void setCachingEnabled(boolean cachingEnabled)
    {
        this.cachingEnabled = cachingEnabled;
    }

    public void getRelatedImageType(SiteImage site_image)
    {
        try
        {
            getRecordById("ImageType", site_image.getImageTypeId().toString());
            site_image.setImageType(ImageType.process(rs));
        }
        catch (SQLException ex)
        {
            System.out.println("getImageType error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public void getRelatedSite(SiteImage site_image)
    {
        try
        {
            getRecordById("Site", site_image.getSiteId().toString());
            site_image.setSite(Site.process(rs));
        }
        catch (SQLException ex)
        {
            System.out.println("getSite error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public void getRelatedImageTypeWithInfo(SiteImage site_image)
    {
        site_image.setImageType(new ImageTypeDaoImpl().findWithInfo(site_image.getImageTypeId()));
    }

    public void getRelatedSiteWithInfo(SiteImage site_image)
    {
        site_image.setSite(new SiteDaoImpl().findWithInfo(site_image.getSiteId()));
    }

}
