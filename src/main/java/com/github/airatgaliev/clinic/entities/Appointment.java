package com.github.airatgaliev.clinic.entities;

import java.time.LocalDate;

public class Appointment {

  private Long id;
  private LocalDate localDate;
  private Doctor doctor;
  private Patient patient;

  public Appointment(Long id, LocalDate localDate, Doctor doctor,
      Patient patient) {
    this.id = id;
    this.localDate = localDate;
    this.doctor = doctor;
    this.patient = patient;
  }

  public Long getId() {
    return id;
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
