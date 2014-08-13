





































    package com.busy.engine.dao;

import com.busy.engine.entity.Site;
import com.busy.engine.entity.SiteAttribute;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteAttributeDaoImpl extends BasicConnection implements Serializable, SiteAttributeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SiteAttribute find(Integer id)
        {
            return findByColumn("SiteAttributeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteAttribute> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteAttribute> site_attribute = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_attribute");
                while(rs.next())
                {
                    site_attribute.add(SiteAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteAttribute object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
         
        }
        
        @Override
        public ArrayList<SiteAttribute> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteAttribute> site_attributeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_attribute", limit, offset);
                while (rs.next())
                {
                    site_attributeList.add(SiteAttribute.process(rs));
                }

                
                    for(SiteAttribute site_attribute : site_attributeList)
                    {
                        
                            getRecordById("Site", site_attribute.getSiteId().toString());
                            site_attribute.setSite(Site.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteAttribute method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attributeList;
        }
        
        @Override
        public ArrayList<SiteAttribute> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteAttribute> site_attribute = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_attribute", SiteAttribute.checkColumnName(columnName), columnValue, SiteAttribute.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_attribute.add(SiteAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteAttribute's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
        } 
    
        @Override
        public int add(SiteAttribute obj)
        {
            int id = 0;
            try
            {
                
                SiteAttribute.checkColumnSize(obj.getAttributeKey(), 100);
                SiteAttribute.checkColumnSize(obj.getAttributeValue(), 255);
                SiteAttribute.checkColumnSize(obj.getAttributeType(), 45);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_attribute(AttributeKey,AttributeValue,AttributeType,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getAttributeKey());
                preparedStatement.setString(2, obj.getAttributeValue());
                preparedStatement.setString(3, obj.getAttributeType());
                preparedStatement.setInt(4, obj.getSiteId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_attribute;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public SiteAttribute update(SiteAttribute obj)
        {
           try
            {   
                
                SiteAttribute.checkColumnSize(obj.getAttributeKey(), 100);
                SiteAttribute.checkColumnSize(obj.getAttributeValue(), 255);
                SiteAttribute.checkColumnSize(obj.getAttributeType(), 45);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_attribute SET AttributeKey=?,AttributeValue=?,AttributeType=?,SiteId=? WHERE SiteAttributeId=?;");                    
                preparedStatement.setString(1, obj.getAttributeKey());
                preparedStatement.setString(2, obj.getAttributeValue());
                preparedStatement.setString(3, obj.getAttributeType());
                preparedStatement.setInt(4, obj.getSiteId());
                preparedStatement.setInt(5, obj.getSiteAttributeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("site_attribute");
        }
        
        
        @Override
        public void getRelatedInfo(SiteAttribute site_attribute)
        {
            
                try
                { 
                    
                            getRecordById("Site", site_attribute.getSiteId().toString());
                            site_attribute.setSite(Site.process(rs));                                       
                    
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
        public void getRelatedObjects(SiteAttribute site_attribute)
        {
             
        }
        
        @Override
        public boolean remove(SiteAttribute obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + obj.getSiteAttributeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + id + ";");           
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
                updateQuery("DELETE FROM site_attribute;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_attribute WHERE " + SiteAttribute.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

