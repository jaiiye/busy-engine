





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object SliderType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
        } 
    
        @Override
        public void add(SliderType obj)
        {
            try
            {
                
                SliderType.checkColumnSize(obj.getTypeName(), 100);
                SliderType.checkColumnSize(obj.getCode(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_type(TypeName,Code) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getCode());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String Code)
        {   
            int id = 0;
            try
            {
                
                SliderType.checkColumnSize(TypeName, 100);
                SliderType.checkColumnSize(Code, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_type(TypeName,Code) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Code);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSliderType error: " + ex.getMessage());
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
                System.out.println("updateSliderType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SliderTypeId,String TypeName,String Code)
        {  
            try
            {   
                
                SliderType.checkColumnSize(TypeName, 100);
                SliderType.checkColumnSize(Code, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_type SET TypeName=?,Code=? WHERE SliderTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Code);
                preparedStatement.setInt(3, SliderTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSliderType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("slider_type");
        }
        
        
        @Override
        public SliderType getRelatedInfo(SliderType slider_type)
        {
              
            
            return slider_type;
        }
        
        
        @Override
        public SliderType getRelatedObjects(SliderType slider_type)
        {
            slider_type.setSliderList(new SliderDaoImpl().findByColumn("SliderTypeId", slider_type.getSliderTypeId().toString(),null,null));
             
            return slider_type;
        }
        
        
        
        @Override
        public void remove(SliderType obj)
        {            
            try
            {
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + obj.getSliderTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_type;");
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_type WHERE " + SliderType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public SliderType getRelatedSliderList(SliderType slider_type)
        {           
            slider_type.setSliderList(new SliderDaoImpl().findByColumn("SliderTypeId", slider_type.getSliderTypeId().toString(),null,null));
            return slider_type;
        }        
        
                             
    }

