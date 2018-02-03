package tools;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppDataException extends Exception{


	private static final long serialVersionUID = 1L;
	String message;
	Throwable innerException;
	
		
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getInnerException() {
		return innerException;
	}

	public void setInnerException(Throwable innerException) {
		this.innerException = innerException;
	}

	public AppDataException(Throwable e,String message){
		this.setInnerException(e);
		this.setMessage(message+":\nAppDataException\n"+e.getMessage());
	}
	
	public AppDataException(Throwable e, String message, Level errorLevel) {
		this(e,message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,message);
	}
	
}
