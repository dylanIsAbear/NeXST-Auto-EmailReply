import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



import java.io.*;

public class eMail {

	private String subject;
	private String from;
	private String to;
	private String sentTime;
	private boolean isread;
	private String priority;
	private boolean needReplySign;
	private boolean containAttach;
	private String content;
	private List<Image> img;
	private List<URL> url;
	private String title;
	private OutputStream os;
	private byte[] byt;
	
	
	
	/* 
	 *  The JavaBean for an email after anylized*/
	
	
	public eMail() {
		img = new ArrayList<>();
		url = new ArrayList<>();
		os = null;
	}
	
	public void setContent(String str) {
		this.content = str;
	}
	public String getContent() {
		return content;
	}
	
	public void addImage(Image img) {
		this.img.add(img);
	}
	public List<Image> getImg(){
		return img;
	}
	
	public void setName(String name) {
		title = name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void addAttachment(URL url) {
		this.url.add(url);
	}
	public List<URL> getAttachment(){
		return url;
	}
	
	public void setSubject(String sub) {
		subject = sub;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setFrom(String fr)
	{
		from = fr;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setSentTime(String time) {
		sentTime = time;
	}
	
	public String getSentTime() {
		return sentTime;
	}
	
	public void setIsRead(boolean bl) {
		isread = bl;
	}
	
	public boolean isRead() {
		return isread;
	}
	
	public void setPriority(String pri) {
		priority = pri;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setNeedReplySign(boolean bl) {
		needReplySign = bl;
	}
	
	public boolean needReplySign() {
		return needReplySign;
	}
	
	public void setContainAttach(boolean bl) {
		containAttach = bl;
	}
	
	public boolean containAttach() {
		return containAttach;
	}
	
	public void writeTo(OutputStream os, File file) throws IOException {
		
		if(!file.exists())
			file.createNewFile();
		
		OutputStream outputStream = os = new FileOutputStream(file);
		String temp;
		
		temp = "From : " + getFrom() + "\n\n\n";
		byt = temp.getBytes();
		os.write(byt);
		
		temp = "To : " + getTo() + "\n\n\n";
		byt = temp.getBytes();
		os.write(byt);
		
		temp = "Sent time : " + getSentTime()  +"\n" ;
		byt = temp.getBytes();
		os.write(byt);
		
		temp = "Subject : " + getSubject() + "\n\n";
		byt = temp.getBytes();
		os.write(byt);
		
		temp = "Title : " + getTitle() + "\n";
		byt = temp.getBytes();
		os.write(byt);
		
		temp = "Content : " + getContent() + "\n\n";
		byt = temp.getBytes();
		os.write(byt);
		
		if(containAttach) {
			for(int i = 0; i < url.size();i++) {
			temp = "URL for attachment : " + getAttachment() + "\n";
			byt = temp.getBytes();
			os.write(byt);
			}
		}
		
		
		
	}
	
}






