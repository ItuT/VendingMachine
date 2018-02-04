package vending.states;

import vending.types.Coin;

public interface State {
	   
    public void insertCoin(Coin coin);
    public void pressButton();
    public void dispense();
}
