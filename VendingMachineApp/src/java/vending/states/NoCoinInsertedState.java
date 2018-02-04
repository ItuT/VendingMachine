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
public class NoCoinInsertedState implements State{
   /* VendingMachine2 machine;
    public NoCoinInsertedState(VendingMachine2 machine) {
        this.machine =  machine;
    }*/
    public void insertCoin(Coin coin) throws MachineWarning{
       /* if (!machine.isEmpty()) {
            machine.setMachineState(machine.getCoinInsertedState());
        }
        else {
            throw new MachineWarning("Can not process request .. Machine is out of stock");
        }*/
    }
    public void pressButton() throws MachineWarning{
        throw new MachineWarning("No coin inserted ..");
    }
    public void dispense() throws MachineWarning{
        throw new MachineWarning("Invalid Operation");
    }
}
