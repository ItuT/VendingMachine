/**
 * 
 */
package vending.states;

import vending.exceptions.MachineWarning;
import vending.types.Coin;


/**
 * @author Itumeleng
 *
 */
public class ItemSelectedState implements State{
   /* VendingMachine2 machine =null;
    public ItemSelectedState(VendingMachine2 vendingMachineImpl2) {
        this.machine =  vendingMachineImpl2;
    }*/
    public void insertCoin(Coin coin) throws MachineWarning{
        throw new MachineWarning("Coin is already inserted.");
    }
    public void dispense() throws MachineWarning{
        throw new MachineWarning("Dispense button is not pressed.");
    
    }
    public void pressButton() throws MachineWarning{
        //machine.setMachineState(machine.getDispensingState());
    }
    public void selectItem() throws MachineWarning {
    	throw new MachineWarning("Item already selected. Are you sure you want to change?");
    }
}
