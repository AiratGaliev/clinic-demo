package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Appointment;
import java.util.ArrayList;
import java.util.List;

public class CalendarRepositoryImpl implements ICalendarRepository {

  List<Appointment> appointments;

  public CalendarRepositoryImpl() {
    this.appointments = new ArrayList<>();
  }

  @Override
  public void addAppointment(Appointment appointment) {
    appointments.add(appointment);
  }

  @Override
  public Appointment getAppointmentById(int id) {
    return appointments.get(id);
  }

  @Override
  public List<Appointment> getAppointments() {
    return appointments;
  }
}
