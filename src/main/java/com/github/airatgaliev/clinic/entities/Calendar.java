package com.github.airatgaliev.clinic.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

  private List<Appointment> appointments;
  private LocalDate localDate;

  public Calendar(LocalDate localDate) {
    this.appointments = new ArrayList<>();
    this.localDate = localDate;
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }
}
