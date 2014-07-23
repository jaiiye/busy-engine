





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SliderType.PROP_SLIDER_TYPE_ID) || column.equals(SliderType.PROP_TYPE_NAME) || column.equals(SliderType.PROP_CODE) )
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
            if (column.equals(SliderType.PROP_SLIDER_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SliderType processSliderType(ResultSet rs) throws SQLException
        {        
            return new SliderType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addSliderType(String TypeName, String Code)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 100);
                checkColumnSize(Code, 255);
                                            
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
        
        public static int getAllSliderTypeCount()
        {
            return getAllRecordsCountByTableName("slider_type");        
        }
        
        public static ArrayList<SliderType> getAllSliderType()
        {
            ArrayList<SliderType> slider_type = new ArrayList<SliderType>();
            try
            {
                getAllRecordsByTableName("slider_type");
                while(rs.next())
                {
                    slider_type.add(processSliderType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
        }
        
        public static ArrayList<SliderType> getAllSliderTypeWithRelatedInfo()
        {
            ArrayList<SliderType> slider_typeList = new ArrayList<SliderType>();
            try
            {
                getAllRecordsByTableName("slider_type");
                while (rs.next())
                {
                    slider_typeList.add(processSliderType(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderTypeWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_typeList;
        }
        
        
        public static SliderType getRelatedInfo(SliderType slider_type)
        {
           
                  
            
            return slider_type;
        }
        
        public static SliderType getAllRelatedObjects(SliderType slider_type)
        {           
            slider_type.setSliderList(SliderDAO.getAllSliderByColumn("SliderTypeId", slider_type.getSliderTypeId().toString()));
             
            return slider_type;
        }
        
        
                    
        public static SliderType getRelatedSliderList(SliderType slider_type)
        {           
            slider_type.setSliderList(SliderDAO.getAllSliderByColumn("SliderTypeId", slider_type.getSliderTypeId().toString()));
            return slider_type;
        }        
        
                
        public static ArrayList<SliderType> getSliderTypePaged(int limit, int offset)
        {
            ArrayList<SliderType> slider_type = new ArrayList<SliderType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("slider_type", limit, offset);
                while (rs.next())
                {
                    slider_type.add(processSliderType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
        } 
        
        public static ArrayList<SliderType> getAllSliderTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<SliderType> slider_type = new ArrayList<SliderType>();
            try
            {
                getAllRecordsByColumn("slider_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    slider_type.add(processSliderType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
        }
        
        public static SliderType getSliderTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SliderType slider_type = new SliderType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("slider_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   slider_type = processSliderType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider_type;
        }                
                
        public static void updateSliderType(Integer SliderTypeId,String TypeName,String Code)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 100);
                checkColumnSize(Code, 255);
                                  
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
        
        public static void deleteAllSliderType()
        {
            try
            {
                updateQuery("DELETE FROM slider_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSliderType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSliderTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + id + ";");            
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

        public static void deleteSliderTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM slider_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

