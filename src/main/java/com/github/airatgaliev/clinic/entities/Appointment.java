package com.github.airatgaliev.clinic.entities;

import java.time.LocalDateTime;

public class Appointment {

  private LocalDateTime localDateTime;
  private Doctor doctor;
  private Patient patient;
  private double bmi;

  public Appointment(LocalDateTime localDateTime, Doctor doctor,
      Patient patient) {
    this.localDateTime = localDateTime;
    this.doctor = doctor;
    this.patient = patient;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
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

  public double getBmi() {
    return bmi;
  }

  public void setBmi(double bmi) {
    this.bmi = bmi;
  }
}
