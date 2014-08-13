





































    package com.busy.engine.dao;

import com.busy.engine.entity.SliderItem;
import com.busy.engine.entity.Slider;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("SliderItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_item;
        } 
    
        @Override
        public int add(SliderItem obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's add method error: " + ex.getMessage());
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
                System.out.println("SliderItem's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("slider_item");
        }
        
        
        @Override
        public void getRelatedInfo(SliderItem slider_item)
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
              
        }
        
        @Override
        public void getRelatedObjects(SliderItem slider_item)
        {
             
        }
        
        @Override
        public boolean remove(SliderItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + obj.getSliderItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + id + ";");           
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
                updateQuery("DELETE FROM slider_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_item WHERE " + SliderItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

