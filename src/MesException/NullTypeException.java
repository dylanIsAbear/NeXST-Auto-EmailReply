package MesException;

import javax.mail.MessagingException;

public class NullTypeException extends MessagingException{

	public NullTypeException() {
		super();
	}
	public void printStackTrace(){
		System.out.println("The type for the message is null !");
	}
}
