package com.github.airatgaliev.clinic;

import com.github.airatgaliev.clinic.entities.Appointment;
import com.github.airatgaliev.clinic.repositories.CalendarRepositoryImpl;
import com.github.airatgaliev.clinic.repositories.ICalendarRepository;
import com.github.airatgaliev.clinic.repositories.IPatientRepository;
import com.github.airatgaliev.clinic.repositories.PatientRepositoryImpl;
import com.github.airatgaliev.clinic.services.AppointmentService;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClinicApp {

  private static IPatientRepository patientRepository;
  private static ICalendarRepository calendarRepository;
  private static AppointmentService appointmentService;

  public static void main(String[] args) throws IOException {
    patientRepository = new PatientRepositoryImpl();
    calendarRepository = new CalendarRepositoryImpl();
    appointmentService = new AppointmentService(patientRepository, calendarRepository);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome patient intake system!\n\n");
    String lastOption = "";
    while (!lastOption.equalsIgnoreCase("x")) {
      lastOption = displayMenu(scanner);
    }
    System.out.println();
  }

  private static String displayMenu(Scanner scanner) throws IOException {
    System.out.println("Please select an option");
    System.out.println("1. Enter patient appointment");
    System.out.println("2. View all appointments");
    System.out.println("3. View today's appointments");
    System.out.println("x. Exit");
    System.out.print("Option: ");
    String option = scanner.next();
    switch (option) {
      case "1":
        performPatientEntry(scanner);
        return option;
      case "2":
        performAllAppointments();
        return option;
      case "3":
        performTodaysAppointments();
        return option;
      default:
        System.out.println("Invalid option, please re-enter");
        return option;
    }
  }

  private static void performPatientEntry(Scanner scanner) {
    scanner.nextLine();
    System.out.println("\n\nPlease enter appointment info:");
    System.out.print(" Patient last name: ");
    String patientLastName = scanner.nextLine();
    System.out.print(" Patient first name: ");
    String patientFirstName = scanner.nextLine();
    System.out.print(" Appointment date (M/d/yyyy h:mm a): ");
    String when = scanner.nextLine();
    System.out.print(" Doctor last name: ");
    String doctorLastName = scanner.nextLine();
    try {
      appointmentService.setAppointment(doctorLastName, patientLastName, patientFirstName, when);
    } catch (Exception e) {
      System.out.println("Error! " + e.getMessage());
      return;
    }
    System.out.println("Patient entered successfully\n\n");
  }

  private static void performAllAppointments() throws IOException {
    System.out.println("\n\nAll appointments in system:");
    for (Appointment appointment : calendarRepository.getAppointments()) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
      String appointmentTime = formatter.format(appointment.getLocalDateTime());
      System.out.println(String.format("%s:  %s, %s\t\tDoctor:%s", appointmentTime,
          appointment.getPatient().getLastName(), appointment.getPatient().getFirstName(),
          appointment.getDoctor().getName()));
    }
    System.out.println("\nPress any key to continue...");
    System.in.read();
    System.out.println("\n\n");
  }

  private static void performTodaysAppointments() throws IOException {
    System.out.println("\n\nAll today's appointments in system:");
    for (Appointment appointment : calendarRepository.getTodayAppointments()) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
      String appointmentTime = formatter.format(appointment.getLocalDateTime());
      System.out.println(String.format("%s:  %s, %s\t\tDoctor:%s", appointmentTime,
          appointment.getPatient().getLastName(), appointment.getPatient().getFirstName(),
          appointment.getDoctor().getName()));
    }
    System.out.println("\nPress any key to continue...");
    System.in.read();
    System.out.println("\n\n");
  }
}
