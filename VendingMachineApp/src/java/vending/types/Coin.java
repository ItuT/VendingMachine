/**
 * 
 */
package vending.types;

/**
 * @author Itumeleng
 *
 */
public enum Coin {
    FIFTYCENTS("fifty_cents",0.5), ONERAND("one_rand",1), TWORAND("three_rand",2), FIVERAND("five_rand",5);
   
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
