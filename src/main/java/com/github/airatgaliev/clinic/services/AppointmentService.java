package com.github.airatgaliev.clinic.services;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Doctor;
import com.github.airatgaliev.clinic.entities.Patient;
import com.github.airatgaliev.clinic.exceptions.DateTimeFormatException;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AppointmentService {

  private final IPatientRepository patientRepository;
  private final ICalendarRepository calendarRepository;
  private final LocalDate today = LocalDate.now();

  public AppointmentService(
      IPatientRepository patientRepository,
      ICalendarRepository calendarRepository) {
    this.patientRepository = patientRepository;
    this.calendarRepository = calendarRepository;
  }

  public void setAppointment(String doctorKey, String patientLastName, String patientFirstName,
      String localDate) {
    Doctor doctor = Doctor.valueOf(doctorKey.toLowerCase());
    Patient patient = new Patient(patientFirstName, patientLastName);
    patientRepository.addPatient(patient);
    LocalDateTime localDateTime = getDateTimeFromString(localDate);
    calendarRepository.addAppointment(
        new Appointment(localDateTime, doctor, patient));
  }

  private LocalDateTime getDateTimeFromString(String localDate) {
    LocalDateTime localDateTime;
    try {
      if (localDate.toLowerCase().startsWith("today")) {
        String[] parts = localDate.split(" ", 2);
        LocalTime time = LocalTime
            .parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
        localDateTime = LocalDateTime.of(today, time);
      } else {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("M/d/yyyy h:mm a", Locale.US);
        localDateTime = LocalDateTime.parse(localDate.toUpperCase(), dateTimeFormatter);
      }
    } catch (Exception e) {
      throw new DateTimeFormatException(
          "Unable to create date time from: [" + localDate
              + "], please enter format [M/d/yyyy h:mm a], " + e.getMessage());
    }
    return localDateTime;
  }

  public boolean hasAppointment(LocalDate date) {
    return calendarRepository.getAppointments().stream()
        .anyMatch(appointment -> appointment.getLocalDateTime().toLocalDate().equals(date));
  }
}
