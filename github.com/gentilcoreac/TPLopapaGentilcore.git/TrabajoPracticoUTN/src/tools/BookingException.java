package tools;

public class BookingException extends Exception{

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
	
}
