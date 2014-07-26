





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object LocalizedString's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
        } 
    
        @Override
        public void add(LocalizedString obj)
        {
            try
            {
                
                
                LocalizedString.checkColumnSize(obj.getStringValue(), 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO localized_string(Locale,StringValue,TextStringId) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, obj.getLocale());
                preparedStatement.setString(2, obj.getStringValue());
                preparedStatement.setInt(3, obj.getTextStringId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer Locale, String StringValue, Integer TextStringId)
        {   
            int id = 0;
            try
            {
                
                
                LocalizedString.checkColumnSize(StringValue, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO localized_string(Locale,StringValue,TextStringId) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, Locale);
                preparedStatement.setString(2, StringValue);
                preparedStatement.setInt(3, TextStringId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from localized_string;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addLocalizedString error: " + ex.getMessage());
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
                System.out.println("updateLocalizedString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer LocalizedStringId,Integer Locale,String StringValue,Integer TextStringId)
        {  
            try
            {   
                
                
                LocalizedString.checkColumnSize(StringValue, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE localized_string SET Locale=?,StringValue=?,TextStringId=? WHERE LocalizedStringId=?;");                    
                preparedStatement.setInt(1, Locale);
                preparedStatement.setString(2, StringValue);
                preparedStatement.setInt(3, TextStringId);
                preparedStatement.setInt(4, LocalizedStringId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateLocalizedString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("localized_string");
        }
        
        
        @Override
        public LocalizedString getRelatedInfo(LocalizedString localized_string)
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
              
            
            return localized_string;
        }
        
        
        @Override
        public LocalizedString getRelatedObjects(LocalizedString localized_string)
        {
                         
            return localized_string;
        }
        
        
        
        @Override
        public void remove(LocalizedString obj)
        {            
            try
            {
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + obj.getLocalizedStringId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocalizedStringById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocalizedStringById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM localized_string;");
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM localized_string WHERE " + LocalizedString.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

