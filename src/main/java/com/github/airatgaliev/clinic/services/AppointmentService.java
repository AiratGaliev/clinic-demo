package com.github.airatgaliev.clinic.services;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Doctor;
import com.github.airatgaliev.clinic.entities.Patient;
import com.github.airatgaliev.clinic.exceptions.DateTimeFormatException;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AppointmentService {

  IPatientRepository patientRepository;
  ICalendarRepository calendarRepository;

  public AppointmentService(
      IPatientRepository patientRepository,
      ICalendarRepository calendarRepository) {
    this.patientRepository = patientRepository;
    this.calendarRepository = calendarRepository;
  }

  public void setAppointment(String doctorKey, String patientLastName, String patientFirstName,
      String localDate) {
    Doctor doctor = Doctor.valueOf(doctorKey.toLowerCase());
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofPattern("MM/dd/yyyy h:mm a", Locale.US);
    LocalDateTime localDateTime;
    try {
      localDateTime = LocalDateTime.parse(localDate.toUpperCase(), dateTimeFormatter);
    } catch (Exception e) {
      throw new DateTimeFormatException(
          "Unable to create date time from: [" + localDate.toUpperCase()
              + "], please enter format [MM/dd/yyyy h:mm a]");
    }
    calendarRepository.addAppointment(
        new Appointment(localDateTime, doctor, new Patient(patientFirstName, patientLastName)));
  }
}
