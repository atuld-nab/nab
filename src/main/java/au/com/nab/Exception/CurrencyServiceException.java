package au.com.nab.Exception;

public class CurrencyServiceException extends RuntimeException {
   public  CurrencyServiceException(String message){
        super("Exception occured at service layer because of"+message);
    }
}
