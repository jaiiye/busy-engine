
package com.busy.engine.store;

import com.busy.engine.store.ShoppingCart;
import com.busy.engine.entity.Address;
import com.busy.engine.entity.OrderItem;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.HashMap;
import javax.servlet.http.HttpSession;


public class PayPalFunctions
{    
    private NVPCallerServices caller = null;
                                
    String gv_APIEndpoint;
    String gv_APIUserName;
    String gv_APIPassword;
    String gv_APISignature;
    String gv_BNCode;
    
    String gv_currencyCode;
    String gv_PaymentType;    
    String gv_Environment;
    
    String gv_ReturnURL;
    String gv_CancelURL;

    String gv_Version;
    String gv_nvpHeader;
    String gv_ProxyServer;	
    String gv_ProxyServerPort; 
    int gv_Proxy;
    boolean gv_UseProxy;
    String PAYPAL_URL;

    
    public PayPalFunctions(ArrayList<String> info)
    {
	/*
            Servers for NVP API
                    Sandbox: https://api-3t.sandbox.paypal.com/nvp
                    Live: https://api-3t.paypal.com/nvp

            Redirect URLs for PayPal Login Screen
                    Sandbox: https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&token=XXXX
                    Live: https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=XXXX
	*/


        PAYPAL_URL      = info.get(0);
        gv_currencyCode = info.get(1);
        gv_APIUserName	= info.get(2);
        gv_APIPassword	= info.get(3);
        gv_APISignature = info.get(4);
        gv_APIEndpoint  = info.get(5);                
        gv_ReturnURL    = info.get(6);
        gv_CancelURL    = info.get(7);
        gv_PaymentType  = info.get(8);
        gv_Environment  = info.get(9);

 	
	String HTTPREQUEST_PROXYSETTING_SERVER = "";
	String HTTPREQUEST_PROXYSETTING_PORT = "";
	boolean USE_PROXY = false;
	
        //BN Code is only applicable for partners
	gv_BNCode		= "PP-ECWizard"; 
	gv_Version              = "72.0";
	
	//WinObjHttp Request proxy settings.
	gv_ProxyServer          = HTTPREQUEST_PROXYSETTING_SERVER;
	gv_ProxyServerPort      = HTTPREQUEST_PROXYSETTING_PORT;
	gv_Proxy		= 2;	//'setting for proxy activation
	gv_UseProxy		= USE_PROXY;
	
    }

