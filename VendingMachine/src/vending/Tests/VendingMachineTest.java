 /**
 * 
 */
package vending.Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import vending.VendingMachineFactory;
import vending.VendingMachineImpl;
import vending.Types.Coin;
import vending.Types.Item;
import vending.api.VendingMachine;
import vending.exceptions.SoldOutException;
import vending.utility.Bucket;

/**
 * @author Itumeleng
 *
 */
public class VendingMachineTest {
	
	private static VendingMachine vm; 
	
	@BeforeClass
	public static void setUp(){
		vm = VendingMachineFactory.createVendingMachine(); 
	} 
	
	@AfterClass 
	public static void tearDown(){ 
		vm = null; 
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testBuyItemWithExactPrice() { 
		//select item, price in cents 
		double price = vm.selectItemAndGetPrice(Item.SIMBA); //price should be SIMBA's price 
		assertEquals(Item.SIMBA.getPrice(), price); //25 cents paid 
		vm.insertCoin(Coin.FIFTYCENTS); 
		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange(); 
		Item item = bucket.getFirst(); 
		List<Coin> change = bucket.getSecond(); //should be SIMBA 
		assertEquals(Item.SIMBA, item); //there should not be any change 
		assertTrue(change.isEmpty()); 
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testBuyItemWithMorePrice(){ 
		
		double price = vm.selectItemAndGetPrice(Item.HENNESSY); 
		assertEquals(Item.HENNESSY.getPrice(), price); 
		vm.insertCoin(Coin.FIFTYCENTS); 
		vm.insertCoin(Coin.FIFTYCENTS); 
		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange(); 
		Item item = bucket.getFirst(); 
		List<Coin> change = bucket.getSecond(); 
		//should be SIMBA 
		assertEquals(Item.HENNESSY, item); 
		//there should not be any change 
		assertTrue(!change.isEmpty());
		//comparing change 
		//assertEquals(50 - Item.HENNESSY.getPrice(), getTotal(change)); 
	} 
	
	@SuppressWarnings("deprecation")
	@Test 
	public void testRefund(){ 
		
		double price = vm.selectItemAndGetPrice(Item.STUYVESANT); 
		assertEquals(Item.STUYVESANT.getPrice(), price); 
		vm.insertCoin(Coin.ONERAND); 
		vm.insertCoin(Coin.TWORAND); 
		vm.insertCoin(Coin.FIVERAND); 
		vm.insertCoin(Coin.FIFTYCENTS); 
		
		//assertEquals(41, getTotal(vm.refund())); 
	} 
		
	@Test(expected=SoldOutException.class) 
		public void testSoldOut(){ 
		for (int i = 0; i < 5; i++) { 
			vm.selectItemAndGetPrice(Item.SIMBA); 
			vm.insertCoin(Coin.FIFTYCENTS); 
			vm.insertCoin(Coin.TWORAND);
			vm.collectItemAndChange(); 
		} 
	}
	
	@Ignore 
	public void testVendingMachineImpl(){ 
		VendingMachineImpl vm = new VendingMachineImpl(); 
	} 

}
