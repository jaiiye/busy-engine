





































    package com.busy.engine.dao;

import com.busy.engine.entity.SliderType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderTypeDaoImpl extends BasicConnection implements Serializable, SliderTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SliderType find(Integer id)
        {
            return findByColumn("SliderTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SliderType> findAll(Integer limit, Integer offset)
        {
            ArrayList<SliderType> slider_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("slider_type");
                while(rs.next())
                {
                    slider_type.add(SliderType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SliderType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
         
        }
        
        @Override
        public ArrayList<SliderType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SliderType> slider_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("slider_type", limit, offset);
                while (rs.next())
                {
                    slider_typeList.add(SliderType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object SliderType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_typeList;
        }
        
        @Override
        public ArrayList<SliderType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SliderType> slider_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("slider_type", SliderType.checkColumnName(columnName), columnValue, SliderType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   slider_type.add(SliderType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SliderType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
        } 
    
        @Override
        public int add(SliderType obj)
        {
            int id = 0;
            try
            {
                
                SliderType.checkColumnSize(obj.getTypeName(), 100);
                SliderType.checkColumnSize(obj.getCode(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_type(TypeName,Code) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getCode());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public SliderType update(SliderType obj)
        {
           try
            {   
                
                SliderType.checkColumnSize(obj.getTypeName(), 100);
                SliderType.checkColumnSize(obj.getCode(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_type SET TypeName=?,Code=? WHERE SliderTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getCode());
                preparedStatement.setInt(3, obj.getSliderTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("slider_type");
        }
        
        
        @Override
        public void getRelatedInfo(SliderType slider_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(SliderType slider_type)
        {
            slider_type.setSliderList(new SliderDaoImpl().findByColumn("SliderTypeId", slider_type.getSliderTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(SliderType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + obj.getSliderTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM slider_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_type WHERE " + SliderType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedSliderList(SliderType slider_type)
        {           
            slider_type.setSliderList(new SliderDaoImpl().findByColumn("SliderTypeId", slider_type.getSliderTypeId().toString(),null,null));
        }        
        
                             
    }

