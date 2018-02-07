package vending.states;

import vending.exceptions.MachineWarning;
import vending.types.Coin;


public class DispensingState implements State{
    /*VendingMachine2 machine ;
    public DispensingState(VendingMachine2 machine) {
        this.machine = machine;
    }*/
    public void insertCoin(Coin coin) throws MachineWarning {
        throw new MachineWarning("wait ... previous order is processing");
    }
    public void pressButton() throws MachineWarning {
        throw new MachineWarning("wait ... previous order is processing");
    }
    public void dispense() throws MachineWarning {
       // machine.setMachineState(machine.getNoCoinInsertedState());
    }
}
