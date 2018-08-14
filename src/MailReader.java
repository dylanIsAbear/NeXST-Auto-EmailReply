import javax.mail.*;
import java.io.*;
import java.util.*;
public class MailReader {

	public static void main(String args[] ) {
		
		String pop3 = "pop.126.com";
		String protocol = "pop3";
		String username = "liyuanhao2000@126.com";
		String password = "Gooduw2018";
		OutputStream os = null;
		byte[] byt;
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", protocol);
		props.setProperty("mail.smtp.host", pop3);
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);
		
		try {
			
			Store store = session.getStore(protocol);
			store.connect(pop3, username, password);
			
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			
			Message[] message = folder.getMessages();
			
			for(Message mes : message) {
				File file = new File("/Users/lewislee/Desktop/mail/" + mes.getSubject() + ".txt");
				os = new FileOutputStream(file);
				String subject = mes.getSubject();
				Address from = (Address) mes.getFrom()[0];
				byt = ("Subject : " + subject + " from : " + from.toString() + "\n").getBytes();
				os.write(byt);
				mes.writeTo(os);
				System.out.println("邮件 : " + file.getName() + " 已被成功生成！!");
			}
			folder.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
