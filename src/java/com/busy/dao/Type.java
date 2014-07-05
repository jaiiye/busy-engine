


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Type extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_TYPEID = "TypeId";
        public static final String PROP_TYPENAME = "TypeName";
        public static final String PROP_TYPEDESCRIPTION = "TypeDescription";
        public static final String PROP_TYPEREDIRECTURL = "TypeRedirectURL";
        
        
        private Integer typeId;
        private String typeName;
        private String typeDescription;
        private String typeRedirectURL;
        
        
        public Type()
        {
            this.typeId = 0; 
       this.typeName = ""; 
       this.typeDescription = ""; 
       this.typeRedirectURL = ""; 
        }
        
        public Type(Integer TypeId, String TypeName, String TypeDescription, String TypeRedirectURL)
        {
            this.typeId = TypeId;
       this.typeName = TypeName;
       this.typeDescription = TypeDescription;
       this.typeRedirectURL = TypeRedirectURL;
        } 
        
        
            public Integer getTypeId()
            {
                return this.typeId;
            }
            
            public void setTypeId(Integer TypeId)
            {
                this.typeId = TypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
        
            public String getTypeDescription()
            {
                return this.typeDescription;
            }
            
            public void setTypeDescription(String TypeDescription)
            {
                this.typeDescription = TypeDescription;
            }
        
            public String getTypeRedirectURL()
            {
                return this.typeRedirectURL;
            }
            
            public void setTypeRedirectURL(String TypeRedirectURL)
            {
                this.typeRedirectURL = TypeRedirectURL;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Type.PROP_TYPEID) || column.equals(Type.PROP_TYPENAME) || column.equals(Type.PROP_TYPEDESCRIPTION) || column.equals(Type.PROP_TYPEREDIRECTURL) )
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
            if (column.equals(Type.PROP_TYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Type processType(ResultSet rs) throws SQLException
        {        
            return new Type(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addType(String TypeName, String TypeDescription, String TypeRedirectURL)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeDescription, 65535);
                checkColumnSize(TypeRedirectURL, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO type(TypeName,TypeDescription,TypeRedirectURL) VALUES (?,?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeDescription);
                preparedStatement.setString(3, TypeRedirectURL);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllTypeCount()
        {
            return getAllRecordsCountByTableName("type");        
        }
        
        public static ArrayList<Type> getAllType()
        {
            ArrayList<Type> type = new ArrayList<Type>();
            try
            {
                getAllRecordsByTableName("type");
                while(rs.next())
                {
                    type.add(processType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return type;
        }
                
        public static ArrayList<Type> getTypePaged(int limit, int offset)
        {
            ArrayList<Type> type = new ArrayList<Type>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("type", limit, offset);
                while (rs.next())
                {
                    type.add(processType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return type;
        } 
        
        public static ArrayList<Type> getAllTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<Type> type = new ArrayList<Type>();
            try
            {
                getAllRecordsByColumn("type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    type.add(processType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return type;
        }
        
        public static Type getTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Type type = new Type();
            try
            {
                getRecordsByColumnWithLimitAndOffset("type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   type = processType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return type;
        }                
                
        public static void updateType(Integer TypeId,String TypeName,String TypeDescription,String TypeRedirectURL)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeDescription, 65535);
                checkColumnSize(TypeRedirectURL, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE type SET TypeName=?,TypeDescription=?,TypeRedirectURL=? WHERE TypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeDescription);
                preparedStatement.setString(3, TypeRedirectURL);
                preparedStatement.setInt(4, TypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllType()
        {
            try
            {
                updateQuery("DELETE FROM type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM type WHERE TypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

