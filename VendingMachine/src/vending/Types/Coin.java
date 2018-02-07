/**
 * 
 */
package vending.types;

/**
 * @author Itumeleng
 *
 */
public enum Coin {
    FIFTYCENTS("Fifty Cents",0.5), ONERAND("One Rand",1), TWORAND("Three Rand",2), FIVERAND("Five Rand",5);
   
    private double denomination;
    private String name;
   
    private Coin(String name,double denomination){
        this.denomination = denomination;
        this.name = name;
    }
   
    public double getDenomination(){
        return denomination;
    }
    
    public String getName(){
        return name;
    }
}
