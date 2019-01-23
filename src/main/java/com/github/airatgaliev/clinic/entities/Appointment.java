package com.github.airatgaliev.clinic.entities;

import java.time.LocalDate;

public class Appointment {

  private LocalDate localDate;
  private Doctor doctor;
  private Patient patient;

  public Appointment(LocalDate localDate, Doctor doctor,
      Patient patient) {
    this.localDate = localDate;
    this.doctor = doctor;
    this.patient = patient;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }
}
