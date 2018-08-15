import java.util.*;
import javax.mail.*;
import javax.mail.internet.MimeMessage;

import MesException.*;

public class MailAnalyzer {
	
	private String contentType;
	private Message mail;
	private eMail email;
	
	/* One Analyzer for each mail*/
	public MailAnalyzer(Message mail) {
		this.mail = mail;
		email = new eMail();
		try {
			doAnalyze();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doAnalyze() throws MessagingException {
		
		contentType = mail.getContentType();
		
		if(contentType == "multipart/*")
			doMultiAnalyze(mail);
		else
			doDefaultAnalyze(mail);
	}
	
	private void doMultiAnalyze(Message msg) {
		
	}
	
	private void doDefaultAnalyze(Message mes) {
		
	}
	
	
	public eMail getMail() {
		return email;
	}
	

}
