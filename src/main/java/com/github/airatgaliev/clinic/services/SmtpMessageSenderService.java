package com.github.airatgaliev.clinic.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpMessageSenderService implements IMessageSenderService {

  @Override
  public void sendNotification(String appointment_reminder, String buildMessageBody, String email) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", "localhost");
    Session session = Session.getDefaultInstance(properties, null);
    Message message = new MimeMessage(session);
    try {
      message.setFrom(new InternetAddress("system@clinic.com"));
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
      message.setSubject(appointment_reminder);
      message.setContent(buildMessageBody, "text/html");
      Transport.send(message);
    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