    /*********************************************************************************
      * CallSetExpressCheckout: Function to perform the SetExpressCheckout API call 
      *
      * Inputs:  
      *		paymentAmount:  	Total value of the shopping cart
      *		currencyCodeType: 	Currency code value the PayPal API
      *		paymentType: 		paymentType has to be one of the following values: Sale or Order or Authorization
      *		returnURL:		Page where buyers return to after they are done with the payment review on PayPal
      *		cancelURL:		Page where buyers return to when they cancel the payment review on PayPal	  
      *
      * Output: Returns a HashMap object containing the response from the server.
    *********************************************************************************/
    public HashMap CallSetExpressCheckout(HttpSession session, String paymentAmount)
    {		
            session.setAttribute("paymentType", gv_PaymentType);
            session.setAttribute("currencyCodeType", gv_currencyCode);
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
            
            /* 
                Construct the parameter string that describes the PayPal payment
                the varialbes were set in the web form, and the resulting string
                is stored in $nvpstr
            */
//            String nvpstr = "&Amt=" + paymentAmount + 
//                            "&PAYMENTACTION=" + gv_PaymentType + 
//                            "&RETURNURL=" + URLEncoder.encode( gv_ReturnURL ) + 
//                            "&CANCELURL=" + URLEncoder.encode( gv_CancelURL ) + 
//                            "&CURRENCYCODE=" + gv_currencyCode;
            
            String nvpstr = "&ReturnUrl=" + URLEncoder.encode( gv_ReturnURL ) + 
                            "&CANCELURL=" + URLEncoder.encode( gv_CancelURL ) + 
                            "&PAYMENTREQUEST_0_PAYMENTACTION=" + gv_PaymentType;                                    
            
            ArrayList<OrderItem> items = cart.getOrderItems();
            int numItems = items.size();

            for (int i = 0; i < numItems; i++)
            {
                OrderItem item = items.get(i);
                nvpstr +=   "&L_PAYMENTREQUEST_0_NAME" + i + "=" + item.getItemId() + 
                            "&L_PAYMENTREQUEST_0_DESC" + i + "=Option:" + item.getOptionName() + 
                            "&L_PAYMENTREQUEST_0_AMT" + i + "=" + item.getUnitPrice() + 
                            "&L_PAYMENTREQUEST_0_QTY" + i + "=" + item.getQuantity() ;
            }
            
            
            nvpstr +=   "&PAYMENTREQUEST_0_ITEMAMT=" + paymentAmount +
                        //"&PAYMENTREQUEST_0_TAXAMT=2.58" +
                        //"&PAYMENTREQUEST_0_SHIPPINGAMT=3.00" +
                        //"&PAYMENTREQUEST_0_HANDLINGAMT=2.99" +
                        //"&PAYMENTREQUEST_0_SHIPDISCAMT=-3.00" +
                        //"&PAYMENTREQUEST_0_INSURANCEAMT=1.00" +
                        "&PAYMENTREQUEST_0_AMT=" + paymentAmount +
                        "&PAYMENTREQUEST_0_CURRENCYCODE=" + gv_currencyCode +
                        "&TOTALTYPE=EstimatedTotal";
        


            /* 
                Make the call to PayPal to get the Express Checkout token
                If the API call succeded, then redirect the buyer to PayPal
                to begin to authorize payment.  If an error occured, show the
                resulting errors
            */
            HashMap nvp = httpcall("SetExpressCheckout", nvpstr);

            if(nvp.get("ACK").toString() !=null && nvp.get("ACK").toString().equalsIgnoreCase("Success"))
            {
                session.setAttribute("TOKEN", nvp.get("TOKEN").toString());
            }
            else
            {
                System.out.println("Calling SetExpressCheckout with arguments: " + nvpstr);
                System.out.println("SetExpressCheckout call Fialed" + nvp);
            }		
            return nvp;
    }
    
    public HashMap ECSetExpressCheckoutCode(HttpSession session, String paymentAmount)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();
        
        String NVPRequest = null;
        String NVPResponse = null;    

