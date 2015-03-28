
package com.busy.engine.data;

import static com.busy.engine.data.BasicConnection.connection;
import static com.busy.engine.data.BasicConnection.rs;
import static com.busy.engine.data.BasicConnection.statement;
import com.busy.engine.entity.*;
import com.busy.engine.dao.*;
import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database extends BasicConnection
{
    public static String encodeHTML(String x)
    {
        String s = x.trim();
        s = s.replaceAll("'", "&#39;");
        s = s.replaceAll("\r\n", "");
        s = s.replaceAll("\n", "");
        s = s.replaceAll("\r", "");
        s = s.replaceAll("\"", "&quot;");
        return s;
    }
    
    public static String decodeHTML(String x)
    {
        String s = x.trim();
        s = s.replaceAll("&#39;", "'");
        s = s.replaceAll("&quot;", "\"");
        return s;
    }

    public static String getCurrentDate() throws java.text.ParseException
    {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date tDate = new java.util.Date();
            return df.format(tDate);
    }
    
    public static void deleteRecord(String tableName, String options)
    {
        try
        {
            openConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + tableName+ " " + options + ";");            
        }
        catch (Exception ex)
        {
            System.out.println(tableName + " delete error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static String createRecord(String tableName, String values)
    {
        String id = "-1";
        try
        {
            openConnection();
            statement = connection.createStatement();            
                     
            String query = "INSERT INTO " + tableName + values + ";";

            System.out.println(query);
            statement.executeUpdate(query);
            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from " + tableName +";");
            while(rs.next())
            {
                id =  rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Create new record error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }
    
    public static void updateRecord(String tableName, String values)
    {       
        try
        {
            openConnection();
            statement = connection.createStatement();            

            String query = "UPDATE '" + tableName + "' " + values + ";";

            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("Create new record error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }        
    }
    
    public static String getUrl(String username, String Password)
    {
        String url = "";
        try
        {
            openConnection();
            statement = connection.createStatement();

            String query = "SELECT u.UserId ,t.RedirectURL FROM user u, user_type t WHERE u.UserTypeId = t.UserTypeId AND Username = '" + username + "' AND Password = '" + Password + "';";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                url = rs.getString(2) + "?uid=" + rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get Url error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return url;
    }

    public static String generateSelectOptionsFromTableAndColumn(String tableName, String selectedOptionValue, int columnToShow)
    {
        return generateHtmlSelectOptionsFromTableAndColumn(tableName, selectedOptionValue, columnToShow);
    }
    
    public static String generateImageSelect(String selectedImageName, int columnToShow)
    {        
        String output = "";
        getAllRecordsByTableName("site_image");
        try
        {
            while(rs.next())
            {     
                output += "<option value=\"" + rs.getString(4) + "\" " + (rs.getString(4).equals(selectedImageName) ? "selected" : "") + ">" + rs.getString(columnToShow) + "</option>\n";
            }   
        }
        catch(Exception ex)
        {
            System.out.println("generateImageSelect error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return output;
    }
    
    public static String getSelectedColumnFromTableAndId(String tableName, String id, int columnValueToReturn)
    {
        return getSelectedColumnValueFromTableAndId(tableName, id, columnValueToReturn);
    }
    
    public static double getTaxRate(String state)
    {
        double rate = 0;
        
        try
        {            
            //this needs to be fixed
            runQuery("SELECT * FROM tax_rate WHERE StateProvinceId = '" + state + "';");
            while(rs.next())
            {
                rate =  Double.parseDouble(rs.getString("Percentage"));
            }             
        }
        catch (Exception ex)
        {
            System.out.println("getTaxRate error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return rate;
    }
    
    public static double[] getShippingRates(String id)
    {
        double base = 0;
        double additional = 0;
        //TODO: needs to be redone
        try
        {
            runQuery("SELECT * FROM shipping WHERE ShippingId = '" + id + "';");
            while(rs.next())
            {
                base =  Double.parseDouble(rs.getString("RatePerUnitCost"));
                additional =  Double.parseDouble(rs.getString("AdditionalCost"));
            }             
        }
        catch (Exception ex)
        {
            System.out.println("getShippingRates error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        double[] b = {base, additional};
        return b;
    }
    
    public static String createCustomer(String fname, String lname, String email, String address, String city, String state, String zip, String country)
    {      
        String contactId = "";
        String addressId = "";
        String customerId = "";
        try
        {
            openConnection();
            String query = "INSERT INTO contact(FirstName, LastName, Email) VALUES('" + fname + "','" + lname + "','" + email + "');";
            System.out.println("New Contact Inserting info: " + query);
            statement.executeUpdate(query);

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from contact;");
            while(rs.next())
            {
                contactId =  rs.getString(1);
            } 
            
            query = "INSERT INTO address(Address1, City, State, Zipcode, Country) VALUES('" + address + "','" + city + "','" + state + "','" + zip + "','" + country + "'" + ");";
            System.out.println("New Customer Inserting info: " + query);
            statement.executeUpdate(query);

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from address;");
            while(rs.next())
            {
                addressId =  rs.getString(1);
            }
            
            query = "INSERT INTO customer(ContactId, AddressId) VALUES(" + contactId + "," + addressId + ");";
            System.out.println("New Customer Inserting info: " + query);
            statement.executeUpdate(query);

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer;");
            while(rs.next())
            {
                customerId =  rs.getString(1);
            }

        }
        catch (Exception ex)
        {
            System.out.println("createCustomer error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return customerId;
    }
    
    public static String createShoppingCart(String customerId, String orderId)
    {       
        String id = "";
        try
        {
            openConnection();
            String query = "INSERT INTO shopping_cart(CustomerId, OrderId) VALUES(" + customerId + "," + orderId + " );";
            System.out.println(query);
            statement.executeUpdate(query);
                        
            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shopping_cart;");
            while(rs.next())
            {
                id =  rs.getString(1);
            } 
        }
        catch (Exception ex)
        {
            System.out.println("createShoppingCart error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return id;
    }
    
    public static void addToShoppingCart(String cartId, String itemId, int itemQuantity, String itemOption,  String itemUnitPrice)
    {       
        try
        {
            updateQuery("INSERT INTO shopping_cart_item(ShoppingCartId, ItemId,ItemQuantity, ItemOption, ItemUnitPrice) " +
                    "VALUES(" +cartId + "," + itemId+ "," + itemQuantity + ",'" + itemOption+ "'," + itemUnitPrice+ ");");
        }
        catch (Exception ex)
        {
            System.out.println("addToShoppingCart error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
    }
    
    public static void subtractFromItemQuantity(String itemId, String itemOptionId, int quantity)
    {       
        try
        {   
            //itemOptionId really contains the value contained in ItemOptionType in the database
            updateQuery("UPDATE item_option_available SET ItemQuantity = ItemQuantity - " + quantity + 
                    " WHERE ItemId = " + itemId + " AND ItemOptionId = (SELECT ItemOptionId FROM item_option WHERE ItemOptionType = '" + itemOptionId + "');");            
        }
        catch (Exception ex)
        {
            System.out.println("subtractFromItemQuantity error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
    }
    
    public static String createOrder(String shippingId, String orderStatus, String orderDate, String paypalTransactionId, String paypalAmountBilled, 
            String paypalPaymentStatus, String paypalPendingReason, String paypalPaymentType, String paypalFeeCharged, String paypalCurrencyCode, 
            String paypalPayerId, String orderTaxAmount, String orderShippingAmount, String orderAdditionalData)
    {    
        String id = "";
        
        //fix the date format
        orderDate = orderDate.substring(0, 10)+ " " + orderDate.substring(11, 19);
        
        try
        {
            String query = "INSERT INTO order(ShippingId, OrderStatus, OrderDate, PayPalTransactionId, PayPalAmountBilled, PayPalPaymentStatus, PayPalPendingReason, " +
                    "PayPalPaymentType, PayPalFeeCharged, PayPalCurrencyCode, PayPalPayerId, OrderTaxAmount, OrderShippingAmount, OrderAdditionalData)" +
                           "VALUES(" + shippingId + ",'" + orderStatus + "','" + orderDate + "','" + paypalTransactionId + "'," + paypalAmountBilled +
                           ",'" + paypalPaymentStatus + "','" + paypalPendingReason + "','" + paypalPaymentType 
                           + "'," + paypalFeeCharged  +",'" + paypalCurrencyCode + "','" + paypalPayerId + "'," + orderTaxAmount + "," + orderShippingAmount + ",'" + orderAdditionalData + "');";

            updateQuery(query);
            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order;");
            while(rs.next())
            {
                id =  rs.getString(1);
            }  
           
        }
        catch (Exception ex)
        {
            System.out.println("Shipping info retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return id;
    }
    

    public static String getOptions(String itemId)
    {
        StringBuilder  s = new StringBuilder();

        try
        {
            openConnection();
            String query = "SELECT ItemOptionType FROM item_option iop, ( SELECT * FROM " +
                           "item_option_available WHERE ItemId = " + itemId +
                           ") As i WHERE i.ItemOptionId = iop.ItemOptionId;";
            
            //need another resultSet other than rs because this query is used in other queries
            ResultSet rs2 = statement.executeQuery(query);
            while(rs2.next())
            {
                s.append(rs2.getString(1)).append(" ");
            }
        }
        catch (Exception ex)
        {
            System.out.println("getOptions error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return s.toString();
    }

    //make a special drop down for the item option selector on the item page
    public static String makeItemOptionDropDown(String name, String selectedItem, int itemToShow)
    {
        StringBuilder  output = new StringBuilder();
        try
        {
            runQuery("SELECT * FROM item_option iop, ( SELECT * FROM item_option_available WHERE ItemId = " + selectedItem + ") As i WHERE i.ItemOptionId = iop.ItemOptionId;");
            output.append("<SELECT NAME=\"").append(name).append("\" class=\"ItemOptionsDropDown\">");
            while(rs.next())
            {
                output.append("<OPTION VALUE=\"").append(rs.getString(itemToShow)).append("\" ").append((rs.getString(1).equals(selectedItem) ? "selected" : "")).append(">").append(rs.getString(itemToShow)).append("</OPTION>");
            }
            output.append("</SELECT>");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return output.toString();
    }

    public static ArrayList<String> getItemFiles(String id)
    {
        ArrayList<String> itemFileNames = new ArrayList<String>();
        try
        {
            String query = "SELECT * FROM item_file WHERE ItemId = " + id + ";";
            runQuery(query);
            while(rs.next())
            {
                   itemFileNames.add(rs.getString(3));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemFiles error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return itemFileNames;

    }
    
    public static void RecordUserObjectCreationAction(String userId, String userName, String currentTime, String objectName, int id) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                + "'" + currentTime + "'" 
                + ", '" + userName + " created: " + objectName + " with id of "+ id  + "' "                
                + ", 5" 
                + ", " + userId + ");";
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordUserObjectCreationAction called: " + query);
            System.out.println("RecordUserObjectCreationAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
       
    public static void RecordUserObjectUpdateAction(String userId, String userName, String currentTime, String objectName, int id) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                    + "'" + currentTime + "'" 
                    + ", '" + userName + " updated: " + objectName + " with id of "+ id  + "' "                
                    + ", 6" 
                    + ", " + userId + ");";
       
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordUserObjectUpdateAction called: " + query);
            System.out.println("RecordUserObjectUpdateAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
       
    public static void RecordUserObjectDeletionAction(String userId, String userName, String currentTime, String objectName, String id) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                    + "'" + currentTime + "'" 
                    + ", '" + userName + " deleted: " + objectName + " with id of "+ id  + "' "                
                    + ", 7" 
                    + ", " + userId + ");";
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordUserObjectDeletionAction called: " + query);
            System.out.println("RecordUserObjectDeletionAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
      
    public static void RecordUserObjectClearAction(String userId, String userName, String currentTime, String objectName) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                + "'" + currentTime + "'" 
                + ", '" + userName + " cleared all " + objectName + " records' "                
                + ", 8" 
                + ", " + userId + ");";
        
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordUserObjectClearAction called: " + query);
            System.out.println("RecordUserObjectClearAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static void RecordUserLoginAction(String userId, String userName, String currentTime, String area) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                        + "'" + currentTime + "'" 
                        + ", '" + userName + " logged-in to " + area + "' "                
                        + ", 1" 
                        + ", " + userId + ");";
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordUserLoginAction called: " + query);
            System.out.println("RecordUserLoginAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
        
    public static void RecordUserLogoutAction(String userId, String userName, String currentTime, String area) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                + "'" + currentTime + "'" 
                + ", '" + userName + " logged-out to " + area + "' "                
                + ", 2" 
                + ", " + userId + ");";
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordUserLogoutAction called: " + query);
            System.out.println("RecordUserLogoutAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static boolean deleteAllUserActions()
    {
        try
        {            
            openConnection();            
            statement.executeUpdate("DELETE FROM user_action;"); 	
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllUserActions error: " + ex.getMessage());                        
        }
        finally
        {
            closeConnection();
        }
        return true;
    }
    
    public static void RecordUserDownloadAction(String userId, String userName, String fileId, String fileName, String folderId, String folderName, String currentTime) 
    {
        String query = "INSERT INTO user_action(Date, Detail, UserActionTypeId, UserId) VALUES ( " 
                + "'" + currentTime + "'" 
                + " downloaded: " + folderName + '/' + fileName  + "' "                
                + ", 3" 
                + ", " + userId + ");";
                
        try
        {
            openConnection();
            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("RecordDownloadAction called: " + query);
            System.out.println("RecordDownloadAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static boolean updateCount(String objectName, int offset)
    {
        String columnName = objectName + "Count";
        boolean success = false;
        try
        {
            if(offset > 0)
            {
                runQuery("UPDATE dashboard SET " + columnName + "= " + columnName + " + 1 WHERE DashboardId = 1;");
                success = true;
            }
            else
            {                
                runQuery("UPDATE dashboard SET " + columnName + "= " + columnName + " - 1  WHERE DashboardId = 1;");
                success = true;
            }
        }
        catch (Exception ex)
        {
            System.out.println("updateCount error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return success;
                        
    }
    
    public static void confirmUser(int id)
    {
        try
        {
            openConnection();

            String query = "UPDATE user SET UserStatus = 3 WHERE UserId = " + id + ";";
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("Set ConfirmUser error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
       
    public static String getTemplate(String name)
    {
        String result = "";
        
        try
        {
            runQuery("SELECT * FROM template WHERE TemplateName = '" + name + "';");
            while(rs.next())
            {
                result = rs.getString(3);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getTemplate error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
    
    
    public static void addNewsletterSubscriber(String name, String email)
    {
        try
        {
            openConnection();

            rs = statement.executeQuery("SELECT MAX(mailinglistId) FROM  mailinglist;");
            rs.next();
            String query = "INSERT INTO mailinglist(FullName, Email, ListStatus) VALUES( '" + name +  "','" + email + "',2)";//status code 2 = subscribed

            System.out.println(query);
            statement.executeUpdate(query);
        }
        catch (Exception ex)
        {
            System.out.println("addNewsletterSubscriber error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static boolean isStringKeyTaken(String key)
    {
        boolean result = true;
        ResultSet rs4;

        try
        {
            openConnection();
            statement = connection.createStatement();
            String query = "SELECT count(*) FROM text_string WHERE Key = '" + key + "';";
            System.out.println(query);

            rs4 = statement.executeQuery(query);

            rs4.next();
            
            if(rs4.getString(1).equals("0"))
            {
                result = false;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get isStringKeyTaken error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return result;
    }
    
    
    public static void addItemImage(String id, String fileName, String thumbnail)
    {        
        String query = "INSERT INTO item_image(ItemId, ImageName, ThumbnailImage) VALUES ( " + id + ", '" + fileName + "', '" +  thumbnail + "' );";
        try
        {
            openConnection();
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("addItemImage called: " + query);
            System.out.println("addItemImage error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static void addSiteImage(String typeId, String fileName, String imageAlternateText, Site site)
    {        
        String query = "INSERT INTO image(FileName, Description, LinkUrl, Rank, ImageTypeId, SiteId) VALUES ( '" + fileName + "', '" +  imageAlternateText + "', ' ', 1, " + typeId + ", " + site.getSiteId() + ");";
        try
        {
            openConnection();
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("addSiteImage called: " + query);
            System.out.println("addSiteImage error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }     
        
    public static void addSiteFile(String id, String fileName, String description, Site site)
    {        
        String query = "INSERT INTO site_file(FileName, Description, Label) VALUES ( '" + fileName + "', '" +  description + "' , '" +  fileName + "');";
        try
        {
            openConnection();
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("addSiteFile query: " + query);
            System.out.println("addSiteFile error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static void addItemFile(String id, String fileName, String description)
    {        
        String query = "INSERT INTO item_file(ItemId, FileName, Description, Label) VALUES ( " + id + ", '" + fileName + "', '" +  description + "', '" + fileName + "');";
        try
        {
            openConnection();
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("addItemFile query: " + query);
            System.out.println("addItemFile error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static String getLoginHeaderImage()
    {
        //ArrayList<Image> images = getImagesByTypeId(6);        
        //return images.get(0).getImageFileName();
        return "logo.jpg";
    }
    
    public static String getMainSiteURL()
    {
        int mode = 0;
        String url = "";

        try
        {
            runQuery("SELECT * FROM site WHERE SiteId=1;");
            while(rs.next())
            {
                mode = rs.getInt(4);
                
                if(mode == 1)
                {
                   url =  rs.getString(3); //SiteURL
                }
                else if(mode == 2)
                {
                   url =  rs.getString(5); //TestURL
                }
                else
                {
                    url = "ERROR";
                }                
            }
        }
        catch (Exception ex)
        {
            System.out.println("Site URL retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return url;
    }
    
    public static User getUser(String userName)
    {
        return new UserDaoImpl().findByColumn(User.PROP_USERNAME, userName, null, null).get(0);        
    }
    
    
    public static Map<String, String> getSiteAttributesMap(String type)
    {
        ResultSet rs2;
        Map<String, String> attributes = new HashMap<String, String>();

        try
        {
            openConnection();
            String query = "SELECT * FROM site_attribute sa;";
            if(type != null) 
            {
                query = "SELECT * FROM site_attribute sa WHERE AttributeType='" + type + "';";
            }
            rs2 = statement.executeQuery(query);

            while(rs2.next())
            {
                attributes.put(rs2.getString(2),rs2.getString(3));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getSiteAttributesMap error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return attributes;
    }
    
    
    public static HashMap<String,Object> getSiteStructure()
    {
        ArrayList<SiteImage> headerImages = new ArrayList<SiteImage>();
        ArrayList<SiteImage> sliderImages = new ArrayList<SiteImage>();
        String socialLinks = "";
        String pageNavigation = "";
        String pageToolbar = "";
        String pageFooter = "";
        
        System.out.println("getSiteStructure called!");
        
        try
        {   
            sliderImages = new SiteImageDaoImpl().findByColumn(SiteImage.PROP_IMAGE_TYPE_ID, "2", null, null);
            headerImages = new SiteImageDaoImpl().findByColumn(SiteImage.PROP_IMAGE_TYPE_ID, "3", null, null);
            
            runQuery("SELECT PageName, Content FROM page WHERE PageName='MainNavigation' OR PageName='MainSocialLinks' OR PageName='MainToolbar' OR PageName='MainFooter';");
         
            while(rs.next())
            {
                if(rs.getString(1).equals("MainNavigation"))
                {
                    pageNavigation =  rs.getString(2);
                }
                if(rs.getString(1).equals("MainSocialLinks"))
                {
                    socialLinks =  rs.getString(2);
                }
                if(rs.getString(1).equals("MainToolbar"))
                {
                    pageToolbar =  rs.getString(2);
                }
                if(rs.getString(1).equals("MainFooter"))
                {
                    pageFooter =  rs.getString(2);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("getSiteStructure error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        HashMap<String,Object> siteStructure = new HashMap<String,Object>();
        siteStructure.put("headerImages", headerImages);
        siteStructure.put("sliderImages", sliderImages);
        siteStructure.put("socialLinks", socialLinks);
        siteStructure.put("pageNavigation", pageNavigation);
        siteStructure.put("pageToolbar", pageToolbar);
        siteStructure.put("pageFooter", pageFooter);
        return siteStructure;
    }
    
    public static ArrayList<AbstractMap.SimpleEntry> getLanguageStrings(String siteId)
    {
        ArrayList<AbstractMap.SimpleEntry> attributes = new ArrayList<>();
        ResultSet rs7;

        try
        {
            openConnection();
            
            String query = "SELECT Locale FROM site WHERE siteId= " + siteId;
            rs7 = statement.executeQuery(query);
            String locale = "";

            while(rs7.next())
            {
                locale = rs7.getString(1);
            }
            
            query = "SELECT ls.StringId, ls.LocaleId, TextStringKey, StringValue, LocaleString  FROM locale l, text_string s, localized_string ls WHERE ls.LocaleId = l.LocaleId AND ls.StringId = s.TextStringId AND ls.LocaleId = " + locale + ";";
            System.out.println(query);
            rs7 = statement.executeQuery(query);

            while(rs7.next())
            {
                String key = rs7.getString(3); 
                String val = rs7.getString(4); 
                attributes.add(new AbstractMap.SimpleEntry<String,String>(key,val));
            }
        }
        catch (Exception ex)
        {
            System.out.println("geLanguageStrings error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return attributes;
    }
    
    
    public static ArrayList<Item> getAllItemsWithInfoByType(int type, String id, String category)
    {
        ArrayList<Item> items = new ItemDaoImpl().findByColumn(Item.PROP_ITEM_TYPE_ID, type + "", null, null);        
        
        for(Item i : items) {
            i.setItemBrand(new ItemBrandDaoImpl().find(i.getItemBrandId()));
            new ItemDaoImpl().getRelatedItemFileList(i);
            new ItemDaoImpl().getRelatedItemCategoryList(i);
            new ItemDaoImpl().getRelatedOptionAvailabilityList(i);
            new ItemDaoImpl().getRelatedItemImageList(i);  
            i.setMetaTag(new MetaTagDaoImpl().find(i.getMetaTagId()));
        }                     
        return items;
    }
    
    /*
    public static ArrayList<SiteFile> getSiteFiles(String folderId)
    {
        ArrayList<SiteFile> files = new ArrayList<SiteFile>();
        try
        {
            String query = "SELECT * FROM site_file sfi, site_folder sfo  WHERE sfi.FolderId = sfo.SiteFolderId;";
            
            if(folderId != null){
                query = "SELECT * FROM site_file sfi, site_folder sfo  WHERE sfi.FolderId = " + folderId + " AND sfi.FolderId = sfo.SiteFolderId;";
            }
            
            runQuery(query);
            while(rs.next())
            {
                   files.add(new SiteFile(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), new Folder(Integer.parseInt(rs.getString(6)),rs.getString(7),rs.getString(8),Integer.parseInt(rs.getString(9)))));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getSiteFiles error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return files;

    }
    public static ArrayList<SiteFolder> getAllFoldersWithFiles()
    {
        ArrayList<SiteFolder> folders = new ArrayList<SiteFolder>();
        ResultSet rs2;
        try
        {
            runQuery("SELECT * FROM site_folder sfo;");
            Statement fileStatement = connection.createStatement();
            while(rs.next())
            {
                SiteFolder folder = new SiteFolder(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)));
                
                ArrayList<SiteFile> files = new ArrayList<SiteFile>();
                rs2 = fileStatement.executeQuery("SELECT * FROM site_file WHERE FolderId=" + rs.getString(1) + ";");
                while(rs2.next()){
                    files.add(new SiteFile(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4)));                   
                }                
                folder.setFileFolderList(files);
                
                folders.add(folder);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllFoldersWithFiles error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return folders;

    }
    
    public static ArrayList<SiteImage> getItemImages(String id)
    {
        ArrayList<SiteImage> itemImages = new ArrayList<SiteImage>();
        try
        {
            String query = "SELECT * FROM item_image WHERE ItemId = " + id + ";";
            runQuery(query);
            while(rs.next())
            {
               itemImages.add(new SiteImage(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),4,rs.getString(3),rs.getString(5),"",0));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemImages retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return itemImages;
    }
    
    public static ArrayList<SiteImage> getImagesByItemId(String id)
    {
        ArrayList<SiteImage> itemImages = new ArrayList<SiteImage>();
        try
        {
            runQuery("SELECT * FROM image WHERE ItemId = " + id + ";");
            while(rs.next())
            {
                itemImages.add(new Image(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),Integer.parseInt(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7))));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getImagesByItemId retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return itemImages;
    }
    
    public static ArrayList<SiteImage> getImagesByTypeId(int id)
    {
        ArrayList<SiteImage> images = new ArrayList<SiteImage>();
        try
        {
            runQuery("SELECT * FROM image WHERE TypeId = " + id + ";");
            while(rs.next())
            {
               images.add(new Image(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),Integer.parseInt(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7))));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getImagesByTypeId retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return images;
    }
    
    
    public static String getLoginHeaderImage()
    {
        ArrayList<Image> images = getImagesByTypeId(6);        
        return images.get(0).getImageFileName();
    }
    
    public static ArrayList<Image> getAllSiteImages()
    {
        ArrayList<Image> images = new ArrayList<Image>();
        try
        {
            runQuery("SELECT * FROM image WHERE TypeId != 4;");
            while(rs.next())
            {
               images.add(new Image(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),Integer.parseInt(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7))));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllSiteImages retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return images;
    }
        
    public static Item getItemById(String id)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, "
                    + "t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemDescription, t.ItemRating, t.ItemVoteCount, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice,"
                    + " i.ItemPrice, ib.ItemBrandName, i.ItemDescription, i.ItemRating, i.ItemVoteCount, i.ItemShortDescription FROM item i, item_brand ib WHERE i.ItemId = " +  id + " )"
                    + " as t WHERE t.ItemId = ie.ItemId;";
            runQuery(query);
            while(rs.next())
            {
                Item i = new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), rs.getString(7),Double.parseDouble(rs.getString(8)),rs.getString(10));
                i.setItemVotes(Integer.parseInt(rs.getString(9)));
                items.add(i);
            }
            
            
            Statement optionsStatement = connection.createStatement();
            for(Item i : items)
            {
                query = "SELECT i.ItemOptionId, ItemOptionType, i.ItemAvailabilityId, i.ITemQuantity   FROM item_option iop, ( SELECT * FROM item_option_available WHERE ItemId = " + i.getItemId() + ") As i WHERE i.ItemOptionId = iop.ItemOptionId;";
                rs = optionsStatement.executeQuery(query);
                ArrayList<Option> itemOptions = new ArrayList<Option>();
                while(rs.next())
                {
                    itemOptions.add(new Option(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
                i.setItemInfo(new ItemInfo());
                i.getItemInfo().setItemOptions(itemOptions);
            }
            optionsStatement.close();
            
        }
        catch (Exception ex)
        {
            System.out.println("getItemById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items.get(0);
    }

    public static ArrayList<Item> getItemsByBrand(int type, String id,int limit, int pageNumber)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, "
                    + "t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice,"
                    + " i.ItemPrice, ib.ItemBrandName, i.ItemRating, i.ItemShortDescription FROM item i, item_brand ib WHERE i.ItemBrandId = " +  id + " AND i.ItemType = " + type + ")"
                    + " as t WHERE t.ItemId = ie.ItemId group by t.ItemId limit " + limit + " offset  " + ((pageNumber*limit)-limit) + ";";
            runQuery(query);
            while(rs.next())
            {
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)),rs.getString(8)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemsByBrand error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;

        
    }

    public static ArrayList<Item> getItemsByBrandName(String brandName)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, "
                    + "t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemVoteCount, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice,"
                    + " i.ItemPrice, ib.ItemBrandName, i.ItemRating, i.ItemVoteCount, i.ItemShortDescription FROM item i, item_brand ib WHERE i.ItemBrandId in (SELECT ItemBrandId FROM item_brand WHERE item_brand.ItemBrandName='" + brandName + "'))"
                    + " as t WHERE t.ItemId = ie.ItemId group by t.ItemId ;";

            runQuery(query);
            while(rs.next())
            {
                Item i = new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)),rs.getString(9));
                i.setItemVotes(Integer.parseInt(rs.getString(8)));
                items.add(i);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemsByBrandName error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;


    }

    public static ArrayList<Item> getItemsByTitleOrDescription(String searchTerm)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, "
                    + "t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemVoteCount, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice,"
                    + " i.ItemPrice, ib.ItemBrandName, i.ItemRating, i.ItemVoteCount, i.ItemShortDescription FROM item i, item_brand ib WHERE i.ItemBrandId in (SELECT ItemBrandId FROM item_brand WHERE item_brand.ItemBrandName='" + "" + "'))"
                    + " as t WHERE t.ItemId = ie.ItemId group by t.ItemId;";

            runQuery(query);
            while(rs.next())
            {
                Item i = new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)),rs.getString(9));
                i.setItemVotes(Integer.parseInt(rs.getString(8)));
                items.add(i);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemsByTitleOrDescription error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;


    }

    public static ArrayList<Item> getItemsByCategory(int type, String id, int limit, int pageNumber)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        String query = "";
        try
        {
            query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, ib.ItemBrandName, i.ItemRating, i.ItemShortDescription FROM item i, item_category ic, item_brand ib WHERE ic.CategoryId = "
                    + id + " AND i.ItemId = ic.ItemId AND i.ItemBrandId = ib.ItemBrandId AND i.ItemType = " + type + ") as t WHERE t.ItemId = ie.ItemId group by t.ItemId limit " + limit + " offset  " + ((pageNumber*limit)-limit) + ";";
            runQuery(query);
            while(rs.next())
            {
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)),rs.getString(8)));
            }
        }
        catch (Exception ex)
        {            
            System.out.println("getItemsByCategory error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;
    }

    public static ArrayList<Item> getItemsByCategory(String id)
    {
        ArrayList<Item> items = new ArrayList<Item>();        
        String query = "";
        
        try
        {
            query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, ib.ItemBrandName, i.ItemRating, i.ItemShortDescription FROM item i, item_category ic, item_brand ib WHERE ic.CategoryId = "
                    + id + " AND i.ItemId = ic.ItemId AND i.ItemBrandId = ib.ItemBrandId) as t WHERE t.ItemId = ie.ItemId group by t.ItemId;";
            runQuery(query);            
            Statement optionsStatement = connection.createStatement();
            
            while(rs.next())
            {
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)),rs.getString(8)));
            }
            
            for(Item i : items)
            {
                query = "SELECT i.ItemOptionId, ItemOptionType, i.ItemAvailabilityId, i.ITemQuantity   FROM item_option iop, ( SELECT * FROM item_option_available WHERE ItemId = " + i.getItemId() + ") As i WHERE i.ItemOptionId = iop.ItemOptionId;";
                rs = optionsStatement.executeQuery(query);
                //get Item files
                ArrayList<Option> itemOptions = new ArrayList<Option>();
                while(rs.next())
                {
                    itemOptions.add(new Option(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
                i.setItemInfo(new ItemInfo());
                i.getItemInfo().setItemOptions(itemOptions);
            }
            optionsStatement.close();
        }
        catch (Exception ex)
        {            
            System.out.println("getItemsByCategory error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;
    }
    
    public static ArrayList<Item> getItemsByOption(int type, String itemOptionId, int limit, int pageNumber)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, (SELECT DISTINCT item.ItemId, item.ItemName, item.ItemListPrice, item.ItemPrice, ib.ItemBrandName, item.ItemRating, item.ItemShortDescription FROM item_option_available, item, item_category ic, item_brand ib WHERE item.ItemId = item_option_available.ItemId AND item.ItemBrandId = ib.ItemBrandId AND ItemOptionId = "+ itemOptionId +") As t WHERE ie.ItemId = t.ItemId group by t.ItemId limit " + limit + " offset  " + ((pageNumber*limit)-limit) + ";";
            runQuery(query);
            while(rs.next())
            {
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)),rs.getString(8)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemsByOption error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;
    }


    public static int getAllItemsCount()
    {
        int count = 0;

        try
        {
            runQuery("SELECT count(*) FROM item WHERE ItemType = 13;");
            while(rs.next())
            {
                count = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllItemsCount error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return count;
    }
 

    public static int getItemsCount(int type, String criteria, int itemType)
    {
        int count = 0;
        String query = "";

        if(type == 1) //All Items
        {
            query = "SELECT count(*) FROM item WHERE itemType = " + itemType + ";";
        }
        else if(type == 2) //Items By Option (Size, etc.)
        {
            query = "SELECT count(*) FROM item_option_available i WHERE ItemOptionId = " + criteria + ";";
        }
        else if(type == 3) //Items By Category
        {
            query = "SELECT count(*) FROM item, item_category ic WHERE ic.ItemId = item.ItemId AND ic.CategoryId = " + criteria + ";";
        }
        else if(type == 4) //Items By Brand
        {
            query = "SELECT count(*) FROM item WHERE item.ItemBrandId = " + criteria + ";";
        }
        else if(type == 5) //Items By Price
        {
            query = "SELECT count(*) FROM item WHERE ItemPrice >= " + criteria + ";";
        }

        try
        {
            runQuery(query);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Items Count retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return count;
    }


    public static ArrayList<Item> getSimilarItems(String id, int limit)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT  ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, "
                    + "(SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, ib.ItemBrandName, i.ItemRating, i.ItemShortDescription FROM item i, item_category ic, item_brand ib WHERE ic.CategoryId = "
                    + id + " AND i.ItemId = ic.ItemId AND i.ItemBrandId = ib.ItemBrandId ) as t WHERE t.ItemId = ie.ItemId group by t.ItemId limit " + limit + ";";
            runQuery(query);
            while(rs.next())
            {
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)), rs.getString(8)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getSimilarItems error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;
    }

    public static ArrayList<Item> getAllItems(int type)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            String query = "SELECT ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, (SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, ib.ItemBrandName , i.ItemRating, i.ItemShortDescription FROM item i, item_brand ib Where i.ItemBrandId = ib.ItemBrandId  AND i.ItemType = " + type + ") as t WHERE t.ItemId = ie.ItemId group by t.ItemId;";
            runQuery(query);
            while(rs.next())
            {
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3),getOptions(rs.getString(2)),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), "",Double.parseDouble(rs.getString(7)), rs.getString(8)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllItems error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;
    }
    
    public static ArrayList<Item> getAllItemsWithInfoByType(int type, String id, String category)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        ItemInfo info;
        ResultSet rs2,rs3,rs4,rs5;
        
        try
        {
            String query = "SELECT ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemDescription, t.ItemSEOTitle, t.ItemSEODescription, t.ItemSEOKeywords, t.ItemBrandId, t.ItemShortDescription FROM item_image ie, (SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, i.ItemBrandId, ib.ItemBrandName , i.ItemRating, i.ItemDescription, i.ItemSEOTitle, i.ItemSEODescription, i.ItemSEOKeywords, i.ItemShortDescription FROM item i, item_brand ib Where i.ItemBrandId = ib.ItemBrandId  AND i.ItemType = " + type + ") as t WHERE t.ItemId = ie.ItemId group by t.ItemId;";
            
            if(id != null)
            {
                query = "SELECT ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemDescription, t.ItemSEOTitle, t.ItemSEODescription, t.ItemSEOKeywords, t.ItemBrandId, t.ItemShortDescription  FROM item_image ie, (SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, i.ItemBrandId, ib.ItemBrandName , i.ItemRating, i.ItemDescription, i.ItemSEOTitle, i.ItemSEODescription, i.ItemSEOKeywords, i.ItemShortDescription FROM item i, item_brand ib Where i.ItemBrandId = ib.ItemBrandId  AND i.ItemId = " + id + " AND i.ItemType = " + type + ") as t WHERE t.ItemId = ie.ItemId group by t.ItemId;";
            }
            if(id == null && category != null)
            {
                query = "SELECT ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemDescription, t.ItemSEOTitle, t.ItemSEODescription, t.ItemSEOKeywords, t.ItemBrandId, t.ItemShortDescription  FROM item_image ie, (SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, ib.ItemBrandName , i.ItemRating, i.ItemDescription, i.ItemSEOTitle, i.ItemSEODescription, i.ItemSEOKeywords , i.ItemBrandId, i.ItemShortDescription FROM item i, item_brand ib Where i.ItemBrandId = ib.ItemBrandId  AND i.ItemType = " + type + " AND i.ItemId in (SELECT ItemId FROM item_category ic WHERE CategoryId = " + category + ")) as t WHERE t.ItemId = ie.ItemId group by t.ItemId;";
            }
            runQuery(query); 
            
            Statement filesStatement = connection.createStatement();
            Statement categoriesStatement = connection.createStatement();
            Statement optionsStatement = connection.createStatement();
            Statement optionStatement = connection.createStatement();
            
            while(rs.next())
            {
                info = new ItemInfo();
                                
                query = "SELECT ItemOptionType FROM item_option iop, ( SELECT * FROM item_option_available WHERE ItemId = " + rs.getString(2) + ") As i WHERE i.ItemOptionId = iop.ItemOptionId;";
                rs2 = optionStatement.executeQuery(query);
                StringBuilder  s = new StringBuilder();
                while(rs2.next())
                {
                    s.append(rs2.getString(1)).append(" ");
                }
                
                query = "SELECT * FROM item_file WHERE ItemId = " + rs.getString(2) + ";";
                rs3 = filesStatement.executeQuery(query);
                //get Item files
                ArrayList<File> itemFiles = new ArrayList<File>();
                while(rs3.next())
                {
                    itemFiles.add(new File(rs3.getString(1),rs3.getString(3),rs3.getString(4),rs3.getString(5)));
                }
                info.setItemFiles(itemFiles);
                
                query = "SELECT t.CategoryId, cs.CategoryName FROM (SELECT * FROM item_category icz WHERE ItemId = " + rs.getString(2) + ") AS t, category cs WHERE t.CategoryId = cs.CategoryId ;";
                rs4 = categoriesStatement.executeQuery(query);
                //get Item files
                ArrayList<Category> itemCategories = new ArrayList<Category>();
                while(rs4.next())
                {
                    itemCategories.add(new Category(rs4.getString(1),rs4.getString(2)));
                }
                info.setItemCategories(itemCategories);
                
                query = "SELECT i.ItemOptionId, ItemOptionType, i.ItemAvailabilityId, i.ITemQuantity   FROM item_option iop, ( SELECT * FROM item_option_available WHERE ItemId = " + rs.getString(2) + ") As i WHERE i.ItemOptionId = iop.ItemOptionId;";
                rs5 = optionsStatement.executeQuery(query);
                //get Item files
                ArrayList<Option> itemOptions = new ArrayList<Option>();
                while(rs5.next())
                {
                    itemOptions.add(new Option(rs5.getString(1), rs5.getString(2), rs5.getString(3), rs5.getString(4)));
                }
                info.setItemOptions(itemOptions);
                

                //get Item data
                items.add(new Item(rs.getString(2),rs.getString(1),rs.getString(3), rs.getString(9), rs.getString(10), rs.getString(11), s.toString(),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)), 0, rs.getString(6), rs.getString(8),Double.parseDouble(rs.getString(7)), rs.getString(12), rs.getString(13)));
                items.get(items.size()-1).setItemInfo(info);
            }
            
            //cleanup opened statements
            filesStatement.close();
            categoriesStatement.close();
            optionsStatement.close();
            optionStatement.close();
            
        }
        catch (Exception ex)
        {
            System.out.println("getAllItemsWithInfoByType retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;
    }
    
    
//    public static DashboardInfo getDashboardInfo()
//    {
//        DashboardInfo info = new DashboardInfo();
//        
//        try
//        {   
//            runQuery("SELECT * FROM dashboard;");         
//            while(rs.next()) 
//            {  
//                info.setUserCount(rs.getInt(2));
//                info.setBlogPostCount(rs.getInt(3));
//                info.setItemCount(rs.getInt(4));
//                info.setOrderCount(rs.getInt(5));
//                info.setFileCount(rs.getInt(6));
//                info.setImageCount(rs.getInt(7));
//                info.setBlogCount(rs.getInt(8));
//                info.setCommentCount(rs.getInt(9));
//                info.setPageCount(rs.getInt(10));
//                info.setFormCount(rs.getInt(11));
//                info.setSliderCount(rs.getInt(12));
//                info.setBrandCount(rs.getInt(13)); 
//                info.setCategoryCount(rs.getInt(14));
//                info.setItemCount(rs.getInt(15));    
//            }  
//            
//            rs = statement.executeQuery("SELECT * FROM user_action ORDER BY Date DESC LIMIT 50 OFFSET 0;");                        
//            while(rs.next()) 
//            {  
//                com.busy.entity.UserAction u = new com.busy.entity.UserAction(rs.getInt(1), rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
//                info.getActivities().add(u);  
//            } 
//        }
//        catch (Exception ex)
//        {
//            System.out.println("getDashboardInfo error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//        return info;
//    }    
    
    public static ArrayList<Category> getAllCategories()
    {
        ArrayList<Category> categories = new ArrayList<Category>();
        try
        {
            getAllRecordsByTableName("category");
            while(rs.next())
            {
                categories.add(new Category(rs.getString(1),rs.getString(2)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllCategories error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return categories;
    }       
          
    public static String getCategoryName(int id)
    {
        String categoryName = "";
        try
        {
            runQuery("SELECT CategoryName FROM category WHERE CategoryId=" + id+ ";");
            while(rs.next())
            {
                categoryName = rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getCategoryName error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return categoryName;
    }
    
    public static ArrayList<Form> getAllForms()
    {
        ArrayList<Form> forms = new ArrayList<Form>();
        try
        {
            getAllRecordsByTableName("form");
            while(rs.next())
            {
                Form newForm = new Form(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),"1".equals(rs.getString(7)),"1".equals(rs.getString(8)));
                newForm.setFormId(Integer.parseInt(rs.getString(1)));
                forms.add(newForm);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllForms error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return forms;
    }
    
//    public static ArrayList<SiteTemplate> getAllSiteTemplates()
//    {
//        ArrayList<SiteTemplate> siteTemplates = new ArrayList<SiteTemplate>();
//        try
//        {
//            getAllRecordsByTableName("site_template");
//            while(rs.next())
//            {
//                SiteTemplate newSiteTemplate = new SiteTemplate(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4));                
//                siteTemplates.add(newSiteTemplate);
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("getAllSiteTemplates error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//        return siteTemplates;
//    }
    
    public static ArrayList<Slider> getAllSliders()
    {
        ArrayList<Slider> sliders = new ArrayList<Slider>();
        try
        {
            getAllRecordsByTableName("slider");
            while(rs.next())
            {
                sliders.add(new Slider(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4))));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllSliders error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return sliders;
    }
    
    public static Form getForm(String id)
    {
        Form form = null;
        try
        {
            getAllRecordsByColumn("form", "FormId", id, true);
            while(rs.next())
            {
                form = new Form(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),"1".equals(rs.getString(7)),"1".equals(rs.getString(8)));
                form.setFormId(Integer.parseInt(rs.getString(1)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getForm with id(" + id + ") error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return form;
    }
            
    
    public static ArrayList<FormField> getFormFields(int formId)
    {
        ArrayList<FormField> fields = new ArrayList<FormField>();
        try
        {
            runQuery("SELECT * FROM form_field WHERE FormId=" + formId  + " ORDER BY FieldRank;");
            while(rs.next())
            {
                //public FormField(String fieldName, String fieldDataType, String fieldLabel, String fieldErrorText, String fieldValidationRegex, int fieldRank, String fieldDefaultValue, String fieldOptions) {
                FormField newField = new FormField(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8)),rs.getString(9),rs.getString(10));
                newField.setFieldId(Integer.parseInt(rs.getString(1)));
                newField.setFieldOptional(rs.getInt(12));
                fields.add(newField);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getFormFields error: " + ex.getMessage() + " for Form: " + formId);
        }
        finally
        {
            closeConnection();
        }
        return fields;
    }
    
    
    
//    public static ArrayList<SiteTemplateUrl> getTemplateResources(int templateId)
//    {
//        ArrayList<SiteTemplateUrl> resources = new ArrayList<SiteTemplateUrl>();
//        try
//        {
//            runQuery("SELECT * FROM site_template_url WHERE SiteTemplateId=" + templateId + ";");
//            while(rs.next())
//            {
//                resources.add(new SiteTemplateUrl(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4)));
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("getTemplateResources error: " + ex.getMessage() + " for id: " + templateId);
//        }
//        finally
//        {
//            closeConnection();
//        }
//        return resources;
//    }
    
    public static Slider getSlider(String id)
    {
        Slider slider = null;
        try
        {
            getAllRecordsByColumn("slider", "SliderId", id, true);
            while(rs.next())
            {
                slider =new Slider(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getSlider with id(" + id + ") error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return slider;
    }
            
    
    public static ArrayList<SliderItem> getSliderItems(int SliderId)
    {
        ArrayList<SliderItem> items = new ArrayList<SliderItem>();
        try
        {
            runQuery("SELECT * FROM slider_item WHERE SliderId=" + SliderId  + " ORDER BY SliderItemRank;");
            while(rs.next())
            {
                int itemId = Integer.parseInt(rs.getString(1));
                int sliderId = Integer.parseInt(rs.getString(8));
                int itemRank = Integer.parseInt(rs.getString(7));
                
                //SliderItem(int itemId, int sliderId, String itemTitle, String itemDescription, String itemUrl, String itemImageName, String itemImageAlt, int itemRank)
                items.add( new SliderItem(itemId,sliderId,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),itemRank));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getSliderItems error: " + ex.getMessage() + " for Slider: " + SliderId);
        }
        finally
        {
            closeConnection();
        }
        return items;
    }
    
        public static String getSliderTypeName(int SliderTypeId)
    {
        String type = "";
        try
        {
            runQuery("SELECT * FROM slider_type WHERE SliderTypeId=" + SliderTypeId + ";");
            while(rs.next())
            {
                type = rs.getString(3);
            }
        }
        catch (Exception ex)
        {
            System.out.println("getSliderTypeName error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return type;
    }
    
    public static ArrayList<Item> getItems(int type, int limit, int pageNumber)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try
        {
            openConnection();
            String query = "SELECT ie.ItemImageName, t.ItemId, t.ItemName, t.ItemListPrice, t.ItemPrice, t.ItemBrandName, t.ItemRating, t.ItemShortDescription FROM item_image ie, (SELECT distinct i.ItemId, i.ItemName, i.ItemListPrice, i.ItemPrice, ib.ItemBrandName , i.ItemRating, i.ItemShortDescription FROM item i, item_brand ib Where i.ItemBrandId = ib.ItemBrandId  AND i.ItemType = " + type + ") as t WHERE t.ItemId = ie.ItemId group by t.ItemId limit " + limit + " offset  " + ((pageNumber*limit)-limit) + ";";
            ResultSet rs2 = statement.executeQuery(query);
            while(rs2.next())
            {
                items.add(new Item(rs2.getString(2),rs2.getString(1),rs2.getString(3),getOptions(rs2.getString(2)),Double.parseDouble(rs2.getString(4)),Double.parseDouble(rs2.getString(5)), 0, rs2.getString(6), "",Double.parseDouble(rs2.getString(7)), rs2.getString(8)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItems retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return items;


    }

    public static ArrayList<Post> getPosts(int blogId, int pageNumber)
    {
        ArrayList<Post> posts = new ArrayList<Post>();
        try
        {
            openConnection();

            String query = "SELECT * FROM blog_post  WHERE BlogId = " + blogId + " Order By PostDate desc;";
            ResultSet rs2 = statement.executeQuery(query);

            while(rs2.next())
            {
                Post p = new Post();

                p.setPostId(Integer.parseInt(rs2.getString("BlogPostId")));
                p.setPostTitle(rs2.getString("PostTitle"));
                p.setPostBody(rs2.getString("PostBody"));
                p.setPostPicURL(rs2.getString("PostPicURL"));
                p.setPostDate(rs2.getString("PostDate"));
                p.setPostTags(rs2.getString("PostTags"));
                p.setUserId(Integer.parseInt(rs2.getString("UserId")));
                p.setPostRating(Double.parseDouble(rs2.getString("PostRating")));

                p.setComments(getComments(rs2.getString("BlogPostId")));
                p.setPostUserName("FixME!");
                
                posts.add(p);

            }
        }
        catch (Exception ex)
        {
            System.out.println("Posts retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return posts;
    }

    public static ArrayList<Post> getPostsByUserId(String userId)
    {
        ArrayList<Post> posts = new ArrayList<Post>();
        try
        {
            runQuery("SELECT * FROM blog_post  WHERE UserId = " + userId + " AND PostFeatured = 5 Order By PostDate desc;");
            while(rs.next())
            {
                Post p = new Post();

                p.setPostId(Integer.parseInt(rs.getString("BlogPostId")));
                p.setPostTitle(rs.getString("PostTitle"));
                p.setPostBody(rs.getString("PostBody"));
                p.setPostPicURL(rs.getString("PostPicURL"));
                p.setPostDate(rs.getString("PostDate"));
                p.setPostTags(rs.getString("PostTags"));
                p.setUserId(Integer.parseInt(rs.getString("UserId")));
                p.setPostRating(Double.parseDouble(rs.getString("PostRating")));
                p.setPostUserName("FixME!");
                
                posts.add(p);
            }
            
            for(Post p : posts)
            {
                ArrayList<Comment> comments = new ArrayList<Comment>();

                String query = "SELECT * FROM comment WHERE PostId = " +  p.getPostId() + " Order By CommentDate desc;";
                rs = statement.executeQuery(query);

                while(rs.next())
                {
                    Comment pc = new Comment();

                    pc.setPostCommentId(Integer.parseInt(rs.getString("CommentId")));
                    pc.setCommentTitle(rs.getString("CommentTitle"));
                    pc.setCommentBody(rs.getString("CommentBody"));
                    pc.setCommentDate(rs.getString("CommentDate"));
                    pc.setPostId(p.getPostId());
                    pc.setUserId(Integer.parseInt(rs.getString("UserId")));

                    comments.add(pc);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("getPostsByUserId retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return posts;
    }

    public static Post getPost(int id)
    {
        Post p = new Post();
        try
        {
            openConnection();

            String query = "SELECT * FROM blog_post  WHERE BlogPostId = " + id + " Order By PostDate desc;";
            ResultSet rs2 = statement.executeQuery(query);

            while(rs2.next())
            {
                p.setPostId(Integer.parseInt(rs2.getString("BlogPostId")));
                p.setPostTitle(rs2.getString("PostTitle"));
                p.setPostBody(rs2.getString("PostBody"));
                p.setPostPicURL(rs2.getString("PostPicURL"));
                p.setPostDate(rs2.getString("PostDate"));
                p.setPostTags(rs2.getString("PostTags"));
                p.setUserId(Integer.parseInt(rs2.getString("UserId")));
                p.setPostRating(Double.parseDouble(rs2.getString("PostRating")));

                p.setComments(getComments(rs2.getString("BlogPostId")));
                p.setPostUserName("FixME!");
            }
        }
        catch (Exception ex)
        {
            p.setPostId(Integer.parseInt("5"));
            p.setPostTitle("Error");
            p.setPostBody(ex.getMessage());
            p.setPostPicURL("PostPicURL");
            p.setPostDate("PostDate");
            p.setPostTags("PostTags");
            p.setUserId(5);
            p.setPostRating(8);

            System.out.println("Post retreival error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return p;
    }


    public static ArrayList<Comment> getComments(String postId)
    {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try
        {
            openConnection();
            
            String query = "SELECT * FROM comment WHERE PostId = " +  postId + " Order By CommentDate desc;";
            ResultSet rs3 = statement.executeQuery(query);

            while(rs3.next())
            {
                Comment pc = new Comment();

                pc.setPostCommentId(Integer.parseInt(rs3.getString("CommentId")));
                pc.setCommentTitle(rs3.getString("CommentTitle"));
                pc.setCommentBody(rs3.getString("CommentBody"));
                pc.setCommentDate(rs3.getString("CommentDate"));
                pc.setPostId(Integer.parseInt(postId));
                pc.setUserId(Integer.parseInt(rs3.getString("UserId")));
                
                comments.add(pc);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Comments retreival for Post " + postId + " error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return comments;


    }


    public static void addBlogPostComment(String values)
    {
        try
        {
            openConnection();

            String query = "INSERT INTO comment " +  values + ";";

            System.out.println(query);
            statement.executeUpdate(query);
           
        }
        catch (Exception ex)
        {
            System.out.println("addBlogPostComment error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }


    public static int addNewBrand(String name, String desc)
    {

        int key = -1;
        try
        {
            openConnection();

           String query =  "INSERT INTO item_brand(ItemBrandName, ItemBrandDescription) VALUES ( '?', '?' );";

            System.out.println(query);

            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString( 1, name );
		pstmt.setString( 2, desc );
            
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();

            keys.next();
            key = keys.getInt(1);
            keys.close();
            pstmt.close();
        }
        catch (Exception ex)
        {
            System.out.println("addNewsletterSubscriber error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
            return key;
        }
    }

    public static String getRoles(String userName)
    {
        StringBuffer roles = new StringBuffer();
        try
        {
            openConnection();

            String query = "SELECT RoleName FROM user_role WHERE  UserName ='" + userName + "';";

            //System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                roles.append(rs.getString(1)).append(", ");
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get User Roles error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return roles.toString();
    }

//    public static ArrayList<Customer> getAllCustomers()
//    {
//        ArrayList<Customer> customers = new ArrayList<Customer>();
//        try
//        {
//            runQuery("SELECT * FROM customer cu, contact co, address ad WHERE cu.ContactId = co.ContactId AND cu.AddressId = ad.AddressId");
//
//            while(rs.next())
//            {
//                Address address = new Address(Integer.valueOf(rs.getString(2)),"", rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22), Integer.valueOf(rs.getString(23)));                
//                Contact contact = new Contact(Integer.valueOf(rs.getString(3)),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));                
//                customers.add(new Customer(Integer.valueOf(rs.getString(1)), contact, address));
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("getAllCustomers error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//
//        return customers;
//    }
//    
//    public static Customer getCustomer(String userName)
//    {
//        Customer c = null;
//        try
//        {
//            runQuery("SELECT * FROM customer cu, contact co, address ad WHERE cu.ContactId = co.ContactId AND cu.AddressId = ad.AddressId AND cu.userid in (select BrandId from `user` where UserName = '" + userName + "' );");
//
//            while(rs.next())
//            {
//                Address address = new Address(Integer.valueOf(rs.getString(2)),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22), Integer.valueOf(rs.getString(23)));
//                Contact contact = new Contact(Integer.valueOf(rs.getString(3)),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
//                c = new Customer(Integer.valueOf(rs.getString(1)), contact, address);
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Get Customer error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//
//        return c;
//    }


    public static ArrayList<StringLiteral> getStringLiterals()
    {
        ArrayList<StringLiteral> sla = new ArrayList<StringLiteral>();
        ResultSet rs2;
        ResultSet rs3;

        try
        {
            openConnection();
            String query = "SELECT * FROM text_string s;";
            //System.out.println(query);
            rs2 = statement.executeQuery(query);

            while(rs2.next())
            {
                StringLiteral sl = new StringLiteral();
                sl.setTextStringId(rs2.getString(1));
                sl.setTextStringKey(rs2.getString(2));
                
                ArrayList<LocalizedString> lsa = new ArrayList<LocalizedString>();
                query = "SELECT ls.StringId, ls.LocaleId, StringValue, LocaleString  FROM locale l, text_string s, localized_string ls WHERE ls.LocaleId = l.LocaleId AND ls.StringId = s.TextStringId AND ls.StringId = " + rs2.getString(1) + ";";
                //System.out.println(query);
                
                rs3 = connection.createStatement().executeQuery(query);
                while(rs3.next())
                {
                    LocalizedString ls = new LocalizedString();
                    ls.setStringLocaleId(rs3.getString(2));
                    ls.setStringValue(rs3.getString(3));
                    ls.setStringLocale(rs3.getString(4));
                    lsa.add(ls);
                }
                sl.setLocalizedStrings(lsa);
                sla.add(sl);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getStringLiterals error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return sla;
    }


    public static ArrayList<SiteAttribute> getSiteAttributes(String type)
    {
        ResultSet rs2;
        ArrayList<SiteAttribute> attributes = new ArrayList<SiteAttribute>();

        try
        {
            openConnection();
            String query = "SELECT * FROM site_attribute sa;";
            if(type != null) 
            {
                query = "SELECT * FROM site_attribute sa WHERE AttributeType='" + type + "';";
            }
            rs2 = statement.executeQuery(query);

            while(rs2.next())
            {
                attributes.add(new SiteAttribute(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4)));;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getSiteAttributes error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return attributes;
    }
    
    
        
    public static ArrayList<LocalizedString> getLocalizedStrings(String stringId)
    {
        ArrayList<LocalizedString> lsa = new ArrayList<LocalizedString>();
        ResultSet rs3;

        try
        {
            openConnection();
            String query = "SELECT ls.StringId, ls.LocaleId, StringValue, LocaleString  FROM locale l, text_string s, localized_string ls WHERE ls.LocaleId = l.LocaleId AND ls.StringId = s.TextStringId AND ls.StringId = " + stringId + ";";
            //System.out.println(query);
            rs3 = statement.executeQuery(query);

            while(rs3.next())
            {
                LocalizedString ls = new LocalizedString();
                ls.setStringLocaleId(rs3.getString(2));
                ls.setStringValue(rs3.getString(3));
                ls.setStringLocale(rs3.getString(4));
                lsa.add(ls);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getLocalizedStrings error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return lsa;
    }


    public static String getStoreLocale()
    {
        String s = "";
        ResultSet rs4;

        try
        {
            openConnection();
            statement = connection.createStatement();
            String query = "SELECT StoreLocalization FROM store_info";
            System.out.println(query);
            rs4 = statement.executeQuery(query);

            while(rs4.next())
            {
                s = rs4.getString(1);

            }
        }
        catch (Exception ex)
        {
            System.out.println("Get StoreLocale error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return s;
    }

    public static int createNewDesignItem(String brandName)
    {
        int key = -1;
        String brandId = getItemBrandId(brandName);
        try
        {
            openConnection();

           String query =  "INSERT INTO item(ItemName, ItemDescription, ItemBrandId, ItemListPrice, ItemPrice, ItemPriceAdjustment, ItemSEOTitle, ItemSEODescription, ItemSEOKeywords, ItemType)";
                  query += " VALUES ( '" + "-" + "', ";
                  query += " '" + "-" + "', ";
                  query += " " + brandId + ", ";
                  query += " " + "0.0" + ", ";
                  query += " " + "0.0" + ", ";
                  query += " " + "0" + ", ";
                  query += " '" + "-" + "', ";
                  query += " '" + "-" + "', ";
                  query += " '" + "-" + "', ";
                  query += " " + "5" + " ";
                  query += ");";

            System.out.println(query);

            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();

            keys.next();
            key = keys.getInt(1);
            keys.close();
            pstmt.close();
        }
        catch (Exception ex)
        {
            System.out.println("createNewDesignItem error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
            return key;
        }
    }

    public static String getItemBrandId(String brandName)
    {
        String s = "";
        ResultSet rs4;

        try
        {
            openConnection();
            statement = connection.createStatement();
            String query = "SELECT ItemBrandId FROM item_brand WHERE ItemBrandName = '" + brandName + "';";
            System.out.println(query);
            rs4 = statement.executeQuery(query);

            while(rs4.next())
            {
                s = rs4.getString(1);

            }
        }
        catch (Exception ex)
        {
            System.out.println("Get ItemBrandId error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return s;
    }

    public static void setNewDesignItemTitle(int itemId, String title)
    {
        try
        {
            openConnection();
            String query = "UPDATE item SET ItemName = '" + title + "' WHERE ItemId = " + itemId + ";";
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("Set NewDesignItemTitle error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void setNewDesignItemDescription(int itemId, String desc)
    {
        try
        {
            openConnection();

            String query = "UPDATE item SET ItemDescription = '" + encodeHTML(desc) + "' WHERE ItemId = " + itemId + ";";
            statement.executeUpdate(query);
            System.out.println(query);
        }
        catch (Exception ex)
        {
            System.out.println("Set NewDesignItemDescription error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static PageInfo getPageInfo(String pageId)
    {
        PageInfo info = null;
        try
        {
            openConnection();

            String query = "SELECT * FROM info where InfoId = " + pageId + ";";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                info =  new PageInfo(rs.getString(2), decodeHTML(rs.getString(3)), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                info.setPageId(rs.getString(1));
                info.setPageFormId(rs.getString(4));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getPageInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }
    
    public static SiteInfo getSiteInfo()
    {
        SiteInfo info = null;
        try
        {
            openConnection();

            String query = "SELECT * FROM site_info;";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                info =  new SiteInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7)), rs.getString(8), rs.getString(9));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getSiteInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }
    
     
    public static StoreInfo getStoreInfo(String id)
    {
        StoreInfo info = null;
        try
        {
            getAllRecordsByColumn("store_info", "StoreInfoId", id, true);

            while(rs.next())
            {
                info =  new StoreInfo(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(6)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getStoreInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }
    
    public static ArrayList<Image> getSiteImagesGivenCategory(int categoryId)
    {
        ArrayList<Image> imageInfo = new ArrayList<Image>();
        try
        {
            openConnection();
            String query = "SELECT * FROM image WHERE TypeId = " + categoryId + ";";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                imageInfo.add(new Image(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),Integer.parseInt(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7))));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getSiteImagesGivenCategory error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return imageInfo;
    }
    
    
    public static ArrayList<User> getAllUsersWithInfo()
    {
        ArrayList<User> users = new ArrayList<User>();
        User u;

        try
        {
            runQuery("SELECT * FROM `user` u;");
            while(rs.next())
            {           
                u = new User(rs.getInt("UserId"), rs.getString("UserName"), rs.getString("UserImgURL"));   
                u.setBrandId(rs.getString(2));
                u.setUserPassword(rs.getString(5));
                u.setUserSecurityQuestion(rs.getString(6));
                u.setUserSecurityAnswer(rs.getString(7));  
                u.setUserEmail(rs.getString("UserEmail"));
                u.setUserConfirmation(rs.getString("UserEmailConfirmed"));
                u.setUserWebUrl(rs.getString("UserWebUrl"));
                users.add(u);
            }
            
            for(User user : users)
            {
                rs = statement.executeQuery("SELECT * FROM `user` u, address a, contact c WHERE u.AddressId = a.AddressId AND u.ContactId = c.ContactId AND u.UserId = " + user.getUserId() + ";");
                while(rs.next())
                {
                    user.setUserContact(new Contact(rs.getInt("ContactId"), rs.getString("Title"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Position"),rs.getString("Phone"), rs.getString("Fax"), rs.getString("Email"), rs.getString("EmailConfirmed"),rs.getString("Info")));
                    user.setUserAddress(new Address(rs.getInt("AddressId"), rs.getString("Recipient"), rs.getString("Address1"), rs.getString("Address2"), rs.getString("City"), rs.getString("State"), rs.getString("Zipcode"), rs.getString("Country"), rs.getString("Region"),rs.getInt("status"), rs.getString("locale")));
                }
            }
            
            for(User user : users)
            {
                rs = statement.executeQuery("SELECT * FROM user_action WHERE UserId = " + user.getUserId() + " ORDER BY ActionDate DESC;");
                while(rs.next())
                {
                        user.getUserActions().add(new UserAction(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));                        
                }
            }
            
            for(User user : users)
            {
                StringBuffer roles = new StringBuffer();
                rs = statement.executeQuery("SELECT RoleName FROM user_role WHERE UserName ='" + user.getUserName() + "';");
                while(rs.next())
                {
                    roles.append(rs.getString(1)).append(", ");
                }
                user.setUserRoles(roles.toString());
            }
            
            for(User user : users)  //get service info
            {                           
                ArrayList<UserService> services = new ArrayList<UserService>();
                ArrayList<String> userServiceIds = new ArrayList<String>();
                
                rs = statement.executeQuery("SELECT * FROM user_service us WHERE UserId = '" + user.getUserId() + "';");
                while(rs.next())
                {
                    services.add(new UserService(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
                    userServiceIds.add(rs.getString(1));
                }
                
                for(int i = 0; i < services.size(); i++)
                {
                    String query = "SELECT * FROM service c WHERE ServiceId = " + userServiceIds.get(i) + ";";
                    System.out.println(query);
                    rs = statement.executeQuery(query);

                    while(rs.next())
                    {
                        services.get(i).setServiceDetails(new Service(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)));                
                    }
                }
                
                user.setUserServices(services);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getAllUsersWithActionsAndRoles error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return users;
    }
    
    public static String getPageContent(int id)
    {
        String info = null;
        try
        {
            openConnection();

            String query = "SELECT InfoDescription FROM info WHERE InfoId=" + id + ";";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                info =  rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getPageContent error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }
    
        
    public static String getPageContentByName(String pageName)
    {
        String info = null;
        try
        {
            openConnection();

            String query = "SELECT InfoDescription FROM info WHERE InfoName='" + pageName + "';";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                info =  rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getPageContent error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }
    
    public static String getAdministratorEmail()
    {
        String email = "";
        try
        {
            openConnection();

            String query = "SELECT si.SiteAdministratorEmail FROM site_info si;";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                email = rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get AdministratorEmail error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return email;
    }
    
    public static String getNotificationEmail()
    {
        String email = "";
        try
        {
            openConnection();

            String query = "SELECT si.SiteNotificationEmail FROM site_info si;";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                email = rs.getString(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get getNotificationEmail error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return email;
    }
        
    
    public static ArrayList<String> getPayPalInfo()
    {
        ArrayList<String> values = new ArrayList<String>();
        try
        {
            openConnection();

            String query = "SELECT * FROM paypal WHERE ActiveProfile = 1;";

            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                values.add(rs.getString(2));//PayPal URL
                values.add(rs.getString(3));//Currency
                values.add(rs.getString(4));//API User Name
                values.add(rs.getString(5));//API Pass
                values.add(rs.getString(6));//API Signature
                values.add(rs.getString(7));//API endpoint
                values.add(rs.getString(9));//Return URL
                values.add(rs.getString(10));//Cancel URL
                values.add(rs.getString(11));//PaymentType
                values.add(rs.getString(12));//Environment
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get PayPal Info error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return values;
    }
        
//    public static User getUser(String userName, String Password)
//    {
//        User u = new User();
//        try
//        {
//            openConnection();
//            statement = connection.createStatement();
//
//            String query = "SELECT * FROM user u, type t WHERE u.TypeId = t.TypeId AND UserName = '" + userName + "' AND UserPassword = '" + Password + "';";
//
//            System.out.println(query);
//            rs = statement.executeQuery(query);
//
//            while(rs.next())
//            {
//                u.setUserName(rs.getString(3));
//                u.setUserId(rs.getString(1));
//            }
//
//            //get folder info
//            query = "SELECT * FROM folders WHERE UserId = '" + u.getUserId() + "';";
//
//            System.out.println(query);
//            rs = statement.executeQuery(query);
//            int folderIndex = 0;
//            while(rs.next())
//            {
//
//                u.getFoldersList().add(new Folder(rs.getString(2)));
//
//                query = "SELECT * FROM bookmarks, folders WHERE UserId = '" + u.getUserId() + "' and bookmarks.FolderId = folders.folderId;";
//                Statement statement2 = connection.createStatement();
//                ResultSet rs2 = statement2.executeQuery(query);
//                while(rs2.next())
//                {
//                    u.getFoldersList().get(folderIndex).addBookmark(new Bookmark(rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5)));
//                }
//                folderIndex++;
//            }
//
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Get User error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//        return u;
//
//    }

    
    
    
    
       
    public static User getUserByEmail(String userEmail)
    {
        User c = new User();
        ResultSet rs7;

        try
        {
            openConnection();
            String query = "SELECT * FROM user u where UserEmail = '" + userEmail + "';";
            System.out.println(query);
            rs7 = statement.executeQuery(query);

            while(rs7.next())
            {
                c.setUserId(Integer.valueOf(rs7.getString(1)));
                c.setUserName(rs7.getString(4));
                c.setUserImgUrl(rs7.getString(8));
                c.setUserEmail(rs7.getString(9));
                c.setUserConfirmation(rs7.getString(10));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Get User by email address error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return c;
    }
    
    public static ArrayList<AbstractMap.SimpleEntry> parseOptions(String optionString){
        ArrayList<AbstractMap.SimpleEntry> fieldOptions = new ArrayList<AbstractMap.SimpleEntry>();
        if(optionString != null){
            if(optionString.length() > 0)
            {
                StringTokenizer st = new StringTokenizer(optionString, "=,"); 
                while(st.hasMoreTokens()) 
                { 
                    String key = st.nextToken(); 
                    String val = st.nextToken(); 
                    fieldOptions.add(new AbstractMap.SimpleEntry<String,String>(key,val));
                }
            }
        }
        return fieldOptions;
    }
        
    
    
    public static ArrayList<ShippingMethod> getShippingMethods()
    {
        ArrayList<ShippingMethod> shippingMethods = new ArrayList<ShippingMethod>();
        try
        {
            getAllRecordsByTableName("shipping");
            while(rs.next())
            {
                shippingMethods.add(new ShippingMethod(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getShippingMethods error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return shippingMethods;
    }
    
//    public static ArrayList<OrderInfo> getOrderInfoByStatus(String status)
//    {
//        ArrayList<OrderInfo> orders = new ArrayList<OrderInfo>();
//
//        try
//        {
//            openConnection();
//            
//            String query = "SELECT * FROM order o, shopping_cart sc WHERE o.OrderId = sc.OrderId order by OrderDate desc;";
//            
//            if (status != null)
//            {
//                if (!status.equals("all"))
//                {
//                    query ="SELECT * FROM order o, shopping_cart sc WHERE o.OrderId = sc.OrderId AND o.OrderStatus='" + status + "' order by OrderDate desc;";
//                }
//            }
//            
//            System.out.println(query);
//            
//            Statement statement2 = connection.createStatement(); 
//            ResultSet rs2;
//            
//            rs = statement.executeQuery(query);
//            
//            while(rs.next())
//            {
//                OrderInfo order = new OrderInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17));
//                
//                rs2 = statement2.executeQuery("SELECT * FROM shopping_cart_item sci WHERE ShoppingCartId = " + rs.getString(16) + ";");
//                while(rs2.next())
//                {
//                    order.getCartItems().add(new ShoppingCartItem(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),rs2.getString(6)));
//                }
//                                               
//                rs2 = statement2.executeQuery("SELECT * FROM customer cu, contact co, address ad WHERE cu.ContactId = co.ContactId AND cu.AddressId = ad.AddressId AND cu.CustomerId = " + rs.getString(17) + ";");
//                while(rs2.next())
//                {
//                    Address address = new Address(Integer.valueOf(rs2.getString(2)),rs2.getString(16),rs2.getString(17),rs2.getString(18),rs2.getString(19),rs2.getString(20),rs2.getString(21),rs2.getString(22),Integer.valueOf(rs2.getString(23)));
//                    Contact contact = new Contact(Integer.valueOf(rs2.getString(3)),rs2.getString(5),rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getString(9),rs2.getString(10),rs2.getString(11),rs2.getString(12),rs.getString(13));
//                    order.setCustomer(new Customer(Integer.valueOf(rs2.getString(1)), contact, address));                    
//                }
//                
//                rs2 = statement2.executeQuery("SELECT * FROM shipping WHERE shipping.ShippingId = " + rs.getString(2) + ";");
//                while(rs2.next())
//                {
//                    order.setShippingMethod(new ShippingMethod(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5)));                    
//                }
//                
//                orders.add(order);
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("getOrderInfoByStatus error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//        return orders;
//    }
    
    public static boolean deleteItem(int id, String filePath, String imagePath)
    {
        try
        {
            runQuery("SELECT * FROM item_image WHERE ItemId = " + id + ";");
            while(rs.next())
            {                                                              
                new java.io.File(imagePath  + java.io.File.separator + rs.getString(3)).delete();//delete image                                
                new java.io.File(imagePath  + java.io.File.separator + rs.getString(4)).delete();//delete thumbnail
            }             
            statement.executeUpdate("DELETE FROM item_image WHERE ItemId = " + id + ";"); 
			
            rs = statement.executeQuery("SELECT * FROM item_file WHERE ItemId = " + id + ";");
            while(rs.next())
            {                                                              
		new java.io.File(filePath  + java.io.File.separator + rs.getString(3)).delete();  //delete file            
            }              
            statement.executeUpdate("DELETE FROM item_file WHERE ItemId = " + id + ";"); 
         
            statement.executeUpdate("DELETE FROM item_option_available WHERE ItemId = " + id + ";"); 
            statement.executeUpdate("DELETE FROM item_category WHERE ItemId = " + id + ";"); 
            statement.executeUpdate("DELETE FROM item WHERE ItemId = " + id + ";"); 
        }
        catch (Exception ex)
        {
            System.out.println("deleteItem error: " + ex.getMessage());            
            return false;
        }
        finally
        {
            closeConnection();
        }
        return true;
    }
    
    public static ArrayList<Blog> getBlogs()
    {
        ArrayList<Blog> blogs = new ArrayList<Blog>();
        try
        {
            getAllRecordsByTableName("blog");
            while(rs.next())
            {
                blogs.add(new Blog(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getBlogs error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blogs;
    }
    
    public static Blog getBlog(int id)
    {
        Blog b = null;
        try
        {
            getAllRecordsByColumn("blog", "BlogId", Integer.toString(id), true);
            while(rs.next())
            {
                b = new Blog(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getBlog for id(" + id + ") error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return b;
    }
    
    public static ArrayList<SiteTemplateResource> getTemplateReources()
    {
        ArrayList<SiteTemplateResource> resources = new ArrayList<SiteTemplateResource>();
        try
        {
            runQuery("SELECT stu.Url, srt.ResourceValue FROM site_template_url stu, site_template st, site_resource_type srt WHERE st.SiteTemplateId = stu.SiteTemplateId AND st.TemplateStatus = 1 AND stu.ResourceTypeId = srt.SiteResourceTypeId;");
            while(rs.next())
            {     
                resources.add(new SiteTemplateResource(rs.getString(1),rs.getString(2)));
            }             

        }
        catch (Exception ex)
        {
            System.out.println("getTemplateReources error: " + ex.getMessage());        
        }
        finally
        {
            closeConnection();
        }
        return resources;
    }
    
    public static ArrayList<SiteTemplateResource> getTemplateReourcesByType(String typeName)
    {
        ArrayList<SiteTemplateResource> resources = new ArrayList<SiteTemplateResource>();
        try
        {
            runQuery("SELECT stu.Url, srt.ResourceValue FROM site_template_url stu, site_template st, site_resource_type srt, site_template_type stt WHERE st.SiteTemplateId = stu.SiteTemplateId AND st.TemplateTypeId = stt.SiteTemplateTypeId AND stt.TypeValue = '" + typeName + "' AND stu.ResourceTypeId = srt.SiteResourceTypeId;");
            while(rs.next())
            {     
                resources.add(new SiteTemplateResource(rs.getString(1),rs.getString(2)));
            }             

        }
        catch (Exception ex)
        {
            System.out.println("getTemplateReourcesByType error: " + ex.getMessage());        
        }
        finally
        {
            closeConnection();
        }
        return resources;
    }

        
    
    
//    public static ArrayList<User> getUsersWithServices()
//    {
//        ArrayList<User> users = new ArrayList<User>();        
//        ResultSet rs4;
//
//        try
//        {
//            openConnection();
//            statement = connection.createStatement();
//
//            String query = "SELECT * FROM user u, address a, contact c WHERE a.AddressId = u.AddressId AND c.ContactId = u.ContactId;";
//            System.out.println(query);
//            rs4 = statement.executeQuery(query);
//
//            while(rs4.next())
//            {                                
//                Contact c = new Contact(rs4.getInt("ContactId"), rs4.getString("Title"),rs4.getString("FirstName"),rs4.getString("LastName"),rs4.getString("Position"),rs4.getString("Phone"), rs4.getString("Fax"), rs4.getString("Email"), rs4.getString("EmailConfirmed"),rs4.getString("Info"));               
//                Address a = new Address(rs4.getInt("AddressId"), rs4.getString("Address1"), rs4.getString("Address2"), rs4.getString("City"), rs4.getString("State"), rs4.getString("Zipcode"), rs4.getString("Country"), rs4.getString("Region"), rs4.getInt("UserId"));
//                
//                User u = new User();                               
//                u.setUserContact(c);
//                u.setUserAddress(a);
//                u.setUserId(Integer.valueOf(rs4.getInt("UserId")));
//                u.setUserName(rs4.getString("UserName"));
//                u.setUserImgUrl(rs4.getString("UserImgURL"));
//                u.setUserEmail(rs4.getString("UserEmail"));
//                u.setUserConfirmation(rs4.getString("UserEmailConfirmed"));
//                u.setUserWebUrl(rs4.getString("UserWebUrl"));
//            
//                ArrayList<UserService> services = new ArrayList<UserService>();
//                ArrayList<String> userServiceIds = new ArrayList<String>();
//                
//                rs = statement.executeQuery("SELECT * FROM user_service us WHERE UserId = '" + u.getUserId() + "';");
//                while(rs.next())
//                {
//                    services.add(new UserService(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
//                    userServiceIds.add(rs.getString(1));
//                }
//                
//                for(int i = 0; i < services.size(); i++)
//                {
//                    query = "SELECT * FROM service c WHERE ServiceId = " + userServiceIds.get(i) + ";";
//                    System.out.println(query);
//                    rs = statement.executeQuery(query);
//
//                    while(rs.next())
//                    {
//                        services.get(i).setServiceDetails(new Service(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)));                
//                    }
//                }
//                
//                u.setUserServices(services);                
//                users.add(u);
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Get getUsersWithServices error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//
//        return users;
//    }
    
//    public static ArrayList<User> getUserWithServices(String userId)
//    {
//        ArrayList<User> users = new ArrayList<User>(); 
//
//        try
//        {
//            String query = "SELECT * FROM `user` u, user_service us, address a, contact c WHERE u.AddressId = a.AddressId AND u.ContactId = c.ContactId AND us.UserId = " + userId + ";";
//            runQuery(query);
//
//            while(rs.next())
//            {                                
//                Contact c = new Contact(rs.getInt("ContactId"), rs.getString("Title"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Position"),rs.getString("Phone"), rs.getString("Fax"), rs.getString("Email"), rs.getString("EmailConfirmed"),rs.getString("Info"));               
//                Address a = new Address(rs.getInt("AddressId"), rs.getString("Address1"), rs.getString("Address2"), rs.getString("City"), rs.getString("State"), rs.getString("Zipcode"), rs.getString("Country"), rs.getString("Region"), rs.getInt("UserId"));
//                
//                User u = new User();                               
//                u.setUserContact(c);
//                u.setUserAddress(a);
//                u.setUserId(Integer.valueOf(rs.getInt("UserId")));
//                u.setUserName(rs.getString("UserName"));
//                u.setUserPassword(rs.getString("UserPassword"));
//                u.setUserSecurityQuestion(rs.getString("UserSecurityQuestion"));
//                u.setUserSecurityAnswer(rs.getString("UserSecurityAnswer"));
//                u.setUserImgUrl(rs.getString("UserImgURL"));
//                u.setUserEmail(rs.getString("UserEmail"));
//                u.setUserConfirmation(rs.getString("UserEmailConfirmed"));
//                u.setUserWebUrl(rs.getString("UserWebUrl"));
//            
//                ArrayList<UserService> services = new ArrayList<UserService>();
//                ArrayList<String> userServiceIds = new ArrayList<String>();
//                
//                rs = statement.executeQuery("SELECT * FROM user_service us WHERE UserId = '" + u.getUserId() + "';");
//                while(rs.next())
//                {
//                    services.add(new UserService(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
//                    userServiceIds.add(rs.getString(1));
//                }
//                
//                for(int i = 0; i < services.size(); i++)
//                {
//                    query = "SELECT * FROM service c WHERE ServiceId = " + userServiceIds.get(i) + ";";
//                    System.out.println(query);
//                    rs = statement.executeQuery(query);
//
//                    while(rs.next())
//                    {
//                        services.get(i).setServiceDetails(new Service(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)));                
//                    }
//                }
//                
//                u.setUserServices(services);  
//                users.add(u);
//
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Get getUserWithServices error: " + ex.getMessage());
//        }
//        finally
//        {
//            closeConnection();
//        }
//
//        return users;
//    }

    public static Service getService(String serviceId)
    {
        Service s = null;
        ResultSet rs3;

        try
        {
            openConnection();
            Statement statement3 = connection.createStatement();

            String query = "SELECT * FROM service c WHERE ServiceId = " + serviceId + ";";
            System.out.println(query);
            rs3 = statement3.executeQuery(query);

            while(rs3.next())
            {
                s = new Service(rs3.getInt(1), rs3.getInt(2),rs3.getInt(3),rs3.getString(4),rs3.getString(5));                
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in getService: " + e.getMessage());
        }

        return s;
    }

    
    public static void addItemReview(String ItemReviewId, String ItemId, String CommentId, String Rating)
    {
        try
        {
            updateQuery("INSERT INTO item_review(ItemReviewId,ItemId,CommentId,Rating) VALUES ('" + ItemReviewId + "','" + ItemId + "','" + CommentId + "','" + Rating + "');");
        }
        catch (Exception ex)
        {
            System.out.println("addItemReview error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static ArrayList<ItemReview> getAllItemReview()
    {
        ArrayList<ItemReview> item_review = new ArrayList<ItemReview>();
        try
        {
            getAllRecordsByTableName("item_review");
            while (rs.next())
            {
                item_review.add(new ItemReview(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllItemReview error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_review;
    }

    public static ItemReview getItemReviewById(String id)
    {
        ItemReview item_review = new ItemReview();
        try
        {
            String query = "SELECT * FROM Item_review WHERE ItemReviewId = " + id + ";";
            runQuery(query);
            while (rs.next())
            {
                item_review = new ItemReview(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemReviewById retrieval error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_review;
    }

    public static void updateItemReview(String ItemReviewId, String ItemId, String CommentId, String Rating)
    {
        try
        {
            updateQuery("UPDATE item_review SET ItemReviewId='" + ItemReviewId + "',ItemId='" + ItemId + "',CommentId='" + CommentId + "',Rating='" + Rating + "';");
        }
        catch (Exception ex)
        {
            System.out.println("updateItemReview error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteItemReviewById(String id)
    {
        try
        {
            updateQuery("DELETE FROM item_review WHERE ItemReviewId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteItemReviewById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteItemReviewByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM item_review WHERE " + columnName + "='" + columnValue + "';");
        }
        catch (Exception ex)
        {
            System.out.println("deleteItemReviewByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static void addItemLocation(String ItemLocationId, String ItemId, String AddressId, String Latitude, String Longitude)
    {
        try
        {
            updateQuery("INSERT INTO item_location(ItemLocationId,ItemId,AddressId,Latitude,Longitude) VALUES ('" + ItemLocationId + "','" + ItemId + "','" + AddressId + "','" + Latitude + "','" + Longitude + "');");
        }
        catch (Exception ex)
        {
            System.out.println("addItemLocation error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static ArrayList<ItemLocation> getAllItemLocation()
    {
        ArrayList<ItemLocation> item_location = new ArrayList<ItemLocation>();
        try
        {
            getAllRecordsByTableName("item_location");
            while (rs.next())
            {
                item_location.add(new ItemLocation(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getAllItemLocation error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_location;
    }

    public static ItemLocation getItemLocationById(String id)
    {
        ItemLocation item_location = new ItemLocation();
        try
        {
            String query = "SELECT * FROM Item_location WHERE ItemLocationId = " + id + ";";
            runQuery(query);
            while (rs.next())
            {
                item_location = new ItemLocation(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
        catch (Exception ex)
        {
            System.out.println("getItemLocationById retrieval error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_location;
    }

    public static void updateItemLocation(String ItemLocationId, String ItemId, String AddressId, String Latitude, String Longitude)
    {
        try
        {
            updateQuery("UPDATE item_location SET ItemLocationId='" + ItemLocationId + "',ItemId='" + ItemId + "',AddressId='" + AddressId + "',Latitude='" + Latitude + "',Longitude='" + Longitude + "';");
        }
        catch (Exception ex)
        {
            System.out.println("updateItemLocation error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteItemLocationById(String id)
    {
        try
        {
            updateQuery("DELETE FROM item_location WHERE ItemLocationId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteItemLocationById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteItemLocationByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM item_location WHERE " + columnName + "='" + columnValue + "';");
        }
        catch (Exception ex)
        {
            System.out.println("deleteItemLocationByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static int checkSliderImageCount(int id)
    {
        int count = 0;
        //first check to see if any slider are using the image
        try
        {
            runQuery("SELECT count(*) FROM slider_item si, image i WHERE si.SliderITemImageName = i.FileName AND i.ImageId = " + id + ";");
            if (rs.next())
            {
                //if the number of associated sliders are more than 0, then image cannot be deleted
                count = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("checkSliderImageCount error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return count;
                        
    }
    
    
    //*/
}