import javax.mail.*;
import java.io.*;
import java.util.*;
import MesException.*;
public class MailReader {
		/*Reader class is for reading a mail and offers the getter method 
		 * for other classes for further information and analyze */
	
		/*并提供获取信息的接口
		 * */
		private String subject;
		private Address[] addr;
		private Message[] mes;
		private Folder folder;

		/* MUST setup setAccount method before using the class */
		
		/* One Reader for one INBOX*/
		public MailReader(Folder folder) {
			this.folder = folder;
		}
		
		public Message[] getMailMessage() throws MessagingException {
			mes = folder.getMessages();
			return mes;
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
		
		public int getTotalCount() throws MessagingException {
			return folder.getMessageCount();
		}
		
		public int getUnreadCount() throws MessagingException{
			return folder.getUnreadMessageCount();
		}
		
		public int getNewCount() throws MessagingException {
			return folder.getNewMessageCount();
		}
		
		public int getDeletedCount() throws MessagingException {
			return folder.getDeletedMessageCount(); 
		}
		
}

