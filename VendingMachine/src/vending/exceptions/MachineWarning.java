/**
 * 
 */
package vending.exceptions;

/**
 * @author Itumeleng
 *
 */
public class MachineWarning extends RuntimeException {
    private String message;
   
    public MachineWarning(String string) {
        this.message = string;
    }
   
    @Override
    public String getMessage(){
        return message;
    }
   
}
