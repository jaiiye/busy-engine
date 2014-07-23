





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderItemDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SliderItem.PROP_SLIDER_ITEM_ID) || column.equals(SliderItem.PROP_TITLE) || column.equals(SliderItem.PROP_DESCRIPTION) || column.equals(SliderItem.PROP_URL) || column.equals(SliderItem.PROP_IMAGE_NAME) || column.equals(SliderItem.PROP_ALTERNATE_TEXT) || column.equals(SliderItem.PROP_RANK) || column.equals(SliderItem.PROP_SLIDER_ID) )
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
            if (column.equals(SliderItem.PROP_SLIDER_ITEM_ID) || column.equals(SliderItem.PROP_RANK) || column.equals(SliderItem.PROP_SLIDER_ID) )
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
        
        public static int addSliderItem(String Title, String Description, String Url, String ImageName, String AlternateText, Integer Rank, Integer SliderId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Title, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(Url, 255);
                checkColumnSize(ImageName, 100);
                checkColumnSize(AlternateText, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_item(Title,Description,Url,ImageName,AlternateText,Rank,SliderId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Url);
                preparedStatement.setString(4, ImageName);
                preparedStatement.setString(5, AlternateText);
                preparedStatement.setInt(6, Rank);
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
        
        public static ArrayList<SliderItem> getAllSliderItemWithRelatedInfo()
        {
            ArrayList<SliderItem> slider_itemList = new ArrayList<SliderItem>();
            try
            {
                getAllRecordsByTableName("slider_item");
                while (rs.next())
                {
                    slider_itemList.add(processSliderItem(rs));
                }

                
                    for(SliderItem slider_item : slider_itemList)
                    {
                        
                            getRecordById("Slider", slider_item.getSliderId().toString());
                            slider_item.setSlider(SliderDAO.processSlider(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderItemWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_itemList;
        }
        
        
        public static SliderItem getRelatedInfo(SliderItem slider_item)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Slider", slider_item.getSliderId().toString());
                            slider_item.setSlider(SliderDAO.processSlider(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return slider_item;
        }
        
        public static SliderItem getAllRelatedObjects(SliderItem slider_item)
        {           
                         
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
                
        public static void updateSliderItem(Integer SliderItemId,String Title,String Description,String Url,String ImageName,String AlternateText,Integer Rank,Integer SliderId)
        {  
            try
            {   
                
                checkColumnSize(Title, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(Url, 255);
                checkColumnSize(ImageName, 100);
                checkColumnSize(AlternateText, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_item SET Title=?,Description=?,Url=?,ImageName=?,AlternateText=?,Rank=?,SliderId=? WHERE SliderItemId=?;");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Url);
                preparedStatement.setString(4, ImageName);
                preparedStatement.setString(5, AlternateText);
                preparedStatement.setInt(6, Rank);
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

