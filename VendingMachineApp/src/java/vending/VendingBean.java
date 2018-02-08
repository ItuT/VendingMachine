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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

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
    double productPrice;
    
    @Override
    public JsonObject processPurchase(JsonObject jobject) throws JSONException{  
        
        
         //https://my-json-server.typicode.com/itut/vendingmachine/products
         JSONObject resJson = null;
        try{
            
            String res = this.dbHelper("products");
            res = res.replace("[","");
            res = res.replace("]","");
          resJson = new JSONObject(res);
            
            product=jobject.getString("product");
            paidAmount=jobject.getString("paid");
            State=jobject.getString("state");
            productPrice = Double.valueOf(resJson.get(product).toString());
            
        }catch(Exception e){
            JsonObject jo = Json.createObjectBuilder()
               // .add("purchase", Json.createArrayBuilder()
                  //.add(Json.createObjectBuilder()
                  .add("result","Something went wrong, please again!")
                .build();
            return jo;
        }
        
        
        if(productPrice > Double.valueOf(paidAmount)){
            
            JsonObject jo = Json.createObjectBuilder()
                  .add("result","Please insert more coins")
                .build();
            return jo;
        }else{
            this.change = Double.valueOf(paidAmount) - productPrice;
            JsonObject jo = Json.createObjectBuilder()
                  .add("result","Enjoy "+product.toUpperCase() +" Change is R"+change)
                .build();
            return jo;
        }
    }
    
    @Override
    public String dbHelper(String res) {
		String uri = "https://my-json-server.typicode.com/itut/vendingmachine/"+res;
		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			 Object xml = connection.getContent();
			
			 BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append("\n"+inputLine);
				}
				in.close();

				//print result
				//System.out.println(response.toString());
                               			 
			//System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\");

			connection.disconnect();
                        return response.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		return null;
	}
}
