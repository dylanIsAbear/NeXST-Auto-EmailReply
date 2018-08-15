package MesException;

import javax.mail.MessagingException;

public class NullSubjectException extends MessagingException{

	public NullSubjectException() {
		super();
	}
	public void printStackTrace() {
		System.out.println("There is no subject for the message ! ");
	}
}


	
