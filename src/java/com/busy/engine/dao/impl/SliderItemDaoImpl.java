





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderItemDaoImpl extends BasicConnection implements Serializable, SliderItemDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SliderItem find(Integer id)
        {
            return findByColumn("SliderItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SliderItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<SliderItem> slider_item = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("slider_item");
                while(rs.next())
                {
                    slider_item.add(SliderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SliderItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
         
        }
        
        @Override
        public ArrayList<SliderItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SliderItem> slider_itemList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("slider_item", limit, offset);
                while (rs.next())
                {
                    slider_itemList.add(SliderItem.process(rs));
                }

                
                    for(SliderItem slider_item : slider_itemList)
                    {
                        
                            getRecordById("Slider", slider_item.getSliderId().toString());
                            slider_item.setSlider(Slider.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SliderItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_itemList;
        }
        
        @Override
        public ArrayList<SliderItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SliderItem> slider_item = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("slider_item", SliderItem.checkColumnName(columnName), columnValue, SliderItem.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   slider_item.add(SliderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object SliderItem's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
        } 
    
        @Override
        public void add(SliderItem obj)
        {
            try
            {
                
                SliderItem.checkColumnSize(obj.getTitle(), 255);
                SliderItem.checkColumnSize(obj.getDescription(), 255);
                SliderItem.checkColumnSize(obj.getUrl(), 255);
                SliderItem.checkColumnSize(obj.getImageName(), 100);
                SliderItem.checkColumnSize(obj.getAlternateText(), 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_item(Title,Description,Url,ImageName,AlternateText,Rank,SliderId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getUrl());
                preparedStatement.setString(4, obj.getImageName());
                preparedStatement.setString(5, obj.getAlternateText());
                preparedStatement.setInt(6, obj.getRank());
                preparedStatement.setInt(7, obj.getSliderId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Title, String Description, String Url, String ImageName, String AlternateText, Integer Rank, Integer SliderId)
        {   
            int id = 0;
            try
            {
                
                SliderItem.checkColumnSize(Title, 255);
                SliderItem.checkColumnSize(Description, 255);
                SliderItem.checkColumnSize(Url, 255);
                SliderItem.checkColumnSize(ImageName, 100);
                SliderItem.checkColumnSize(AlternateText, 255);
                
                
                                            
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
        
        
        @Override
        public SliderItem update(SliderItem obj)
        {
           try
            {   
                
                SliderItem.checkColumnSize(obj.getTitle(), 255);
                SliderItem.checkColumnSize(obj.getDescription(), 255);
                SliderItem.checkColumnSize(obj.getUrl(), 255);
                SliderItem.checkColumnSize(obj.getImageName(), 100);
                SliderItem.checkColumnSize(obj.getAlternateText(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_item SET Title=?,Description=?,Url=?,ImageName=?,AlternateText=?,Rank=?,SliderId=? WHERE SliderItemId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getUrl());
                preparedStatement.setString(4, obj.getImageName());
                preparedStatement.setString(5, obj.getAlternateText());
                preparedStatement.setInt(6, obj.getRank());
                preparedStatement.setInt(7, obj.getSliderId());
                preparedStatement.setInt(8, obj.getSliderItemId());
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
            return obj;
        }
        
        public static void update(Integer SliderItemId,String Title,String Description,String Url,String ImageName,String AlternateText,Integer Rank,Integer SliderId)
        {  
            try
            {   
                
                SliderItem.checkColumnSize(Title, 255);
                SliderItem.checkColumnSize(Description, 255);
                SliderItem.checkColumnSize(Url, 255);
                SliderItem.checkColumnSize(ImageName, 100);
                SliderItem.checkColumnSize(AlternateText, 255);
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("slider_item");
        }
        
        
        @Override
        public SliderItem getRelatedInfo(SliderItem slider_item)
        {
            
                try
                { 
                    
                        getRecordById("Slider", slider_item.getSliderId().toString());
                        slider_item.setSlider(Slider.process(rs));               
                    

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
        
        
        @Override
        public SliderItem getRelatedObjects(SliderItem slider_item)
        {
                         
            return slider_item;
        }
        
        
        
        @Override
        public void remove(SliderItem obj)
        {            
            try
            {
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + obj.getSliderItemId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM slider_item;");
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_item WHERE " + SliderItem.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

