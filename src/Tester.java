import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class Tester {

	private MimeMessage mimemessage = null;
	private String savePath = "";
	private StringBuffer content = new StringBuffer();
	private String dateFormat = "yy-MM-dd HH:mm";

	public Tester(MimeMessage mes) {
		this.mimemessage = mes;
		System.out.println("Creating a RecevMail ");
	}

	public void SetMsg(MimeMessage msg) {
		this.mimemessage = msg;
		System.out.println("Setting up a MimeMessage ");
	}

	public String getFrom() throws Exception {

		InternetAddress address[] = (InternetAddress[]) mimemessage.getFrom();
		String from = address[0].getAddress();
		if (from == null) {
			from = "";
			System.out.println("Unknown From. ");
		}

		String person = address[0].getPersonal();
		if (person == null) {
			person = "";
			System.out.println("Unknown person. ");
		}

		String fromAddr = null;
		if (person != null || from != null) {
			fromAddr = person + "<" + from + ">";
			System.out.println("发送者是：" + fromAddr);
		} else
			System.out.println("无法获得发送者信息.");

		return fromAddr;

	}

	public String getMailAddress(String type) throws Exception {

		String mailAddr = "";
		String addType = type.toUpperCase();

		InternetAddress[] address = null;

		if (addType.equals("TO") || addType.equals("CC") || addType.equals("BCC")) {

			switch (addType) {

			case "TO":
				address = (InternetAddress[]) mimemessage.getRecipients(Message.RecipientType.TO);
			case "CC":
				address = (InternetAddress[]) mimemessage.getRecipients(Message.RecipientType.CC);
			case "BCC":
				address = (InternetAddress[]) mimemessage.getRecipients(Message.RecipientType.BCC);

			}

			if (address != null) {
				for (int i = 0; i < address.length; i++) {
					String emailAddr = address[i].getAddress();
					if (emailAddr == null) {
						emailAddr = "";
					} else {
						System.out.println("emailAddr before Converting : " + emailAddr);
						emailAddr = MimeUtility.decodeText(emailAddr);
						System.out.println("emailAddr after Converting : " + emailAddr);
					}
					String person = address[i].getPersonal();
					if (person == null) {
						person = "";
					} else {
						System.out.println("person before converting : " + person);
						person = MimeUtility.decodeText(person);
						System.out.println("person after converting : " + person);
					}
					String comp = person + "<" + emailAddr + ">";
					System.out.println("Email Address : " + comp);
					mailAddr += "," + comp;
				}
				mailAddr = mailAddr.substring(1);
			}
		} else {
			throw new Exception("Wrong Email type");
		}

		return mailAddr;
	}
	
	public String getSubject() throws MessagingException {
		String subject = "";
		try {
			System.out.println("subject before converting : " + subject);
			subject = MimeUtility.decodeText(subject);
			System.out.println("subject after converting : " + subject);
			
			if(subject == null) {
				subject = "";
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subject;
	}
	
	public String getSentDate () throws Exception{
		Date sentDate = mimemessage.getSentDate();
		System.out.println("Raw date : "+ dateFormat);
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String strSentDate = format.format(sentDate);
		System.out.println("Date : "+ strSentDate);
		return strSentDate;
	}
	
	public String getBodyContext() {
		return content.toString();
	}
	
	public void getMailContent(Part part) throws Exception{
		
		String contentType = part.getContentType();
		System.out.println("The type of mail : " + contentType);
		
		int nameIndex = contentType.indexOf("name");
		
		boolean conName = false;
		
		if(nameIndex != -1)
			conName = true;
		System.out.println("Mail Content Type : " + contentType);
		
		if(part.isMimeType("text/plain") && conName == false)
			content.append((String)part.getContent());
		
		else if(part.isMimeType("text/html") && conName == false)
			content.append((String)part.getContent());
		
		else if(part.isMimeType("multipart/*")) {
			
			Multipart multipart = (Multipart)part.getContent();
			int count = multipart.getCount();
			
				for(int i = 0; i<count;i++) {
					getMailContent(multipart.getBodyPart(i));
				}
				
		}else if(part.isMimeType("message/rfc822")) {
			getMailContent((Part)part.getContent());
		}
		
	}
	
	public boolean getReplySign() throws MessagingException{
		boolean rpl = false;
		String[] str = mimemessage.getHeader("Disposition-Notification-To");
		
		if(str != null) {
			rpl = true;
			System.out.println("Need Reply");
		}else
			System.out.println("No need to reply");
		
		return rpl;
	}
	
	public String getMessageID() throws MessagingException {
		String id = mimemessage.getMessageID();
		System.out.println("Message ID is : " + id);
		return id;
	}
	
	public boolean isRead() throws MessagingException{
		boolean read = false;
		Flags flags = ((Message)mimemessage).getFlags();
		Flags.Flag[] flagArray = flags.getSystemFlags();
		System.out.println("The length of flags : " + flagArray.length);
		return read;
		
		
	}
	
	
	

}
