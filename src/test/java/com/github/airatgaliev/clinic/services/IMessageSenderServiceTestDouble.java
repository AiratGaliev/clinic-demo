package com.github.airatgaliev.clinic.services;

import java.util.ArrayList;
import java.util.List;

class IMessageSenderServiceTestDouble implements IMessageSenderService {

  List<Message> messages = new ArrayList<>();

  @Override
  public void sendNotification(String appointment_reminder, String buildMessageBody, String email) {
    messages.add(new Message(appointment_reminder, buildMessageBody, email));
  }

  static class Message {

    public String toAddress;
    public String subject;
    public String body;

    public Message(String subject, String body, String toAddress) {
      this.toAddress = toAddress;
      this.subject = subject;
      this.body = body;
    }
  }

  public List<Message> getMessages() {
    return messages;
  }
}