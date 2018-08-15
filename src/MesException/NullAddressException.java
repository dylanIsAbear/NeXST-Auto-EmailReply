package MesException;

import javax.mail.MessagingException;

public class NullAddressException extends MessagingException{

	public NullAddressException() {
		super();
	}
	
	public void printStackTrace() {
		System.out.println("There is no known address for the message ! ");
	}
	
}
