





































    package com.busy.engine.dao;

import com.busy.engine.entity.SiteLanguage;
import com.busy.engine.entity.Site;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteLanguageDaoImpl extends BasicConnection implements Serializable, SiteLanguageDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SiteLanguage find(Integer id)
        {
            return findByColumn("SiteLanguageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteLanguage> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteLanguage> site_language = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_language");
                while(rs.next())
                {
                    site_language.add(SiteLanguage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteLanguage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_language;
         
        }
        
        @Override
        public ArrayList<SiteLanguage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteLanguage> site_languageList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_language", limit, offset);
                while (rs.next())
                {
                    site_languageList.add(SiteLanguage.process(rs));
                }

                
                    for(SiteLanguage site_language : site_languageList)
                    {
                        
                            getRecordById("Site", site_language.getSiteId().toString());
                            site_language.setSite(Site.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteLanguage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_languageList;
        }
        
        @Override
        public ArrayList<SiteLanguage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteLanguage> site_language = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_language", SiteLanguage.checkColumnName(columnName), columnValue, SiteLanguage.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_language.add(SiteLanguage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteLanguage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_language;
        } 
    
        @Override
        public int add(SiteLanguage obj)
        {
            int id = 0;
            try
            {
                
                SiteLanguage.checkColumnSize(obj.getLanguageName(), 100);
                SiteLanguage.checkColumnSize(obj.getLocale(), 10);
                
                SiteLanguage.checkColumnSize(obj.getFlagFileName(), 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_language(LanguageName,Locale,Rtl,FlagFileName,SiteId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getLanguageName());
                preparedStatement.setString(2, obj.getLocale());
                preparedStatement.setInt(3, obj.getRtl());
                preparedStatement.setString(4, obj.getFlagFileName());
                preparedStatement.setInt(5, obj.getSiteId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_language;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public SiteLanguage update(SiteLanguage obj)
        {
           try
            {   
                
                SiteLanguage.checkColumnSize(obj.getLanguageName(), 100);
                SiteLanguage.checkColumnSize(obj.getLocale(), 10);
                
                SiteLanguage.checkColumnSize(obj.getFlagFileName(), 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_language SET LanguageName=?,Locale=?,Rtl=?,FlagFileName=?,SiteId=? WHERE SiteLanguageId=?;");                    
                preparedStatement.setString(1, obj.getLanguageName());
                preparedStatement.setString(2, obj.getLocale());
                preparedStatement.setInt(3, obj.getRtl());
                preparedStatement.setString(4, obj.getFlagFileName());
                preparedStatement.setInt(5, obj.getSiteId());
                preparedStatement.setInt(6, obj.getSiteLanguageId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("site_language");
        }
        
        
        @Override
        public void getRelatedInfo(SiteLanguage site_language)
        {
            
                try
                { 
                    
                            getRecordById("Site", site_language.getSiteId().toString());
                            site_language.setSite(Site.process(rs));                                       
                    
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
        public void getRelatedObjects(SiteLanguage site_language)
        {
             
        }
        
        @Override
        public boolean remove(SiteLanguage obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_language WHERE SiteLanguageId=" + obj.getSiteLanguageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_language WHERE SiteLanguageId=" + id + ";");           
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
                updateQuery("DELETE FROM site_language;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_language WHERE " + SiteLanguage.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

