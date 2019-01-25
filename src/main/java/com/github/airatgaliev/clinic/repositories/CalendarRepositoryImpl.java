package com.github.airatgaliev.clinic.repositories;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.entities.Calendar;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarRepositoryImpl implements ICalendarRepository {

  private Calendar calendar;
  private LocalDate today;

  public CalendarRepositoryImpl(LocalDate today) {
    this.today = today;
    this.calendar = new Calendar(today);
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
            appointment -> appointment.getLocalDateTime().toLocalDate().equals(today))
        .collect(Collectors.toList());
  }
}