        try
        {		
            session.setAttribute("paymentType", gv_PaymentType);
            session.setAttribute("currencyCodeType", gv_currencyCode);
            
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);
            
            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);			
            encoder.add("METHOD","SetExpressCheckout");

            // Add request-specific fields to the request string.
            encoder.add("RETURNURL",gv_ReturnURL);
            encoder.add("CANCELURL",gv_CancelURL);	
            encoder.add("AMT",paymentAmount);
            encoder.add("PAYMENTACTION",gv_PaymentType);
            encoder.add("CURRENCYCODE",gv_currencyCode);
            //encoder.add("NAME","Window Film Products");
            encoder.add("DESC","Order Subtotal: " + paymentAmount);
            //encoder.add("LANDINGPAGE","Billing");



            // Execute the API operation and obtain the response.
            NVPRequest = encoder.encode();
            NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);

        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        if(decoder.get("ACK").toString() != null && decoder.get("ACK").toString().equalsIgnoreCase("Success"))
        {
            session.setAttribute("TOKEN", decoder.get("TOKEN").toString());
        }
        else
        {
            System.out.println("Calling SetExpressCheckout");
            System.out.println("ECSetExpressCheckoutCode call Fialed" + deformatNVP(NVPResponse));
        }
        
        
        return deformatNVP(NVPResponse);
    }
        

    /*********************************************************************************
      * CallMarkExpressCheckout: Function to perform the SetExpressCheckout API call 
      *
      * Inputs:  
      *		paymentAmount:  	Total value of the shopping cart
      *		currencyCodeType: 	Currency code value the PayPal API
      *		paymentType: 		paymentType has to be one of the following values: Sale or Order or Authorization
      *		returnURL:		the page where buyers return to after they are done with the payment review on PayPal
      *		cancelURL:		the page where buyers return to when they cancel the payment review on PayPal
      *		shipToName:		the Ship to name entered on the merchant's site
      *		shipToStreet:		the Ship to Street entered on the merchant's site
      *		shipToCity:		the Ship to City entered on the merchant's site
      *		shipToState:		the Ship to State entered on the merchant's site
      *		shipToCountryCode:	the Code for Ship to Country entered on the merchant's site
      *		shipToZip:		the Ship to ZipCode entered on the merchant's site
      *		shipToStreet2:		the Ship to Street2 entered on the merchant's site
      *		phoneNum:		the phoneNum  entered on the merchant's site  
      *
      * Output: Returns a HashMap object containing the response from the server.
    *********************************************************************************/
    public HashMap CallMarkExpressCheckout(HttpSession session,  String paymentAmount, String currencyCodeType, String paymentType, String shipToName, String shipToStreet, String shipToCity, String shipToState,
                                           String shipToCountryCode, String shipToZip, String shipToStreet2, String phoneNum)
    {

        session.setAttribute("paymentType", paymentType);
        session.setAttribute("currencyCodeType", currencyCodeType);

        /* 
            Construct the parameter string that describes the PayPal payment
            the varialbes were set in the web form, and the resulting string
            is stored in $nvpstr
        */
        String nvpstr = "&ADDROVERRIDE=1&Amt=" + paymentAmount + "&PAYMENTACTION=" + paymentType  
        + "&CURRENCYCODE=" + currencyCodeType + "&ReturnUrl=" + URLEncoder.encode( gv_ReturnURL ) + "&CANCELURL=" + URLEncoder.encode( gv_CancelURL )
        + "&SHIPTONAME=" + shipToName + "&SHIPTOSTREET=" + shipToStreet + "&SHIPTOSTREET2=" + shipToStreet2
        +"&SHIPTOCITY=" + shipToCity + "&SHIPTOSTATE=" + shipToState + "&SHIPTOCOUNTRYCODE=" + shipToCountryCode
        + "&SHIPTOZIP=" + shipToZip + "&PHONENUM" + phoneNum;

        /* 
            Make the call to PayPal to set the Express Checkout token
            If the API call succeded, then redirect the buyer to PayPal
            to begin to authorize payment.  If an error occured, show the
            resulting errors
        */

        HashMap nvp = httpcall("SetExpressCheckout", nvpstr);

        String strAck = nvp.get("ACK").toString();
        if(strAck !=null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
        {
            session.setAttribute("TOKEN", nvp.get("token").toString());
        }
        else
        {
            System.out.println("Calling CallMarkExpressCheckout with arguments: " + nvpstr);
            System.out.println("CallMarkExpressCheckout call Fialed" + nvp);
        }		
        return nvp;
    }


    /*********************************************************************************
      * GetShippingDetails: Function to perform the GetExpressCheckoutDetails API call 
      *
      * Inputs: None
      * Output: Returns a HashMap object containing the response from the server.
    *********************************************************************************/
    public HashMap GetShippingDetails(HttpSession session,  String token)
    {
        /* 
            Build a second API request to PayPal, using the token as the
            ID to get the details on the payment authorization
        */
        String nvpstr= "&TOKEN=" + token;

        /*
            Make the API call and store the results in an array.  If the
            call was a success, show the authorization details, and provide
            an action to complete the payment.  If failed, show the error
        */
        System.out.println("About to call GetShippingDetails with arguments: " + nvpstr);

        HashMap nvp = httpcall("GetExpressCheckoutDetails", nvpstr);
        String strAck = nvp.get("ACK").toString();
        
        if(strAck !=null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
        {
            System.out.println("GetShippingDetails Results: " + nvp);
            System.out.println("Successfully got the Shipping Details for Customer " + nvp.get("PAYERID").toString());            
            session.setAttribute("PAYERID", nvp.get("PAYERID").toString());
        }
        else
        {
            System.out.println("GetShippingDetails call Fialed " + nvp);
        }	
        
        return nvp;
    }
    
    public Address getShippingAddress(HashMap shipping_nvp)
    {
        Address shipping = new Address();

        shipping.setRecipient(shipping_nvp.get("FIRSTNAME").toString() + " " + shipping_nvp.get("LASTNAME").toString());
        shipping.setAddress1(shipping_nvp.get("SHIPTOSTREET").toString());
        //shipping.setAddress2(shipping_nvp.get("SHIPTOSTREET2").toString());
        shipping.setCity(shipping_nvp.get("SHIPTOCITY").toString());
        shipping.setStateProvince(shipping_nvp.get("SHIPTOSTATE").toString());
        shipping.setZipPostalCode(shipping_nvp.get("SHIPTOZIP").toString());
        shipping.setCountry(shipping_nvp.get("SHIPTOCOUNTRYCODE").toString());
        //shipping.setEmail(shipping_nvp.get("EMAIL").toString());
        //shipping.setMethod("");
        
        return shipping;
    }
    
    public HashMap ECGetExpressCheckoutDetails(String token)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        String NVPRequest = null;
        String NVPResponse = null;  

        try
        {            
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);

            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD","DoExpressCheckoutPayment");

            // Add request-specific fields to the request string.
            // Pass the token value returned in SetExpressCheckout.
            encoder.add("TOKEN", token);

            // Execute the API operation and obtain the response.
            NVPRequest = encoder.encode();
            NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);			
        }
        catch (Exception ex)
        {
              ex.printStackTrace();
        }

        return (HashMap)decoder.getMap();
    }

    /*********************************************************************************
    * Perform the DoExpressCheckoutPayment API call 
    *
    * Inputs:  Session object
    *          Final payment amount      
    * Output:  A HashMap object containing the response from the server.
    *********************************************************************************/
    public HashMap DoExpressCheckoutPayment(HttpSession session,  String finalPaymentAmount)
    {
        /*
        '----------------------------------------------------------------------------
        '----	Use the values stored in the session from the previous SetEC call	
        '----------------------------------------------------------------------------
        */
        String token 		=  (String)session.getAttribute("TOKEN");
        String payerID 		=  (String)session.getAttribute("PAYERID");
        String currencyCodeType	=  (String)session.getAttribute("currencyCodeType");
        String paymentAction    =  (String)session.getAttribute("paymentType");
        
        //String serverName 	=  request.getServerName();

        String nvpstr  = "&TOKEN=" + token + "&PAYERID=" + payerID + "&PAYMENTACTION=" + paymentAction + "&AMT=" + finalPaymentAmount;
        nvpstr = nvpstr + "&CURRENCYCODE=" + currencyCodeType + "&IPADDRESS=" ;//+ serverName;

        /* 
        Make the call to PayPal to finalize payment
        If an error occured, show the resulting errors
        */
        System.out.println("About to call ConfirmPayment with arguments: " + nvpstr);
        HashMap nvp = httpcall("DoExpressCheckoutPayment", nvpstr);

        String strAck = nvp.get("ACK").toString();
        if(strAck !=null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
        {
            System.out.println("Successfully Confirmed Payment! " + nvp.toString());
        }
        else
        {
            System.out.println("DoExpressCheckoutPayment call Fialed" + nvp);
        }
        return nvp;
    }


    /*********************************************************************************
      * httpcall: Function to perform the API call to PayPal using API signature
      * 	@methodName is name of API  method.
      * 	@nvpStr is nvp string.
      * returns a NVP string containing the response from the server.
    *********************************************************************************/
    public HashMap httpcall( String methodName, String nvpStr)
    {		
        String version = gv_Version;
        String agent = "Mozilla/4.0";
        String respText = "";
        HashMap nvp;

        //deformatNVP( nvpStr );	
        String encodedData = "METHOD=" + methodName + "&VERSION=" + gv_Version + "&PWD=" + gv_APIPassword + "&USER=" + gv_APIUserName + "&SIGNATURE=" + gv_APISignature + nvpStr + "&BUTTONSOURCE=" + gv_BNCode;

        try 
        {
            URL postURL = new URL( gv_APIEndpoint );
            HttpURLConnection conn = (HttpURLConnection)postURL.openConnection();

            // Set connection parameters. 
            // We need to perform input and output, so set both as true. 
            conn.setDoInput (true);
            conn.setDoOutput (true);

            // Set the content type we are POSTing. We impersonate it as encoded form data 
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "User-Agent", agent );

            //conn.setRequestProperty( "Content-Type", "" );
            conn.setRequestProperty( "Content-Length", String.valueOf( encodedData.length()) );
            conn.setRequestMethod("POST");

            // get the output stream to POST to. 
            DataOutputStream output = new DataOutputStream( conn.getOutputStream());
            output.writeBytes( encodedData );
            output.flush();
            output.close ();

            // Read input from the input stream.
            DataInputStream  in = new DataInputStream (conn.getInputStream()); 
            int rc = conn.getResponseCode();
            if ( rc != -1)
            {
                BufferedReader is = new BufferedReader(new InputStreamReader( conn.getInputStream()));
                String _line = null;
                while(((_line = is.readLine()) !=null))
                {
                        respText = respText + _line;
                }			
                nvp = deformatNVP( respText );
            }
            else nvp = null;

            return nvp;
        }
        catch( IOException e )
        {
            // handle the error here
            System.out.println("Error in making an HTTP call: " + e.getMessage());
            return null;
        }
    }

    /*********************************************************************************
      * deformatNVP: Function to break the NVP string into a HashMap
      * 	pPayLoad is the NVP string.
      * returns a HashMap object containing all the name value pairs of the string.
    *********************************************************************************/
    public HashMap<String,String> deformatNVP( String pPayload ) 
    {
        HashMap<String,String> nvp = new HashMap<String,String>(); 
        StringTokenizer stTok = new StringTokenizer( pPayload, "&");
        while (stTok.hasMoreTokens())
        {
            StringTokenizer stInternalTokenizer = new StringTokenizer( stTok.nextToken(), "=");
            if (stInternalTokenizer.countTokens() == 2)
            {
                try
                {
                    String key = URLDecoder.decode(stInternalTokenizer.nextToken(), "UTF-8");
                    String value = URLDecoder.decode( stInternalTokenizer.nextToken(), "UTF-8");
                    nvp.put( key.toUpperCase(), value );
                } 
                catch (UnsupportedEncodingException ex)
                {
                    System.out.println("Exception in deformatNVP function of PayPalFunctions.java:" + ex.getMessage());
                }
            }
        }
        return nvp;
    }

    /*********************************************************************************
      * RedirectURL: Function to redirect the user to the PayPal site
      * 	     token is the parameter that was returned by PayPal
      * returns a HashMap object containing all the name value pairs of the string.
    *********************************************************************************/
    public void RedirectURL( javax.servlet.http.HttpServletResponse response, String token )
    {
        String payPalURL = PAYPAL_URL + token; 

        try
        {
            response.setStatus(302);
            response.setHeader( "Location", payPalURL );
            response.setHeader( "Connection", "close" );
            System.out.println("redirecting to Paypal with URL: " + payPalURL);
            response.sendRedirect( payPalURL );

        }
        catch(Exception e)
        {
            System.out.println("Error in redirecting to paypal");
        }
    } 
    
    public HashMap DoDirectPayment(HttpSession session, String amount)
    {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
        Address b = cart.getBillingAddress();
        
        int seperatorIndex = b.getRecipient().indexOf(' ');
        String firstName = b.getRecipient().substring(0, seperatorIndex);
        String lastName = b.getRecipient().substring(seperatorIndex, b.getRecipient().length());
        String street = b.getAddress1();
        String city  = b.getCity();
        String state = b.getStateProvince();
        String zip = b.getZipPostalCode();
        String countryCode = b.getCountry();
        
        //TODO: need a structure to hold this info
//        String cardType = b.getCardType();
//        String acct = b.getAccountNumber();
//        String expdate = b.getExpirationDate();
//        String cvv2  = b.getCvv2();
        
        
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();
        
        String NVPRequest = null;
        String NVPResponse = null;      

        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();
           
            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);
            
            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);

            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD","DoDirectPayment");
            encoder.add("RETURNFMFDETAILS","1"); //0 - do not receive FMF details (default)
                                                 //1 - receive FMF details

            // Add request-specific fields to the request string.
            encoder.add("AMT",amount);
            encoder.add("PAYMENTACTION", gv_PaymentType);
            
