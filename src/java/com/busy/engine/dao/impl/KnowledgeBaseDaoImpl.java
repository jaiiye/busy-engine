





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object KnowledgeBase's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
        } 
    
        @Override
        public void add(KnowledgeBase obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String KnowledgeBaseName, String Description, Integer Rank, Date LastModified, Integer LatestTopic, Integer LatestPost)
        {   
            int id = 0;
            try
            {
                
                KnowledgeBase.checkColumnSize(KnowledgeBaseName, 200);
                KnowledgeBase.checkColumnSize(Description, 65535);
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO knowledge_base(KnowledgeBaseName,Description,Rank,LastModified,LatestTopic,LatestPost) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, KnowledgeBaseName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setDate(4, new java.sql.Date(LastModified.getTime()));
                preparedStatement.setInt(5, LatestTopic);
                preparedStatement.setInt(6, LatestPost);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from knowledge_base;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addKnowledgeBase error: " + ex.getMessage());
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
                System.out.println("updateKnowledgeBase error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer KnowledgeBaseId,String KnowledgeBaseName,String Description,Integer Rank,Date LastModified,Integer LatestTopic,Integer LatestPost)
        {  
            try
            {   
                
                KnowledgeBase.checkColumnSize(KnowledgeBaseName, 200);
                KnowledgeBase.checkColumnSize(Description, 65535);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE knowledge_base SET KnowledgeBaseName=?,Description=?,Rank=?,LastModified=?,LatestTopic=?,LatestPost=? WHERE KnowledgeBaseId=?;");                    
                preparedStatement.setString(1, KnowledgeBaseName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setDate(4, new java.sql.Date(LastModified.getTime()));
                preparedStatement.setInt(5, LatestTopic);
                preparedStatement.setInt(6, LatestPost);
                preparedStatement.setInt(7, KnowledgeBaseId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateKnowledgeBase error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("knowledge_base");
        }
        
        
        @Override
        public KnowledgeBase getRelatedInfo(KnowledgeBase knowledge_base)
        {
              
            
            return knowledge_base;
        }
        
        
        @Override
        public KnowledgeBase getRelatedObjects(KnowledgeBase knowledge_base)
        {
            knowledge_base.setBlogList(new BlogDaoImpl().findByColumn("KnowledgeBaseId", knowledge_base.getKnowledgeBaseId().toString(),null,null));
             
            return knowledge_base;
        }
        
        
        
        @Override
        public void remove(KnowledgeBase obj)
        {            
            try
            {
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + obj.getKnowledgeBaseId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteKnowledgeBaseById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteKnowledgeBaseById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM knowledge_base;");
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM knowledge_base WHERE " + KnowledgeBase.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public KnowledgeBase getRelatedBlogList(KnowledgeBase knowledge_base)
        {           
            knowledge_base.setBlogList(new BlogDaoImpl().findByColumn("KnowledgeBaseId", knowledge_base.getKnowledgeBaseId().toString(),null,null));
            return knowledge_base;
        }        
        
                             
    }

