





































    package com.busy.engine.dao;

import com.busy.engine.entity.TextString;
import com.busy.engine.entity.LocalizedString;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class LocalizedStringDaoImpl extends BasicConnection implements Serializable, LocalizedStringDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public LocalizedString find(Integer id)
        {
            return findByColumn("LocalizedStringId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<LocalizedString> findAll(Integer limit, Integer offset)
        {
            ArrayList<LocalizedString> localized_string = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("localized_string");
                while(rs.next())
                {
                    localized_string.add(LocalizedString.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("LocalizedString object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
         
        }
        
        @Override
        public ArrayList<LocalizedString> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<LocalizedString> localized_stringList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("localized_string", limit, offset);
                while (rs.next())
                {
                    localized_stringList.add(LocalizedString.process(rs));
                }

                
                    for(LocalizedString localized_string : localized_stringList)
                    {
                        
                            getRecordById("TextString", localized_string.getTextStringId().toString());
                            localized_string.setTextString(TextString.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object LocalizedString method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_stringList;
        }
        
        @Override
        public ArrayList<LocalizedString> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<LocalizedString> localized_string = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("localized_string", LocalizedString.checkColumnName(columnName), columnValue, LocalizedString.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   localized_string.add(LocalizedString.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("LocalizedString's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
        } 
    
        @Override
        public int add(LocalizedString obj)
        {
            int id = 0;
            try
            {
                
                
                LocalizedString.checkColumnSize(obj.getStringValue(), 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO localized_string(Locale,StringValue,TextStringId) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, obj.getLocale());
                preparedStatement.setString(2, obj.getStringValue());
                preparedStatement.setInt(3, obj.getTextStringId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from localized_string;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public LocalizedString update(LocalizedString obj)
        {
           try
            {   
                
                
                LocalizedString.checkColumnSize(obj.getStringValue(), 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE localized_string SET Locale=?,StringValue=?,TextStringId=? WHERE LocalizedStringId=?;");                    
                preparedStatement.setInt(1, obj.getLocale());
                preparedStatement.setString(2, obj.getStringValue());
                preparedStatement.setInt(3, obj.getTextStringId());
                preparedStatement.setInt(4, obj.getLocalizedStringId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("localized_string");
        }
        
        
        @Override
        public void getRelatedInfo(LocalizedString localized_string)
        {
            
                try
                { 
                    
                            getRecordById("TextString", localized_string.getTextStringId().toString());
                            localized_string.setTextString(TextString.process(rs));                                       
                    
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
        public void getRelatedObjects(LocalizedString localized_string)
        {
             
        }
        
        @Override
        public boolean remove(LocalizedString obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + obj.getLocalizedStringId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + id + ";");           
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
                updateQuery("DELETE FROM localized_string;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM localized_string WHERE " + LocalizedString.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

