/**
 * 
 */
package vending.exceptions;

/**
 * @author Itumeleng
 *
 */
public class NotFullPaidException extends RuntimeException {
    private String message;
    private double remaining;
   
    public NotFullPaidException(String message, double remaining) {
        this.message = message;
        this.remaining = remaining;
    }
   
    public double getRemaining(){
        return remaining;
    }
   
    @Override
    public String getMessage(){
        return message + remaining;
    } 
   
}
