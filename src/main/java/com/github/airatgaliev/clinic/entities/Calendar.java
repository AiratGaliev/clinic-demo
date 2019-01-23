package com.github.airatgaliev.clinic.entities;

import java.util.List;

public class Calendar {

  private List<Appointment> appointments;

  public Calendar(List<Appointment> appointments) {
    this.appointments = appointments;
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }
}
