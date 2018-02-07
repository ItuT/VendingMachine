/**
 * 
 */
package vending;

import vending.api.VendingMachine;
import vending.exceptions.NotFullPaidException;
import vending.exceptions.NotSufficientChangeException;
import vending.exceptions.SoldOutException;
import vending.inventory.Inventory;
import vending.utility.Bucket;
import vending.Types.Coin;
import vending.Types.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {   
    private Inventory<Coin> cashInventory = new Inventory<Coin>();
    private Inventory<Item> itemInventory = new Inventory<Item>();  
    private double totalSales;
    private Item currentItem;
    private double currentBalance; 
   
    public VendingMachineImpl(){
        initialize();
    }
   
    private void initialize(){       
        //initialize machine with 5 coins of each denomination
        //and 5 cans of each Item       
        for(Coin c : Coin.values()){
            cashInventory.put(c, 5);
        }
       
        for(Item i : Item.values()){
            itemInventory.put(i, 5);
        }
       
    }
   
   @Override
	public double selectItemAndGetPrice(Item item) {
        if(itemInventory.hasItem(item)){
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("Sold Out, Please buy another item");
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getDenomination();
        cashInventory.add(coin);
    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {
        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();
       
        List<Coin> change = collectChange();
       
        return new Bucket<Item, List<Coin>>(item, change);
    }
       
    private Item collectItem() throws NotSufficientChangeException,
            NotFullPaidException{
        if(isFullPaid()){
            if(hasSufficientChange()){
                itemInventory.deduct(currentItem);
                return currentItem;
            }           
            throw new NotSufficientChangeException("Not Sufficient change in Inventory");
           
        }
        double remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotFullPaidException("Price not full paid, remaining : ", 
                                          remainingBalance);
    }
   
    private List<Coin> collectChange() {
        double changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }
   
    @Override
    public List<Coin> refund(){
        List<Coin> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
    }
   
   
    private boolean isFullPaid() {
        if(currentBalance >= currentItem.getPrice()){
            return true;
        }
        return false;
    }

      
    private List<Coin> getChange(double amount) throws NotSufficientChangeException{
        List<Coin> changes = Collections.EMPTY_LIST;
       
        if(amount > 0){
            changes = new ArrayList<Coin>();
            double balance = amount;
            while(balance > 0){
                if(balance >= Coin.FIFTYCENTS.getDenomination() && cashInventory.hasItem(Coin.FIFTYCENTS)){
                    changes.add(Coin.FIFTYCENTS);
                    balance = balance - Coin.FIFTYCENTS.getDenomination();
                    continue;
                   
                }else if(balance >= Coin.ONERAND.getDenomination() 
                                 && cashInventory.hasItem(Coin.ONERAND)) {
                    changes.add(Coin.ONERAND);
                    balance = balance - Coin.ONERAND.getDenomination();
                    continue;
                   
                }else if(balance >= Coin.TWORAND.getDenomination() 
                                 && cashInventory.hasItem(Coin.TWORAND)) {
                    changes.add(Coin.TWORAND);
                    balance = balance - Coin.TWORAND.getDenomination();
                    continue;
                   
                }else if(balance >= Coin.FIVERAND.getDenomination() 
                                 && cashInventory.hasItem(Coin.FIVERAND)) {
                    changes.add(Coin.FIVERAND);
                    balance = balance - Coin.FIVERAND.getDenomination();
                    continue;
                   
                }else{
                    throw new NotSufficientChangeException("NotSufficientChange, Please try another product");
                }
            }
        }
       
        return changes;
    }
   
    @Override
    public void reset(){
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    } 
       
    public void printStats(){
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory : " + cashInventory);
    }   
   
  
    private boolean hasSufficientChange(){
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }
   
    private boolean hasSufficientChangeForAmount(double amount){
        boolean hasChange = true;
        try{
            getChange(amount);
        }catch(NotSufficientChangeException nsce){
            return hasChange = false;
        }
       
        return hasChange;
    }

    private void updateCashInventory(List<Coin> change) {
        for(Coin c : change){
            cashInventory.deduct(c);
        }
    }
   
    public double getTotalSales(){
        return totalSales;
    }
   
}
