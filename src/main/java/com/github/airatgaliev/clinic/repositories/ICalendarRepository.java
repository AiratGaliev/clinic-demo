package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Appointment;
import java.util.List;

public interface ICalendarRepository {

  public void addAppointment();

  public Appointment getAppointmentById();

  public List<Appointment> getAppointments();
}
