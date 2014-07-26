





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderDaoImpl extends BasicConnection implements Serializable, SliderDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Slider find(Integer id)
        {
            return findByColumn("SliderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Slider> findAll(Integer limit, Integer offset)
        {
            ArrayList<Slider> slider = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("slider");
                while(rs.next())
                {
                    slider.add(Slider.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Slider object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
         
        }
        
        @Override
        public ArrayList<Slider> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Slider> sliderList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("slider", limit, offset);
                while (rs.next())
                {
                    sliderList.add(Slider.process(rs));
                }

                
                    for(Slider slider : sliderList)
                    {
                        
                            getRecordById("SliderType", slider.getSliderTypeId().toString());
                            slider.setSliderType(SliderType.process(rs));               
                        
                            getRecordById("Form", slider.getFormId().toString());
                            slider.setForm(Form.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Slider method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return sliderList;
        }
        
        @Override
        public ArrayList<Slider> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Slider> slider = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("slider", Slider.checkColumnName(columnName), columnValue, Slider.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   slider.add(Slider.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Slider's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
        } 
    
        @Override
        public void add(Slider obj)
        {
            try
            {
                
                Slider.checkColumnSize(obj.getSliderName(), 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO slider(SliderName,SliderTypeId,FormId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getSliderName());
                preparedStatement.setInt(2, obj.getSliderTypeId());
                preparedStatement.setInt(3, obj.getFormId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Slider's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String SliderName, Integer SliderTypeId, Integer FormId)
        {   
            int id = 0;
            try
            {
                
                Slider.checkColumnSize(SliderName, 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO slider(SliderName,SliderTypeId,FormId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, SliderName);
                preparedStatement.setInt(2, SliderTypeId);
                preparedStatement.setInt(3, FormId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSlider error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public Slider update(Slider obj)
        {
           try
            {   
                
                Slider.checkColumnSize(obj.getSliderName(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider SET SliderName=?,SliderTypeId=?,FormId=? WHERE SliderId=?;");                    
                preparedStatement.setString(1, obj.getSliderName());
                preparedStatement.setInt(2, obj.getSliderTypeId());
                preparedStatement.setInt(3, obj.getFormId());
                preparedStatement.setInt(4, obj.getSliderId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSlider error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SliderId,String SliderName,Integer SliderTypeId,Integer FormId)
        {  
            try
            {   
                
                Slider.checkColumnSize(SliderName, 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider SET SliderName=?,SliderTypeId=?,FormId=? WHERE SliderId=?;");                    
                preparedStatement.setString(1, SliderName);
                preparedStatement.setInt(2, SliderTypeId);
                preparedStatement.setInt(3, FormId);
                preparedStatement.setInt(4, SliderId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSlider error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("slider");
        }
        
        
        @Override
        public Slider getRelatedInfo(Slider slider)
        {
            
                try
                { 
                    
                        getRecordById("SliderType", slider.getSliderTypeId().toString());
                        slider.setSliderType(SliderType.process(rs));               
                    
                        getRecordById("Form", slider.getFormId().toString());
                        slider.setForm(Form.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return slider;
        }
        
        
        @Override
        public Slider getRelatedObjects(Slider slider)
        {
            slider.setPageList(new PageDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
slider.setSliderItemList(new SliderItemDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
             
            return slider;
        }
        
        
        
        @Override
        public void remove(Slider obj)
        {            
            try
            {
                updateQuery("DELETE FROM slider WHERE SliderId=" + obj.getSliderId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider WHERE SliderId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider;");
            }
            catch (Exception ex)
            {
                System.out.println("Slider's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider WHERE " + Slider.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Slider's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Slider getRelatedPageList(Slider slider)
        {           
            slider.setPageList(new PageDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
            return slider;
        }        
                    
        public Slider getRelatedSliderItemList(Slider slider)
        {           
            slider.setSliderItemList(new SliderItemDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
            return slider;
        }        
        
                             
    }

