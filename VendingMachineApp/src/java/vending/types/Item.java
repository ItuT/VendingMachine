/**
 * 
 */
package vending.types;

/**
 * @author Itumeleng
 *
 */
public enum Item{
    SIMBA("Simba", 2.5), HENNESSY("Hennessy", 3.5), STYVESANT("Stuyvesant", 4.5), PEANUTS("Peanuts", 1.5), PANADO("Panado", 0.5);
   
    private String name;
    private double price;
   
    private Item(String name, double price){
        this.name = name;
        this.price = price;
    }
   
    public String getName(){
        return name;
    }
   
    public double getPrice(){
        return price;
    }
}
