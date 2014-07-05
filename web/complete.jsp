
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>
<%@ page import="java.math.BigDecimal" %> 
<%@ page import="java.util.Map" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %>    

        <script type="text/javascript" src="scripts/checkout.js"></script>  
        <script type="text/javascript">
            //build menu with DIV Id="myslidemenu" on page:
            droplinemenu.buildmenu("mydroplinemenu")
        </script>
    </head>
    <body>

        <div id="wrapper">

            <%@include file="store_header.jsp" %>

            <div id="topbarNoSide"></div>

            <div id="contentNoSide">    
                <div id="body">
                    <div id="titleGraphic" align="center"></div>        
                    <div id="featuredDesigns">


                        <%! String makeOrderInfoTable(String ln, String fn, String addr, String city, String state, String zip, String country, String email, String orderId, String orderTime, String subTotalPrice, String tax, String shipping, String TotalPrice)
                            {
                                String output = "";

                                output += "<table width=\"754\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\">";
                                output += "    <tbody>";
                                output += "        <tr>";
                                output += "            <td width=\"6\"></td>";
                                output += "            <td width=\"742\"><font color=\"#800000\" face=\"Arial\">All orders are shipped ";
                                output += "                    using UPS and are sent fully insured. Delivery time is usually 2-5 ";
                                output += "                    <b>business</b> <b>days </b>from the time your package leaves our ";
                                output += "                    warehouse (on in stock items) unless you choose an expedited ";
                                output += "                   shipping method..</font>";
                                output += "            </td>";
                                output += "        </tr>";
                                output += "        <tr>";
                                output += "            <td colspan=\"2\" valign=\"top\" width=\"750\" bgcolor=\"#cccccc\">";
                                output += "            <table width=\"750\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\"><tbody>";
                                output += "          <tr>";
                                output += "            <td valign=\"top\" width=\"375\" bgcolor=\"#cccccc\"><font class=\"cart\"><b>Shipping Information</b></font></td>";
                                output += "            <td valign=\"top\" bgcolor=\"#cccccc\"><font class=\"cart\"><b>Order Details</b></font></td>";
                                output += "          </tr>";
                                output += "         <tr>";
                                output += "            <td valign=\"top\" bgcolor=\"#eeeeee\">";
                                output += "              <table width=\"375\" border=\"0\" cellpadding=\"0\" cellspacing=\"3\">";
                                output += "                <tbody>";
                                output += "                <tr>";
                                output += "                  <td>";
                                output += "                    <p><strong>" + fn + " " + ln + "</strong></p>";
                                output += "                    <p>" + addr + "<br>";
                                output += "                      " + city + ", " + state + " " + zip + "<br>" + country + "";
                                output += "                    <br><br>" + email + "</p>                        </td></tr>";
                                output += "                <tr>";
                                output += "                <tr>";
                                output += "                    <td valign=\"top\" width=\"375\" bgcolor=\"#cccccc\"><font class=\"cart\"><b>Billing Information</b></font></td> ";
                                output += "                    </tr>";
                                output += "                    <tr>";
                                output += "                  <td>";
                                output += "                    <p><strong>" + fn + " " + ln + "</strong></p>";
                                output += "                    <p>" + addr + "<br>";
                                output += "                      " + city + ", " + state + " " + zip + "<br>" + country + "";
                                output += "                    <br><br>" + email + "</p>                        </td></tr>";
                                output += "</tbody></table></td>";
                                output += "            <td valign=\"top\" bgcolor=\"#eeeeee\">";
                                output += "              <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"3\">";
                                output += "                <tbody>";
                                output += "                <tr>";
                                output += "                  <td>";
                                output += "                  	<table width=\"95%\" border=\"0\" cellspacing=\"2\" cellpadding=\"0\">";
                                output += " <tr>";
                                output += "  <td width=\"31%\"><div align=\"right\"> Order Number: </div></td>";
                                output += "   <td width=\"69%\">&nbsp;" + orderId + "</td>";
                                output += "  </tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\">Order Date: </div></td>";
                                output += "  <td>&nbsp;" + orderTime + "</td>";
                                output += "</tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\"></div></td>";
                                output += "  <td>&nbsp;</td>";
                                output += "</tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\">Payment Term:</div></td>";
                                output += "  <td>&nbsp;PayPal</td>";
                                output += "</tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\">Subtotal:</div></td>";
                                output += "  <td>&nbsp;" + subTotalPrice + "</td>";
                                output += "</tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\">Tax:</div></td>";
                                output += "  <td>&nbsp;" + tax + "</td>";
                                output += "</tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\">Shipping:</div></td>";
                                output += "  <td>&nbsp;" + shipping + "</td>";
                                output += "</tr>";
                                output += "  <tr>";
                                output += "  <td><div align=\"right\">Total Amount:</div></td>";
                                output += "  <td>&nbsp;$" + TotalPrice + "</td>";
                                output += "</tr>";
                                output += "<tr>";
                                output += "  <td><div align=\"right\"></div></td>";
                                output += "  <td>&nbsp;</td>";
                                output += "</tr>";
                                output += "</table>";
                                output += "                    </td>";
                                output += "                 </tr>";
                                output += "                 <tr>";
                                output += "                   <td>";
                                output += "";
                                output += "                    </td>";
                                output += "                 </tr>";
                                output += "               <tr>";
                                output += "                  <td>";
                                output += "                    <br>";
                                output += "                   </td></tr>";
                                output += "               </tbody></table>";
                                output += "";
                                output += "                 </td></tr>";
                                output += "</tbody></table></td></tr>";
                                output += "       </tbody></table>";

                                return output;
                            }
                        %>

                        <%
                            // Get a formatter to write out currency values.
                            NumberFormat currency = NumberFormat.getCurrencyInstance();
                            
                            PayPalFunctions PayPal = new PayPalFunctions(Database.getPayPalInfo());

                            // Get the current shopping cart from the user's session.
                            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

                            // If the user doesn't have a shopping cart yet, create one.
                            if (cart == null)
                            {
                                cart = new ShoppingCart();
                                session.setAttribute("ShoppingCart", cart);
                            }
                        %>


                        <%
                            /*
                             '------------------------------------
                             ' The paymentAmount is the total value of the shopping cart, that was set 
                             ' earlier in a session variable by the shopping cart page
                             '------------------------------------
                             */
                            String finalPaymentAmount = currency.format(cart.getTotalPrice());
                            finalPaymentAmount = finalPaymentAmount.substring(1, finalPaymentAmount.length()); //cut the $ symbol

                            HashMap nvp = null;


                            //ExpressCheckout Response Variables
                            String transactionType = null;
                            String paymentType = null;
                            String feeAmt = null;
                            String taxAmt = null;
                            String paymentStatus = null;
                            String pendingReason = null;
                            String reasonCode = null;

                            String orderTime = "";
                            String orderId = "";

                            //DirectPayment Response Variables
                            String timeStamp = null;
                            String correlationId = null;
                            String version = null;
                            String build = null;
                            String AVScode = null;
                            String CVV2match = null;

                            //shared Variables
                            String amt = "";
                            String currencyCode = null;
                            String transactionId = null;


                            if (((String) session.getAttribute("CheckoutMethod")).equals("Direct"))
                            {

                                System.out.println("Direct Payment Method was chosen");
                                /*
                                 '------------------------------------------------------------------------
                                 ' Calls the DoDirectPayment API call
                                 '
                                 ' Defined in the file PayPalFunctions.jsp
                                 '------------------------------------------------------------------------
                                 */
                                nvp = PayPal.DoDirectPayment(session, finalPaymentAmount);

                                String strAck = nvp.get("ACK").toString();
                                if (strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
                                {
                                    timeStamp = nvp.get("TIMESTAMP").toString();      //used in order record	
                                    amt = nvp.get("AMT").toString();	        //used in order record		             
                                    currencyCode = nvp.get("CURRENCYCODE").toString();   //used in order record
                                    transactionId = nvp.get("TRANSACTIONId").toString();  //used in order record

                                    correlationId = nvp.get("CORRELATIONId").toString();  //Added to OrderAdditionaldata column in Orders table
                                    version = nvp.get("VERSION").toString();        //Added to OrderAdditionaldata column in Orders table
                                    build = nvp.get("BUILD").toString();	        //Added to OrderAdditionaldata column in Orders table	                                               	             
                                    AVScode = nvp.get("AVSCODE").toString(); 	//Added to OrderAdditionaldata column in Orders table
                                    CVV2match = nvp.get("CVV2MATCH").toString();      //Added to OrderAdditionaldata column in Orders table

                                    String additionalData = "Correlation Id: " + correlationId + "\n ";
                                    additionalData += "Version: " + version + "\n ";
                                    additionalData += "Build: " + build + "\n ";
                                    additionalData += "AVScode: " + AVScode + "\n ";
                                    additionalData += "CVV2: " + CVV2match + "\n ";

                                    if (AVScode.equals("X") || AVScode.equals("Y") || AVScode.equals("D") || AVScode.equals("F"))
                                    {
                                        if (AVScode.equals("D"))
                                        {
                                            additionalData += "InternationalTransaction: " + "Yes" + "\n ";
                                        }
                                        if (AVScode.equals("F"))
                                        {
                                            additionalData += "UKTransaction: " + "Yes" + "\n ";
                                        }
                                        if (CVV2match.equals("M"))
                                        {
                                            System.out.println("Adding Customer and Order details to the Database!");

                                            //insert the information into the database
                                            String customerId = Database.createCustomer(cart.getShippingAddress().getFirstName(), cart.getShippingAddress().getLastName(),
                                                    cart.getShippingAddress().getEmail(), cart.getShippingAddress().getAddress1(),
                                                    cart.getShippingAddress().getCity(), cart.getShippingAddress().getState(),
                                                    cart.getShippingAddress().getPostalCode(), cart.getShippingAddress().getCountry());

                                            System.out.println("Added Customer: " + customerId);
                                            System.out.println("Adding Order to the Database!");

                                            String finalTaxAmount = currency.format(cart.getTaxPrice());
                                            finalTaxAmount = finalTaxAmount.substring(1, finalTaxAmount.length()); //cut the $ symbol

                                            orderId = Database.createOrder(cart.getShippingAddress().getMethod(), "paid", timeStamp,
                                                    transactionId, amt, "Completed", "none", "Instant", "0", currencyCode,
                                                    "CreditDebitCard", finalTaxAmount, Double.toString(cart.getShipPrice()), additionalData);

                                            System.out.println("Added Order " + orderId);

                                            String shoppingCartId = Database.createShoppingCart(customerId, orderId);

                                            /*
                                             prepare e-mail to be sent to the customer
                                             MailerBean EMail = new MailerBean();
                                             EMail.setReceiver(cart.getShipping().getEmail());                
                                             EMail.setSubject("Your Order Information ");
                                             EMail.addToMessage("<HTML>\n" + "<HEAD><TITLE>Hello, </TITLE></HEAD>\n" +
                                             "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=CENTER>Thank you for shopping </H1>\n<br>");
                                             EMail.addToMessage(makeOrderInfoTable(cart.getShipping().getLastName(), cart.getShipping().getFirstName(), 
                                             cart.getShipping().getAddress1(),cart.getShipping().getCity(), 
                                             cart.getShipping().getState(), cart.getShipping().getPostalCode(), 
                                             cart.getShipping().getCountry(), cart.getShipping().getEmail(),orderId, 
                                             orderTime, Double.toString(cart.getSubTotalPrice()),formatter.format(cart.getTaxPrice()), 
                                             Double.toString(cart.getShipPrice()), amt));

                                             EMail.addToMessage("\n</BODY></HTML>");		   
                                             EMail.sendMail();    
                                             */

                                            System.out.println("Populating Shopping Cart!");
                                            for (Item i : (Vector<Item>) cart.getItems())
                                            {
                                                Database.addToShoppingCart(shoppingCartId, i.getItemId(), i.getQuantity(), i.getItemOption(), Double.toString(i.getItemPrice()));
                                                Database.subtractFromItemQuantity(i.getItemId(), i.getItemOption(), i.getQuantity());
                                            }
                                        } 
                                        else //CVV2 check returned a response indicating a problem with the code 
                                        {
                                            String responseText = "";
                                            if (CVV2match.equals("N"))
                                            {
                                                responseText += "CVV Code Error: Only Address Matches, Not Zip Code<br/>";
                                            } 
                                            else if (CVV2match.equals("P"))
                                            {
                                                responseText += "CVV Code Error: (International) Address matches, Not Zip Code<br/>";
                                            } 
                                            else if (CVV2match.equals("S"))
                                            {
                                                responseText += "CVV Code Error: (International) Transaction Declied<br/>";
                                            } 
                                            else if (CVV2match.equals("U"))
                                            {
                                                responseText += "CVV Code Error: Not allowed for MOTO (Internet/Phone) transactions, Transaction Declied<br/>";
                                            } 
                                            else if (CVV2match.equals("X"))
                                            {
                                                responseText += "CVV Code Error: Global Unavailable, Transaction Declied<br/>";
                                            } 
                                            else
                                            {
                                                responseText += "Unknown Error regarding Address Verification Occured, contact site admin<br/>";
                                            }

                                            System.out.println("Error in Transaction: " + responseText);
                                            response.sendRedirect("Checkout.jsp?error=" + responseText);
                                        }
                                    } 
                                    else //Address Verification System (AVS) returned a response indicating a problem with the address 
                                    {
                                        String responseText = "";
                                        if (AVScode.equals("A"))
                                        {
                                            responseText += "Address Verification Error: Only Address Matches, Not Zip Code<br/>";
                                        } 
                                        else if (AVScode.equals("B"))
                                        {
                                            responseText += "Address Verification Error: (International) Address matches, Not Zip Code<br/>";
                                        } 
                                        else if (AVScode.equals("C"))
                                        {
                                            responseText += "Address Verification Error: (International) Transaction Declied<br/>";
                                        } 
                                        else if (AVScode.equals("E"))
                                        {
                                            responseText += "Address Verification Error: Not allowed for MOTO (Internet/Phone) transactions, Transaction Declied<br/>";
                                        } 
                                        else if (AVScode.equals("G"))
                                        {
                                            responseText += "Address Verification Error: Global Unavailable, Transaction Declied<br/>";
                                        } 
                                        else if (AVScode.equals("I"))
                                        {
                                            responseText += "Address Verification Error: International Unavailable, Transaction Declied<br/>";
                                        } 
                                        else if (AVScode.equals("N"))
                                        {
                                            responseText += "Address Verification Error: Transaction Declied<br/>";
                                        } 
                                        else if (AVScode.equals("P"))
                                        {
                                            responseText += "Address Verification Error: (International) Zip Code matches, Not Address<br/>";
                                        } 
                                        else if (AVScode.equals("R"))
                                        {
                                            responseText += "Address Verification Error: Retry<br/>";
                                        } 
                                        else if (AVScode.equals("S"))
                                        {
                                            responseText += "Address Verification Error: Service Not Supported<br/>";
                                        } 
                                        else if (AVScode.equals("U"))
                                        {
                                            responseText += "Address Verification Error: Unavailable<br/>";
                                        } 
                                        else if (AVScode.equals("W"))
                                        {
                                            responseText += "Address Verification Error: (9Digit) Zip Code matches, Not Address<br/>";
                                        } 
                                        else if (AVScode.equals("Z"))
                                        {
                                            responseText += "Address Verification Error: (5Digit) Zip Code matches, Not Address<br/>";
                                        } 
                                        else
                                        {
                                            responseText += "Unknown Error regarding Address Verification Occured, contact site admin<br/>";
                                        }

                                        System.out.println("Error in Transaction: " + responseText);
                                        response.sendRedirect("Checkout.jsp?error=" + responseText);
                                    }

                                } 
                                else
                                {
                                    // Display a user friendly Error on the page using any of the following error information returned by PayPal

                                    String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                                    String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                                    String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                                    String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();

                                    System.out.println("-----------------------------------------------");
                                    System.out.println("Error occured on the complete.jsp page in Confirming the payment:");
                                    System.out.println("     Code: " + ErrorCode);
                                    System.out.println("Short Msg: " + ErrorShortMsg);
                                    System.out.println(" Long Msg: " + ErrorLongMsg);
                                    System.out.println(" Severity: " + ErrorSeverityCode);
                                    System.out.println("-----------------------------------------------");
                                }

                            } 
                            else if (((String) session.getAttribute("CheckoutMethod")).equals("Express"))
                            {
                                System.out.println("Express Checkout Method was chosen");

                                /*==================================================================
                                 PayPal Express Checkout Call
                                 5b. Save the total payment amount in a Session variable named "Payment_Amount". 
                                 5c. Process the information returned by your code and complete your order backend processing.
                                 ===================================================================
                                 */

                                /*
                                 '----------------------------------------------------
                                 ' Get the token parameter value stored in the session 
                                 ' from the previous SetExpressCheckout call
                                 '----------------------------------------------------
                                 */
                                String token = (String) session.getAttribute("TOKEN");

                                if (token == null)
                                {
                                    System.out.println("Token was NULL, trying the request");
                                    token = (String) request.getAttribute("token");
                                }


                                /*
                                 '------------------------------------------------------------------------
                                 ' Calls the DoExpressCheckoutPayment API call
                                 ' The ConfirmPayment function is defined in the file PayPalFunctions
                                 '------------------------------------------------------------------------
                                 */
                                nvp = PayPal.DoExpressCheckoutPayment(session, finalPaymentAmount);

                                String strAck = nvp.get("ACK").toString();
                                if (strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
                                {
                                    // ' Unique transaction Id of the payment. 
                                    //Note:  If the PaymentAction of the request was Authorization or Order, 
                                    //       this value is your AuthorizationId for use with the Authorization & Capture APIs.
                                    transactionId = nvp.get("TRANSACTIONId").toString();

                                    //' The type of transaction Possible values: 0  cart l  express-checkout 
                                    transactionType = nvp.get("TRANSACTIONTYPE").toString();

                                    //' Indicates whether the payment is instant or delayed. 
                                    //  Possible values: 0  none 
                                    //                   l  echeck 
                                    //                   2  instant 
                                    paymentType = nvp.get("PAYMENTTYPE").toString();

                                    //' Time/date stamp of payment
                                    orderTime = nvp.get("ORDERTIME").toString();

                                    //' The final amount charged, including any shipping and taxes from your Merchant Profile.
                                    amt = nvp.get("AMT").toString();

                                    //' A three-character currency code for one of the currencies listed in 
                                    //  PayPay-Supported Transactional Currencies. Default: USD.
                                    currencyCode = nvp.get("CURRENCYCODE").toString();

                                    //' PayPal fee amount charged for the transaction
                                    feeAmt = nvp.get("FEEAMT").toString();

                                    //' Amount deposited in your PayPal account after a currency conversion.
                                    //String settleAmt	= nvp.get("SETTLEAMT").toString();       

                                    //' Tax charged on the transaction.
                                    taxAmt = nvp.get("TAXAMT").toString();

                                    //' Exchange rate if a currency conversion occurred. Relevant only if your are billing in 
                                    //  their non-primary currency. If the customer chooses to pay with a currency other than 
                                    //  the non-primary currency, the conversion occurs in the customer?s account.
                                    //String exchangeRate	= nvp.get("EXCHANGERATE").toString();    

                                    /*
                                     ' Status of the payment: 
                                     ' Completed: The payment has been completed, and the funds have been added successfully to your account balance.
                                     '   Pending: The payment is pending. See the PendingReason element for more information. 
                                     */

                                    paymentStatus = nvp.get("PAYMENTSTATUS").toString();

                                    /*
                                     'The reason the payment is pending:
                                     '            none: No pending reason 
                                     '         address: The payment is pending because your customer did not include a confirmed shipping 
                                     '                  address and your Payment Receiving Preferences is set such that you want to manually 
                                     '                  accept or deny each of these payments. To change your preference, go to the Preferences 
                                     '                  section of your Profile. 
                                     '          echeck: The payment is pending because it was made by an eCheck that has not yet cleared. 
                                     '            intl: The payment is pending because you hold a non-U.S. account and do not have a withdrawal 
                                     '                  mechanism. You must manually accept or deny this payment from your Account Overview. 		
                                     '  multi-currency: You do not have a balance in the currency sent, and you do not have your Payment Receiving
                                     '                  Preferences set to automatically convert and accept this payment. You must manually accept 
                                     '                  or deny this payment. 
                                     '          verify: The payment is pending because you are not yet verified. You must verify your account 
                                     '                  before you can accept this payment. 
                                     '           other: The payment is pending for a reason other than those listed above. For more information, 
                                     '                  contact PayPal customer service. 
                                     */
                                    pendingReason = nvp.get("PENDINGREASON").toString();

                                    /*
                                     'The reason for a reversal if TransactionType is reversal:
                                     '             none: No reason code 
                                     '       chargeback: A reversal has occurred on this transaction due to a chargeback by your customer. 
                                     '        guarantee: A reversal has occurred on this transaction due to your customer triggering a 
                                     '                   money-back guarantee. 
                                     '  buyer-complaint: A reversal has occurred on this transaction due to a complaint about the transaction 
                                     '                   from your customer. 
                                     '           refund: A reversal has occurred on this transaction because you have given the customer a refund. 
                                     '            other: A reversal has occurred on this transaction due to a reason not listed above. 
                                     */

                                    reasonCode = nvp.get("REASONCODE").toString();

                                    /*
                                     '*********************************************************************************************************
                                     ' THE PARTNER SHOULD SAVE THE KEY TRANSACTION RELATED INFORMATION LIKE transactionId & orderTime 
                                     '  IN THEIR DATABASE AND THE REST OF THE INFORMATION CAN BE USED TO UNDERSTAND THE STATUS OF THE PAYMENT 
                                     '*********************************************************************************************************
                                     */

                                    System.out.println("Adding Customer and Order details to the Database!");

                                    //insert the information into the database
                                    String customerId = Database.createCustomer(cart.getShippingAddress().getFirstName(), cart.getShippingAddress().getLastName(),
                                            cart.getShippingAddress().getEmail(), cart.getShippingAddress().getAddress1(),
                                            cart.getShippingAddress().getCity(), cart.getShippingAddress().getState(),
                                            cart.getShippingAddress().getPostalCode(), cart.getShippingAddress().getCountry());

                                    System.out.println("Added Customer: " + customerId);

                                    System.out.println("Adding Order to the Database!");


                                    orderId = Database.createOrder(cart.getShippingAddress().getMethod(), "paid", orderTime,
                                            transactionId, amt, paymentStatus, pendingReason, paymentType, feeAmt, currencyCode,
                                            (String) session.getAttribute("PAYERId"), Double.toString(cart.getTaxPrice()), Double.toString(cart.getShipPrice()), "");

                                    System.out.println("Added Order " + orderId);

                                    String shoppingCartId = Database.createShoppingCart(customerId, orderId);

                                    /*prepare e-mail to be sent to the customer
                                     MailerBean EMail = new MailerBean();
                                     EMail.setReceiver(cart.getShipping().getEmail());                
                                     EMail.setSubject("Your Order Information ");
                                     EMail.addToMessage("<HTML>\n" + "<HEAD><TITLE>Hello, </TITLE></HEAD>\n" +
                                     "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=CENTER>Thank you for shopping </H1>\n<br>");
                                     EMail.addToMessage(makeOrderInfoTable(cart.getShipping().getLastName(), cart.getShipping().getFirstName(), 
                                     cart.getShipping().getAddress1(),cart.getShipping().getCity(), 
                                     cart.getShipping().getState(), cart.getShipping().getPostalCode(), 
                                     cart.getShipping().getCountry(), cart.getShipping().getEmail(),orderId, 
                                     orderTime, Double.toString(cart.getSubTotalPrice()),formatter.format(cart.getTaxPrice()), 
                                     Double.toString(cart.getShipPrice()), amt));
	
                                     EMail.addToMessage("\n</BODY></HTML>");
		   
                                     EMail.sendMail();
                                     */

                                    System.out.println("Populating Shopping Cart!");
                                    for (Item i : (Vector<Item>) cart.getItems())
                                    {
                                        Database.addToShoppingCart(shoppingCartId, i.getItemId(), i.getQuantity(), i.getItemOption(), Double.toString(i.getItemPrice()));
                                        Database.subtractFromItemQuantity(i.getItemId(), i.getItemOption(), i.getQuantity());
                                    }
                                } 
                                else
                                {
                                    // Display a user friendly Error on the page using any of the following error information returned by PayPal

                                    String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                                    String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                                    String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                                    String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();

                                    System.out.println("-----------------------------------------------");
                                    System.out.println("Error occured on the confirmation.jsp page in Confirming the payment:");
                                    System.out.println("     Code: " + ErrorCode);
                                    System.out.println("Short Msg: " + ErrorShortMsg);
                                    System.out.println(" Long Msg: " + ErrorLongMsg);
                                    System.out.println(" Severity: " + ErrorSeverityCode);
                                    System.out.println("-----------------------------------------------");
                                }
                            }




                        %>





                        <br />
                        <table id="Table3" width="90%" border="0" cellpadding="0" cellspacing="0" align="center">

                            <tr>
                                <td>

                                    <h2>Order Complete!      </h2>
                                    <div>
                                        <div>
                                            The invoice and receipt for your recent order will be e-mailed to you shortly. The following is a summary of your order. Please keep a copy for your records. <br />
                                        </div>
                                    </div>
                                    <br /> 
                                    <table align="center" border="0" cellpadding="0" cellspacing="2" width="90%"><tbody>
                                            <tr>
                                                <td valign="top"  bgcolor="#cccccc" width="50%"><strong>Shipping Information</strong></td>
                                                <td valign="top" bgcolor="#cccccc" width="*"><strong>Order Details</strong></td>
                                            </tr>
                                            <tr>

                                                <td valign="top">
                                                    <table border="0" cellpadding="0" cellspacing="3" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <p><strong><%=cart.getShippingAddress().getFirstName()%> <%=cart.getShippingAddress().getLastName()%></strong></p>
                                                                    <p><%=cart.getShippingAddress().getAddress1()%>,<br>
                                                                            <%=cart.getShippingAddress().getCity()%>, <%=cart.getShippingAddress().getState()%> <%=cart.getShippingAddress().getPostalCode()%><br><%=cart.getShippingAddress().getCountry()%>
                                                                                <br /><%=cart.getShippingAddress().getEmail()%></p> 
                                                                                        </td>
                                                                                        </tr>                      
                                                                                        <tr>
                                                                                            <td valign="top" bgcolor="#cccccc"><b>Billing Information</b></td>                        
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>
                                                                                                <p><strong><%=cart.getShippingAddress().getFirstName()%> <%=cart.getShippingAddress().getLastName()%></strong></p>
                                                                                                <p><%=cart.getShippingAddress().getAddress1()%>,<br>
                                                                                                        <%=cart.getShippingAddress().getCity()%>, <%=cart.getShippingAddress().getState()%> <%=cart.getShippingAddress().getPostalCode()%><br><%=cart.getShippingAddress().getCountry()%>
                                                                                                            <br /><%=cart.getShippingAddress().getEmail()%></p>                        </td>                       
                                                                                                                    </tr>
                                                                                                                    </tbody></table></td>
                                                                                                                    <td valign="top">
                                                                                                                        <table width="100%" border="0" cellpadding="0" cellspacing="3">
                                                                                                                            <tbody>
                                                                                                                                <tr>
                                                                                                                                    <td>
                                                                                                                                        <table width="95%" border="0" cellspacing="2" cellpadding="0">
                                                                                                                                            <tr>
                                                                                                                                                <td width="31%"><div align="right">Order Number: </div></td>
                                                                                                                                                <td width="69%">&nbsp;<%=orderId%></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td><div align="right"></div></td>
                                                                                                                                                <td>&nbsp;</td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td><div align="right">Subtotal:</div></td>
                                                                                                                                                <td>&nbsp;<%= currency.format(cart.getSubTotalPrice())%></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td><div align="right">Tax:</div></td>
                                                                                                                                                <td>&nbsp;<%= currency.format(cart.getTaxPrice())%></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td><div align="right">Shipping:</div></td>
                                                                                                                                                <td>&nbsp;<%= currency.format(cart.getShipPrice())%></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td><div align="right">Total Amount:</div></td>
                                                                                                                                                <td>&nbsp;$<%= amt%></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td><div align="right"></div></td>
                                                                                                                                                <td>&nbsp;</td>
                                                                                                                                            </tr>
                                                                                                                                        </table>

                                                                                                                                    </td>
                                                                                                                                </tr>
                                                                                                                            </tbody></table>

                                                                                                                        <table valign="top" width="369" height="1">
                                                                                                                            <tbody>
                                                                                                                            <tr>
        <td colspan="2"><hr /></td>
       </tr>
                                                                                                                                <tr>
                                                                                                                                    <td colspan="2" align="center">We recommended that you close this window or<br><a href='showcase.jsp'>Click Here</a> to return to the site</td>
                                                                                                                                </tr>

                                                                                                                            </tbody></table>
                                                                                                                    </td></tr>


                                                                                                                    </tbody></table>



                                                                                                                    </td></tr></tbody></table>
                                                                                                                    </center>

                                                                                                                    <%
                                                                                                                        //once all items and order info are added to the database, invalidate the session
                                                                                                                        session.invalidate();
                                                                                                                    %>



                                                                                                                    </div>

                                                                                                                    </div>


                                                                                                                    </div>    

                                                                                                                    <div id="bottomNoSide"></div>

                                                                                                                    <%@include file="store_footer.jsp" %> 


                                                                                                                    </div>

                                                                                                                    </body>
                                                                                                                    </html>
