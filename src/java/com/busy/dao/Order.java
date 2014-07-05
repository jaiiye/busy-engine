package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Order extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_ORDERID = "OrderId";
    public static final String PROP_SHIPPINGID = "ShippingId";
    public static final String PROP_ORDERSTATUS = "OrderStatus";
    public static final String PROP_ORDERDATE = "OrderDate";
    public static final String PROP_PAYPALTRANSACTIONID = "PayPalTransactionId";
    public static final String PROP_PAYPALAMOUNTBILLED = "PayPalAmountBilled";
    public static final String PROP_PAYPALPAYMENTSTATUS = "PayPalPaymentStatus";
    public static final String PROP_PAYPALPENDINGREASON = "PayPalPendingReason";
    public static final String PROP_PAYPALPAYMENTTYPE = "PayPalPaymentType";
    public static final String PROP_PAYPALFEECHARGED = "PayPalFeeCharged";
    public static final String PROP_PAYPALCURRENCYCODE = "PayPalCurrencyCode";
    public static final String PROP_PAYPALPAYERID = "PayPalPayerId";
    public static final String PROP_ORDERTAXAMOUNT = "OrderTaxAmount";
    public static final String PROP_ORDERSHIPPINGAMOUNT = "OrderShippingAmount";
    public static final String PROP_ORDERADDITIONALDATA = "OrderAdditionalData";

    private Integer orderId;
    private Integer shippingId;
    private String orderStatus;
    private Date orderDate;
    private String payPalTransactionId;
    private Double payPalAmountBilled;
    private String payPalPaymentStatus;
    private String payPalPendingReason;
    private String payPalPaymentType;
    private Double payPalFeeCharged;
    private String payPalCurrencyCode;
    private String payPalPayerId;
    private Double orderTaxAmount;
    private Double orderShippingAmount;
    private String orderAdditionalData;

    public Order()
    {
        this.orderId = 0;
        this.shippingId = 0;
        this.orderStatus = "";
        this.orderDate = null;
        this.payPalTransactionId = "";
        this.payPalAmountBilled = 0.0;
        this.payPalPaymentStatus = "";
        this.payPalPendingReason = "";
        this.payPalPaymentType = "";
        this.payPalFeeCharged = 0.0;
        this.payPalCurrencyCode = "";
        this.payPalPayerId = "";
        this.orderTaxAmount = 0.0;
        this.orderShippingAmount = 0.0;
        this.orderAdditionalData = "";
    }

    public Order(Integer OrderId, Integer ShippingId, String OrderStatus, Date OrderDate, String PayPalTransactionId, Double PayPalAmountBilled, String PayPalPaymentStatus, String PayPalPendingReason, String PayPalPaymentType, Double PayPalFeeCharged, String PayPalCurrencyCode, String PayPalPayerId, Double OrderTaxAmount, Double OrderShippingAmount, String OrderAdditionalData)
    {
        this.orderId = OrderId;
        this.shippingId = ShippingId;
        this.orderStatus = OrderStatus;
        this.orderDate = OrderDate;
        this.payPalTransactionId = PayPalTransactionId;
        this.payPalAmountBilled = PayPalAmountBilled;
        this.payPalPaymentStatus = PayPalPaymentStatus;
        this.payPalPendingReason = PayPalPendingReason;
        this.payPalPaymentType = PayPalPaymentType;
        this.payPalFeeCharged = PayPalFeeCharged;
        this.payPalCurrencyCode = PayPalCurrencyCode;
        this.payPalPayerId = PayPalPayerId;
        this.orderTaxAmount = OrderTaxAmount;
        this.orderShippingAmount = OrderShippingAmount;
        this.orderAdditionalData = OrderAdditionalData;
    }

    public Integer getOrderId()
    {
        return this.orderId;
    }

    public void setOrderId(Integer OrderId)
    {
        this.orderId = OrderId;
    }

    public Integer getShippingId()
    {
        return this.shippingId;
    }

    public void setShippingId(Integer ShippingId)
    {
        this.shippingId = ShippingId;
    }

    public String getOrderStatus()
    {
        return this.orderStatus;
    }

    public void setOrderStatus(String OrderStatus)
    {
        this.orderStatus = OrderStatus;
    }

    public Date getOrderDate()
    {
        return this.orderDate;
    }

    public void setOrderDate(Date OrderDate)
    {
        this.orderDate = OrderDate;
    }

    public String getPayPalTransactionId()
    {
        return this.payPalTransactionId;
    }

    public void setPayPalTransactionId(String PayPalTransactionId)
    {
        this.payPalTransactionId = PayPalTransactionId;
    }

    public Double getPayPalAmountBilled()
    {
        return this.payPalAmountBilled;
    }

    public void setPayPalAmountBilled(Double PayPalAmountBilled)
    {
        this.payPalAmountBilled = PayPalAmountBilled;
    }

    public String getPayPalPaymentStatus()
    {
        return this.payPalPaymentStatus;
    }

    public void setPayPalPaymentStatus(String PayPalPaymentStatus)
    {
        this.payPalPaymentStatus = PayPalPaymentStatus;
    }

    public String getPayPalPendingReason()
    {
        return this.payPalPendingReason;
    }

    public void setPayPalPendingReason(String PayPalPendingReason)
    {
        this.payPalPendingReason = PayPalPendingReason;
    }

    public String getPayPalPaymentType()
    {
        return this.payPalPaymentType;
    }

    public void setPayPalPaymentType(String PayPalPaymentType)
    {
        this.payPalPaymentType = PayPalPaymentType;
    }

    public Double getPayPalFeeCharged()
    {
        return this.payPalFeeCharged;
    }

    public void setPayPalFeeCharged(Double PayPalFeeCharged)
    {
        this.payPalFeeCharged = PayPalFeeCharged;
    }

    public String getPayPalCurrencyCode()
    {
        return this.payPalCurrencyCode;
    }

    public void setPayPalCurrencyCode(String PayPalCurrencyCode)
    {
        this.payPalCurrencyCode = PayPalCurrencyCode;
    }

    public String getPayPalPayerId()
    {
        return this.payPalPayerId;
    }

    public void setPayPalPayerId(String PayPalPayerId)
    {
        this.payPalPayerId = PayPalPayerId;
    }

    public Double getOrderTaxAmount()
    {
        return this.orderTaxAmount;
    }

    public void setOrderTaxAmount(Double OrderTaxAmount)
    {
        this.orderTaxAmount = OrderTaxAmount;
    }

    public Double getOrderShippingAmount()
    {
        return this.orderShippingAmount;
    }

    public void setOrderShippingAmount(Double OrderShippingAmount)
    {
        this.orderShippingAmount = OrderShippingAmount;
    }

    public String getOrderAdditionalData()
    {
        return this.orderAdditionalData;
    }

    public void setOrderAdditionalData(String OrderAdditionalData)
    {
        this.orderAdditionalData = OrderAdditionalData;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Order.PROP_ORDERID) || column.equals(Order.PROP_SHIPPINGID) || column.equals(Order.PROP_ORDERSTATUS) || column.equals(Order.PROP_ORDERDATE) || column.equals(Order.PROP_PAYPALTRANSACTIONID) || column.equals(Order.PROP_PAYPALAMOUNTBILLED) || column.equals(Order.PROP_PAYPALPAYMENTSTATUS) || column.equals(Order.PROP_PAYPALPENDINGREASON) || column.equals(Order.PROP_PAYPALPAYMENTTYPE) || column.equals(Order.PROP_PAYPALFEECHARGED) || column.equals(Order.PROP_PAYPALCURRENCYCODE) || column.equals(Order.PROP_PAYPALPAYERID) || column.equals(Order.PROP_ORDERTAXAMOUNT) || column.equals(Order.PROP_ORDERSHIPPINGAMOUNT) || column.equals(Order.PROP_ORDERADDITIONALDATA))
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
        if (column.equals(Order.PROP_ORDERID) || column.equals(Order.PROP_SHIPPINGID) || column.equals(Order.PROP_PAYPALAMOUNTBILLED) || column.equals(Order.PROP_PAYPALFEECHARGED) || column.equals(Order.PROP_ORDERTAXAMOUNT) || column.equals(Order.PROP_ORDERSHIPPINGAMOUNT))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Order processOrder(ResultSet rs) throws SQLException
    {
        return new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10), rs.getString(11), rs.getString(12), rs.getDouble(13), rs.getDouble(14), rs.getString(15));
    }

    public static int addOrder(Integer ShippingId, String OrderStatus, Date OrderDate, String PayPalTransactionId, Double PayPalAmountBilled, String PayPalPaymentStatus, String PayPalPendingReason, String PayPalPaymentType, Double PayPalFeeCharged, String PayPalCurrencyCode, String PayPalPayerId, Double OrderTaxAmount, Double OrderShippingAmount, String OrderAdditionalData)
    {
        int id = 0;
        try
        {

            checkColumnSize(OrderStatus, 45);
            checkColumnSize(PayPalTransactionId, 45);
            checkColumnSize(PayPalPaymentStatus, 45);
            checkColumnSize(PayPalPendingReason, 45);
            checkColumnSize(PayPalPaymentType, 15);
            checkColumnSize(PayPalCurrencyCode, 45);
            checkColumnSize(PayPalPayerId, 45);
            checkColumnSize(OrderAdditionalData, 65535);

            openConnection();
            prepareStatement("INSERT INTO `order`(ShippingId,OrderStatus,OrderDate,PayPalTransactionId,PayPalAmountBilled,PayPalPaymentStatus,PayPalPendingReason,PayPalPaymentType,PayPalFeeCharged,PayPalCurrencyCode,PayPalPayerId,OrderTaxAmount,OrderShippingAmount,OrderAdditionalData) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, ShippingId);
            preparedStatement.setString(2, OrderStatus);
            preparedStatement.setDate(3, new java.sql.Date(OrderDate.getTime()));
            preparedStatement.setString(4, PayPalTransactionId);
            preparedStatement.setDouble(5, PayPalAmountBilled);
            preparedStatement.setString(6, PayPalPaymentStatus);
            preparedStatement.setString(7, PayPalPendingReason);
            preparedStatement.setString(8, PayPalPaymentType);
            preparedStatement.setDouble(9, PayPalFeeCharged);
            preparedStatement.setString(10, PayPalCurrencyCode);
            preparedStatement.setString(11, PayPalPayerId);
            preparedStatement.setDouble(12, OrderTaxAmount);
            preparedStatement.setDouble(13, OrderShippingAmount);
            preparedStatement.setString(14, OrderAdditionalData);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from `order`;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addOrder error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllOrderCount()
    {
        return getAllRecordsCountByTableName("`order`");
    }

    public static ArrayList<Order> getAllOrder()
    {
        ArrayList<Order> order = new ArrayList<Order>();
        try
        {
            getAllRecordsByTableName("`order`");
            while (rs.next())
            {
                order.add(processOrder(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllOrder error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return order;
    }

    public static ArrayList<Order> getOrderPaged(int limit, int offset)
    {
        ArrayList<Order> order = new ArrayList<Order>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("`order`", limit, offset);
            while (rs.next())
            {
                order.add(processOrder(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getOrderPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return order;
    }

    public static ArrayList<Order> getAllOrderByColumn(String columnName, String columnValue)
    {
        ArrayList<Order> order = new ArrayList<Order>();
        try
        {
            getAllRecordsByColumn("`order`", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                order.add(processOrder(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllOrderByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return order;
    }

    public static Order getOrderByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        Order order = new Order();
        try
        {
            getRecordsByColumnWithLimitAndOffset("`order`", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                order = processOrder(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getOrderByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return order;
    }

    public static void updateOrder(Integer OrderId, Integer ShippingId, String OrderStatus, Date OrderDate, String PayPalTransactionId, Double PayPalAmountBilled, String PayPalPaymentStatus, String PayPalPendingReason, String PayPalPaymentType, Double PayPalFeeCharged, String PayPalCurrencyCode, String PayPalPayerId, Double OrderTaxAmount, Double OrderShippingAmount, String OrderAdditionalData)
    {
        try
        {

            checkColumnSize(OrderStatus, 45);
            checkColumnSize(PayPalTransactionId, 45);
            checkColumnSize(PayPalPaymentStatus, 45);
            checkColumnSize(PayPalPendingReason, 45);
            checkColumnSize(PayPalPaymentType, 15);
            checkColumnSize(PayPalCurrencyCode, 45);
            checkColumnSize(PayPalPayerId, 45);
            checkColumnSize(OrderAdditionalData, 65535);

            openConnection();
            prepareStatement("UPDATE `order` SET ShippingId=?,OrderStatus=?,OrderDate=?,PayPalTransactionId=?,PayPalAmountBilled=?,PayPalPaymentStatus=?,PayPalPendingReason=?,PayPalPaymentType=?,PayPalFeeCharged=?,PayPalCurrencyCode=?,PayPalPayerId=?,OrderTaxAmount=?,OrderShippingAmount=?,OrderAdditionalData=? WHERE OrderId=?;");
            preparedStatement.setInt(1, ShippingId);
            preparedStatement.setString(2, OrderStatus);
            preparedStatement.setDate(3, new java.sql.Date(OrderDate.getTime()));
            preparedStatement.setString(4, PayPalTransactionId);
            preparedStatement.setDouble(5, PayPalAmountBilled);
            preparedStatement.setString(6, PayPalPaymentStatus);
            preparedStatement.setString(7, PayPalPendingReason);
            preparedStatement.setString(8, PayPalPaymentType);
            preparedStatement.setDouble(9, PayPalFeeCharged);
            preparedStatement.setString(10, PayPalCurrencyCode);
            preparedStatement.setString(11, PayPalPayerId);
            preparedStatement.setDouble(12, OrderTaxAmount);
            preparedStatement.setDouble(13, OrderShippingAmount);
            preparedStatement.setString(14, OrderAdditionalData);
            preparedStatement.setInt(15, OrderId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateOrder error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllOrder()
    {
        try
        {
            updateQuery("DELETE FROM `order`;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllOrder error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteOrderById(String id)
    {
        try
        {
            updateQuery("DELETE FROM `order` WHERE OrderId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteOrderById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteOrderByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM `order` WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteOrderByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
