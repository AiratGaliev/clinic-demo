package com.github.airatgaliev.clinic.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Doctor;
import com.github.airatgaliev.clinic.repositories.CalendarRepositoryImpl;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import com.github.airatgaliev.clinic.repositories.PatientRepositoryImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("AppointmentService Should")
class AppointmentServiceTest {

  private IPatientRepository patientRepository;
  private ICalendarRepository calendarRepository;
  private AppointmentService appointmentService;

  @BeforeEach
  public void init() {
    patientRepository = new PatientRepositoryImpl();
    calendarRepository = new CalendarRepositoryImpl(LocalDate.now());
    appointmentService = new AppointmentService(patientRepository,
        calendarRepository);
  }

  @Test
  @DisplayName("allow entry of appointment")
  public void allowEntryOfAppointment() {
    appointmentService.setAppointment("martinez", "Galiev", "Airat", "01/11/2019 2:15 pm");
    List<Appointment> appointments = calendarRepository.getAppointments();
    assertNotNull(appointments);
    assertEquals(1, appointments.size());
    Appointment appointment = appointments.get(0);
    assertAll(
        () -> assertEquals("Galiev", appointment.getPatient().getLastName()),
        () -> assertEquals("Airat", appointment.getPatient().getFirstName()),
        () -> assertSame(Doctor.martinez, appointment.getDoctor()),
        () -> assertEquals("1/11/2019 2:15 PM",
            appointment.getLocalDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a"))));
  }

  @Nested
  @DisplayName("indicate if there are appointments correctly")
  class HasAppointments {

    @Test
    @DisplayName("when there are appointments")
    public void returnTrueForHasAppointmentsIfThereAreAppointments() {
      appointmentService.setAppointment("martinez", "Galiev", "Airat", "01/11/2019 2:15 pm");
      assertTrue(appointmentService.hasAppointment(LocalDate.of(2019, 1, 11)));
    }

    @Test
    @DisplayName("when there are no appointments")
    public void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
      assertFalse(appointmentService.hasAppointment(LocalDate.of(2019, 1, 11)));
    }
  }

  @Nested
  @DisplayName("return appointments correctly")
  class AppointmentsForDay {

    @Test
    @DisplayName("for today")
    public void returnCurrentDaysAppointments() {
      appointmentService.setAppointment("martinez", "Galiev", "Airat", "today 2:15 pm");
      appointmentService.setAppointment("goodman", "Galiev", "Airat", "today 3:15 pm");
      appointmentService.setAppointment("white", "Galiev", "Airat", "01/11/2019 2:15 pm");
      assertEquals(2, calendarRepository.getTodayAppointments().size());
    }

    @Test
    @DisplayName("for tomorrow")
    public void returnTomorrowsAppointments() {
      LocalDate tomorrow = LocalDate.now().plusDays(1);
      String localDate = String
          .format("%s/%s/%s", tomorrow.getMonthValue(), tomorrow.getDayOfMonth(),
              tomorrow.getYear());
      appointmentService.setAppointment("martinez", "Galiev", "Airat", localDate + " 2:15 pm");
      appointmentService.setAppointment("goodman", "Galiev", "Airat", localDate + " 3:15 pm");
      appointmentService.setAppointment("white", "Galiev", "Airat", "01/11/2019 2:15 pm");
      assertEquals(2, calendarRepository.getTomorrowAppointments().size());
    }
  }

  @Nested
  @DisplayName("return upcoming appointments")
  class UpcomingAppointments {

    @Test
    public void whenThereAreNone() {
      assertEquals(0, calendarRepository.getUpcomingAppointments().size());
    }

    @Test
    public void whenThereAreSomePastAndFuture() {
      String pastDate = String
          .format("%s/%s/%s", LocalDate.now().minusYears(1).getMonthValue(),
              LocalDate.now().minusYears(1).getDayOfMonth(),
              LocalDate.now().minusYears(1).getYear());
      String futureDate = String
          .format("%s/%s/%s", LocalDate.now().plusMonths(1).getMonthValue(),
              LocalDate.now().plusMonths(1).getDayOfMonth(),
              LocalDate.now().plusMonths(1).getYear());
      appointmentService.setAppointment("martinez", "Galiev", "Airat", pastDate + " 2:15 pm");
      appointmentService.setAppointment("goodman", "Galiev", "Airat", pastDate + " 3:15 pm");
      appointmentService.setAppointment("white", "Galiev", "Airat", futureDate + " 2:15 pm");
      assertEquals(1, calendarRepository.getUpcomingAppointments().size());
    }
  }
}