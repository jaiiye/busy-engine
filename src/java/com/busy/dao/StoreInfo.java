


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class StoreInfo extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_STOREINFOID = "StoreInfoId";
        public static final String PROP_STORELOGOTITLE = "StoreLogoTitle";
        public static final String PROP_STORELOGOIMAGE = "StoreLogoImage";
        public static final String PROP_STORENAME = "StoreName";
        public static final String PROP_COMPANYNAME = "CompanyName";
        public static final String PROP_STORELOCALIZATION = "StoreLocalization";
        
        
        private Integer storeInfoId;
        private String storeLogoTitle;
        private String storeLogoImage;
        private String storeName;
        private String companyName;
        private Integer storeLocalization;
        
        
        public StoreInfo()
        {
            this.storeInfoId = 0; 
       this.storeLogoTitle = ""; 
       this.storeLogoImage = ""; 
       this.storeName = ""; 
       this.companyName = ""; 
       this.storeLocalization = 0; 
        }
        
        public StoreInfo(Integer StoreInfoId, String StoreLogoTitle, String StoreLogoImage, String StoreName, String CompanyName, Integer StoreLocalization)
        {
            this.storeInfoId = StoreInfoId;
       this.storeLogoTitle = StoreLogoTitle;
       this.storeLogoImage = StoreLogoImage;
       this.storeName = StoreName;
       this.companyName = CompanyName;
       this.storeLocalization = StoreLocalization;
        } 
        
        
            public Integer getStoreInfoId()
            {
                return this.storeInfoId;
            }
            
            public void setStoreInfoId(Integer StoreInfoId)
            {
                this.storeInfoId = StoreInfoId;
            }
        
            public String getStoreLogoTitle()
            {
                return this.storeLogoTitle;
            }
            
            public void setStoreLogoTitle(String StoreLogoTitle)
            {
                this.storeLogoTitle = StoreLogoTitle;
            }
        
            public String getStoreLogoImage()
            {
                return this.storeLogoImage;
            }
            
            public void setStoreLogoImage(String StoreLogoImage)
            {
                this.storeLogoImage = StoreLogoImage;
            }
        
            public String getStoreName()
            {
                return this.storeName;
            }
            
            public void setStoreName(String StoreName)
            {
                this.storeName = StoreName;
            }
        
            public String getCompanyName()
            {
                return this.companyName;
            }
            
            public void setCompanyName(String CompanyName)
            {
                this.companyName = CompanyName;
            }
        
            public Integer getStoreLocalization()
            {
                return this.storeLocalization;
            }
            
            public void setStoreLocalization(Integer StoreLocalization)
            {
                this.storeLocalization = StoreLocalization;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(StoreInfo.PROP_STOREINFOID) || column.equals(StoreInfo.PROP_STORELOGOTITLE) || column.equals(StoreInfo.PROP_STORELOGOIMAGE) || column.equals(StoreInfo.PROP_STORENAME) || column.equals(StoreInfo.PROP_COMPANYNAME) || column.equals(StoreInfo.PROP_STORELOCALIZATION) )
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
            if (column.equals(StoreInfo.PROP_STOREINFOID) || column.equals(StoreInfo.PROP_STORELOCALIZATION) )
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
            return new StoreInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
        }
        
        public static int addStoreInfo(String StoreLogoTitle, String StoreLogoImage, String StoreName, String CompanyName, Integer StoreLocalization)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(StoreLogoTitle, 145);
                checkColumnSize(StoreLogoImage, 255);
                checkColumnSize(StoreName, 245);
                checkColumnSize(CompanyName, 45);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO store_info(StoreLogoTitle,StoreLogoImage,StoreName,CompanyName,StoreLocalization) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, StoreLogoTitle);
                preparedStatement.setString(2, StoreLogoImage);
                preparedStatement.setString(3, StoreName);
                preparedStatement.setString(4, CompanyName);
                preparedStatement.setInt(5, StoreLocalization);
                
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
                
        public static void updateStoreInfo(Integer StoreInfoId,String StoreLogoTitle,String StoreLogoImage,String StoreName,String CompanyName,Integer StoreLocalization)
        {  
            try
            {   
                
                checkColumnSize(StoreLogoTitle, 145);
                checkColumnSize(StoreLogoImage, 255);
                checkColumnSize(StoreName, 245);
                checkColumnSize(CompanyName, 45);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE store_info SET StoreLogoTitle=?,StoreLogoImage=?,StoreName=?,CompanyName=?,StoreLocalization=? WHERE StoreInfoId=?;");                    
                preparedStatement.setString(1, StoreLogoTitle);
                preparedStatement.setString(2, StoreLogoImage);
                preparedStatement.setString(3, StoreName);
                preparedStatement.setString(4, CompanyName);
                preparedStatement.setInt(5, StoreLocalization);
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

