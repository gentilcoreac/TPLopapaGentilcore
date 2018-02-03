package tools;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingException extends Exception{


	private static final long serialVersionUID = 1L;
	String message;
	Throwable BookingException;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getBookingException() {
		return BookingException;
	}
	public void setBookingException(Throwable innerException) {
		this.BookingException = innerException;
	}
	
	public BookingException(){}
	
	public BookingException(Throwable e,String message){
		this.setBookingException(e);
		this.setMessage(message+"\n  "+e.getMessage());
	}
	
	public BookingException(Throwable e,String message,Level errorLevel){
		this(e,message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,message);
	}
}
