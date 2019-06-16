package au.com.nab.Exception;

public class OperatorServiceException extends RuntimeException {
   public  OperatorServiceException(String message){
        super("Exception occured at service layer because of"+message);
    }
}
