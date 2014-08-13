





































    package com.busy.engine.dao;

import com.busy.engine.entity.CustomerOrder;
import com.busy.engine.entity.OrderItem;
import com.busy.engine.entity.Item;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class OrderItemDaoImpl extends BasicConnection implements Serializable, OrderItemDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public OrderItem find(Integer id)
        {
            return findByColumn("OrderItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<OrderItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<OrderItem> order_item = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("order_item");
                while(rs.next())
                {
                    order_item.add(OrderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OrderItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
         
        }
        
        @Override
        public ArrayList<OrderItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<OrderItem> order_itemList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("order_item", limit, offset);
                while (rs.next())
                {
                    order_itemList.add(OrderItem.process(rs));
                }

                
                    for(OrderItem order_item : order_itemList)
                    {
                        
                            getRecordById("CustomerOrder", order_item.getCustomerOrderId().toString());
                            order_item.setCustomerOrder(CustomerOrder.process(rs));               
                        
                            getRecordById("Item", order_item.getItemId().toString());
                            order_item.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object OrderItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_itemList;
        }
        
        @Override
        public ArrayList<OrderItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<OrderItem> order_item = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("order_item", OrderItem.checkColumnName(columnName), columnValue, OrderItem.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   order_item.add(OrderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OrderItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
        } 
    
        @Override
        public int add(OrderItem obj)
        {
            int id = 0;
            try
            {
                
                
                
                
                OrderItem.checkColumnSize(obj.getOptionName(), 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO order_item(CustomerOrderId,ItemId,Quantity,OptionName,UnitPrice) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getCustomerOrderId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getQuantity());
                preparedStatement.setString(4, obj.getOptionName());
                preparedStatement.setDouble(5, obj.getUnitPrice());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public OrderItem update(OrderItem obj)
        {
           try
            {   
                
                
                
                
                OrderItem.checkColumnSize(obj.getOptionName(), 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE order_item SET CustomerOrderId=?,ItemId=?,Quantity=?,OptionName=?,UnitPrice=? WHERE OrderItemId=?;");                    
                preparedStatement.setInt(1, obj.getCustomerOrderId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getQuantity());
                preparedStatement.setString(4, obj.getOptionName());
                preparedStatement.setDouble(5, obj.getUnitPrice());
                preparedStatement.setInt(6, obj.getOrderItemId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("order_item");
        }
        
        
        @Override
        public void getRelatedInfo(OrderItem order_item)
        {
            
                try
                { 
                    
                            getRecordById("CustomerOrder", order_item.getCustomerOrderId().toString());
                            order_item.setCustomerOrder(CustomerOrder.process(rs));                                       
                    
                            getRecordById("Item", order_item.getItemId().toString());
                            order_item.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(OrderItem order_item)
        {
            order_item.setReturnRequestList(new ReturnRequestDaoImpl().findByColumn("OrderItemId", order_item.getOrderItemId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(OrderItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + obj.getOrderItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + id + ";");           
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
                updateQuery("DELETE FROM order_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order_item WHERE " + OrderItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedReturnRequestList(OrderItem order_item)
        {           
            order_item.setReturnRequestList(new ReturnRequestDaoImpl().findByColumn("OrderItemId", order_item.getOrderItemId().toString(),null,null));
        }        
        
                             
    }

