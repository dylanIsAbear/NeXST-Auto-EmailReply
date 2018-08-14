import java.util.*;
import javax.mail.*;


public class MailAnalyzer {

public static final String protocol = "pop3";
public static final String pop3 = "pop.126.com";

	public static Folder getFolder(String host, String username, String password) {
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", protocol);
		props.setProperty("mail.smtp.host", pop3);
		
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		
		try {
			
			Store store = session.getStore(protocol);
			store.connect(host, username, password);
			Folder folder = store.getFolder("inbox");
			folder.open(Folder.READ_WRITE);
			return folder;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
