package com.github.airatgaliev.clinic.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

  private List<Appointment> appointments;
  private LocalDate today;

  public Calendar(LocalDate today) {
    this.today = today;
    this.appointments = new ArrayList<>();
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }

  public LocalDate getToday() {
    return today;
  }

  public void setToday(LocalDate today) {
    this.today = today;
  }
}
