





































    package com.busy.engine.dao;

import com.busy.engine.entity.Mailinglist;
import com.busy.engine.entity.User;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class MailinglistDaoImpl extends BasicConnection implements Serializable, MailinglistDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Mailinglist find(Integer id)
        {
            return findByColumn("MailinglistId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Mailinglist> findAll(Integer limit, Integer offset)
        {
            ArrayList<Mailinglist> mailinglist = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("mailinglist");
                while(rs.next())
                {
                    mailinglist.add(Mailinglist.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Mailinglist object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
         
        }
        
        @Override
        public ArrayList<Mailinglist> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Mailinglist> mailinglistList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("mailinglist", limit, offset);
                while (rs.next())
                {
                    mailinglistList.add(Mailinglist.process(rs));
                }

                
                    for(Mailinglist mailinglist : mailinglistList)
                    {
                        
                            getRecordById("User", mailinglist.getUserId().toString());
                            mailinglist.setUser(User.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Mailinglist method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglistList;
        }
        
        @Override
        public ArrayList<Mailinglist> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Mailinglist> mailinglist = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("mailinglist", Mailinglist.checkColumnName(columnName), columnValue, Mailinglist.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   mailinglist.add(Mailinglist.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Mailinglist's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
        } 
    
        @Override
        public int add(Mailinglist obj)
        {
            int id = 0;
            try
            {
                
                Mailinglist.checkColumnSize(obj.getFullName(), 45);
                Mailinglist.checkColumnSize(obj.getEmail(), 245);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO mailinglist(FullName,Email,ListStatus,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFullName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setInt(3, obj.getListStatus());
                preparedStatement.setInt(4, obj.getUserId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from mailinglist;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Mailinglist update(Mailinglist obj)
        {
           try
            {   
                
                Mailinglist.checkColumnSize(obj.getFullName(), 45);
                Mailinglist.checkColumnSize(obj.getEmail(), 245);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE mailinglist SET FullName=?,Email=?,ListStatus=?,UserId=? WHERE MailinglistId=?;");                    
                preparedStatement.setString(1, obj.getFullName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setInt(3, obj.getListStatus());
                preparedStatement.setInt(4, obj.getUserId());
                preparedStatement.setInt(5, obj.getMailinglistId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("mailinglist");
        }
        
        
        @Override
        public void getRelatedInfo(Mailinglist mailinglist)
        {
            
                try
                { 
                    
                            getRecordById("User", mailinglist.getUserId().toString());
                            mailinglist.setUser(User.process(rs));                                       
                    
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
        public void getRelatedObjects(Mailinglist mailinglist)
        {
             
        }
        
        @Override
        public boolean remove(Mailinglist obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM mailinglist WHERE MailinglistId=" + obj.getMailinglistId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM mailinglist WHERE MailinglistId=" + id + ";");           
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
                updateQuery("DELETE FROM mailinglist;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM mailinglist WHERE " + Mailinglist.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

