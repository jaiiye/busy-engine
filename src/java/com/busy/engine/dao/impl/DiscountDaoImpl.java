





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class DiscountDaoImpl extends BasicConnection implements Serializable, DiscountDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Discount find(Integer id)
        {
            return findByColumn("DiscountId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Discount> findAll(Integer limit, Integer offset)
        {
            ArrayList<Discount> discount = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("discount");
                while(rs.next())
                {
                    discount.add(Discount.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Discount object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
         
        }
        
        @Override
        public ArrayList<Discount> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Discount> discountList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("discount", limit, offset);
                while (rs.next())
                {
                    discountList.add(Discount.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Discount method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discountList;
        }
        
        @Override
        public ArrayList<Discount> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Discount> discount = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("discount", Discount.checkColumnName(columnName), columnValue, Discount.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   discount.add(Discount.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Discount's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        } 
    
        @Override
        public void add(Discount obj)
        {
            try
            {
                
                Discount.checkColumnSize(obj.getDiscountName(), 100);
                
                
                
                
                Discount.checkColumnSize(obj.getCouponCode(), 100);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO discount(DiscountName,DiscountAmount,DiscountPercent,StartDate,EndDate,CouponCode,DiscountStatus,AskCouponCode,UsePercentage) VALUES (?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getDiscountName());
                preparedStatement.setDouble(2, obj.getDiscountAmount());
                preparedStatement.setDouble(3, obj.getDiscountPercent());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(obj.getEndDate().getTime()));
                preparedStatement.setString(6, obj.getCouponCode());
                preparedStatement.setInt(7, obj.getDiscountStatus());
                preparedStatement.setInt(8, obj.getAskCouponCode());
                preparedStatement.setInt(9, obj.getUsePercentage());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Discount's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String DiscountName, Double DiscountAmount, Double DiscountPercent, Date StartDate, Date EndDate, String CouponCode, Integer DiscountStatus, Integer AskCouponCode, Integer UsePercentage)
        {   
            int id = 0;
            try
            {
                
                Discount.checkColumnSize(DiscountName, 100);
                
                
                
                
                Discount.checkColumnSize(CouponCode, 100);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO discount(DiscountName,DiscountAmount,DiscountPercent,StartDate,EndDate,CouponCode,DiscountStatus,AskCouponCode,UsePercentage) VALUES (?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, DiscountName);
                preparedStatement.setDouble(2, DiscountAmount);
                preparedStatement.setDouble(3, DiscountPercent);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(6, CouponCode);
                preparedStatement.setInt(7, DiscountStatus);
                preparedStatement.setInt(8, AskCouponCode);
                preparedStatement.setInt(9, UsePercentage);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from discount;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public Discount update(Discount obj)
        {
           try
            {   
                
                Discount.checkColumnSize(obj.getDiscountName(), 100);
                
                
                
                
                Discount.checkColumnSize(obj.getCouponCode(), 100);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE discount SET DiscountName=?,DiscountAmount=?,DiscountPercent=?,StartDate=?,EndDate=?,CouponCode=?,DiscountStatus=?,AskCouponCode=?,UsePercentage=? WHERE DiscountId=?;");                    
                preparedStatement.setString(1, obj.getDiscountName());
                preparedStatement.setDouble(2, obj.getDiscountAmount());
                preparedStatement.setDouble(3, obj.getDiscountPercent());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(obj.getEndDate().getTime()));
                preparedStatement.setString(6, obj.getCouponCode());
                preparedStatement.setInt(7, obj.getDiscountStatus());
                preparedStatement.setInt(8, obj.getAskCouponCode());
                preparedStatement.setInt(9, obj.getUsePercentage());
                preparedStatement.setInt(10, obj.getDiscountId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer DiscountId,String DiscountName,Double DiscountAmount,Double DiscountPercent,Date StartDate,Date EndDate,String CouponCode,Integer DiscountStatus,Integer AskCouponCode,Integer UsePercentage)
        {  
            try
            {   
                
                Discount.checkColumnSize(DiscountName, 100);
                
                
                
                
                Discount.checkColumnSize(CouponCode, 100);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE discount SET DiscountName=?,DiscountAmount=?,DiscountPercent=?,StartDate=?,EndDate=?,CouponCode=?,DiscountStatus=?,AskCouponCode=?,UsePercentage=? WHERE DiscountId=?;");                    
                preparedStatement.setString(1, DiscountName);
                preparedStatement.setDouble(2, DiscountAmount);
                preparedStatement.setDouble(3, DiscountPercent);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(6, CouponCode);
                preparedStatement.setInt(7, DiscountStatus);
                preparedStatement.setInt(8, AskCouponCode);
                preparedStatement.setInt(9, UsePercentage);
                preparedStatement.setInt(10, DiscountId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("discount");
        }
        
        
        @Override
        public Discount getRelatedInfo(Discount discount)
        {
              
            
            return discount;
        }
        
        
        @Override
        public Discount getRelatedObjects(Discount discount)
        {
            discount.setCategoryList(new CategoryDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setUserGroupList(new UserGroupDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
             
            return discount;
        }
        
        
        
        @Override
        public void remove(Discount obj)
        {            
            try
            {
                updateQuery("DELETE FROM discount WHERE DiscountId=" + obj.getDiscountId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteDiscountById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM discount WHERE DiscountId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteDiscountById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM discount;");
            }
            catch (Exception ex)
            {
                System.out.println("Discount's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM discount WHERE " + Discount.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Discount's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Discount getRelatedCategoryList(Discount discount)
        {           
            discount.setCategoryList(new CategoryDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
            return discount;
        }        
                    
        public Discount getRelatedCustomerOrderList(Discount discount)
        {           
            discount.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
            return discount;
        }        
                    
        public Discount getRelatedItemDiscountList(Discount discount)
        {           
            discount.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
            return discount;
        }        
                    
        public Discount getRelatedUserGroupList(Discount discount)
        {           
            discount.setUserGroupList(new UserGroupDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
            return discount;
        }        
        
                             
    }

