


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SliderType extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SLIDERTYPEID = "SliderTypeId";
        public static final String PROP_SLIDERTYPENAME = "SliderTypeName";
        public static final String PROP_SLIDERTYPECODE = "SliderTypeCode";
        
        
        private Integer sliderTypeId;
        private String sliderTypeName;
        private String sliderTypeCode;
        
        
        public SliderType()
        {
            this.sliderTypeId = 0; 
       this.sliderTypeName = ""; 
       this.sliderTypeCode = ""; 
        }
        
        public SliderType(Integer SliderTypeId, String SliderTypeName, String SliderTypeCode)
        {
            this.sliderTypeId = SliderTypeId;
       this.sliderTypeName = SliderTypeName;
       this.sliderTypeCode = SliderTypeCode;
        } 
        
        
            public Integer getSliderTypeId()
            {
                return this.sliderTypeId;
            }
            
            public void setSliderTypeId(Integer SliderTypeId)
            {
                this.sliderTypeId = SliderTypeId;
            }
        
            public String getSliderTypeName()
            {
                return this.sliderTypeName;
            }
            
            public void setSliderTypeName(String SliderTypeName)
            {
                this.sliderTypeName = SliderTypeName;
            }
        
            public String getSliderTypeCode()
            {
                return this.sliderTypeCode;
            }
            
            public void setSliderTypeCode(String SliderTypeCode)
            {
                this.sliderTypeCode = SliderTypeCode;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SliderType.PROP_SLIDERTYPEID) || column.equals(SliderType.PROP_SLIDERTYPENAME) || column.equals(SliderType.PROP_SLIDERTYPECODE) )
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
            if (column.equals(SliderType.PROP_SLIDERTYPEID) )
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
        
        public static int addSliderType(String SliderTypeName, String SliderTypeCode)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(SliderTypeName, 100);
                checkColumnSize(SliderTypeCode, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO slider_type(SliderTypeName,SliderTypeCode) VALUES (?,?);");                    
                preparedStatement.setString(1, SliderTypeName);
                preparedStatement.setString(2, SliderTypeCode);
                
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
                
        public static void updateSliderType(Integer SliderTypeId,String SliderTypeName,String SliderTypeCode)
        {  
            try
            {   
                
                checkColumnSize(SliderTypeName, 100);
                checkColumnSize(SliderTypeCode, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_type SET SliderTypeName=?,SliderTypeCode=? WHERE SliderTypeId=?;");                    
                preparedStatement.setString(1, SliderTypeName);
                preparedStatement.setString(2, SliderTypeCode);
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

