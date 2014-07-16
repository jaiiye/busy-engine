











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.KnowledgeBase;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class KnowledgeBaseDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(KnowledgeBase.PROP_KNOWLEDGE_BASE_ID) || column.equals(KnowledgeBase.PROP_KNOWLEDGE_BASE_NAME) || column.equals(KnowledgeBase.PROP_DESCRIPTION) || column.equals(KnowledgeBase.PROP_RANK) || column.equals(KnowledgeBase.PROP_LAST_MODIFIED) || column.equals(KnowledgeBase.PROP_LATEST_TOPIC) || column.equals(KnowledgeBase.PROP_LATEST_POST) )
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
            if (column.equals(KnowledgeBase.PROP_KNOWLEDGE_BASE_ID) || column.equals(KnowledgeBase.PROP_RANK) || column.equals(KnowledgeBase.PROP_LATEST_TOPIC) || column.equals(KnowledgeBase.PROP_LATEST_POST) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static KnowledgeBase processKnowledgeBase(ResultSet rs) throws SQLException
        {        
            return new KnowledgeBase(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(6), rs.getInt(7));
        }
        
        public static int addKnowledgeBase(String KnowledgeBaseName, String Description, Integer Rank, Date LastModified, Integer LatestTopic, Integer LatestPost)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(KnowledgeBaseName, 200);
                checkColumnSize(Description, 65535);
                
                
                
                
                                            
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
        
        public static int getAllKnowledgeBaseCount()
        {
            return getAllRecordsCountByTableName("knowledge_base");        
        }
        
        public static ArrayList<KnowledgeBase> getAllKnowledgeBase()
        {
            ArrayList<KnowledgeBase> knowledge_base = new ArrayList<KnowledgeBase>();
            try
            {
                getAllRecordsByTableName("knowledge_base");
                while(rs.next())
                {
                    knowledge_base.add(processKnowledgeBase(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllKnowledgeBase error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
        }
                
        public static ArrayList<KnowledgeBase> getKnowledgeBasePaged(int limit, int offset)
        {
            ArrayList<KnowledgeBase> knowledge_base = new ArrayList<KnowledgeBase>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("knowledge_base", limit, offset);
                while (rs.next())
                {
                    knowledge_base.add(processKnowledgeBase(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getKnowledgeBasePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
        } 
        
        public static ArrayList<KnowledgeBase> getAllKnowledgeBaseByColumn(String columnName, String columnValue)
        {
            ArrayList<KnowledgeBase> knowledge_base = new ArrayList<KnowledgeBase>();
            try
            {
                getAllRecordsByColumn("knowledge_base", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    knowledge_base.add(processKnowledgeBase(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllKnowledgeBaseByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
        }
        
        public static KnowledgeBase getKnowledgeBaseByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            KnowledgeBase knowledge_base = new KnowledgeBase();
            try
            {
                getRecordsByColumnWithLimitAndOffset("knowledge_base", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   knowledge_base = processKnowledgeBase(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getKnowledgeBaseByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledge_base;
        }                
                
        public static void updateKnowledgeBase(Integer KnowledgeBaseId,String KnowledgeBaseName,String Description,Integer Rank,Date LastModified,Integer LatestTopic,Integer LatestPost)
        {  
            try
            {   
                
                checkColumnSize(KnowledgeBaseName, 200);
                checkColumnSize(Description, 65535);
                
                
                
                
                                  
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
        
        public static void deleteAllKnowledgeBase()
        {
            try
            {
                updateQuery("DELETE FROM knowledge_base;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllKnowledgeBase error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteKnowledgeBaseById(String id)
        {
            try
            {
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + id + ";");            
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

        public static void deleteKnowledgeBaseByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM knowledge_base WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteKnowledgeBaseByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

