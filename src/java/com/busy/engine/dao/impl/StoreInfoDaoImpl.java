





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class StoreInfoDaoImpl extends BasicConnection implements Serializable, StoreInfoDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public StoreInfo find(Integer id)
        {
            return findByColumn("StoreInfoId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<StoreInfo> findAll(Integer limit, Integer offset)
        {
            ArrayList<StoreInfo> store_info = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("store_info");
                while(rs.next())
                {
                    store_info.add(StoreInfo.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("StoreInfo object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_info;
         
        }
        
        @Override
        public ArrayList<StoreInfo> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<StoreInfo> store_infoList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("store_info", limit, offset);
                while (rs.next())
                {
                    store_infoList.add(StoreInfo.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object StoreInfo method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_infoList;
        }
        
        @Override
        public ArrayList<StoreInfo> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<StoreInfo> store_info = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("store_info", StoreInfo.checkColumnName(columnName), columnValue, StoreInfo.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   store_info.add(StoreInfo.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object StoreInfo's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_info;
        } 
    
        @Override
        public void add(StoreInfo obj)
        {
            try
            {
                
                StoreInfo.checkColumnSize(obj.getLogoTitle(), 145);
                StoreInfo.checkColumnSize(obj.getLogoImage(), 255);
                StoreInfo.checkColumnSize(obj.getStoreName(), 245);
                StoreInfo.checkColumnSize(obj.getCompanyName(), 45);
                StoreInfo.checkColumnSize(obj.getLocale(), 10);
                                            
                openConnection();
                prepareStatement("INSERT INTO store_info(LogoTitle,LogoImage,StoreName,CompanyName,Locale) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getLogoTitle());
                preparedStatement.setString(2, obj.getLogoImage());
                preparedStatement.setString(3, obj.getStoreName());
                preparedStatement.setString(4, obj.getCompanyName());
                preparedStatement.setString(5, obj.getLocale());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("StoreInfo's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String LogoTitle, String LogoImage, String StoreName, String CompanyName, String Locale)
        {   
            int id = 0;
            try
            {
                
                StoreInfo.checkColumnSize(LogoTitle, 145);
                StoreInfo.checkColumnSize(LogoImage, 255);
                StoreInfo.checkColumnSize(StoreName, 245);
                StoreInfo.checkColumnSize(CompanyName, 45);
                StoreInfo.checkColumnSize(Locale, 10);
                                            
                openConnection();
                prepareStatement("INSERT INTO store_info(LogoTitle,LogoImage,StoreName,CompanyName,Locale) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, LogoTitle);
                preparedStatement.setString(2, LogoImage);
                preparedStatement.setString(3, StoreName);
                preparedStatement.setString(4, CompanyName);
                preparedStatement.setString(5, Locale);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from store_info;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addStoreInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public StoreInfo update(StoreInfo obj)
        {
           try
            {   
                
                StoreInfo.checkColumnSize(obj.getLogoTitle(), 145);
                StoreInfo.checkColumnSize(obj.getLogoImage(), 255);
                StoreInfo.checkColumnSize(obj.getStoreName(), 245);
                StoreInfo.checkColumnSize(obj.getCompanyName(), 45);
                StoreInfo.checkColumnSize(obj.getLocale(), 10);
                                  
                openConnection();                           
                prepareStatement("UPDATE store_info SET LogoTitle=?,LogoImage=?,StoreName=?,CompanyName=?,Locale=? WHERE StoreInfoId=?;");                    
                preparedStatement.setString(1, obj.getLogoTitle());
                preparedStatement.setString(2, obj.getLogoImage());
                preparedStatement.setString(3, obj.getStoreName());
                preparedStatement.setString(4, obj.getCompanyName());
                preparedStatement.setString(5, obj.getLocale());
                preparedStatement.setInt(6, obj.getStoreInfoId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateStoreInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer StoreInfoId,String LogoTitle,String LogoImage,String StoreName,String CompanyName,String Locale)
        {  
            try
            {   
                
                StoreInfo.checkColumnSize(LogoTitle, 145);
                StoreInfo.checkColumnSize(LogoImage, 255);
                StoreInfo.checkColumnSize(StoreName, 245);
                StoreInfo.checkColumnSize(CompanyName, 45);
                StoreInfo.checkColumnSize(Locale, 10);
                                  
                openConnection();                           
                prepareStatement("UPDATE store_info SET LogoTitle=?,LogoImage=?,StoreName=?,CompanyName=?,Locale=? WHERE StoreInfoId=?;");                    
                preparedStatement.setString(1, LogoTitle);
                preparedStatement.setString(2, LogoImage);
                preparedStatement.setString(3, StoreName);
                preparedStatement.setString(4, CompanyName);
                preparedStatement.setString(5, Locale);
                preparedStatement.setInt(6, StoreInfoId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateStoreInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("store_info");
        }
        
        
        @Override
        public StoreInfo getRelatedInfo(StoreInfo store_info)
        {
              
            
            return store_info;
        }
        
        
        @Override
        public StoreInfo getRelatedObjects(StoreInfo store_info)
        {
                         
            return store_info;
        }
        
        
        
        @Override
        public void remove(StoreInfo obj)
        {            
            try
            {
                updateQuery("DELETE FROM store_info WHERE StoreInfoId=" + obj.getStoreInfoId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteStoreInfoById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM store_info WHERE StoreInfoId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteStoreInfoById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM store_info;");
            }
            catch (Exception ex)
            {
                System.out.println("StoreInfo's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM store_info WHERE " + StoreInfo.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("StoreInfo's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

