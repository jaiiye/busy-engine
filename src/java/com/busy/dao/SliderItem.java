


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderItem extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SLIDERITEMID = "SliderItemId";
        public static final String PROP_SLIDERITEMTITLE = "SliderItemTitle";
        public static final String PROP_SLIDERITEMDESCRIPTION = "SliderItemDescription";
        public static final String PROP_SLIDERITEMURL = "SliderItemUrl";
        public static final String PROP_SLIDERITEMIMAGENAME = "SliderItemImageName";
        public static final String PROP_SLIDERITEMIMAGEALT = "SliderItemImageAlt";
        public static final String PROP_SLIDERITEMRANK = "SliderItemRank";
        public static final String PROP_SLIDERID = "SliderId";
        
        
        private Integer sliderItemId;
        private String sliderItemTitle;
        private String sliderItemDescription;
        private String sliderItemUrl;
        private String sliderItemImageName;
        private String sliderItemImageAlt;
        private Integer sliderItemRank;
        private Integer sliderId;
        
        
        public SliderItem()
        {
            this.sliderItemId = 0; 
       this.sliderItemTitle = ""; 
       this.sliderItemDescription = ""; 
       this.sliderItemUrl = ""; 
       this.sliderItemImageName = ""; 
       this.sliderItemImageAlt = ""; 
       this.sliderItemRank = 0; 
       this.sliderId = 0; 
        }
        
        public SliderItem(Integer SliderItemId, String SliderItemTitle, String SliderItemDescription, String SliderItemUrl, String SliderItemImageName, String SliderItemImageAlt, Integer SliderItemRank, Integer SliderId)
        {
            this.sliderItemId = SliderItemId;
       this.sliderItemTitle = SliderItemTitle;
       this.sliderItemDescription = SliderItemDescription;
       this.sliderItemUrl = SliderItemUrl;
       this.sliderItemImageName = SliderItemImageName;
       this.sliderItemImageAlt = SliderItemImageAlt;
       this.sliderItemRank = SliderItemRank;
       this.sliderId = SliderId;
        } 
        
        
            public Integer getSliderItemId()
            {
                return this.sliderItemId;
            }
            
            public void setSliderItemId(Integer SliderItemId)
            {
                this.sliderItemId = SliderItemId;
            }
        
            public String getSliderItemTitle()
            {
                return this.sliderItemTitle;
            }
            
            public void setSliderItemTitle(String SliderItemTitle)
            {
                this.sliderItemTitle = SliderItemTitle;
            }
        
            public String getSliderItemDescription()
            {
                return this.sliderItemDescription;
            }
            
            public void setSliderItemDescription(String SliderItemDescription)
            {
                this.sliderItemDescription = SliderItemDescription;
            }
        
            public String getSliderItemUrl()
            {
                return this.sliderItemUrl;
            }
            
            public void setSliderItemUrl(String SliderItemUrl)
            {
                this.sliderItemUrl = SliderItemUrl;
            }
        
            public String getSliderItemImageName()
            {
                return this.sliderItemImageName;
            }
            
            public void setSliderItemImageName(String SliderItemImageName)
            {
                this.sliderItemImageName = SliderItemImageName;
            }
        
            public String getSliderItemImageAlt()
            {
                return this.sliderItemImageAlt;
            }
            
            public void setSliderItemImageAlt(String SliderItemImageAlt)
            {
                this.sliderItemImageAlt = SliderItemImageAlt;
            }
        
            public Integer getSliderItemRank()
            {
                return this.sliderItemRank;
            }
            
            public void setSliderItemRank(Integer SliderItemRank)
            {
                this.sliderItemRank = SliderItemRank;
            }
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SliderItem.PROP_SLIDERITEMID) || column.equals(SliderItem.PROP_SLIDERITEMTITLE) || column.equals(SliderItem.PROP_SLIDERITEMDESCRIPTION) || column.equals(SliderItem.PROP_SLIDERITEMURL) || column.equals(SliderItem.PROP_SLIDERITEMIMAGENAME) || column.equals(SliderItem.PROP_SLIDERITEMIMAGEALT) || column.equals(SliderItem.PROP_SLIDERITEMRANK) || column.equals(SliderItem.PROP_SLIDERID) )
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
            if (column.equals(SliderItem.PROP_SLIDERITEMID) || column.equals(SliderItem.PROP_SLIDERITEMRANK) || column.equals(SliderItem.PROP_SLIDERID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SliderItem processSliderItem(ResultSet rs) throws SQLException
        {        
            return new SliderItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addSliderItem(String SliderItemTitle, String SliderItemDescription, String SliderItemUrl, String SliderItemImageName, String SliderItemImageAlt, Integer SliderItemRank, Integer SliderId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(SliderItemTitle, 255);
                checkColumnSize(SliderItemDescription, 255);
                checkColumnSize(SliderItemUrl, 255);
                checkColumnSize(SliderItemImageName, 100);
                checkColumnSize(SliderItemImageAlt, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_item(SliderItemTitle,SliderItemDescription,SliderItemUrl,SliderItemImageName,SliderItemImageAlt,SliderItemRank,SliderId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, SliderItemTitle);
                preparedStatement.setString(2, SliderItemDescription);
                preparedStatement.setString(3, SliderItemUrl);
                preparedStatement.setString(4, SliderItemImageName);
                preparedStatement.setString(5, SliderItemImageAlt);
                preparedStatement.setInt(6, SliderItemRank);
                preparedStatement.setInt(7, SliderId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSliderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSliderItemCount()
        {
            return getAllRecordsCountByTableName("slider_item");        
        }
        
        public static ArrayList<SliderItem> getAllSliderItem()
        {
            ArrayList<SliderItem> slider_item = new ArrayList<SliderItem>();
            try
            {
                getAllRecordsByTableName("slider_item");
                while(rs.next())
                {
                    slider_item.add(processSliderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
        }
                
        public static ArrayList<SliderItem> getSliderItemPaged(int limit, int offset)
        {
            ArrayList<SliderItem> slider_item = new ArrayList<SliderItem>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("slider_item", limit, offset);
                while (rs.next())
                {
                    slider_item.add(processSliderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
        } 
        
        public static ArrayList<SliderItem> getAllSliderItemByColumn(String columnName, String columnValue)
        {
            ArrayList<SliderItem> slider_item = new ArrayList<SliderItem>();
            try
            {
                getAllRecordsByColumn("slider_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    slider_item.add(processSliderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
        }
        
        public static SliderItem getSliderItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SliderItem slider_item = new SliderItem();
            try
            {
                getRecordsByColumnWithLimitAndOffset("slider_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   slider_item = processSliderItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
        }                
                
        public static void updateSliderItem(Integer SliderItemId,String SliderItemTitle,String SliderItemDescription,String SliderItemUrl,String SliderItemImageName,String SliderItemImageAlt,Integer SliderItemRank,Integer SliderId)
        {  
            try
            {   
                
                checkColumnSize(SliderItemTitle, 255);
                checkColumnSize(SliderItemDescription, 255);
                checkColumnSize(SliderItemUrl, 255);
                checkColumnSize(SliderItemImageName, 100);
                checkColumnSize(SliderItemImageAlt, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_item SET SliderItemTitle=?,SliderItemDescription=?,SliderItemUrl=?,SliderItemImageName=?,SliderItemImageAlt=?,SliderItemRank=?,SliderId=? WHERE SliderItemId=?;");                    
                preparedStatement.setString(1, SliderItemTitle);
                preparedStatement.setString(2, SliderItemDescription);
                preparedStatement.setString(3, SliderItemUrl);
                preparedStatement.setString(4, SliderItemImageName);
                preparedStatement.setString(5, SliderItemImageAlt);
                preparedStatement.setInt(6, SliderItemRank);
                preparedStatement.setInt(7, SliderId);
                preparedStatement.setInt(8, SliderItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSliderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSliderItem()
        {
            try
            {
                updateQuery("DELETE FROM slider_item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSliderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSliderItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSliderItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM slider_item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

