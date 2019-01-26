package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Appointment;
import java.util.List;

public interface ICalendarRepository {

  void addAppointment(Appointment appointment);

  List<Appointment> getAppointments();

  List<Appointment> getTodayAppointments();

  List<Appointment> getTomorrowAppointments();

  List<Appointment> getUpcomingAppointments();
}
