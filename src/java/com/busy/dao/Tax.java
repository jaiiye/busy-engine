


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Tax extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_TAXID = "TaxId";
        public static final String PROP_TAXSTATE = "TaxState";
        public static final String PROP_TAXRATE = "TaxRate";
        
        
        private Integer taxId;
        private String taxState;
        private Double taxRate;
        
        
        public Tax()
        {
            this.taxId = 0; 
       this.taxState = ""; 
       this.taxRate = 0.0; 
        }
        
        public Tax(Integer TaxId, String TaxState, Double TaxRate)
        {
            this.taxId = TaxId;
       this.taxState = TaxState;
       this.taxRate = TaxRate;
        } 
        
        
            public Integer getTaxId()
            {
                return this.taxId;
            }
            
            public void setTaxId(Integer TaxId)
            {
                this.taxId = TaxId;
            }
        
            public String getTaxState()
            {
                return this.taxState;
            }
            
            public void setTaxState(String TaxState)
            {
                this.taxState = TaxState;
            }
        
            public Double getTaxRate()
            {
                return this.taxRate;
            }
            
            public void setTaxRate(Double TaxRate)
            {
                this.taxRate = TaxRate;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Tax.PROP_TAXID) || column.equals(Tax.PROP_TAXSTATE) || column.equals(Tax.PROP_TAXRATE) )
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
            if (column.equals(Tax.PROP_TAXID) || column.equals(Tax.PROP_TAXRATE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Tax processTax(ResultSet rs) throws SQLException
        {        
            return new Tax(rs.getInt(1), rs.getString(2), rs.getDouble(3));
        }
        
        public static int addTax(String TaxState, Double TaxRate)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TaxState, 2);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO tax(TaxState,TaxRate) VALUES (?,?);");                    
                preparedStatement.setString(1, TaxState);
                preparedStatement.setDouble(2, TaxRate);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from tax;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTax error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllTaxCount()
        {
            return getAllRecordsCountByTableName("tax");        
        }
        
        public static ArrayList<Tax> getAllTax()
        {
            ArrayList<Tax> tax = new ArrayList<Tax>();
            try
            {
                getAllRecordsByTableName("tax");
                while(rs.next())
                {
                    tax.add(processTax(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTax error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax;
        }
                
        public static ArrayList<Tax> getTaxPaged(int limit, int offset)
        {
            ArrayList<Tax> tax = new ArrayList<Tax>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("tax", limit, offset);
                while (rs.next())
                {
                    tax.add(processTax(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTaxPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax;
        } 
        
        public static ArrayList<Tax> getAllTaxByColumn(String columnName, String columnValue)
        {
            ArrayList<Tax> tax = new ArrayList<Tax>();
            try
            {
                getAllRecordsByColumn("tax", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    tax.add(processTax(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTaxByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax;
        }
        
        public static Tax getTaxByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Tax tax = new Tax();
            try
            {
                getRecordsByColumnWithLimitAndOffset("tax", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   tax = processTax(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTaxByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax;
        }                
                
        public static void updateTax(Integer TaxId,String TaxState,Double TaxRate)
        {  
            try
            {   
                
                checkColumnSize(TaxState, 2);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tax SET TaxState=?,TaxRate=? WHERE TaxId=?;");                    
                preparedStatement.setString(1, TaxState);
                preparedStatement.setDouble(2, TaxRate);
                preparedStatement.setInt(3, TaxId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTax error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllTax()
        {
            try
            {
                updateQuery("DELETE FROM tax;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllTax error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTaxById(String id)
        {
            try
            {
                updateQuery("DELETE FROM tax WHERE TaxId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTaxById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteTaxByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM tax WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTaxByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

