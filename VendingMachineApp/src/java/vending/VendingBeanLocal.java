/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending;

import javax.ejb.Local;
import javax.json.JsonObject;
import org.json.JSONException;

/**
 *
 * @author Itumeleng
 */
@Local
public interface VendingBeanLocal {
      JsonObject processPurchase(JsonObject jobject) throws JSONException;
      public String dbHelper(String res);
}
