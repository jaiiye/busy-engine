





































    package com.busy.engine.dao;

import com.busy.engine.entity.Discount;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("Discount's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        } 
    
        @Override
        public int add(Discount obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from discount;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Discount's add method error: " + ex.getMessage());
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
                System.out.println("Discount's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("discount");
        }
        
        
        @Override
        public void getRelatedInfo(Discount discount)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Discount discount)
        {
            discount.setCategoryList(new CategoryDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setUserGroupList(new UserGroupDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Discount obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM discount WHERE DiscountId=" + obj.getDiscountId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Discount's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM discount WHERE DiscountId=" + id + ";");           
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
                updateQuery("DELETE FROM discount;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Discount's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM discount WHERE " + Discount.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Discount's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedCategoryList(Discount discount)
        {           
            discount.setCategoryList(new CategoryDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
                    
        public void getRelatedCustomerOrderList(Discount discount)
        {           
            discount.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
                    
        public void getRelatedItemDiscountList(Discount discount)
        {           
            discount.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
                    
        public void getRelatedUserGroupList(Discount discount)
        {           
            discount.setUserGroupList(new UserGroupDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
        
                             
    }

