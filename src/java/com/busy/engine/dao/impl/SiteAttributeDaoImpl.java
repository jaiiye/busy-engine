





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object SiteAttribute's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
        } 
    
        @Override
        public void add(SiteAttribute obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String AttributeKey, String AttributeValue, String AttributeType, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                SiteAttribute.checkColumnSize(AttributeKey, 100);
                SiteAttribute.checkColumnSize(AttributeValue, 255);
                SiteAttribute.checkColumnSize(AttributeType, 45);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_attribute(AttributeKey,AttributeValue,AttributeType,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, AttributeKey);
                preparedStatement.setString(2, AttributeValue);
                preparedStatement.setString(3, AttributeType);
                preparedStatement.setInt(4, SiteId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_attribute;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteAttribute error: " + ex.getMessage());
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
                System.out.println("updateSiteAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SiteAttributeId,String AttributeKey,String AttributeValue,String AttributeType,Integer SiteId)
        {  
            try
            {   
                
                SiteAttribute.checkColumnSize(AttributeKey, 100);
                SiteAttribute.checkColumnSize(AttributeValue, 255);
                SiteAttribute.checkColumnSize(AttributeType, 45);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_attribute SET AttributeKey=?,AttributeValue=?,AttributeType=?,SiteId=? WHERE SiteAttributeId=?;");                    
                preparedStatement.setString(1, AttributeKey);
                preparedStatement.setString(2, AttributeValue);
                preparedStatement.setString(3, AttributeType);
                preparedStatement.setInt(4, SiteId);
                preparedStatement.setInt(5, SiteAttributeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site_attribute");
        }
        
        
        @Override
        public SiteAttribute getRelatedInfo(SiteAttribute site_attribute)
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
              
            
            return site_attribute;
        }
        
        
        @Override
        public SiteAttribute getRelatedObjects(SiteAttribute site_attribute)
        {
                         
            return site_attribute;
        }
        
        
        
        @Override
        public void remove(SiteAttribute obj)
        {            
            try
            {
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + obj.getSiteAttributeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteAttributeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteAttributeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_attribute;");
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_attribute WHERE " + SiteAttribute.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

