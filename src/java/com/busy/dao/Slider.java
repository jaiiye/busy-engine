


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Slider extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SLIDERID = "SliderId";
        public static final String PROP_SLIDERNAME = "SliderName";
        public static final String PROP_SLIDERTYPEID = "SliderTypeId";
        public static final String PROP_FORMID = "FormId";
        
        
        private Integer sliderId;
        private String sliderName;
        private Integer sliderTypeId;
        private Integer formId;
        
        
        public Slider()
        {
            this.sliderId = 0; 
       this.sliderName = ""; 
       this.sliderTypeId = 0; 
       this.formId = 0; 
        }
        
        public Slider(Integer SliderId, String SliderName, Integer SliderTypeId, Integer FormId)
        {
            this.sliderId = SliderId;
       this.sliderName = SliderName;
       this.sliderTypeId = SliderTypeId;
       this.formId = FormId;
        } 
        
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
            }
        
            public String getSliderName()
            {
                return this.sliderName;
            }
            
            public void setSliderName(String SliderName)
            {
                this.sliderName = SliderName;
            }
        
            public Integer getSliderTypeId()
            {
                return this.sliderTypeId;
            }
            
            public void setSliderTypeId(Integer SliderTypeId)
            {
                this.sliderTypeId = SliderTypeId;
            }
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Slider.PROP_SLIDERID) || column.equals(Slider.PROP_SLIDERNAME) || column.equals(Slider.PROP_SLIDERTYPEID) || column.equals(Slider.PROP_FORMID) )
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
            if (column.equals(Slider.PROP_SLIDERID) || column.equals(Slider.PROP_SLIDERTYPEID) || column.equals(Slider.PROP_FORMID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Slider processSlider(ResultSet rs) throws SQLException
        {        
            return new Slider(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addSlider(String SliderName, Integer SliderTypeId, Integer FormId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(SliderName, 100);
                
                
                                            
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
        
        public static int getAllSliderCount()
        {
            return getAllRecordsCountByTableName("slider");        
        }
        
        public static ArrayList<Slider> getAllSlider()
        {
            ArrayList<Slider> slider = new ArrayList<Slider>();
            try
            {
                getAllRecordsByTableName("slider");
                while(rs.next())
                {
                    slider.add(processSlider(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSlider error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
        }
                
        public static ArrayList<Slider> getSliderPaged(int limit, int offset)
        {
            ArrayList<Slider> slider = new ArrayList<Slider>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("slider", limit, offset);
                while (rs.next())
                {
                    slider.add(processSlider(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
        } 
        
        public static ArrayList<Slider> getAllSliderByColumn(String columnName, String columnValue)
        {
            ArrayList<Slider> slider = new ArrayList<Slider>();
            try
            {
                getAllRecordsByColumn("slider", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    slider.add(processSlider(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSliderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
        }
        
        public static Slider getSliderByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Slider slider = new Slider();
            try
            {
                getRecordsByColumnWithLimitAndOffset("slider", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   slider = processSlider(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
        }                
                
        public static void updateSlider(Integer SliderId,String SliderName,Integer SliderTypeId,Integer FormId)
        {  
            try
            {   
                
                checkColumnSize(SliderName, 100);
                
                
                                  
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
        
        public static void deleteAllSlider()
        {
            try
            {
                updateQuery("DELETE FROM slider;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSlider error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSliderById(String id)
        {
            try
            {
                updateQuery("DELETE FROM slider WHERE SliderId=" + id + ";");            
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

        public static void deleteSliderByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM slider WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSliderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

