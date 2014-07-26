





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                getAllRecordsByTableName("site");
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
                System.out.println("Object Site's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        } 
    
        @Override
        public void add(Site obj)
        {
            try
            {
                
                Site.checkColumnSize(obj.getSiteName(), 100);
                Site.checkColumnSize(obj.getDomain(), 255);
                
                Site.checkColumnSize(obj.getUrl(), 255);
                Site.checkColumnSize(obj.getEmailHost(), 255);
                
                Site.checkColumnSize(obj.getEmailUsername(), 255);
                Site.checkColumnSize(obj.getEmailPassword(), 45);
                
                Site.checkColumnSize(obj.getLocale(), 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site(SiteName,Domain,Mode,Url,EmailHost,EmailPort,EmailUsername,EmailPassword,SiteStatus,Locale,TemplateId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getSiteName());
                preparedStatement.setString(2, obj.getDomain());
                preparedStatement.setInt(3, obj.getMode());
                preparedStatement.setString(4, obj.getUrl());
                preparedStatement.setString(5, obj.getEmailHost());
                preparedStatement.setInt(6, obj.getEmailPort());
                preparedStatement.setString(7, obj.getEmailUsername());
                preparedStatement.setString(8, obj.getEmailPassword());
                preparedStatement.setInt(9, obj.getSiteStatus());
                preparedStatement.setString(10, obj.getLocale());
                preparedStatement.setInt(11, obj.getTemplateId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Site's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String SiteName, String Domain, Integer Mode, String Url, String EmailHost, Integer EmailPort, String EmailUsername, String EmailPassword, Integer SiteStatus, String Locale, Integer TemplateId)
        {   
            int id = 0;
            try
            {
                
                Site.checkColumnSize(SiteName, 100);
                Site.checkColumnSize(Domain, 255);
                
                Site.checkColumnSize(Url, 255);
                Site.checkColumnSize(EmailHost, 255);
                
                Site.checkColumnSize(EmailUsername, 255);
                Site.checkColumnSize(EmailPassword, 45);
                
                Site.checkColumnSize(Locale, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site(SiteName,Domain,Mode,Url,EmailHost,EmailPort,EmailUsername,EmailPassword,SiteStatus,Locale,TemplateId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, SiteName);
                preparedStatement.setString(2, Domain);
                preparedStatement.setInt(3, Mode);
                preparedStatement.setString(4, Url);
                preparedStatement.setString(5, EmailHost);
                preparedStatement.setInt(6, EmailPort);
                preparedStatement.setString(7, EmailUsername);
                preparedStatement.setString(8, EmailPassword);
                preparedStatement.setInt(9, SiteStatus);
                preparedStatement.setString(10, Locale);
                preparedStatement.setInt(11, TemplateId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSite error: " + ex.getMessage());
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
                Site.checkColumnSize(obj.getEmailHost(), 255);
                
                Site.checkColumnSize(obj.getEmailUsername(), 255);
                Site.checkColumnSize(obj.getEmailPassword(), 45);
                
                Site.checkColumnSize(obj.getLocale(), 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site SET SiteName=?,Domain=?,Mode=?,Url=?,EmailHost=?,EmailPort=?,EmailUsername=?,EmailPassword=?,SiteStatus=?,Locale=?,TemplateId=? WHERE SiteId=?;");                    
                preparedStatement.setString(1, obj.getSiteName());
                preparedStatement.setString(2, obj.getDomain());
                preparedStatement.setInt(3, obj.getMode());
                preparedStatement.setString(4, obj.getUrl());
                preparedStatement.setString(5, obj.getEmailHost());
                preparedStatement.setInt(6, obj.getEmailPort());
                preparedStatement.setString(7, obj.getEmailUsername());
                preparedStatement.setString(8, obj.getEmailPassword());
                preparedStatement.setInt(9, obj.getSiteStatus());
                preparedStatement.setString(10, obj.getLocale());
                preparedStatement.setInt(11, obj.getTemplateId());
                preparedStatement.setInt(12, obj.getSiteId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SiteId,String SiteName,String Domain,Integer Mode,String Url,String EmailHost,Integer EmailPort,String EmailUsername,String EmailPassword,Integer SiteStatus,String Locale,Integer TemplateId)
        {  
            try
            {   
                
                Site.checkColumnSize(SiteName, 100);
                Site.checkColumnSize(Domain, 255);
                
                Site.checkColumnSize(Url, 255);
                Site.checkColumnSize(EmailHost, 255);
                
                Site.checkColumnSize(EmailUsername, 255);
                Site.checkColumnSize(EmailPassword, 45);
                
                Site.checkColumnSize(Locale, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site SET SiteName=?,Domain=?,Mode=?,Url=?,EmailHost=?,EmailPort=?,EmailUsername=?,EmailPassword=?,SiteStatus=?,Locale=?,TemplateId=? WHERE SiteId=?;");                    
                preparedStatement.setString(1, SiteName);
                preparedStatement.setString(2, Domain);
                preparedStatement.setInt(3, Mode);
                preparedStatement.setString(4, Url);
                preparedStatement.setString(5, EmailHost);
                preparedStatement.setInt(6, EmailPort);
                preparedStatement.setString(7, EmailUsername);
                preparedStatement.setString(8, EmailPassword);
                preparedStatement.setInt(9, SiteStatus);
                preparedStatement.setString(10, Locale);
                preparedStatement.setInt(11, TemplateId);
                preparedStatement.setInt(12, SiteId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site");
        }
        
        
        @Override
        public Site getRelatedInfo(Site site)
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
              
            
            return site;
        }
        
        
        @Override
        public Site getRelatedObjects(Site site)
        {
            site.setSiteAttributeList(new SiteAttributeDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteFolderList(new SiteFolderDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteImageList(new SiteImageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteItemList(new SiteItemDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteLanguageList(new SiteLanguageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSitePageList(new SitePageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setUserGroupList(new UserGroupDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
             
            return site;
        }
        
        
        
        @Override
        public void remove(Site obj)
        {            
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + obj.getSiteId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM site;");
            }
            catch (Exception ex)
            {
                System.out.println("Site's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site WHERE " + Site.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Site's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Site getRelatedSiteAttributeList(Site site)
        {           
            site.setSiteAttributeList(new SiteAttributeDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
                    
        public Site getRelatedSiteFolderList(Site site)
        {           
            site.setSiteFolderList(new SiteFolderDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
                    
        public Site getRelatedSiteImageList(Site site)
        {           
            site.setSiteImageList(new SiteImageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
                    
        public Site getRelatedSiteItemList(Site site)
        {           
            site.setSiteItemList(new SiteItemDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
                    
        public Site getRelatedSiteLanguageList(Site site)
        {           
            site.setSiteLanguageList(new SiteLanguageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
                    
        public Site getRelatedSitePageList(Site site)
        {           
            site.setSitePageList(new SitePageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
                    
        public Site getRelatedUserGroupList(Site site)
        {           
            site.setUserGroupList(new UserGroupDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
            return site;
        }        
        
                             
    }

