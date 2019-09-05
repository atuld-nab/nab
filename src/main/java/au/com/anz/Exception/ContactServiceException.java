package au.com.anz.Exception;

public class ContactServiceException extends RuntimeException {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public  ContactServiceException(String message){
        super("Exception occured at service layer because of"+message);
    }
}
