package vending.states;

import vending.exceptions.MachineWarning;
import vending.types.Coin;

public class EmptyState implements State{
    /*VendingMachine2 machine;
    public EmptyState(VendingMachine2 machine) {
        this.machine =  machine;
    }*/
    public void insertCoin(Coin coin) throws MachineWarning{
        throw new MachineWarning("Can not process the request");
    }
    public void pressButton() throws MachineWarning{
        throw new MachineWarning("Invalid Action");
    }
    public void dispense() throws MachineWarning{
        throw new MachineWarning("Invalid Action");
    }
}
