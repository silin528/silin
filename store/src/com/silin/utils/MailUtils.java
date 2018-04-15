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
		//props.setProperty("mail.smtp.auth", "true"); // 指定验证为true
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("silin_Liu528@126.com", "silin528");
			}
		};
		Session session = Session.getInstance(props, auth);
		
		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("silin_Liu528@126.com")); // 设置发送者

		message.setRecipient(RecipientType.TO, new InternetAddress(to)); // 设置发送方式与接收者

		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
//		String url= "http://localhost:8080/store/servlet/ActiveServlet?method=active&code="+code;
//		String uri = "<h1>来自silin的激活邮件！激活请点击一下链接！</h1><h3><a href='+url+'>请点击</a></h3>";
		message.setContent(emailMsg , "text/html;charset=utf-8");
		// 3.创建 Transport用于将邮件发送
		 Transport transport = session.getTransport("smtp");
         transport.connect("smtp.126.com", "silin_Liu528@126.com", "silin528");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
		
	}
}
