package com.silin.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String to,String emailMsg) throws AddressException, MessagingException{
		Properties props = new Properties();
		//props.setProperty("mail.transport.protocol", "SMTP");
		//props.setProperty("mail.host", "smtp.126.com");
		//props.setProperty("mail.smtp.auth", "true"); // ָ����֤Ϊtrue
		// ������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("silin_Liu528@126.com", "silin528");
			}
		};
		Session session = Session.getInstance(props, auth);
		
		// 2.����һ��Message�����൱�����ʼ�����
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("silin_Liu528@126.com")); // ���÷�����

		message.setRecipient(RecipientType.TO, new InternetAddress(to)); // ���÷��ͷ�ʽ�������

		message.setSubject("�û�����");
		// message.setText("����һ�⼤���ʼ�����<a href='#'>���</a>");
//		String url= "http://localhost:8080/store/servlet/ActiveServlet?method=active&code="+code;
//		String uri = "<h1>����silin�ļ����ʼ�����������һ�����ӣ�</h1><h3><a href='+url+'>����</a></h3>";
		message.setContent(emailMsg , "text/html;charset=utf-8");
		// 3.���� Transport���ڽ��ʼ�����
		 Transport transport = session.getTransport("smtp");
         transport.connect("smtp.126.com", "silin_Liu528@126.com", "silin528");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
		
	}
}
