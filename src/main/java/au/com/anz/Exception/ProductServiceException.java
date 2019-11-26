package au.com.anz.Exception;

public class ProductServiceException extends RuntimeException {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public  ProductServiceException(String message){
        super("Exception occured at service layer because of"+message);
    }
}
