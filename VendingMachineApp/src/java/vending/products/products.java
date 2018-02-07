/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.products;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vending.VendingBean;
import vending.types.Item;

/**
 *
 * @author Itumeleng
 */
@WebServlet(name = "products", urlPatterns = {"/products"})
public class products extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Item item = Item.SIMBA;

        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet products</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet products at " + item.getName() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject jo = Json.createObjectBuilder()
                .add("products", Json.createArrayBuilder()
                  .add(Json.createObjectBuilder()
                    .add(Item.PANADO.getName(),Item.PANADO.getPrice() )
                    .add(Item.HENNESSY.getName(), Item.HENNESSY.getPrice())
                    .add(Item.SIMBA.getName(), Item.SIMBA.getPrice())
                    .add(Item.PEANUTS.getName(), Item.PEANUTS.getPrice())
                    .add(Item.STUYVESANT.getName(),Item.STUYVESANT.getPrice())))
                .build();
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jo);
       // processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        JsonReader jsonreader = Json.createReader(new StringReader(data));
         
        JsonObject jobj = jsonreader.readObject();
        //jobj.getString("product");
        //jobj.getString("paid");
        //jobj.getString("state");
         
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
        VendingBean vbean = new VendingBean();
        try{
            JsonObject res = vbean.processPurchase(jobj);
            out.print(res);
        }catch(Exception e){
            out.print(e.getMessage());
        }
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
