package com.algo;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class email {
	
	public static void mailsend(String msg,String toEmail)
	{
		String fromEmail = "thokes726@gmail.com";
       // String toEmail = "jitusunsofttech@gmail.com"; // Change to recipient's email
        String host = "smtp.gmail.com";
        String username = "thokes726@gmail.com";
        String password = "fsay wvyg vthr ivvi"; // Use the app password

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Product Quantity Information");
            message.setText(msg);

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		String toEmail = "kaustubhsagale007@gmail.com";
		mailsend("Not Available Quantity", toEmail);
    }

}
