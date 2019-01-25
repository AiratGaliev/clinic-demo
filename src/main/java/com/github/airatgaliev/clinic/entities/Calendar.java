package com.github.airatgaliev.clinic.entities;

import java.util.ArrayList;
import java.util.List;

public class Calendar {

  private List<Appointment> appointments;

  public Calendar() {
    this.appointments = new ArrayList<>();
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }
}
