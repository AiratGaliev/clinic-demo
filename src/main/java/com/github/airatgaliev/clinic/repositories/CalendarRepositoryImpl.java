package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Calendar;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarRepositoryImpl implements ICalendarRepository {

  private final Calendar calendar;

  public CalendarRepositoryImpl() {
    this.calendar = new Calendar();
  }

  @Override
  public void addAppointment(Appointment appointment) {
    calendar.getAppointments().add(appointment);
  }

  @Override
  public List<Appointment> getAppointments() {
    return calendar.getAppointments();
  }

  @Override
  public List<Appointment> getTodayAppointments() {
    return calendar.getAppointments().stream()
        .filter(
            appointment -> appointment.getLocalDateTime().toLocalDate().equals(LocalDate.now()))
        .collect(Collectors.toList());
  }
}
