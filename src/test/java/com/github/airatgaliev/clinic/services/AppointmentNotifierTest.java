package com.github.airatgaliev.clinic.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.airatgaliev.clinic.repositories.CalendarRepositoryImpl;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import com.github.airatgaliev.clinic.repositories.PatientRepositoryImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentNotifierTest {

  private IPatientRepository patientRepository;
  private ICalendarRepository calendarRepository;
  private AppointmentService appointmentService;
  private AppointmentNotifier appointmentNotifier;
  private IMessageSenderServiceTestDouble messageSenderServiceTestDouble;

  @BeforeEach
  public void init() {
    messageSenderServiceTestDouble = new IMessageSenderServiceTestDouble();
  }

  @Test
  public void sendNotificationWithCorrectFormat() {
    calendarRepository = new CalendarRepositoryImpl(LocalDate.of(2019, 1, 26));
    patientRepository = new PatientRepositoryImpl();
    appointmentService = new AppointmentService(patientRepository, calendarRepository);
    appointmentService.setAppointment("martinez", "Galiev", "Airat", "1/27/2019 2:00 pm");
    appointmentNotifier = new AppointmentNotifier(calendarRepository,
        messageSenderServiceTestDouble);
    appointmentNotifier.run();

    assertEquals(1, messageSenderServiceTestDouble.getMessages().size());
    IMessageSenderServiceTestDouble.Message message = messageSenderServiceTestDouble.getMessages()
        .get(0);
    assertAll(
        () -> assertEquals("galievairat@mail.com", message.toAddress),
        () -> assertEquals("Appointment Reminder", message.subject),
        () -> assertEquals("You have an appointment tomorrow at 2:00 PM "
            + "with Dr. John Martinez.", message.body)
    );
  }
}