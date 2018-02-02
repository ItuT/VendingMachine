package vending;

import vending.api.VendingMachine;

public class VendingMachineFactory {      
    public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl();
    }
}
