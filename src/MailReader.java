import javax.mail.*;
import java.io.*;
import java.util.*;
import MesException.*;
public class MailReader {
		/*Reader class is for reading a mail and offers the getter method 
		 * for other classes for further information and analyze */
		private String pop3 = "pop.126.com";
		private String protocol = "pop3";
		private String username = "liyuanhao2000@126.com";
		private String password = "Gooduw2018";
		private OutputStream os = null;
		private byte[] byt;
		private String subject;
		private Address[] addr;
		
		public boolean setAccout(String pop, String protocol, String username,
				 String password)
		{
			boolean bo = true;
			this.pop3 = pop;
			this.protocol = protocol;
			this.username = username;
			this.password = password;
			return bo;
		}
		private Message[] get() {
			
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
			
		/*	for(Message mes : message) {
				File file = new File("/Users/lewislee/Desktop/mail/" + mes.getSubject() + ".txt");
				os = new FileOutputStream(file);
				String subject = mes.getSubject();
				Address from = (Address) mes.getFrom()[0];
				byt = ("Subject : " + subject + " from : " + from.toString() + "\n").getBytes();
				os.write(byt);
				mes.writeTo(os);
				System.out.println("邮件 : " + file.getName() + " 已被成功生成！!");
			} */
			folder.close(false);
			store.close();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
		}
		
		public String getSubject(Message[] mes, int index) throws MessagingException  {
			if((subject = mes[index].getSubject() )!= null && (subject.length() >= 1))
				return subject;
			else
				throw new NullSubjectException();
		}
		
		public Address[] getFrom(Message[] mes, int index) throws MessagingException {
			addr = mes[index].getFrom();
				if(addr[0] != null)
					return addr;
				else
					throw new NullAddressException();
		}
		
		public String getMesContentType(Message[] mes, int index) throws MessagingException {
			String type = mes[index].getContentType();
			if(type != null)
				return type;
			else
				throw new NullTypeException();
		}
		
		public Object getMesContent(Message mes[], int index) throws MessagingException, IOException{
			return mes[index].getContent();
		}
		
		
}

