





































    package com.busy.engine.dao;

import com.busy.engine.entity.KnowledgeBase;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class KnowledgeBaseDaoImpl extends BasicConnection implements Serializable, KnowledgeBaseDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public KnowledgeBase find(Integer id)
        {
            return findByColumn("KnowledgeBaseId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<KnowledgeBase> findAll(Integer limit, Integer offset)
        {
            ArrayList<KnowledgeBase> knowledge_base = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("knowledge_base");
                while(rs.next())
                {
                    knowledge_base.add(KnowledgeBase.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("KnowledgeBase object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
         
        }
        
        @Override
        public ArrayList<KnowledgeBase> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<KnowledgeBase> knowledge_baseList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("knowledge_base", limit, offset);
                while (rs.next())
                {
                    knowledge_baseList.add(KnowledgeBase.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object KnowledgeBase method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_baseList;
        }
        
        @Override
        public ArrayList<KnowledgeBase> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<KnowledgeBase> knowledge_base = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("knowledge_base", KnowledgeBase.checkColumnName(columnName), columnValue, KnowledgeBase.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   knowledge_base.add(KnowledgeBase.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("KnowledgeBase's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
        } 
    
        @Override
        public int add(KnowledgeBase obj)
        {
            int id = 0;
            try
            {
                
                KnowledgeBase.checkColumnSize(obj.getKnowledgeBaseName(), 200);
                KnowledgeBase.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO knowledge_base(KnowledgeBaseName,Description,Rank,LastModified,LatestTopic,LatestPost) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getKnowledgeBaseName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setDate(4, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setInt(5, obj.getLatestTopic());
                preparedStatement.setInt(6, obj.getLatestPost());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from knowledge_base;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public KnowledgeBase update(KnowledgeBase obj)
        {
           try
            {   
                
                KnowledgeBase.checkColumnSize(obj.getKnowledgeBaseName(), 200);
                KnowledgeBase.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE knowledge_base SET KnowledgeBaseName=?,Description=?,Rank=?,LastModified=?,LatestTopic=?,LatestPost=? WHERE KnowledgeBaseId=?;");                    
                preparedStatement.setString(1, obj.getKnowledgeBaseName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setDate(4, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setInt(5, obj.getLatestTopic());
                preparedStatement.setInt(6, obj.getLatestPost());
                preparedStatement.setInt(7, obj.getKnowledgeBaseId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("knowledge_base");
        }
        
        
        @Override
        public void getRelatedInfo(KnowledgeBase knowledge_base)
        {
              
        }
        
        @Override
        public void getRelatedObjects(KnowledgeBase knowledge_base)
        {
            knowledge_base.setBlogList(new BlogDaoImpl().findByColumn("KnowledgeBaseId", knowledge_base.getKnowledgeBaseId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(KnowledgeBase obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + obj.getKnowledgeBaseId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + id + ";");           
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
                updateQuery("DELETE FROM knowledge_base;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM knowledge_base WHERE " + KnowledgeBase.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedBlogList(KnowledgeBase knowledge_base)
        {           
            knowledge_base.setBlogList(new BlogDaoImpl().findByColumn("KnowledgeBaseId", knowledge_base.getKnowledgeBaseId().toString(),null,null));
        }        
        
                             
    }

