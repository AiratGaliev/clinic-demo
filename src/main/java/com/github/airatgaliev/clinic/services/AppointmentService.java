package com.github.airatgaliev.clinic.services;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Doctor;
import com.github.airatgaliev.clinic.entities.Patient;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(localDate, today);
    calendarRepository.addAppointment(
        new Appointment(localDateTime, doctor, patient));
  }

  public boolean hasAppointment(LocalDate date) {
    return calendarRepository.getAppointments().stream()
        .anyMatch(appointment -> appointment.getLocalDateTime().toLocalDate().equals(date));
  }
}
