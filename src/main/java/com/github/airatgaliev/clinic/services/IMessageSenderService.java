package com.github.airatgaliev.clinic.services;

public interface IMessageSenderService {

  void sendNotification(String appointment_reminder, String buildMessageBody, String email);
}
