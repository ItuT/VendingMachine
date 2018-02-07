/**
 * 
 */
package vending.api;

import java.util.List;

import vending.Types.Coin;
import vending.Types.Item;
import vending.utility.Bucket;

/**
 * @author Itumeleng
 *
 */
public interface VendingMachine {
	public double selectItemAndGetPrice(Item item);
    public void insertCoin(Coin coin);
    public List<Coin> refund();
    public Bucket<Item, List<Coin>> collectItemAndChange();   
    public void reset();
}