//            encoder.add("CREDITCARDTYPE",cardType);		
//            encoder.add("ACCT",acct);						
//            encoder.add("EXPDATE",expdate);
//            encoder.add("CVV2",cvv2);
            
            encoder.add("FIRSTNAME",firstName);
            encoder.add("LASTNAME",lastName);										
            encoder.add("STREET",street);
            encoder.add("CITY",city);	
            encoder.add("STATE",state);			
            encoder.add("ZIP",zip);	
            encoder.add("COUNTRYCODE",countryCode);

            // Execute the API operation and obtain the response.
            NVPRequest = encoder.encode();
            NVPResponse =(String) caller.call(NVPRequest);
            decoder.decode(NVPResponse);
        } 
        catch(Exception ex)
        {
            System.out.println("" + ex.getMessage());
            ex.printStackTrace();
        }

        return deformatNVP(NVPResponse);
    }
    
    public String ECDoExpressCheckoutCode(String token,String payerID,String amount,String paymentType,String currencyCode)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);
            
            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD","DoExpressCheckoutPayment");

            // Add request-specific fields to the request string.
            // Pass the token value by actual value returned in the SetExpressCheckout.
            encoder.add("TOKEN",token);
            encoder.add("PAYERID",payerID);
            encoder.add("AMT",amount);
            encoder.add("PAYMENTACTION",paymentType);
            encoder.add("CURRENCYCODE",currencyCode);
            
            // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse =caller.call(NVPRequest);
            decoder.decode(NVPResponse);

        } 
        catch(Exception ex)
        {
                ex.printStackTrace();
        }

        return decoder.get("ACK");
    }
    
    public String ECGetExpressCheckoutCode(String token)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);

            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD", "GetExpressCheckoutDetails");

            // Add request-specific fields to the request string.
            // Pass the token value returned in SetExpressCheckout.
            encoder.add("TOKEN", token);

            // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return decoder.get("ACK");
    }



   
     public String getTransactionDetailsCode(String transactionId)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();
        
        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);

            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);	
            encoder.add("METHOD","GetTransactionDetails");

            // Add request-specific fields to the request string.
            encoder.add("TRANSACTIONID",transactionId);

            // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return decoder.get("ACK");

    }
     
    public String RefundTransactionCode(String refundType, String transactionId, String amount, String note)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);
            
            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD", "RefundTransaction");

            // Add request-specific fields to the request string.
            encoder.add("REFUNDTYPE", refundType);
            encoder.add("TRANSACTIONID", transactionId);
            if ((refundType != null) && (refundType.length() > 0) && (refundType.equalsIgnoreCase("Partial")))
            {
                encoder.add("AMT", amount);
            }
            encoder.add("NOTE", note);

            // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse = (String) caller.call(NVPRequest);
            decoder.decode(NVPResponse);
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return decoder.get("ACK"); 
    }
    
    public String DoAuthorizationCode(String transactionId, String amount)
    {
        NVPDecoder decoder = new NVPDecoder();
        NVPEncoder encoder = new NVPEncoder();
        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();
            
            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);
            
            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD", "DoAuthorization");

            // Add request-specific fields to the request string.
            encoder.add("TRANSACTIONID", transactionId);
            encoder.add("AMT", amount);
            encoder.add("CURRENCYCODE", "USD");

            // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return decoder.get("ACK");
    }
    
    
    public String ReauthorizationCode(String authorizationId, String amount, String currencyCode)
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();
            
            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);
            
            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD", "DoReauthorization");

            // Add request-specific fields to the request string.
            encoder.add("AuthorizationID", authorizationId);
            encoder.add("AMT", amount);
            encoder.add("CURRENCYCODE", currencyCode);

            // Execute the API operation and obtain the response.
            String NVPRequest = encoder.encode();
            String NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);

        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return decoder.get("ACK");
    }

    
    public String GetBalanceCode()
    {
        NVPEncoder encoder = new NVPEncoder();
        NVPDecoder decoder = new NVPDecoder();

        try
        {
            caller = new NVPCallerServices();
            APIProfile profile = ProfileFactory.createSignatureAPIProfile();

            // Set up your API credentials, PayPal end point, API operation and version.
            profile.setAPIUsername(gv_APIUserName);
            profile.setAPIPassword(gv_APIPassword);
            profile.setSignature(gv_APISignature);

            profile.setEnvironment(gv_Environment);
            profile.setSubject("");
            caller.setAPIProfile(profile);
            encoder.add("VERSION", gv_Version);
            encoder.add("METHOD", "GetBalance");
            String NVPRequest = encoder.encode();
            String NVPResponse = caller.call(NVPRequest);
            decoder.decode(NVPResponse);

        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return decoder.get("ACK");
    }


   

}
