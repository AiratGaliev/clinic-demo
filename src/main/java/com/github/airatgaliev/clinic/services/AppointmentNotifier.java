package com.github.airatgaliev.clinic.services;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AppointmentNotifier {

  private ICalendarRepository calendarRepository;
  private IMessageSenderService notifier;

  public AppointmentNotifier(
      ICalendarRepository calendarRepository, IMessageSenderService notifier) {
    this.calendarRepository = calendarRepository;
    this.notifier = notifier;
  }

  public void run() {
    calendarRepository.getTomorrowAppointments()
        .forEach(this::sendNotificationForAppointment);
  }

  private void sendNotificationForAppointment(Appointment appointment) {
    String email = appointment.getPatient().getLastName().toLowerCase() + appointment.getPatient()
        .getFirstName().toLowerCase() + "@mail.com";
    System.out.println("Sending with body: " + buildMessageBody(appointment));
    notifier.sendNotification("Appointment Reminder", buildMessageBody(appointment), email);
  }

  private String buildMessageBody(Appointment appointment) {
    return "You have an appointment tomorrow at "
        + appointment.getLocalDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
        + " with Dr. " + appointment.getDoctor().getName() + ".";
  }
}
