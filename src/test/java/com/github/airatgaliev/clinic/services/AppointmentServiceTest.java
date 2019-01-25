package com.github.airatgaliev.clinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Doctor;
import com.github.airatgaliev.clinic.repositories.CalendarRepositoryImpl;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import com.github.airatgaliev.clinic.repositories.PatientRepositoryImpl;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {

  @Test
  public void allowEntryOfAppointment() {
    IPatientRepository patientRepository = new PatientRepositoryImpl();
    ICalendarRepository calendarRepository = new CalendarRepositoryImpl();
    AppointmentService appointmentService = new AppointmentService(patientRepository,
        calendarRepository);
    appointmentService.setAppointment("martinez", "Galiev", "Airat", "01/11/2019 2:15 pm");
    List<Appointment> appointments = calendarRepository.getAppointments();
    assertNotNull(appointments);
    assertEquals(1, appointments.size());
    Appointment appointment = appointments.get(0);
    assertEquals("Galiev", appointment.getPatient().getLastName());
    assertEquals("Airat", appointment.getPatient().getFirstName());
    assertEquals(Doctor.martinez, appointment.getDoctor());
    assertEquals("1/11/2019 2:15 PM",
        appointment.getLocalDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
  }
}