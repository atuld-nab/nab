package au.com.anz.Exception;

public class TransactionServiceException extends RuntimeException {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public  TransactionServiceException(String message){
        super("Exception occured at service layer because of"+message);
    }
}
