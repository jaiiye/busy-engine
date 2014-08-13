
package com.busy.engine.store;

import com.busy.engine.entity.OrderItem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuantities extends HttpServlet 
{
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            HttpSession session = request.getSession();

            // Get the cart.
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

            // If there is no shopping cart, create one.
            if (cart == null)
            {
                cart = new ShoppingCart();
                session.setAttribute("ShoppingCart", cart);
                System.out.println("No Shopping Cart present when trying to Update Quantities");
            }
            
            //get the old amounts
            int[] oldAmounts = new int[cart.getOrderItems().size()];
            for (int i = 0; i < cart.getOrderItems().size(); i++)
            {                           
              oldAmounts[i] = ((OrderItem)cart.getOrderItems().get(i)).getQuantity();
            }

            //check availability for each item to make sure quantities are available
//            for (int i = 0; i < cart.getOrderItems().size(); i++)
//            {
//                OrderItem cartItem = (OrderItem)cart.getOrderItems().get(i);
//                ArrayList<Item> items = Database.getAllItemsWithInfoByType(1,cartItem.getItemId() + "", null);
//                Item item = items.get(0);
//                for(Option o : item.getItemInfo().getItemOptions())
//                {
//                    if(o.getOptionType().equalsIgnoreCase(cartItem.getOptionId()))
//                    {
//                        int newQuantity = Integer.parseInt(request.getParameter("txtQ" + i));
//                        if(Integer.parseInt(o.getOptionAvailableQuantity()) < newQuantity)
//                        {
//                            response.sendRedirect("cart.jsp?error=Maximum%20availability%20available%20for%20item " + item.getItemName() + "-" + o.getOptionType() + " is " + o.getOptionAvailableQuantity());
//                            return;
//                        }
//                    }
//                }
//            }
            
            for (int i = 0; i < cart.getOrderItems().size(); i++)
            {
              ((OrderItem)cart.getOrderItems().get(i)).setQuantity(Integer.parseInt(request.getParameter("txtQ" + i)));                            
            }
            
            //if the total shopping cart amount goes above $8500, then reverse the amounts to previous values
            if(cart.getSubTotalPrice() > 8500.00)
            {
                for (int i = 0; i < cart.getOrderItems().size(); i++)
                {
                  ((OrderItem)cart.getOrderItems().get(i)).setQuantity(oldAmounts[i]);                            
                } 
                response.sendRedirect("cart.jsp?error=Subtotal%20Amount%20Cannot%20Exceed%20$8500");
            }
            else
            {
                // Now display the cart and allow the user to check out or order more items.
                response.sendRedirect("cart.jsp");
                
            }
            



        } 
        finally 
        { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
