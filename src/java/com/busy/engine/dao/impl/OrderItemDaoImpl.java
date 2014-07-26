





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object OrderItem's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
        } 
    
        @Override
        public void add(OrderItem obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer CustomerOrderId, Integer ItemId, Integer Quantity, String OptionName, Double UnitPrice)
        {   
            int id = 0;
            try
            {
                
                
                
                
                OrderItem.checkColumnSize(OptionName, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO order_item(CustomerOrderId,ItemId,Quantity,OptionName,UnitPrice) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, CustomerOrderId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, Quantity);
                preparedStatement.setString(4, OptionName);
                preparedStatement.setDouble(5, UnitPrice);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addOrderItem error: " + ex.getMessage());
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
                System.out.println("updateOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer OrderItemId,Integer CustomerOrderId,Integer ItemId,Integer Quantity,String OptionName,Double UnitPrice)
        {  
            try
            {   
                
                
                
                
                OrderItem.checkColumnSize(OptionName, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE order_item SET CustomerOrderId=?,ItemId=?,Quantity=?,OptionName=?,UnitPrice=? WHERE OrderItemId=?;");                    
                preparedStatement.setInt(1, CustomerOrderId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, Quantity);
                preparedStatement.setString(4, OptionName);
                preparedStatement.setDouble(5, UnitPrice);
                preparedStatement.setInt(6, OrderItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("order_item");
        }
        
        
        @Override
        public OrderItem getRelatedInfo(OrderItem order_item)
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
              
            
            return order_item;
        }
        
        
        @Override
        public OrderItem getRelatedObjects(OrderItem order_item)
        {
            order_item.setReturnRequestList(new ReturnRequestDaoImpl().findByColumn("OrderItemId", order_item.getOrderItemId().toString(),null,null));
             
            return order_item;
        }
        
        
        
        @Override
        public void remove(OrderItem obj)
        {            
            try
            {
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + obj.getOrderItemId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderItemById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderItemById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order_item;");
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order_item WHERE " + OrderItem.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public OrderItem getRelatedReturnRequestList(OrderItem order_item)
        {           
            order_item.setReturnRequestList(new ReturnRequestDaoImpl().findByColumn("OrderItemId", order_item.getOrderItemId().toString(),null,null));
            return order_item;
        }        
        
                             
    }

