package com.fssa.librarymanagement.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	public static void main(String[] args) {
		// Sender's email and password
		final String senderEmail = "kishor.muruganandham@gmail.com";
		final String senderPassword = "#Master_55";

		// Recipient's email
		String recipientEmail = "aerokk5@gmail.com";

		// Email properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
		try {
			// Create a default MimeMessage object
			Message message = new MimeMessage(session);

			// Set From: header field of the header
			message.setFrom(new InternetAddress(senderEmail));

			// Set To: header field of the header
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

			// Set Subject: header field
			message.setSubject("Testing JavaMail API");

			// Now set the actual message
			message.setText("This is a test message from JavaMail API.");

			// Send the message
			Transport.send(message);

			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
