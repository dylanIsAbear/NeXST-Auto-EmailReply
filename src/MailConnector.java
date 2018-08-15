import javax.mail.*;
import java.util.*;


public class MailConnector {
	
	private String username;
	private String pop;
	private String password;
	private Folder folder;
	private static final String protocol = "pop3";
	/* This class is used for connecting with the mail server.
	 * Must use Connect() method to connect */
	public MailConnector(String username, String pop, String password) {
		this.username = username;
		this.pop = pop;
		this.password = password;
		
	}
	
	public void Connect() {
		
		Properties props = new Properties();
		props.setProperty("mail.tranport.protocol", protocol);
		props.setProperty("mail.stmp.host", pop);
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);
		
		try {
			
			Store store = session.getStore(protocol);
			store.connect(pop, username, password);
			
			folder = store.getFolder("INBOX");
			
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* Return Folder object */
	public Folder getMailFolder() {
		
		return folder;
	}
}
