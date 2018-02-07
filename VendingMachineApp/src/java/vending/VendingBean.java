/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import vending.types.Item;

/**
 *
 * @author Itumeleng
 */
@Stateless
public class VendingBean implements VendingBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private long totalSales;
    private Item currentItem;
    private long currentBalance; 
    String State;
    String product;
    String paidAmount;
    double change = 0.0;
    
    @Override
    public JsonObject processPurchase(JsonObject jobject){  
        
        try{
            
            product=jobject.getString("product");
            paidAmount=jobject.getString("paid");
            State=jobject.getString("state");
            
        }catch(Exception e){
            JsonObject jo = Json.createObjectBuilder()
               // .add("purchase", Json.createArrayBuilder()
                  //.add(Json.createObjectBuilder()
                  .add("result","Something went wrong, please again!")
                .build();
            return jo;
        }
        
        
        if(Double.valueOf(Item.valueOf(product.toUpperCase()).getPrice()) > Double.valueOf(paidAmount)){
            
            JsonObject jo = Json.createObjectBuilder()
                  .add("result","Please insert more coins")
                .build();
            return jo;
        }else{
            this.change = Double.valueOf(paidAmount) - Double.valueOf(Item.valueOf(product.toUpperCase()).getPrice());
            JsonObject jo = Json.createObjectBuilder()
                  .add("result","Enjoy "+product.toUpperCase() +" Change is R"+change)
                .build();
            return jo;
        }
    }
}
