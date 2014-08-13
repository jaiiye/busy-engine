





































    package com.busy.engine.dao;

import com.busy.engine.entity.Template;
import com.busy.engine.entity.Site;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteDaoImpl extends BasicConnection implements Serializable, SiteDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Site find(Integer id)
        {
            return findByColumn("SiteId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Site> findAll(Integer limit, Integer offset)
        {
            ArrayList<Site> site = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site", limit, offset);
                while(rs.next())
                {
                    site.add(Site.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Site object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
         
        }
        
        @Override
        public ArrayList<Site> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Site> siteList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site", limit, offset);
                while (rs.next())
                {
                    siteList.add(Site.process(rs));
                }

                
                    for(Site site : siteList)
                    {
                        
                            getRecordById("Template", site.getTemplateId().toString());
                            site.setTemplate(Template.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Site method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteList;
        }
        
        @Override
        public ArrayList<Site> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Site> site = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site", Site.checkColumnName(columnName), columnValue, Site.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site.add(Site.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Site's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        } 
    
        @Override
        public int add(Site obj)
        {
            int id = 0;
            try
            {
                
                Site.checkColumnSize(obj.getSiteName(), 100);
                Site.checkColumnSize(obj.getDomain(), 255);
                
                Site.checkColumnSize(obj.getUrl(), 255);
                Site.checkColumnSize(obj.getLogoTitle(), 100);
                Site.checkColumnSize(obj.getLogoImage(), 255);
                
                Site.checkColumnSize(obj.getEmailHost(), 255);
                
                Site.checkColumnSize(obj.getEmailUsername(), 255);
                Site.checkColumnSize(obj.getEmailPassword(), 45);
                
                Site.checkColumnSize(obj.getLocale(), 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site(SiteName,Domain,Mode,Url,LogoTitle,LogoImage,UseAsStore,EmailHost,EmailPort,EmailUsername,EmailPassword,SiteStatus,Locale,TemplateId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getSiteName());
                preparedStatement.setString(2, obj.getDomain());
                preparedStatement.setInt(3, obj.getMode());
                preparedStatement.setString(4, obj.getUrl());
                preparedStatement.setString(5, obj.getLogoTitle());
                preparedStatement.setString(6, obj.getLogoImage());
                preparedStatement.setInt(7, obj.getUseAsStore());
                preparedStatement.setString(8, obj.getEmailHost());
                preparedStatement.setInt(9, obj.getEmailPort());
                preparedStatement.setString(10, obj.getEmailUsername());
                preparedStatement.setString(11, obj.getEmailPassword());
                preparedStatement.setInt(12, obj.getSiteStatus());
                preparedStatement.setString(13, obj.getLocale());
                preparedStatement.setInt(14, obj.getTemplateId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Site's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Site update(Site obj)
        {
           try
            {   
                
                Site.checkColumnSize(obj.getSiteName(), 100);
                Site.checkColumnSize(obj.getDomain(), 255);
                
                Site.checkColumnSize(obj.getUrl(), 255);
                Site.checkColumnSize(obj.getLogoTitle(), 100);
                Site.checkColumnSize(obj.getLogoImage(), 255);
                
                Site.checkColumnSize(obj.getEmailHost(), 255);
                
                Site.checkColumnSize(obj.getEmailUsername(), 255);
                Site.checkColumnSize(obj.getEmailPassword(), 45);
                
                Site.checkColumnSize(obj.getLocale(), 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site SET SiteName=?,Domain=?,Mode=?,Url=?,LogoTitle=?,LogoImage=?,UseAsStore=?,EmailHost=?,EmailPort=?,EmailUsername=?,EmailPassword=?,SiteStatus=?,Locale=?,TemplateId=? WHERE SiteId=?;");                    
                preparedStatement.setString(1, obj.getSiteName());
                preparedStatement.setString(2, obj.getDomain());
                preparedStatement.setInt(3, obj.getMode());
                preparedStatement.setString(4, obj.getUrl());
                preparedStatement.setString(5, obj.getLogoTitle());
                preparedStatement.setString(6, obj.getLogoImage());
                preparedStatement.setInt(7, obj.getUseAsStore());
                preparedStatement.setString(8, obj.getEmailHost());
                preparedStatement.setInt(9, obj.getEmailPort());
                preparedStatement.setString(10, obj.getEmailUsername());
                preparedStatement.setString(11, obj.getEmailPassword());
                preparedStatement.setInt(12, obj.getSiteStatus());
                preparedStatement.setString(13, obj.getLocale());
                preparedStatement.setInt(14, obj.getTemplateId());
                preparedStatement.setInt(15, obj.getSiteId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Site's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("site");
        }
        
        
        @Override
        public void getRelatedInfo(Site site)
        {
            
                try
                { 
                    
                            getRecordById("Template", site.getTemplateId().toString());
                            site.setTemplate(Template.process(rs));                                       
                    
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
        public void getRelatedObjects(Site site)
        {
            site.setSiteAttributeList(new SiteAttributeDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteFolderList(new SiteFolderDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteImageList(new SiteImageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteItemList(new SiteItemDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteLanguageList(new SiteLanguageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSitePageList(new SitePageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setUserGroupList(new UserGroupDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Site obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + obj.getSiteId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Site's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + id + ";");           
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
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Site's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM site WHERE " + Site.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Site's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedSiteAttributeList(Site site)
        {           
            site.setSiteAttributeList(new SiteAttributeDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteFolderList(Site site)
        {           
            site.setSiteFolderList(new SiteFolderDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteImageList(Site site)
        {           
            site.setSiteImageList(new SiteImageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteItemList(Site site)
        {           
            site.setSiteItemList(new SiteItemDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteLanguageList(Site site)
        {           
            site.setSiteLanguageList(new SiteLanguageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSitePageList(Site site)
        {           
            site.setSitePageList(new SitePageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedUserGroupList(Site site)
        {           
            site.setUserGroupList(new UserGroupDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
        
                             
    }

