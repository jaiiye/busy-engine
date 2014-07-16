


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class StoreInfoDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(StoreInfo.PROP_STORE_INFO_ID) || column.equals(StoreInfo.PROP_LOGO_TITLE) || column.equals(StoreInfo.PROP_LOGO_IMAGE) || column.equals(StoreInfo.PROP_STORE_NAME) || column.equals(StoreInfo.PROP_COMPANY_NAME) || column.equals(StoreInfo.PROP_LOCALE) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(StoreInfo.PROP_STORE_INFO_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static StoreInfo processStoreInfo(ResultSet rs) throws SQLException
        {        
            return new StoreInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        }
        
        public static int addStoreInfo(String LogoTitle, String LogoImage, String StoreName, String CompanyName, String Locale)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(LogoTitle, 145);
                checkColumnSize(LogoImage, 255);
                checkColumnSize(StoreName, 245);
                checkColumnSize(CompanyName, 45);
                checkColumnSize(Locale, 10);
                                            
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
        
        public static int getAllStoreInfoCount()
        {
            return getAllRecordsCountByTableName("store_info");        
        }
        
        public static ArrayList<StoreInfo> getAllStoreInfo()
        {
            ArrayList<StoreInfo> store_info = new ArrayList<StoreInfo>();
            try
            {
                getAllRecordsByTableName("store_info");
                while(rs.next())
                {
                    store_info.add(processStoreInfo(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStoreInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_info;
        }
                
        public static ArrayList<StoreInfo> getStoreInfoPaged(int limit, int offset)
        {
            ArrayList<StoreInfo> store_info = new ArrayList<StoreInfo>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("store_info", limit, offset);
                while (rs.next())
                {
                    store_info.add(processStoreInfo(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getStoreInfoPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_info;
        } 
        
        public static ArrayList<StoreInfo> getAllStoreInfoByColumn(String columnName, String columnValue)
        {
            ArrayList<StoreInfo> store_info = new ArrayList<StoreInfo>();
            try
            {
                getAllRecordsByColumn("store_info", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    store_info.add(processStoreInfo(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStoreInfoByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_info;
        }
        
        public static StoreInfo getStoreInfoByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            StoreInfo store_info = new StoreInfo();
            try
            {
                getRecordsByColumnWithLimitAndOffset("store_info", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   store_info = processStoreInfo(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getStoreInfoByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return store_info;
        }                
                
        public static void updateStoreInfo(Integer StoreInfoId,String LogoTitle,String LogoImage,String StoreName,String CompanyName,String Locale)
        {  
            try
            {   
                
                checkColumnSize(LogoTitle, 145);
                checkColumnSize(LogoImage, 255);
                checkColumnSize(StoreName, 245);
                checkColumnSize(CompanyName, 45);
                checkColumnSize(Locale, 10);
                                  
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
        
        public static void deleteAllStoreInfo()
        {
            try
            {
                updateQuery("DELETE FROM store_info;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllStoreInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteStoreInfoById(String id)
        {
            try
            {
                updateQuery("DELETE FROM store_info WHERE StoreInfoId=" + id + ";");            
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

        public static void deleteStoreInfoByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM store_info WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteStoreInfoByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

